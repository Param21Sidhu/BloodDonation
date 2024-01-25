package donormasWala;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DonorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtAdd;

    @FXML
    private TextField txtAge;

    @FXML
    private ComboBox<String> txtBG;

    @FXML
    private Button txtBrowse;

    @FXML
    private ComboBox<String> txtCity;

    @FXML
    private TextArea txtDis;

    @FXML
    private ComboBox<String> txtGen;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtName;

    @FXML
    private ImageView txtPic;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;

    @FXML
    void doBrowse(ActionEvent event) {
    	  FileChooser file=new FileChooser();
          file.getExtensionFilters().add(new ExtensionFilter("JPG FILES","*.jpg"));
          File f=file.showOpenDialog(null);
          
          if(f!=null)
          {
          	try {
          		Image img=new Image(new FileInputStream(f.getAbsoluteFile()));
          		txtPic.setImage(img);
          		txtBrowse.setText(f.getAbsolutePath());
          		txtBrowse.setMinWidth(200);
          		
          	}catch(Exception ex)
          	{
          		ex.printStackTrace();
          	}
          }
    }

    @FXML
    void doDelete(ActionEvent event) {
    	if(!chkmobile(txtMob.getText()))
    	{
    		showMsg("Invalid User");
    		return;
    	}
           try {
        	   pst=con.prepareStatement("delete from donormasterregister where Mobile=?");
        	   pst.setString(1,txtMob.getText());
        	   
        	   pst.executeUpdate();
        	   showMsg("Delted Successfully");
           }catch(Exception ex)
           {
        	   ex.printStackTrace();
           }
    }

    @FXML
    void doNew(ActionEvent event) {
       txtMob.setText("");
       txtName.setText("");
       txtAge.setText("");
       txtBrowse.setText("Browse");
       txtAdd.setText("");
       txtDis.setText("");
       txtGen.getSelectionModel().clearSelection();
       txtBG.getSelectionModel().clearSelection();
       txtCity.getSelectionModel().clearSelection();
       
    }

    @FXML
    void doRegister(ActionEvent event) {
    	if(chkmobile(txtMob.getText()))
    	{
    		showMsg("Mobile Number Already Occupied");
    		return;
    	}
    	 try {
         	pst=con.prepareStatement("insert into donormasterregister values(?,?,?,?,?,?,?,?,?,current_date())");
         	
         	pst.setString(1, txtMob.getText());
         	pst.setString(2, txtName.getText());
         	pst.setString(3, txtAge.getText());
         	pst.setString(4, txtGen.getSelectionModel().getSelectedItem());
         	pst.setString(5, txtBrowse.getText());
         	pst.setString(6, txtCity.getSelectionModel().getSelectedItem());
         	pst.setString(7, txtAdd.getText());
         	pst.setString(8, txtBG.getSelectionModel().getSelectedItem());
         	pst.setString(9, txtDis.getText());
         	
         	pst.executeUpdate();
         	doNew(event);
         	showMsg("Registered Succesfully");
         }catch(Exception ex) {
         	ex.printStackTrace();
         }      
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	if(!chkmobile(txtMob.getText()))
    	{
    		showMsg("Invalid User");
    		return;
    	}
                 try {
                	 pst=con.prepareStatement("update donormasterregister set Name=?,Age=?,Gender=?,Picpath=?,City=?,Address=?,BloodGroup=?,Disease=? where Mobile=?");
                	 
                                                                             		
                     	pst.setString(1, txtName.getText());
                     	pst.setString(4, txtBrowse.getText());
                     	pst.setString(3, txtGen.getSelectionModel().getSelectedItem());
                     	pst.setString(2, txtAge.getText());
                     	pst.setString(5, txtCity.getSelectionModel().getSelectedItem());
                     	pst.setString(7, txtBG.getSelectionModel().getSelectedItem());
                     	pst.setString(6, txtAdd.getText());
                     	pst.setString(8, txtDis.getText());
                     	pst.setString(9, txtMob.getText()); 
                     	
                     	pst.executeUpdate();
                     	doNew(event);
                     	showMsg("Updated Successfully");
                 }catch(Exception ex)
                 {
                	 ex.printStackTrace();
                 }
    }
    boolean chkmobile(String mob)
    {
    	boolean jasoos=false;
    	
    	
    	try {
    		pst=con.prepareStatement("select * from donormasterregister where Mobile=?");
    		pst.setString(1,mob);
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			jasoos=true;
    		}
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return jasoos;
    }
    void showMsg(String msg)
    {
          Alert alert=new Alert(AlertType.INFORMATION);
          alert.setTitle("TITLE");
          alert.setContentText(msg);
          alert.show();
    }

    @FXML
    void doFetch(ActionEvent event) {
    	if(!chkmobile(txtMob.getText()))
    	{
    		showMsg("Invalid User");
    		return;
    	}
      try {
    	  pst=con.prepareStatement("select * from donormasterregister where Mobile=?");
    	  pst.setString(1, txtMob.getText());
    	  
    	  table=pst.executeQuery();
    	  
    	  while(table.next())
    	  {
    		  txtName.setText(table.getString("Name"));
    		  txtGen.getSelectionModel().select(table.getString("Gender"));
    		  txtAge.setText(table.getString("Age"));
    		  txtCity.getSelectionModel().select(table.getString("City"));
    		  txtBG.getSelectionModel().select(table.getString("BloodGroup"));
    		  txtAdd.setText(table.getString("Address"));
              txtDis.setText(table.getString("Disease"));
                  if(table.getString("Picpath")!=null && table.getString("Picpath")!="Browse")
       	           {
                	  System.out.println(table.getString("Picpath"));
       		         Image image = new Image(new FileInputStream(table.getString("Picpath"))); 
           	          txtPic.setImage(image);   
       	           }
       	        else
       	       {
       		   
       		       Image image=new Image(new FileInputStream("‪C://Users//HP//Downloads//download.jpg"));
       		      txtPic.setImage(image);
       	       }
    	  }
      }catch(Exception ex)
      {
          ex.printStackTrace();    	  
      }
    }

    @FXML
    void initialize() {
    	
    	con=DatabaseConnection.doConnect();
    			
    			
       ArrayList<String> alist=new ArrayList<String>(Arrays.asList("Male","Female","Other"));
       txtGen.getItems().setAll(alist);
       
       ArrayList<String> alistbg=new ArrayList<String>(Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-"));
        txtBG.getItems().setAll(alistbg);
        
        ArrayList<String> alistcity=new ArrayList<String>(Arrays.asList("Bathinda","Mansa","Goniana","Jalandhar"));
        txtCity.getItems().setAll(alistcity);
         
    }

}
