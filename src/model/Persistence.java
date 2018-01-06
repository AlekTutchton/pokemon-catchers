package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Persistence {
	@SuppressWarnings("unchecked")
	public static Trainer readPersistedTrainer() {
		Trainer trainer = null;

		try {
			System.out.println("in try");
			FileInputStream rawBytes = new FileInputStream("SavedTrainer");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			//System.out.println("inFile: " + inFile.readObject().getClass());
			trainer = (Trainer) inFile.readObject();
			inFile.close();
		} catch (Exception e) {
			System.out.println("Something went wrong while reading a trainer");
		}
		// return list.getSongList();
		return trainer;
	}
	
	public static void writePersistedTrainer(Trainer trainer) {
		// emails is not Serializable
		
		try {
			System.out.println("here");
			FileOutputStream fileOutput = new FileOutputStream(new File("SavedTrainer"));
			ObjectOutputStream out = new ObjectOutputStream(fileOutput);
			out.writeObject(trainer);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
