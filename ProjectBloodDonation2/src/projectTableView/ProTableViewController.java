package projectTableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import practiceTableView.StuBean;

public class ProTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> txtCombo;

    @FXML
    private TableView<BGBean> txtTable;
   
    Connection con;
    ResultSet table;
    PreparedStatement pst;
    
    @FXML
    void doFetch(ActionEvent event) {

    	TableColumn<BGBean, String> Name=new TableColumn<BGBean, String>("Name");
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));//same as bean property
        Name.setMinWidth(100);
        
    	TableColumn<BGBean, String> Mobile=new TableColumn<BGBean, String>("Mobile");
    	Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	Mobile.setMinWidth(100);
    	
    	TableColumn<BGBean, String> BloodGroup=new TableColumn<BGBean, String>("BG");
    	BloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));//same as bean property
    	BloodGroup.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Age=new TableColumn<BGBean, String>("Age");
    	Age.setCellValueFactory(new PropertyValueFactory<>("Age"));//same as bean property
    	Age.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Gender=new TableColumn<BGBean, String>("Gender");
    	Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));//same as bean property
    	Gender.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Dis=new TableColumn<BGBean, String>("Disease");
        Dis.setCellValueFactory(new PropertyValueFactory<>("Disease"));//same as bean property
        Dis.setMinWidth(100);
        
        txtTable.getColumns().clear();
        txtTable.getColumns().addAll(Name,Mobile,BloodGroup,Age,Gender,Dis);
        
ObservableList<BGBean> allRecords=getParticularData();	
    	

    	txtTable.setItems(allRecords);
    }
    ObservableList<BGBean> getParticularData()
    {
ObservableList<BGBean> ary=FXCollections.observableArrayList();
          String s=txtCombo.getEditor().getText();
          
    	try {
    		pst=con.prepareStatement("select * from donormasterregister where BloodGroup=?");
    		pst.setString(1, s);
    		
    	
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			String Mob=table.getString("Mobile");
    			String Name=table.getString("Name");
    			String Gen=table.getString("Gender");
    			String BG=table.getString("BloodGroup");
    			String Dis=table.getString("Disease");
    			String Age=table.getString("Age");
    			
    			
    			BGBean obj=new BGBean(Mob,Name,Gen,BG,Dis,Age);
    			ary.add(obj);
    			
    			
    		}
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return ary;
    }

    @FXML
    void doShowAll(ActionEvent event) {
    	
    	TableColumn<BGBean, String> Name=new TableColumn<BGBean, String>("Name");
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));//same as bean property
        Name.setMinWidth(100);
        
    	TableColumn<BGBean, String> Mobile=new TableColumn<BGBean, String>("Mobile");
    	Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	Mobile.setMinWidth(100);
    	
    	TableColumn<BGBean, String> BloodGroup=new TableColumn<BGBean, String>("BG");
    	BloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));//same as bean property
    	BloodGroup.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Age=new TableColumn<BGBean, String>("Age");
    	Age.setCellValueFactory(new PropertyValueFactory<>("Age"));//same as bean property
    	Age.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Gender=new TableColumn<BGBean, String>("Gender");
    	Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));//same as bean property
    	Gender.setMinWidth(100);
    	
    	TableColumn<BGBean, String> Dis=new TableColumn<BGBean, String>("Disease");
        Dis.setCellValueFactory(new PropertyValueFactory<>("Disease"));//same as bean property
        Dis.setMinWidth(100);
        
        txtTable.getColumns().addAll(Name,Mobile,BloodGroup,Age,Gender,Dis);
        
ObservableList<BGBean> allRecords=getAllData();	
    	

    	txtTable.setItems(allRecords);

    }
    ObservableList<BGBean> getAllData()
    {
    	ObservableList<BGBean> ary=FXCollections.observableArrayList();
    	
    	try {
    		pst=con.prepareStatement("select * from donormasterregister");
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			String Mob=table.getString("Mobile");
    			String Name=table.getString("Name");
    			String Gen=table.getString("Gender");
    			String BG=table.getString("BloodGroup");
    			String Dis=table.getString("Disease");
    			String Age=table.getString("Age");
    			
    			BGBean obj=new BGBean(Mob,Name,Gen,BG,Dis,Age);
    			ary.add(obj);
    			
    			
    		}
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return ary;
    }

    @FXML
    void initialize() {
       con=DatabaseConnection.doConnect();
       ArrayList<String> alist=new ArrayList<String>(Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-"));
       txtCombo.getItems().setAll(alist);
       
    }

}
