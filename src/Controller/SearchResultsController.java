package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.classes.Account;
import Model.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SearchResultsController extends ResultsAbs{

	private User user;	
	
	@FXML
    private VBox resultPane;
	
	@FXML
	private String searchName;
	
	private ArrayList<Account> matches;
	
	public SearchResultsController(User user, String searchname) {
		this.user = user;
		this.searchName = searchname;
		this.matches = user.searchAccount(searchName);
		System.out.println("User: " + user);
		System.out.println("Searching for... " + searchname);
	}
	
	@FXML
	public void initialize() {					
		if(matches.size() != 0)
		{
			for (Account match : matches)
			{
				Button button = new Button();
				setInformation(match, button);				
				resultPane.getChildren().add(button);						
			}		
		}
		if(matches.size()==0) {
			displayErrorMessage();
			return;
		}
	}

	@FXML
    public void SwitchSceneToAddWebsite(ActionEvent event) throws IOException {
		getAddWebsitePage(user);
    }
	// This method switch scene to EnterWebsitePage
	@FXML
    public void SwitchSceneToEnterWebsite(MouseEvent event) throws IOException {
		getEnterWebsitePage(user, user.accounts());
    }
	
	@FXML
	public void SwitchSceneToProfile(ActionEvent event) throws IOException {
		getProfilePage(user);
	}
	
}
