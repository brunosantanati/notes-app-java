package me.brunosantana.notes.menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.JsonIOException;

import me.brunosantana.notes.json.NotesHandler;
import me.brunosantana.notes.model.Note;

public class MenuHandler {
	
	public void showMenu(NotesHandler notesHandler) throws JsonIOException, IOException {
		
		clearScreen();
		
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(option != 6) {
			
			System.out.println("\nSelect an option:");
			System.out.println("1 - Create a note");
			System.out.println("2 - Remove a note");
			System.out.println("3 - Edit a note");
			System.out.println("4 - List all notes");
			System.out.println("5 - Show note by title");
			System.out.println("6 - Exit");
			
			option = scanner.nextInt();
			
			clearScreen();
			
			switch (option) {
			case 1:
				addNote(notesHandler, scanner);
				break;
			case 2:
				removeNote(notesHandler, scanner);
				break;
			case 3:
				editNote(notesHandler, scanner);
				break;
			case 4:
				notesHandler.showAllNote();
				break;
			case 5:
				showNoteByTitle(notesHandler, scanner);
				break;
			case 6:
				System.out.println("Bye");
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		}
		
		scanner.close();
	}
	
	private void addNote(NotesHandler notesHandler, Scanner scanner) throws IOException {
		scanner.nextLine(); //this is to clear the keyboard buffer
		System.out.println("Type a title:");
		String title = scanner.nextLine();
		System.out.println("Type a body:");
		String body = scanner.nextLine();
		Note note = new Note(title, body);
		notesHandler.addNote(note);
	}

	private void removeNote(NotesHandler notesHandler, Scanner scanner) throws IOException {
		scanner.nextLine(); //this is to clear the keyboard buffer
		System.out.println("Type a title:");
		String title = scanner.nextLine();
		notesHandler.removeNote(title);
	}
	
	private void editNote(NotesHandler notesHandler, Scanner scanner) throws IOException {
		scanner.nextLine(); //this is to clear the keyboard buffer
		System.out.println("Type a title:");
		String title = scanner.nextLine();
		System.out.println("Type a body:");
		String newBody = scanner.nextLine();
		notesHandler.editNote(title, newBody);
	}
	
	private void showNoteByTitle(NotesHandler notesHandler, Scanner scanner) throws FileNotFoundException {
		scanner.nextLine(); //this is to clear the keyboard buffer
		System.out.println("Type a title:");
		String title = scanner.nextLine();
		notesHandler.showNoteByTitle(title);
	}

	private void clearScreen() throws IOException {
		//temporary workaround
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

}