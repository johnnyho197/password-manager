package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.classes.Account;
import Model.classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class ControllerAbs 
{
	public Stage primaryStage = application.Main.window;
	@FXML Label searchLabel;
	@FXML Label errorLabel;
	@FXML Label errorPasswordLabel;
	
	// This method changes the scene to the login page.
	public void getLoginPage() throws IOException
	{
		StackPane loginPagePane = (StackPane)FXMLLoader.load(getClass().getResource("../View/fxml/LoginPage.fxml"));
		Scene loginPageScene = new Scene(loginPagePane,1100,650);
		
		primaryStage.setScene(loginPageScene);
		primaryStage.show();
	}
	
	
	// This method changes the scene to the reset password page.
	public void getResetPasswordPage() throws IOException
	{
		StackPane resetPasswordPagePane = (StackPane)FXMLLoader.load(getClass().getResource("../View/fxml/ResetPassword.fxml"));
		Scene resetPasswordPageScene = new Scene(resetPasswordPagePane,1100,650);
		
		primaryStage.setScene(resetPasswordPageScene);
		primaryStage.show();
	}
	
	/* This method changes the scene to the reset password page.
	 *
	 */
	public void getEnterWebsitePage(User user, ArrayList<Account> accounts) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/EnterWebsitePage.fxml"));
		EnterWebsiteController controller = new EnterWebsiteController(user, accounts);
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
	
	public void getPasswordGeneratorPage(User user) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/PasswordGenerator.fxml"));
		PasswordGeneratorController controller = new PasswordGeneratorController(user);
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
	
	// This method changes the scene to the addwebsite page.
	
	public void getAddWebsitePage(User user) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/AddWebsitePage.fxml"));
		AddWebsiteController controller = new AddWebsiteController(user);
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
	
	public void getProfilePage(User user) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/ProfilePage.fxml"));
		ProfileController controller = new ProfileController(user);
		loader.setController(controller);
		
		primaryStage.setScene(new Scene(loader.load(),1100,650));
		primaryStage.show();
	}
		
	/*
	 * The following are label controls.
	 */
	
	public void closeApplication()
	{
		primaryStage.close();
	}
	
	public void displayErrorMessage()
	{
		errorLabel.setOpacity(1);
	}
	
	public void hideErrorMessage()
	{
		errorLabel.setOpacity(0);
	}
	
	public void displayErrorPasswordMessage()
	{
		errorPasswordLabel.setOpacity(1);
	}
	
	public void hideErrorPasswordMessage()
	{
		errorPasswordLabel.setOpacity(0);
	}
	
	public void showSearchLabel()
	{
		searchLabel.setOpacity(1);
	}
	
	public void hideSearchLabel()
	{
		searchLabel.setOpacity(0.0);
	}

}