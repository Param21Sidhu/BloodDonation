package bloodIssueTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodIssuedViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> txtBG;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TableView<?> txtTable;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    ObservableList<BissueBean> allRecords;

    @FXML
    void doGetAll(ActionEvent event) {
    	TableColumn<BissueBean, String> Name=new TableColumn<BissueBean, String>("Naam");
    	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	Name.setMinWidth(100);
    	
    	txtTable.getColumns().addAll(Name);
    	
        
       
    }
    ObservableList<BissueBean> getDiffData()
    {
     	ObservableList<BissueBean> ary=FXCollections.observableArrayList();
         
     	String sbg=txtBG.getSelectionModel().getSelectedItem();
     	String sd=String.valueOf(txtDate.getValue()); 
     	
     	
     	
    	try {
    		pst=con.prepareStatement("select * from issued where BloodGroup=? and Doi=?");
            pst.setString(1,sbg);
    		
    		pst.setString(2, sd);
    		
    		table=pst.executeQuery();
    		while(table.next())
    		{
    			String n=table.getString("Name");
    			String m=table.getString("Mobile");
    			String hos=table.getString("Hospital");
    			String pur=table.getString("Purpose");
    			String bg=table.getString("BloodGroup");
    			String nu=table.getString("NoUnits");
    			
    			BissueBean obj=new BissueBean(n,m,hos,pur,bg,nu);
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
       ArrayList<String> aList=new ArrayList<String>(Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-"));
       txtBG.getItems().setAll(aList);
    }

}
