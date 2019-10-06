package me.brunosantana.notes.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import me.brunosantana.notes.filesystem.FileSystemHandler;
import me.brunosantana.notes.model.Note;

public class NotesHandler {
	
	Gson gson = new Gson();
	String jsonFilePath = "/home/bruno/eclipse-2019‑06-workspace/notes/notes-storage/notes.json";
	
	public void addNote(Note note) throws JsonIOException, IOException {		
		List<Note> notes = getAllNotes();
		
		if(checkIfNoteExist(note.getTitle(), notes)) {			
			System.out.println("Invalid note. This title [" + note.getTitle() + "] already exists.");
		}else {
			notes.add(note);
			writeNotesIntoFile(notes);
			System.out.println("Note [" + note.getTitle() + "] added!");
		}
		
	}
	
	private boolean checkIfNoteExist(String title, List<Note> notes) {
		long totalNotes = notes.stream()
			.filter(n -> n.getTitle().equals(title))
			.count();
		
		if(totalNotes == 0)
			return false;
		
		return true;
	}
	
	public void removeNote(String title) throws IOException {
		List<Note> notes = getAllNotes();
		
		List<Note> listMinusRemovedNote = notes.stream()
			.filter(n -> !n.getTitle().equals(title))
			.collect(Collectors.toList());
		
		if(notes.size() == listMinusRemovedNote.size()) {
			System.out.println("This note [" + title + "] does not exist.");
		}else {
			writeNotesIntoFile(listMinusRemovedNote);
			System.out.println("Note [" + title + "] removed!");
		}
	}
	
	public void editNote(String title, String newBody) throws IOException {
		List<Note> notes = getAllNotes();
		
		if(checkIfNoteExist(title, notes)) {
			notes.forEach(n -> {
				if(n.getTitle().equals(title)) {
					n.setBody(newBody);
				}
			});
			
			writeNotesIntoFile(notes);
			System.out.println("Note [" + title + "] edited!");
		}else {
			System.out.println("Note [" + title + "] does not exist.");
		}
	}
	
	private void writeNotesIntoFile(List<Note> notes) throws IOException {
		Writer writer = new FileWriter(jsonFilePath);
		gson.toJson(notes, writer);
		writer.flush();
		writer.close();
	}
	
	public void showAllNote() throws FileNotFoundException {
		getAllNotes().forEach(System.out::println);
	}
	
	public List<Note> getAllNotes() throws FileNotFoundException {
		if (jsonFileExists()) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFilePath));
			Gson gson = new Gson();
			Type type = new TypeToken<ArrayList<Note>>() {}.getType();
			ArrayList<Note> list = gson.fromJson(bufferedReader, type);
			return list;
		} else {
			return new ArrayList<Note>();
		}
	}
	
	private boolean jsonFileExists() {
		FileSystemHandler fileSystemHandler = new FileSystemHandler();
		return fileSystemHandler.checkIfFileExists(jsonFilePath);
	}

}
