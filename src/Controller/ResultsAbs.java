package Controller;

import java.io.IOException;
import Model.classes.Account;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class ResultsAbs extends ControllerAbs{
	
	@FXML
    private Text accountName;

    @FXML
    private Label email;
    
    @FXML
    private Label password;
    
    @FXML
    private Label url;

    @FXML
    private Label username;
    
    @FXML
	private Pane informationPane;
    
    @FXML
    private Label expirationDate;
    
    private String copiedPassword = "";
    
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    
    final ClipboardContent content = new ClipboardContent();
    
    PassUtil passUtil = new PassUtil();
    String decryptedPw = "";
    
	public void setInformation(Account matchID, Button button) 
	{
		button.setMaxSize(120, 400);
		button.setText(matchID.getAccountName());
		button.setOnAction(event -> fillInformation(event, matchID));		
	}
	
	private void fillInformation(ActionEvent event, Account matchID) {
		accountName.setText(matchID.getAccountName());
		url.setText("URL: " + matchID.getUrlName());
		username.setText("Username: " + matchID.getUserName());
		decryptedPw = passUtil.decrypt(matchID.getPassword());
		password.setText("Password: " + decryptedPw);
		email.setText("Email: " + matchID.getEmail());
		expirationDate.setText("Expiration Date: " + matchID.getExpirationDate());
		setCopiedPassword(decryptedPw);
		displayInformation();
	}
	protected void displayInformation() {
		// TODO Auto-generated method stub
		informationPane.setOpacity(1);
	}
	
	protected String getCopiedPassword() {
		return copiedPassword;
	}
	
	protected void setCopiedPassword(String password) {
		copiedPassword = password;
	}
	
	 @FXML
	private void copyPassword(MouseEvent event) throws IOException
	{		 
		 getCopiedPassword();
		 content.putString(copiedPassword);
		 clipboard.setContent(content);
	}
}
