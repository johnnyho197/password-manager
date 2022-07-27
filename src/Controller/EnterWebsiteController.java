package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.classes.Account;
import Model.classes.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EnterWebsiteController extends ControllerAbs{
	
	
    @FXML
    private HBox accountInformationPane;

    @FXML
    private VBox accountPane;
	
	private User user;
	@FXML
    private TextField SearchName;
	
	@FXML
	private Label expiredAccountsWarning;
	
	private ArrayList<Account> accounts;
	
	PassUtil passUtil = new PassUtil();
	
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  
		
	/* This method sets text for the warning sign label
	 * It also displays all the expired accounts if there's any and let the user update the passwords and expiration date
	 */
	@FXML
	public void initialize() {
		if(user.getNumberOfExpiredAcc()>=1)
		{
			expiredAccountsWarning.setText("You have " + user.getNumberOfExpiredAcc() + " expired passwords. Please Update!");
			for (Account account : user.accounts())
			{
				if(account.isExpired())
				{
					HBox hbox = new HBox();
					Button applicationName = new Button(account.getAccountName());
					applicationName.setOnAction(event -> {
						try {
							getInformation(event,user,account);
						} catch (IOException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});				
					Text username = new Text(account.getUserName());
					String password = new String(account.getPassword());
					String decryptedPW = new String();
					decryptedPW = passUtil.decrypt(password);
					Text newpassword = new Text(decryptedPW);
					Text expirationDate = new Text(format.format(account.getExpirationDate()));
					hbox.getChildren().addAll(applicationName, username,newpassword, expirationDate);
					hbox.setMaxSize(787, 41);
					hbox.setPadding(new Insets(0,0,0,10));
					hbox.setSpacing(70);
					accountPane.getChildren().add(hbox);
				}
			}
		} else {
			expiredAccountsWarning.setText("");
			for (Account account : user.accounts())
			{
				HBox hbox = new HBox();
				Button applicationName = new Button(account.getAccountName());
				applicationName.setOnAction(event -> {
					try {
						getInformation(event,user,account);
					} catch (IOException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				Text username = new Text(account.getUserName());
				String password = new String(account.getPassword());
				String decryptedPW = new String();
				System.out.println("encrypted password:" + password);
				decryptedPW = passUtil.decrypt(password);
				Text newpassword = new Text(decryptedPW);
				Text expirationDate = new Text(format.format(account.getExpirationDate()));
				hbox.getChildren().addAll(applicationName, username,newpassword, expirationDate);
				hbox.setMaxSize(787, 41);
				hbox.setPadding(new Insets(0,0,0,10));
				hbox.setSpacing(70);
				accountPane.getChildren().add(hbox);
			}
		}
			
	}
	
	// This method is to get the current user object
	public EnterWebsiteController(User user, ArrayList<Account> accounts) {
		this.user = user;
		this.accounts = accounts;	
		System.out.println("User: " + user);
	}
	
	// This method switch scene to AddWebsitePage
	@FXML
    public void SwitchSceneToAddWebsite(ActionEvent event) throws IOException {
		getAddWebsitePage(user);
    }
	// This method switch scene to EnterWebsitePage
	@FXML
    public void SwitchSceneToEnterWebsite(MouseEvent event) throws IOException {
		getEnterWebsitePage(user, accounts);
    }
	
	@FXML
	public void SwitchSceneToProfile(ActionEvent event) throws IOException {
		getProfilePage(user);
	}
	
	@FXML
	public void SwitchSceneToResults(ActionEvent event) throws IOException
	{
		if (SearchName.getText().isEmpty())
		{
			displayErrorMessage();
			return;
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/SearchResults.fxml"));
		SearchResultsController controller = new SearchResultsController(user, SearchName.getText());
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
	
	private void getInformation(ActionEvent event,User user, Account account) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/AccountInformation.fxml"));
		AccountInformationController controller = new AccountInformationController(user, account);
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
	
}
