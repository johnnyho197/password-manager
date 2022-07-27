package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.classes.Account;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ProfileController extends ControllerAbs {
	
	final int MIN_Uppercase = 1;
	final int Special = 1;
	int uppercaseCounter = 0;
	int specialCounter = 0;
	PassUtil passUtil = new PassUtil();
	
	ObservableList<String> questionList = FXCollections.
			observableArrayList("What was your favorite food as a child?","What was the name of your first school?"
					,"What car brands do you like?","Who is your famous actor/actress?");
	
	@FXML
    private TextField newanswerID;

    @FXML
    private TextField newpasswordID;
    @FXML
    private TextField comfirmpasswordID;

	private User user;
	
	@FXML
    private ComboBox questionBox;
	
    @FXML
    private Label answerLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label questionLabel;
    
    @FXML
    private Label usernameLabel;
    
    @FXML
    private Pane modifyPane;
    
    @FXML
    private Pane profilePane;
    
	private String newpassword = "";
	private String newanswer = "";
	private String newquestion= "";
	private String decrypedPW = "";
	private String encrypedPW = "";

    public ProfileController(User user) {
		this.user = user;
	}
    
    @FXML
    void SwitchSceneToEnterWebsite(MouseEvent event) throws IOException {
    	getEnterWebsitePage(user, user.accounts() );
    }
	
	@FXML
	void SwitchSceneToProfile(ActionEvent event) throws IOException {
		getProfilePage(user);
	}
    
    @FXML
    public void getSelectedQuestion() 
    {   	
    	newquestion = questionBox.getSelectionModel().getSelectedItem().toString();
    }

	//This method will record the password entered in the text field.
    
	public void getPassword()
	{
		newpassword = newpasswordID.getText();
		encrypedPW = passUtil.encrypt(newpassword);

	}
	
	//This method will record the answer entered in the text field.
	
	public void getAnswer() 
	{
		newanswer = newanswerID.getText();
	}
    
    @FXML
	private void initialize() 
	{
		questionBox.setItems(questionList);
		usernameLabel.setText("Username:     " + user.getUserName());
		decrypedPW = passUtil.decrypt(user.getMasterPassword());
		passwordLabel.setText("Password:     " + decrypedPW);
		questionLabel.setText("Security Question:     " + user.getSecurityQuestion());
		answerLabel.setText("Security Answer:     " + user.getSecurityAnswer());
	}	
	
	public void modifyButton(ActionEvent event) throws IOException{
		disableprofilePane();
		displayModifyPane();		
	}
	
	@FXML
    void updateButton(ActionEvent event) throws IOException {
		if(!isLegal()) {
			return;
		}
		Database.getInstance().modifyInformation(user.getUserName(), encrypedPW, newquestion, newanswer);
		System.out.println("Your information is updated!");
		getEnterWebsitePage(user, user.accounts());
    }
	
	public void displayModifyPane() {
		modifyPane.setOpacity(1);
	}
	
	public void disableprofilePane() {
		profilePane.setDisable(true);
	}
	
	public boolean isLegal() {	
		getPassword();
		getSelectedQuestion();
		getAnswer();
		hideErrorPasswordMessage();
		return true;
	}
			
}
