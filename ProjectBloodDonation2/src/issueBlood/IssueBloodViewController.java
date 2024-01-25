package issueBlood;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class IssueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBG;


    @FXML
    private DatePicker txtDate;

    @FXML
    private TextArea txtHos;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtPur;

    @FXML
    private TextField txtUnit;
    Connection con;
    ResultSet table;
    PreparedStatement pst;
    String s;

    @FXML
    void doUpload(ActionEvent event) {
    	decreaseBloodBottles(txtBG.getText(), Integer.parseInt(txtUnit.getText()));
    	
    	
    	
    }
    void decreaseBloodBottles(String ss,int qty)
    {
    	
    	 if(ss.equals("A+"))
    		 s="ap";
    	 else if(ss.equals("A-"))
    		 s="an";
    	 else if(ss.equals("B+"))
    		 s="bp";
    	 else if(ss.equals("B-"))
    		 s="bn";
    	 else if(ss.equals("O+"))
    		 s="opos";
    	 else if(ss.equals("O-"))
    		 s="oneg";
    	 else if(ss.equals("AB+"))
    		 s="abp";
    	 else if(ss.equals("AB+"))
    	     s="abn";
    	
    	if(checkAvailableOrNot(s, qty))
    	{
    		showMsg("Quantity of "+s+" group not available");
    		return;
    	}
    	
    	
    	
    	try {
    		pst=con.prepareStatement("update totalbloodcount set "+s+"="+s+"-"+qty);
    		pst.executeUpdate();
    		showMsg("Succesfully "+qty+" units blood of "+s+" group issued");
    		saveData();	
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    boolean checkAvailableOrNot(String ss,int qty)
    {
    	boolean jasoos=false;
    	try {
    		pst=con.prepareStatement("select "+ss+" from totalbloodcount");
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    		     if(table.getInt(ss)<qty)
    		     {
    		    	 jasoos=true;
    		     }
    		}
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return jasoos;
    }
    void saveData()
    {
    	LocalDate localt=txtDate.getValue(); 
    	try {
    		pst=con.prepareStatement("insert into issued values(?,?,?,?,?,?,?)");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtMobile.getText());
    		pst.setString(3, txtHos.getText());
    		pst.setString(4, txtPur.getText());
    		
    		java.sql.Date date=java.sql.Date.valueOf(localt);
    		pst.setDate(5, date);
    		
    		pst.setString(6, txtBG.getText());
    		pst.setString(7, txtUnit.getText());
    		
    		pst.executeUpdate();
    		showMsg("Uploaded Successfully");
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
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
      con=DatabaseConnection.doConnect();
  
     
      
   
    }

}
