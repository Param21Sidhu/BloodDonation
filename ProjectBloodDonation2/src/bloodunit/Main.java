package bloodunit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=(Parent)FXMLLoader.load(getClass().getResource("BloodCollectionView.fxml")); 
				
				Scene scene = new Scene(root,600,650);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("JsgViewss");
				primaryStage.setScene(scene);
				primaryStage.show();
				
                  
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

