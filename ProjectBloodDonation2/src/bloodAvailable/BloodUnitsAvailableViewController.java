package bloodAvailable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BloodUnitsAvailableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtABn;

    @FXML
    private TextField txtABp;

    @FXML
    private TextField txtAn;

    @FXML
    private TextField txtAp;

    @FXML
    private TextField txtBn;

    @FXML
    private TextField txtBp;

    @FXML
    private TextField txtOn;

    @FXML
    private TextField txtOp;
    @FXML
    private ImageView txtPic1;
    @FXML
    private AnchorPane txtAnchor;
    
    
    Connection con;
    ResultSet table;
    PreparedStatement pst;
      void setvaluess() {
    	  try {
    		  pst=con.prepareStatement("select * from totalbloodcount");
    		  table=pst.executeQuery();
    		  while(table.next())
    		  {
    			  txtAp.setText(String.valueOf(table.getInt("ap")));
    			  txtAn.setText(String.valueOf(table.getInt("an")));
    			  txtBp.setText(String.valueOf(table.getInt("bp")));
    			  txtBn.setText(String.valueOf(table.getInt("bn")));
    			  txtOp.setText(String.valueOf(table.getInt("opos")));
    			  txtOn.setText(String.valueOf(table.getInt("oneg")));
    			  txtABp.setText(String.valueOf(table.getInt("abp")));
    			  txtABn.setText(String.valueOf(table.getInt("abn")));
    		  }
    	  }catch(Exception ex)
    	  {
    		  ex.printStackTrace();
    	  }
      }
    @FXML
    void initialize() {
      con=DatabaseConnection.doConnect();
      txtAp.setStyle("-fx-text-fill: red");
      txtBp.setStyle("-fx-text-fill: red");
      txtOp.setStyle("-fx-text-fill: red");
      txtABp.setStyle("-fx-text-fill: red");
      txtAn.setStyle("-fx-text-fill: red");
      txtBn.setStyle("-fx-text-fill: red");
      txtOn.setStyle("-fx-text-fill: red");
      txtABn.setStyle("-fx-text-fill: red");
      txtPic1.setStyle("-fx-border-radius: 70%;");
      
      
      
      txtAnchor.setStyle("-fx-background-image: url('/bground.jpg');");
      
      
      
      setvaluess();
      
    }

}
