package Controller;

import java.io.IOException;

import Model.classes.Database;
import Model.classes.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class LoginPageController extends ControllerAbs {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField passwordID;

    @FXML
    private TextField userID;
    
    @FXML
    private String question;
    
    @FXML
    private String answer;
    
    PassUtil passUtil = new PassUtil();
    
    String PW = "";
    
    /*
	 * This method switches the scene to create a new account. 
	 */
    
    @FXML
    void signupButton(ActionEvent event) throws IOException 
    {
    	StackPane SignUpPane = (StackPane)FXMLLoader.load(getClass().getResource("../View/fxml/SignUpPage.fxml"));
        Scene SignUpScene = new Scene(SignUpPane,1100,650);
        SignUpScene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
        
        primaryStage.setScene(SignUpScene);
        primaryStage.show();
    }

    /*
	 * When this method is called, if the login credentials are valid, log the user in and switch the scene
	 * to add website.
	 */
    
    @FXML
    void loginButton(ActionEvent event) throws IOException
	{
    	PW = passUtil.encrypt(passwordID.getText());
    	User user = Database.getInstance().getUser(userID.getText(), PW);
    	if (user == null) {
    		displayErrorMessage();
    		return;
    	}
        hideErrorMessage();
		getEnterWebsitePage(user,user.accounts());
	}
}
