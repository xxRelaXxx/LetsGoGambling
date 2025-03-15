package menu;

import java.io.File;
import main.*;
import fileManager.*;


public class GameAccess {
	private UserFileManager userFile = new UserFileManager();
	private PswEncrypting pswManager = new PswEncrypting(userFile);
	
	
	
	public Boolean signIn(SignInController signInController) {
		//get infos from the fxml
    	String username = signInController.getInpUsername().getText();
		String psw1 = signInController.getInpPsw1().getText();
    	String psw2 = signInController.getInpPsw2().getText();
    	
    	if (username!="" && psw1!="" && psw2!="") {
    		//no empty fields
    		signInController.getLbEmptyFields().setVisible(false);
    		
    		userFile.setDirectoryPath("userFiles/" + username);
        	File playerDirectory = new File(userFile.getDirectoryPath());        	
        	
        	if (playerDirectory.exists()) {
        		//user name already used
        		signInController.getLbUsernameError().setVisible(true);
        		return false;
    		}else {
    			signInController.getLbUsernameError().setVisible(false);
    	    	if (psw1.equals(psw2)) {
    	    		//sign in done successfully
    	    		signInController.getLbPswError().setVisible(false);
    	    		playerDirectory.mkdirs(); //create the directory for new user
    				pswManager.cifraCesare(psw1, signInController.getShift()); //encrypt the psw
    				
    				Main.player = new Player(username, 0, 0, 0, 0, 0); //assign values to Player attributes
    				
    				//create all the necessary files for the new user. These are then used when logging in 
    				userFile.saveToFile("0", "money.txt");
    				userFile.saveToFile("0", "gamesPlayed.txt");
    				userFile.saveToFile("0", "gamesWon.txt");
    				userFile.saveToFile("0", "gamesLost.txt");
    				userFile.saveToFile("0", "highestEarning.txt");
    				userFile.saveToFile("username: " + username, "playerData.txt");
    				
    				return true;
    			}else {
    				//non matching psw
    				signInController.getLbPswError().setVisible(true);
    				return false;
    			}
    		}
		}else {
			signInController.getLbEmptyFields().setVisible(true);
			return false;
		}
    	
	}
	
	public Boolean logIn(LogInController logInController) {  
		//get infos from the fxml
    	String username = logInController.getInpUsername().getText();
    	String psw = logInController.getInpPsw().getText();

    	if (username!="" && psw!="") {
    		//no empty fields
    		logInController.getLbEmptyFields().setVisible(false);
    		
    		userFile.setDirectoryPath("userFiles/" + username);
        	File playerDirectory = new File(userFile.getDirectoryPath());
        	
        	if (playerDirectory.exists()) {
        		logInController.getLbUserNotFound().setVisible(false);
        		if (psw.equals(pswManager.decifraCesare(logInController.getShift()))) {
        			//log-in completed successfully
        			logInController.getLbWrongPsw().setVisible(false);
        			System.out.println("log-in completed successfully");
        			
        			//assign values to Player attributes getting them from directory userFiles/username
        			Main.player = new Player(username, Integer.parseInt(userFile.readFromFile("money.txt")), Integer.parseInt(userFile.readFromFile("gamesPlayed.txt")),Integer.parseInt(userFile.readFromFile("gamesWon.txt")),Integer.parseInt(userFile.readFromFile("gamesLost.txt")) ,Integer.parseInt(userFile.readFromFile("highestEarning.txt")));
        			
        			return true;
    			}else {
    				logInController.getLbWrongPsw().setVisible(true);
    				return false;
    			}    		
    		}else {
    			logInController.getLbUserNotFound().setVisible(true);
    			return false;
    		}
		}else {
			logInController.getLbEmptyFields().setVisible(true);
			return false;
		}
    	
	}
}
