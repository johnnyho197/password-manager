package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.classes.Account;
import Model.classes.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddWebsiteController extends ControllerAbs {

	private User user;
    
    PassUtil passUtil = new PassUtil();
  
    @FXML
    private TextField nameID;
    
    @FXML
    private TextField urlID;
    
    @FXML
    private TextField usernameID;

    @FXML
    private TextField passwordID;
    
    @FXML
    private TextField emailID;
    
    @FXML
    private TextField expirationDateID;

    
	private String name = "";
	private String url = "";
    private String username = "";
	private String password = "";
	private String email = "";
	
	private String encryptedPW = "";
	
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private Date expirationDate;
	private String expirationDateNotFormat;

	// This method is to get the current user object
	public AddWebsiteController(User user) {
		this.user = user;
		System.out.println("User: " + user);
	}

	
	/* This method will first check if the isLegal returns true or false,
	 * then it creates an account in the user object by calling addAccount method from User class
	 */
    @FXML
    void AddButton(ActionEvent event) throws IOException, ParseException 
    {
    	if(!isLegal()) {
    		return;
    	}
    	System.out.println("Adding account...");
    	user.addAccount(new Account(name, url, username, encryptedPW, email, expirationDate));
    	System.out.println("PW: " +encryptedPW);
    	getEnterWebsitePage(user, user.accounts());
    }
    
    // This methods switches the scene to EnterWebsitePage
    @FXML
    void SwitchSceneToEnterWebsite(MouseEvent event) throws IOException {
    	getEnterWebsitePage(user, user.accounts());
    }
    
    @FXML
    void SwitchSceneToProfile(ActionEvent event) throws IOException {
    	getProfilePage(user);
    }
    
    @FXML
    void GButton(ActionEvent event) throws IOException 
    {
    	getPasswordGeneratorPage(user);
    }
    
    //This method will record the website name entered in the text field.

    public void getWebsiteName() 
    {
    	name = nameID.getText();
    }
    
    //This method will record the url entered in the text field.
    
    public void getUrl() 
    {
    	url = urlID.getText();
    }
    
    //This method will record the user name entered in the text field.
    
    public void getUsername()
    {
    	username = usernameID.getText();
    }
    
    //This method will record the password entered in the text field.
    
    public void getPassword()
    {
    	password = passwordID.getText();
    	encryptedPW = passUtil.encrypt(password);
    }
    
    //This method will record the email entered in the text field.
    
    public void getEmail()
    {
    	email = emailID.getText();
    }
    
    public void getExpirationDate1() throws ParseException {
    	expirationDate = format.parse(expirationDateNotFormat);
    }
    
    public void getExpirationDate() {
    	expirationDateNotFormat = expirationDateID.getText();
    }
    
    /* This method is to check if username and password are greater than 0 characters,
	 * check if all the information text field in entered and the password meets the criteria.
	 * Pop up error message if the requirements doesn't meet.
	 */
    
    public boolean isLegal() throws ParseException
	{
		getUsername();
		getPassword();
		getWebsiteName();
		getUrl();
		getEmail();
		getExpirationDate();
		getExpirationDate1();
		hideErrorMessage();
		return true;		
	}
}
