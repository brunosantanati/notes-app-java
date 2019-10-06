package me.brunosantana.notes.main;

import java.io.IOException;

import com.google.gson.JsonIOException;

import me.brunosantana.notes.json.NotesHandler;
import me.brunosantana.notes.model.Note;

public class NotesApp {

	public static void main(String[] args) {
		
		if(args.length == 1) {			
			try {
				
				String jsonFilePath = args[0];
				
				Note note = new Note("note5", "this is my note");
				
				NotesHandler notesHandler = new NotesHandler(jsonFilePath);
				notesHandler.addNote(note);
				notesHandler.removeNote("note4");
				notesHandler.editNote("note5", "Edited note! Uhu");
				notesHandler.showAllNote();
				
			} catch (JsonIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Pass only one argument with the json file path");
		}
		

	}
}
