package donorCollectionTbl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import projectTableView.BGBean;

public class DonorCollectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMob;

    @FXML
    private TableView<DonorBean> txtTable;
    
    Connection con;
    ResultSet table;
    PreparedStatement pst;
    ObservableList<DonorBean> allRecords;
    

    @FXML
    void doMobDon(ActionEvent event) {
    	TableColumn<DonorBean, String> Mobile=new TableColumn<DonorBean, String>("Mobile");
    	Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	Mobile.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> BloodGroup=new TableColumn<DonorBean, String>("Blood Group");
    	BloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));//same as bean property
    	BloodGroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> d=new TableColumn<DonorBean, String>("Date");
    	d.setCellValueFactory(new PropertyValueFactory<>("DateofDon"));//same as bean property
    	d.setMinWidth(100);
    	
    	
    	    
    	    txtTable.getColumns().clear();
           txtTable.getColumns().addAll(Mobile,BloodGroup,d);
          
          
            allRecords=getMobData();	
      	

      	txtTable.setItems(allRecords);
    }

    @FXML
    void doSaveExcel(ActionEvent event) {

    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public void writeExcel( ObservableList<DonorBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,Blood Group,Date of Donation\n";
            writer.write(text);
            for (DonorBean p : list)
            {
				text = p.getMobile()+ "," + p.getBloodGroup()+ "," + p.getDateofDon()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void doShowAllDon(ActionEvent event) {
   
    	TableColumn<DonorBean, String> Mobile=new TableColumn<DonorBean, String>("Mobile");
    	Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	Mobile.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> BloodGroup=new TableColumn<DonorBean, String>("Blood Group");
    	BloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));//same as bean property
    	BloodGroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> d=new TableColumn<DonorBean, String>("Date");
    	d.setCellValueFactory(new PropertyValueFactory<>("DateofDon"));//same as bean property
    	d.setMinWidth(100);
    	
    	
    	    
    	    txtTable.getColumns().clear();
           txtTable.getColumns().addAll(Mobile,BloodGroup,d);
          
          
          allRecords=getAllData();	
      	

      	txtTable.setItems(allRecords);
    	
    	
    	
    }
    ObservableList<DonorBean> getAllData()
    {
    	ObservableList<DonorBean> ary=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select * from donations");
    		table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			String Mob=table.getString("Mobile");
    			String BG=table.getString("BloodGroup");
    			String dat=table.getString("DateOfDonation");
    			
    			DonorBean obj=new DonorBean(Mob,BG,dat);
    			
    			ary.add(obj);
    		}
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return ary;
    }
    
    ObservableList<DonorBean> getMobData()
    {
    	String s=txtMob.getText();
    	if(!chkMob(s))
    	{
    		showMsg("Invalid User");
    		return null;
    	}
    	ObservableList<DonorBean> ary=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select * from donations where Mobile=?");
    		pst.setString(1, s);
    		
    		table=pst.executeQuery();
    		
    		
    		while(table.next())
    		{
    			String Mob=table.getString("Mobile");
    			String BG=table.getString("BloodGroup");
    			String dat=table.getString("DateOfDonation");
    			
    			DonorBean obj=new DonorBean(Mob,BG,dat);
    			
    			ary.add(obj);
    		}
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return ary;
    }
    boolean chkMob(String s)
    {
    	boolean jasoos=false;
    	try {
    		pst=con.prepareStatement("select * from donations where Mobile=?");
              pst.setString(1, s);
    		
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
    void initialize() {
       con=DatabaseConnection.doConnect();
    }

}
