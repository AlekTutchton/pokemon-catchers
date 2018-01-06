package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javafx.concurrent.Task;
import model.Pokemon;
import model.pokemon.FindingPlayer;
import tests.MultiplayerBattleTest;

public class MultiplayerBattle extends Battle {
	private Pokemon yours;
	private Pokemon theres;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	/** True iff this client is hosting the game. */
	Boolean host;
	private Server server;
	private Socket socketServer;

	/**
	 * Works like a client to a multiplayer server
	 * 
	 * @param multiplayerBattleTest
	 */
	public MultiplayerBattle(Pokemon yours, Observer gui) {
		super(gui);
		this.theres = null;
		this.yours = yours;
		try {
			startServer();
		} catch (Exception e) {
			yourTurn = true;
		}
		makeConnection();

		this.theres = exchangePokemon(yours);

		ServerUpdates listener = new ServerUpdates();
		Thread thread = new Thread(listener);
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * Starts the game server in a new thread should fail, if there is already a
	 * server open on port 4000.
	 */
	private void startServer() {
		server = new Server();
		Thread thread = new Thread(server);
		thread.setDaemon(true);
		thread.start();
	}

	/** Sends your Pokemon overs receives there Pokemon in return */
	private Pokemon exchangePokemon(Pokemon yours) {
		try {
			output.writeObject(yours);
			output.flush();
			theres = (Pokemon) input.readObject();
			output.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theres;
	}

	/** Should return the 3 available options corresponding to move(0) - move(2). */
	public String[] avaliableOptions() {
		return yours.getAttackNames();
	}

	private boolean makeConnection() {
		boolean con = false;
		try {
			socketServer = new Socket("localhost", 4000);
			setChanged();
			notifyObservers("Found Server");
			output = new ObjectOutputStream(socketServer.getOutputStream());
			input = new ObjectInputStream(socketServer.getInputStream());
			con = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Used to input a player action, i should correspond to an attack the pokemon
	 * used has, indexed from 1.
	 */
	@Override
	public Boolean action(int i) {
		if (!yourTurn || isOver())
			return false;
		try {
			output.reset();
			output.writeObject(i);
			output.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

	/** Should match isOver on the server */
	@Override
	public Boolean isOver() {
		if (yours.isFainted() || theres.isFainted()) {
			return true;
		} else {
			return false;
		}
	}

	public class ServerUpdates implements Runnable {
		@Override
		public void run() {
			while (!isOver()) {
				String response = null;
				try {
					response = (String) input.readObject();
					if (response.equals("POKEMON")) {
						yourTurn = true;
						/**
						 * I can't figure out why but it's necessary to clone these on both ends in
						 * order to get the Pokemons health and temporary stats to update
						 */
						Pokemon _yours = (Pokemon) input.readObject();
						Pokemon _other = (Pokemon) input.readObject();
						yours = _yours.clone();
						theres = _other.clone();
						if (theres.isFainted()) {
							message(yours.getName() + " fainted! Victory!");
						} else if (yours.isFainted())
							message(theres.getName() + " fainted! Defeat!");
					} else {
						message(response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	Pokemon getRight() {
		if (theres == null) {
			return new FindingPlayer();
		} else
			return theres;
	}

	@Override
	Pokemon getLeft() {
		return yours;
	}

	@Override
	void startMessage() {
		message("A fine day for a battle!");

	}

}
