package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserFileManager {
	private static String directoryPath; //Shared UserFileManager instance
	
	
	
	public void saveToFile(String text, String fileName) {
		//for saving any kind of string in any file. Save in the userFile directory specified. Receive the file name whit it's extension
		String path = directoryPath + "/" + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text);
            System.out.println("Text successfully saved to " + path);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
	}
	
	public String readFromFile(String fileName) {
		//for reading any kind of file. Return the file content. Read from file passed whit it's extension
		StringBuilder textFromFile = new StringBuilder();
        
        String path = directoryPath + "/" + fileName;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textFromFile.append(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return textFromFile.toString();
	}
	
	public Boolean appendTextToFile(String text, String fileName) {
		//for adding any kind of string in any file. Save in the userFile directory specified. Receive the file name whit it's extension
		String path = directoryPath + "/" + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.newLine(); //create a new line on the file 
        	writer.append(text); //write the text on the new line created before
            System.out.println("Text successfully added to " + path);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
            return false;
        }
	}
	
	
	
	public String getDirectoryPath() {return directoryPath;	}
	public void setDirectoryPath(String directoryPath) {UserFileManager.directoryPath = directoryPath;}
}
