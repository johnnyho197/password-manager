package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import Model.classes.Database;
import Model.classes.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController extends ControllerAbs {
	
	ObservableList<String> questionList = FXCollections.
			observableArrayList("What was your favorite food as a child?","What was the name of your first school?"
					,"What car brands do you like?","Who is your famous actor/actress?");
	
	@FXML
    private ComboBox questionBox;
	
	@FXML
	private TextField answerID;
	
    @FXML
    private TextField passwordID;

    @FXML
    private TextField userID;
    
    @FXML
    private Label errorLabel;
    
    PassUtil passUtil = new PassUtil();
    
    private String username = "";
    private String encryptedPW = "";
	private String password = "";
	private String answer = "";
	private String question= "";
	
	// This method sets items to the combobox
	@FXML
	private void initialize() 
	{
		questionBox.setItems(questionList);
	}
	
	/* This method will first check if the isLegal returns true or false,
	 * then it creates an user by calling addUser method from database
	 */
    @FXML
    public void createButton(ActionEvent event) throws IOException
	{
    	if(!isLegal()) {
    		return;
    	}

    	if (!Database.getInstance().addUser(new User(username, encryptedPW, question, answer))) {
    		return;
    	}
		getLoginPage();
	}
    
    //This method will record the question chosen in the menu.
    
    @FXML
    public void getSelectedQuestion() 
    {   	
    	question = questionBox.getSelectionModel().getSelectedItem().toString();
    }

    //This method will record the user name entered in the text field.
    
    public void getUsername()
	{
		username = userID.getText();
	}
	
	//This method will record the password entered in the text field.
    
	public void getPassword()
	{
		password = passwordID.getText();
		encryptedPW = passUtil.encrypt(password);
	}
	
	//This method will record the answer entered in the text field.
	
	public void getAnswer() 
	{
		answer = answerID.getText();
	}
	
	/* This method is to check if username and password are greater than 0 characters,
	 * check if the password meets the criteria.
	 * Pops up an error message if the password doesn't meet the requirements
	 */
	public boolean isLegal()
	{
		getUsername();
		getPassword();
		getSelectedQuestion();
		getAnswer(); 
		hideErrorMessage();
		return true;
		 
	}
}

