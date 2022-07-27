package Controller;

import java.io.IOException;
import java.util.Random;

import Model.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class PasswordGeneratorController extends ControllerAbs{
	
	Random random = new Random();
	private int length = 0;
	private int charater = 0;
	private int upperCase = 0;
	private String sPassword = "";
	private String upperC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String lowerC = "abcdefghijklmnopqrstuvwxyz0123456789";
	private String specialC = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~)";
			
	@FXML
    private TextField lengthID;
	
	@FXML
    private TextField characterID;
	
	@FXML
    private TextField upperCaseID;
	
	@FXML
    private TextField GeneratedPasswordID;
	
	@FXML
	private Label LengthCheckerID;
	
	@FXML
	private Pane GeneratedPasswordPane;
	
	private User user;
	
	final Clipboard clipboard = Clipboard.getSystemClipboard();
    
    final ClipboardContent content = new ClipboardContent();
    
    private String randomPassword = "";
	
	public PasswordGeneratorController (User user){
		this.user = user;
	}
	

	//// get the length of password user wanted for the password
	
	public void getLength()
	{
		length = Integer.valueOf(lengthID.getText());		
	}
	// get number of special characters user want to included for the password
	
	public void getNumberOfCharacter()
	{
		charater = Integer.valueOf(characterID.getText());		
	}
	// get number of uppercases user want to included for the password
	public void getNumberOfUpperCase()
	{
		upperCase = Integer.valueOf(upperCaseID.getText());		
	}
	// get generated password
	public String getGeneratedPassword() {
		GeneratedPasswordID.setText(sPassword);
		randomPassword = GeneratedPasswordID.getText();
		return randomPassword;
	}
	// this method displays the generated password
	@FXML
	void generateButton(ActionEvent event) throws IOException{
		if(!validGenerate()) {
			return;
			
		}
		GeneratedPasswordPane.setOpacity(1);
		passwordGenerate();
		getGeneratedPassword();
		
	}
	
	@FXML
	void backButton(ActionEvent event) throws IOException {
		getAddWebsitePage(user);
	}
	@FXML
	void copyButton(ActionEvent event) throws IOException{
		content.putString(getGeneratedPassword());
		clipboard.setContent(content);
	}
	// this method checks if user's input meets the requirement
	boolean validGenerate() {
		getLength();
		getNumberOfCharacter();
		getNumberOfUpperCase();
		if((charater + upperCase) > length) {
			LengthCheckerID.setOpacity(1);
			return false;
		}
		
		LengthCheckerID.setOpacity(0);
		return true;
	}
	// this method will randomly generates password depends on users choice
	void passwordGenerate() {
		
		getLength();
		getNumberOfCharacter();
		getNumberOfUpperCase();
		char[] password = new char[length+1];
		for(int i = 0; i < length + 1; i++) {
			if(i < upperCase) {
				password[i] = upperC.charAt(random.nextInt(25));
					
			}
			if(i > upperCase && i <= (upperCase + charater)) {
				password[i] = specialC.charAt(random.nextInt(20));
				
			}
			if(i > (upperCase + charater)){
			password[i] = lowerC.charAt(random.nextInt(34));
			}
			
		}		
		sPassword = new String(password);
	}

}
