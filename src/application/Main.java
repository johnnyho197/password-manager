package application;
	
import Model.classes.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static Stage window;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("../View/fxml/LoginPage.fxml"));
			Scene scene = new Scene(root,1100,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void stop() {
		System.out.println("Saving data");
		Database.getInstance().saveToFile();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
