package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.classes.Account;
import Model.classes.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccountInformationController extends ControllerAbs{
	
	private User user;
	
	private Account account;
	
	private int opacity;
	
	@FXML
    private Pane updateInformationPane;
	
	@FXML
    private Text Name;

    @FXML
    private Text Url;

    @FXML
    private Text Username;
    
    @FXML
    private Text Email;
    
    @FXML
    private TextField newEmailID;
    
    @FXML
    private TextField newUsernameID;
    
    @FXML
    private TextField newUrlID;
    
    @FXML
    private TextField newExpirationDateID;
    
    @FXML
    private TextField newPasswordID;
    
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    
    private String expirationDateNotFormat;
    
    private String newPassword = "";   
	
	private Date newExpirationDate;
	
	private String encryptedPW = "";
	
	private String newURL = "" ;
	
	private String newEmail ="" ;
	private String newUsername = "" ;
	
	PassUtil passUtil = new PassUtil();
    
    @FXML
	public void initialize() {
    	Name.setText(account.getAccountName());
    	Url.setText(account.getUrlName());
    	Username.setText(account.getUserName());
    	Email.setText(account.getEmail());
    }	
 // This method is to get the current user object
 	public AccountInformationController(User user,Account account) {
 		this.user = user;
 		this.account = account;
 	}
    
    @FXML
    void SwitchSceneToEnterWebsite(MouseEvent event) throws IOException {
    	getEnterWebsitePage(user,user.accounts());
    }

    @FXML
    void SwitchSceneToProfile(ActionEvent event) throws IOException {
    	getProfilePage(user);
    }
    
    @FXML
    void UpdateButton(ActionEvent event) throws ParseException, IOException {
    	getOpacity();
    	if(!isLegal()) {
    		displayErrorMessage();
    		return;
    	}
    	if (opacity == 0)
    	{
    		System.out.println("Updating account...");
        	account.setPassword(encryptedPW);
        	account.setExpirationDate(newExpirationDate);
        	getEnterWebsitePage(user, user.accounts());
    	} else
    	{
    		System.out.println("Updating account...");
    		account.setUserName(newUsername);
    		account.setUrlName(newURL);
    		account.setEmail(newEmail);
        	account.setPassword(encryptedPW);
        	account.setExpirationDate(newExpirationDate);
        	getEnterWebsitePage(user, user.accounts());
    	}	  	
    }

	@FXML
	void DeleteButton(ActionEvent event) throws ParseException, IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this account? ", ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			user.deleteAccount(account);
			getEnterWebsitePage(user, user.accounts());
		}
	}
	
	@FXML
	void modifyButton(ActionEvent event) {
		showUpdateInformationPane();
	}
    
    public void getPassword()
    {
    	newPassword = newPasswordID.getText();
    	encryptedPW = passUtil.encrypt(newPassword);
    }
    
    public void getURL() {
    	newURL = newUrlID.getText();
    }
    
    public void getUsername() {
    	newUsername = newUsernameID.getText();
    }
    
    public void getEmail() {
    	newEmail = newEmailID.getText();
    }

    public void getExpirationDate() {
    	expirationDateNotFormat = newExpirationDateID.getText();
    }
    
    public void getExpirationDate1() throws ParseException {
    	newExpirationDate = format.parse(expirationDateNotFormat);
    }
    
    public void showUpdateInformationPane()
   	{
    	updateInformationPane.setOpacity(1);
       	
   	}
    
    public int getOpacity() {
    	opacity = (int) updateInformationPane.getOpacity();
		return opacity;
    }
    
     //when this method being called, will hide reset password pane
   	public void hideUpdateInformationPane()
   	{
   		updateInformationPane.setOpacity(0);
   	}
    
    public boolean isLegal() throws ParseException
	{
    	getPassword();
		getExpirationDate();
		getExpirationDate1();
		getUsername();
		getURL();
		getEmail();
		if (newPassword.isEmpty() || expirationDateNotFormat.isEmpty())
		{
			return false;
		}
		hideErrorMessage();
		return true;		
	}
}
