package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Pokemon;

/** UNDER CONSTRUCTION */
public class Server implements Runnable {
	private static ServerSocket serverSocket;

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(4000);
			System.out.println("Server started on port 4000");
			Boolean firstRun = true;
			Vector<Thread> clients = new Vector<Thread>();
			

			while (!clients.isEmpty() || firstRun) {
				firstRun = false;
				System.out.print("Searching for player 1... ");
				Socket socketP1 = serverSocket.accept();
				ObjectInputStream inputFromP1 = new ObjectInputStream(socketP1.getInputStream());
				ObjectOutputStream outputToP1 = new ObjectOutputStream(socketP1.getOutputStream());
				System.out.println("found.");

				System.out.print("Searching for player 2... ");
				Socket socketP2 = serverSocket.accept();
				ObjectInputStream inputFromP2 = new ObjectInputStream(socketP2.getInputStream());
				ObjectOutputStream ouputToP2 = new ObjectOutputStream(socketP2.getOutputStream());
				System.out.println("found.");

				System.out.print("Starting a new thread and handler... ");
				ClientHandler clientHandler = new ClientHandler(inputFromP1, outputToP1, inputFromP2, ouputToP2);
				Thread thread = new Thread(clientHandler);
				thread.setDaemon(true);
				thread.start();
				// Filter out dead threads
				Vector<Thread> clients_ = new Vector<Thread>();
				for(Thread client : clients) {
					if (client.isAlive())
						clients_.add(client);
				}
				clients = clients_;
			}
							
			System.out.println("done.");
		} catch (Exception e) {
		}
	}

	private static class ClientHandler implements Runnable {
		private static ObjectInputStream inputP1;
		private static ObjectOutputStream outputP1;
		private static ObjectInputStream inputP2;
		private static ObjectOutputStream outputP2;

		public ClientHandler(ObjectInputStream inputP1, ObjectOutputStream outputP1, ObjectInputStream inputP2,
				ObjectOutputStream outputP2) {
			ClientHandler.inputP1 = inputP1;
			ClientHandler.outputP1 = outputP1;
			ClientHandler.inputP2 = inputP2;
			ClientHandler.outputP2 = outputP2;
		}

		@Override
		public void run() {
			try {
				Pokemon p1 = (Pokemon) inputP1.readObject();
				Pokemon p2 = (Pokemon) inputP2.readObject();
				outputP2.writeObject(p1);
				outputP2.flush();

				outputP1.writeObject(p2);
				outputP1.flush();

				outputP1.writeObject("Your rival used " + p2.title());
				outputP1.flush();

				outputP2.writeObject("Your rival used " + p1.title());
				outputP2.flush();
				while (!isOver(p1, p2)) {
					attack(p1, p2, inputP1);

					syncPokemon(p1, p2, outputP1);
					syncPokemon(p2, p1, outputP2);

					attack(p2, p1, inputP2);

					syncPokemon(p1, p2, outputP1);
					syncPokemon(p2, p1, outputP2);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static void attack(Pokemon attacker, Pokemon defender, ObjectInputStream inputAttacker)
				throws ClassNotFoundException, IOException {
			if (isOver(attacker, defender)) {
				return;
			}
			int action = (int) inputAttacker.readObject();

			String response = attacker.attack(defender, action);

			outputP1.reset();
			outputP1.writeObject(response);
			outputP1.flush();

			outputP2.reset();
			outputP2.writeObject(response);
			outputP2.flush();
		}

		private static Boolean isOver(Pokemon p1, Pokemon p2) {
			return p1.isFainted() || p2.isFainted();
		}

		private static void syncPokemon(Pokemon poke1, Pokemon poke2, ObjectOutputStream output1) throws IOException {
			// "POKEMON" should be read in before the pokemon are synced
			output1.writeObject("POKEMON");

			output1.writeObject(poke1.clone());

			output1.writeObject(poke2.clone());
			output1.flush();
		}
	}
}
