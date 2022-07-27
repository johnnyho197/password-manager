package Controller;

import java.io.IOException;

import Model.classes.Database;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ResetPasswordController extends ControllerAbs{
	private String password = "";
	private String comfirmPassword = "";
	PassUtil passUtil = new PassUtil();
	private String encrypedPW = "";
	final int MIN_Uppercase = 1;
	final int Special = 1;
	int uppercaseCounter = 0;
	int specialCounter = 0;
	
	ObservableList<String> questionList = FXCollections.
			observableArrayList("What was your favorite food as a child?","What was the name of your first school?"
					,"What car brands do you like?", "Who is your famous actor/actress?");
	
	@FXML
	private Label NotMatchLabel;
	
    @FXML
    private TextField answerID;
    
    @FXML
    private Button resetB;

    @FXML
    private PasswordField newPasswordID;
    
    @FXML
    private PasswordField comfirmPasswordID;

    @FXML
    private ComboBox questionBox;

    @FXML
    private TextField userID;
    
    @FXML
    private Pane resetPasswordPane;
    
    @FXML
    private Pane verifyPane;
    

	@FXML
	// initialize the question box with three security questions
	private void initialize() 
	{
		questionBox.setItems(questionList);
	}
	
	// get password from the password field
	public void getPassword()
	{
		password = newPasswordID.getText();
		encrypedPW = passUtil.encrypt(password);
	}
	// get password from the confirm password field
	public void getComfirmPassword()
	{
		comfirmPassword = comfirmPasswordID.getText();		
	}
	

    @FXML
    /**
     * This method will call vailiPassword and matchPassword function
     * to verify if the new password user entered meet the requirement.
     * if requirements met, update the new password to database and display
     * the login page
     * @param event
     * @throws IOException
     */
    public void resetButton(ActionEvent event) throws IOException{
    	getPassword();
    	if(!validPassword()|| !matchPassword()) {
    		return;	
    	}
			
    	Database.getInstance().updatePassword(userID.getText(),encrypedPW);
		getLoginPage();
    }
    @FXML
    /**
     * This method will verify user's information and security question and answer
     * when user trying to reset the password
     * @param event
     * @throws IOException
     */
    public void verifyButton(ActionEvent event) throws IOException{
    	if(!validReset()) {
    		return;
    	}				
    	showResetPasswordPane();
    	disableVerifyPane();   	
    }
    
    //when this method being called, will display reset password pane
    public void showResetPasswordPane()
	{
    	resetPasswordPane.setOpacity(1);
    	
	}
  //when this method being called, will hide reset password pane
	public void hideResetPasswordPane()
	{
		resetPasswordPane.setOpacity(0);
	}
	
	//when this method being called, will disable the verify pane
	public void disableVerifyPane()
	{
		verifyPane.setDisable(true);
	}
	/**
	 * This method will check if the value in new password field and 
	 * the confirm password field matches
	 * @return
	 */
	boolean matchPassword() {
		getPassword();
		getComfirmPassword();
			
		if(password.equals(comfirmPassword)) {
			return true;
		}
		displayNotMatchLabel();	
		return false;	
	}
	/**
	 * This method valid the password if the password meet the requirement
	 * contains at least one special characters
	 * contains at least one upper case
	 * @return true or false
	 */
    
    boolean validPassword() {
    	getPassword();		  	
    	
			 	hideErrorPasswordMessage();
			 	
    	return true;	
    }
    /**
     * This method get user's information from database
     * if the information entered matched the database
     * it will return true
     * @return true or false
     */
    boolean validReset()
    {
    	boolean valid = Database.getInstance().resetPassword(userID.getText(), questionBox.getSelectionModel().getSelectedItem().toString()
    			, answerID.getText());
    	if(!valid)
			displayErrorMessage();
		else
			hideErrorMessage();
		return valid;
    }
    
    // Display error message if the passwords don't match
    public void displayNotMatchLabel() {
    	NotMatchLabel.setOpacity(1);
    };
    
}
