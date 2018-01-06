package model.pokemon;

import model.Pokemon;

final public class FindingPlayer extends Pokemon {
	private static final long serialVersionUID = 6900469307881441316L;

	/** Should only appear when waiting for the other client to connect to a game.  */
	public FindingPlayer() {
		super("", 0);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 0;
		baseDefense = 0;
		baseHP = 0;
		//TODO find a image for when a player is not found
		imgPath = "file:src/images/charizard.gif";
	}

	@Override
	protected void setAttacks() {
	}
}
