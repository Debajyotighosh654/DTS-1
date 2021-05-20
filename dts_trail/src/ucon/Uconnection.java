package ucon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Uconnection {
	Connection con=null;
	Statement stmt;
	PreparedStatement pstmt;
	//String url="jdbc:mysql://localhost:3306/";
	//String Driver="com.mysql.jdbc.Driver";
	//String uname="root";
	//String pass="root";
	//String db="updated_ibis";
	ResultSet rs=null;
	
	
	 public Connection open()
	  {
		  System.out.println("In connect ()");
		  
		  try
		  {
		    Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dts","root","root");
		    System.out.println("connection created.......");
		    
		   }
			catch(Exception e)
			{
				System.out.println(e);	
			}
			return con;
	   }
	    
	    public void close() throws SQLException
	    {
	    	stmt.close();
	    	rs.close();
	        
	    	con.close();
	    	
	    }

	
}
