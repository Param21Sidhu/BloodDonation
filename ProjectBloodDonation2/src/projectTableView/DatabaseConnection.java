package projectTableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection doConnect() {
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/javaprac","root","");
			System.out.println("Connected to mysql successfully");
		}catch(ClassNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return con;

	}
	public static void main(String []args)
	{
		doConnect();
	}

};
