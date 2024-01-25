package loginpack;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPwd;

    @FXML
    void txtLogin(MouseEvent event) {
    	
           if(txtEmail.getText().equals("admin123")  && txtPwd.getText().equals("12345678"))
           {
        	   //showMsg("Correct");
        	   try{
   	    		Parent root=FXMLLoader.load(getClass().getResource("/controlpanel/ControlPanelView.fxml")); 
   								//OR
   				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
   				Scene scene = new Scene(root);
   				Stage stage=new Stage();
   				stage.setScene(scene);
   				stage.show();
   				//to hide parent window
   				Scene scene1=(Scene)txtEmail.getScene();
   				   scene1.getWindow().hide();
   			}
   			catch(Exception e)
   			{
   				e.printStackTrace();
   			}
        	   
           }   
         else
           {
        	   showMsg("Invalid");
           }
    }
    void showMsg(String msg)
    {
          Alert alert=new Alert(AlertType.INFORMATION);
          alert.setTitle("TITLE");
          alert.setContentText(msg);
          alert.show();
    }

    @FXML
    void initialize() {

    }

}

