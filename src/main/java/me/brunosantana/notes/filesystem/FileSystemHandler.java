package me.brunosantana.notes.filesystem;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemHandler {
	
	public boolean checkIfFileExists(String path) {
		return Files.exists(Paths.get(path));
	}

}
