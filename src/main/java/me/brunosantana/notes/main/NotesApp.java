package me.brunosantana.notes.main;

import java.io.IOException;

import com.google.gson.JsonIOException;

import me.brunosantana.notes.json.NotesHandler;
import me.brunosantana.notes.model.Note;

public class NotesApp {

	public static void main(String[] args) {
		
		try {
			
			Note note = new Note("note5", "this is my note");
			
			NotesHandler notesHandler = new NotesHandler();
			notesHandler.addNote(note);
			notesHandler.removeNote("note4");
			notesHandler.editNote("note5", "Edited note! Uhu");
			notesHandler.showAllNote();
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
