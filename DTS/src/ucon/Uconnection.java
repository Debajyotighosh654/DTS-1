package ucon;

import java.io.IOException;
import java.sql.*;

public class Uconnection {
	
	Connection con = null;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	String Driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/dts";
	String uname = "root";
	String pass = "root";
	
	public Connection open() throws ClassNotFoundException, SQLException {		
		System.out.println("In connect()");
		
		Class.forName(Driver);
		
		System.out.println("Load Driver");
		
		con = DriverManager.getConnection(url, uname, pass);
		
		System.out.println("connection created.......");
		return con;
	}
	
	public void close() throws SQLException, IOException {
		stmt.close();
		rs.close();
		
		con.close();
	}

}
