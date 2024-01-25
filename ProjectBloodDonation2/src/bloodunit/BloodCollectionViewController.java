package bloodunit;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BloodCollectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBG;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ComboBox<String> txtMob;

    @FXML
    private ImageView txtPic;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    String s;

    @FXML
    void doClear(ActionEvent event) {
       txtMob.getItems().clear();
       txtBG.setText("");
       txtDate.setValue(null);
       
    }

    @FXML
    void doSearch(ActionEvent event) {
    	if(!chkmobile(txtMob.getSelectionModel().getSelectedItem()))
    	{
            showMsg("Mobile Number Not Registered");
            return;
    	}
            try {
                	pst=con.prepareStatement("select * from donormasterregister where Mobile=?");
                	pst.setString(1, txtMob.getSelectionModel().getSelectedItem());
                	table=pst.executeQuery();
                	
                	while(table.next())
                	{
                		txtBG.setText(table.getString("BloodGroup"));
                		
                		      if(table.getString("Picpath")!=null && table.getString("Picpath")!="Browse")
              	              {
                       	     
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
    void showMsg(String msg)
    {
          Alert alert=new Alert(AlertType.INFORMATION);
          alert.setTitle("TITLE");
          alert.setContentText(msg);
          alert.show();
    }
    @FXML
    void doUploadData(ActionEvent event) {
    	
        if(!chkmobile(txtMob.getSelectionModel().getSelectedItem()))
        {
        	showMsg("Invalid Number");
        	return;
        }
        if(txtBG.getText()==null || txtBG.getText()=="")
        {
        	showMsg("Plese Fill Blood Register Or Reregister your Number");
        	return;
        }
        else if(txtDate.getValue()==null)
        {
        	showMsg("Please Fill the Date");
        	return;
        }
        uploadinDonations();
        addBloodtoTotal(txtBG.getText());
        doClear(event);
    }
    void uploadinDonations()
    {
    	
    	LocalDate locald=txtDate.getValue();
    	
    	try {
    		pst=con.prepareStatement("insert into donations values(?,?,?)");
    		pst.setString(1,txtMob.getSelectionModel().getSelectedItem());
    		pst.setString(2, txtBG.getText());
    		java.sql.Date date=java.sql.Date.valueOf(locald);
    		pst.setDate(3, date);
    		pst.executeUpdate();
    		showMsg("Data Uploaded");
    		
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
     void addBloodtoTotal(String ss)
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
    	
    	
    	  
    	 try {
    		pst=con.prepareStatement("update totalbloodcount set "+s+"="+s+"+1");
    		//pst.setString(1, s);
    		
    		pst.executeUpdate();
    		showMsg("One bottle more added to "+ss+" Group");
    	 }catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    	 }
     }

    @FXML
    void initialize() {
       con=DatabaseConnection.doConnect();
       ArrayList<String> alist=new ArrayList<String>();
       try {
    	   pst=con.prepareStatement("select Mobile from donormasterregister");
    	   table=pst.executeQuery();
    	   while(table.next())
    	   {
    		  
    		   alist.add(table.getString("Mobile"));
    	   }
    	   txtMob.getItems().setAll(alist);
       }catch(Exception ex)
       {
    	   System.out.println(ex.getMessage());
       }
    }

}
