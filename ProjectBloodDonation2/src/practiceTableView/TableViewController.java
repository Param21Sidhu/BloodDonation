package practiceTableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StuBean> txtTable;
    
    Connection con;

    @FXML
    void doTable(ActionEvent event) {
    	TableColumn<StuBean, Integer> rollno=new TableColumn<StuBean, Integer>("Roll No kuch");
    	rollno.setCellValueFactory(new PropertyValueFactory<>("rollno"));//same as bean property
    	rollno.setMinWidth(100);
    	
    	TableColumn<StuBean, String> Name=new TableColumn<StuBean, String>("Name");
    	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));//same as bean property
    	Name.setMinWidth(100);
    	
    	TableColumn<StuBean, Float> per=new TableColumn<StuBean, Float>("Percen");
    	per.setCellValueFactory(new PropertyValueFactory<>("per"));//same as bean property
    	per.setMinWidth(100);
    	
    	
    	//tblGrid.getColumns().addAll(roll,name,per,doj);
    	txtTable.getColumns().addAll(rollno,Name,per);
    	

    	ObservableList<StuBean> allRecords=getAllObjects();	
    	

    	txtTable.setItems(allRecords);
    	
    	
    	
    }
    
    
    ObservableList<StuBean> getAllObjects()
    {
    	ObservableList<StuBean> ary=FXCollections.observableArrayList();
    	
    	PreparedStatement pst;
    	ResultSet table;
    	
    	try {
    		pst=con.prepareStatement("select * from students");
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    		int rollno=table.getInt("rollno");
    		float per=table.getFloat("per");
    		String Name=table.getString("Name");
    		String branch=table.getString("branch");
    		
    		StuBean obj=new StuBean(Name,rollno,per,branch);
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

    }

}
