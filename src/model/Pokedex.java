package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokedex implements Serializable{

		ArrayList<Pokemon> pokedex;
		
		public Pokedex() {
			pokedex = new ArrayList<Pokemon>();
		}
		
		
		//Return the entire pokedex.
		public ArrayList<Pokemon> getPokedex(){
			return pokedex;
		}
		
		//Add a Pokemon to the trainer's pokedex.
		public void addPokemon(Pokemon p) {
			pokedex.add(p);
		}
		
		//Return how many pokemon the trainer has
		public int size() {
			return pokedex.size();
		}
		
		
}
