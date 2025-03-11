package main;

import menu.ModeMenuController;

public class MoneyManger {
	
	public void deposit() {
		ModeMenuController modeMenu = new ModeMenuController();
		if (modeMenu.getInpCardNumber().getText()!="" && modeMenu.getInpCardOwner().getText()!="" &&modeMenu.getInpCvv().getText()!="" && modeMenu.getInpDepositBalance().getText()!="") {
			modeMenu.getLbEmptyFields().setVisible(false);
			
			
			//_________________________________
			
			
		}else {
			modeMenu.getLbEmptyFields().setVisible(true);
		}
	}
	
	public Boolean canConvertStringToDouble(String str){
		try {
	        Double.parseDouble(str);  // Try to convert the string to a Double
	        return true;               // If conversion is successful, return true
	    } catch (NumberFormatException e) {
	        return false;              // If an exception is thrown, the string is not a valid Double
	    }
	}
}
