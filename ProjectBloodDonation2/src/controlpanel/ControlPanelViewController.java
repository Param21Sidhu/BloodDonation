package controlpanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControlPanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void diIssue(MouseEvent event) {
    	   try{
  	    		Parent root=FXMLLoader.load(getClass().getResource("/issueBlood/IssueBloodView.fxml")); 
  								//OR
  				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
  				Scene scene = new Scene(root);
  				Stage stage=new Stage();
  				stage.setScene(scene);
  				stage.show();
  				//to hide parent window
  	//			Scene scene1=(Scene)txtEmail.getScene();
  		//		   scene1.getWindow().hide();
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
    }

    @FXML
    void doAvailable(MouseEvent event) {
         
    	
    	   try{
  	    		Parent root=FXMLLoader.load(getClass().getResource("/bloodAvailable/BloodUnitsAvailableView.fxml")); 
  								//OR
  				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
  				Scene scene = new Scene(root);
  				Stage stage=new Stage();
  				stage.setScene(scene);
  				stage.show();
  				//to hide parent window
  				//S/cene scene1=(Scene)txtEmail.getScene();
  				 //  scene1.getWindow().hide();
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
    	
    	
    	
    	
    	
    }

    @FXML
    void doDonation(MouseEvent event) {
    	   try{
  	    		Parent root=FXMLLoader.load(getClass().getResource("/bloodunit/BloodCollectionView.fxml")); 
  								//OR
  				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
  				Scene scene = new Scene(root);
  				Stage stage=new Stage();
  				stage.setScene(scene);
  				stage.show();
  				//to hide parent window
  				//Scene scene1=(Scene)txtEmail.getScene();
  				 //  scene1.getWindow().hide();
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
    }

    @FXML
    void doHistory(MouseEvent event) {

    }

    @FXML
    void doRegister(MouseEvent event) {
            
    	
    	   try{
  	    		Parent root=FXMLLoader.load(getClass().getResource("/donormasWala/DonorView.fxml")); 
  								//OR
  				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
  				Scene scene = new Scene(root);
  				Stage stage=new Stage();
  				stage.setScene(scene);
  				stage.show();
  				//to hide parent window
  				//Scene scene1=(Scene)txt.getScene();
  				  // scene1.getWindow().hide();
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
    	
    	
    	
    }

    @FXML
    void initialize() {

    }

}
