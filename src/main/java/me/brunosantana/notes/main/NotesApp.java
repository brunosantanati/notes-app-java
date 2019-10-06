package me.brunosantana.notes.main;

import java.io.IOException;

import com.google.gson.JsonIOException;

import me.brunosantana.notes.json.NotesHandler;
import me.brunosantana.notes.menu.MenuHandler;

public class NotesApp {

	public static void main(String[] args) {
		
		if(args.length == 1) {			
			try {
				
				String jsonFilePath = args[0];
				NotesHandler notesHandler = new NotesHandler(jsonFilePath);
				
				MenuHandler menuHandler = new MenuHandler();
				menuHandler.showMenu(notesHandler);
				
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
