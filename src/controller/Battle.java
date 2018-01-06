package controller;

import java.util.Observable;
import java.util.Observer;

import model.Pokemon;

public abstract class Battle extends Observable {
	private Observer gui;
	protected Boolean yourTurn = true;

	public Battle(Observer gui) {
		this.gui = gui;
		this.addObserver(gui);
	}
	
	/** Make the action n, N should correspond to one
	 * of the choices in
	 * @param n
	 */
	abstract Boolean action(int n);

	abstract Boolean isOver();

	/**
	 * Pokemon that should be rendered on the right side of the screen
	 * NOTE this function might return null in some cases (like when 
	 * searching for an opponent in a multiplayer game.
	 */
	abstract Pokemon getRight();
	abstract Pokemon getLeft();
	abstract String[] avaliableOptions();
	abstract void startMessage();
	
	void disconect() {
		deleteObserver(gui);
	};

	public void message(String message) {
		notifyObservers(message);
		setChanged();
	}
	
	public Boolean isYourTurn() {
		return yourTurn;
	}

}
//		@Override
//public void update(Observable o, Object arg) {
//	String message = (String) arg;
//	if(message.equals("END")) {
//		battleStage.close();
//	}

// }