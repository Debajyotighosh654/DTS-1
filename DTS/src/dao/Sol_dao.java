package dao;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

import bean.SolInfo;
import ucon.Uconnection;

public class Sol_dao {
	
	Uconnection mycon = new Uconnection();
	Connection cn = null;
	
	public void getTempConnection() {
		try {
			cn = mycon.open();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List retriveAllSol(String db_username) throws SQLException {
		List li = null;
		String status = null;
		long bug_id = 0;;
		String sol_title = null;
		int sol_id = 0;
		String sol_desc = null;
		Timestamp sol_date = null;
		Statement st = null;
		ResultSet rs = null;
		
		getTempConnection();
		st = cn.createStatement();
		String query_all_sol = "select sol_id, sol_title, sol_desc, sol_date, bug_id from dts_sol";
		rs = st.executeQuery(query_all_sol);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				sol_id = rs.getInt("sol_id");
				sol_title = rs.getString("sol_title");
				sol_desc = rs.getString("sol_desc");
				sol_date = rs.getTimestamp("sol_date");
				bug_id = rs.getInt("bug_id");
				
				System.out.println(sol_id);
				System.out.println(sol_title);
				System.out.println(sol_desc);
				System.out.println(sol_date);
				System.out.println(bug_id);
				
				li.add(sol_id);
				li.add(sol_title);
				li.add(sol_desc);
				li.add(sol_date);
				li.add(bug_id);
			}
		}
		System.out.println("Through list...");
		Iterator it = li.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		cn.close();
		return li;
	}
	
	
	public List retriveSol(String db_username) throws SQLException {
		List li = null;
		String status = null;
		long bug_id = 0;
		String sol_title = null;
		int sol_id = 0;
		String sol_desc = null;
		Timestamp sol_date = null;
		Statement st = null;
		ResultSet rs = null;
		
		getTempConnection();
		st = cn.createStatement();
		String query_sol = "select sol_id, sol_title, sol_desc, sol_date, bug_id from dts_sol where bug_id in(select bug_id from dts_bug where project_id=(select DISTINCT project_id from dts_project where user_name='"+db_username+"'))";
		rs = st.executeQuery(query_sol);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				sol_id = rs.getInt("sol_id");
				sol_title = rs.getString("sol_title");
				sol_desc = rs.getString("sol_desc");
				sol_date = rs.getTimestamp("sol_date");
				bug_id = rs.getInt("bug_id");
				
				System.out.println(sol_id);
				System.out.println(sol_title);
				System.out.println(sol_desc);
				System.out.println(sol_date);
				System.out.println(bug_id);
				
				li.add(sol_id);
				li.add(sol_title);
				li.add(sol_desc);
				li.add(sol_date);
				li.add(bug_id);
			}
		}
		System.out.println("Through list...");
		Iterator it = li.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		cn.close();
		return li;
	}
	
	
	public int insertNewSol(SolInfo obj) throws SQLException, ParseException {
		int i = 0;
		Statement st = null;
		
		int sol_id = obj.getSol_id();
		String sol_title = obj.getSol_title();
		String sol_desc = obj.getSol_desc();
		String sol_date1 = obj.getSol_date();
		long bug_id= obj.getBug_id();
	    int status= obj.getStatus();
	    
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Calendar calendar = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date sol_date2 = null;
		
		sol_date2 = df.parse(sol_date1);
        System.out.println("date imput is:" +sol_date2);
        // = "date imput is:Thu Dec 24 00:00:00 CET 2009"
        
        calendar.setTime(sol_date2);
	    java.sql.Timestamp sol_date = new java.sql.Timestamp(calendar.getTime().getTime());
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	    getTempConnection();
	    st = cn.createStatement();
	    String qry_insertSolInfo="insert into dts_sol (sol_id,sol_title, sol_desc, sol_date, status,bug_id)" +
				"values("+sol_id+",'"+sol_title+"','"+sol_desc+"','"+sol_date+"',"+status+","+bug_id+")";
		i = st.executeUpdate(qry_insertSolInfo);
		
		return i;
	}
	
	
	public List getSolInfoEdit (int sol_id) throws SQLException {
		List li = null;
		long bug_id=0;
		String sol_title=null;
		String sol_desc=null;
		int status=0;
		Timestamp sol_date=null;
		Statement st=null;
		ResultSet rs=null;
		
		getTempConnection();
		st = cn.createStatement();
		String query_sol="select DISTINCT sol_id, sol_title, sol_desc, sol_date, status, bug_id from dts_sol where sol_id="+sol_id+"";
		rs = st.executeQuery(query_sol);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				sol_id = rs.getInt("sol_id");
				sol_title = rs.getString("sol_title");
				sol_desc = rs.getString("sol_desc");
				sol_date = rs.getTimestamp("sol_date");
				status = rs.getInt("status");
				bug_id = rs.getInt("bug_id");
				
				System.out.println(sol_id);
				System.out.println(sol_title);
				System.out.println(sol_desc);
				System.out.println(sol_date);
				System.out.println(status);
				System.out.println(bug_id);
				
				li.add(sol_id);
				li.add(sol_title);
				li.add(sol_desc);
				li.add(sol_date);
				li.add(status);
				li.add(bug_id);
			}
		}
		System.out.println("Through List...");
		Iterator it = li.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		cn.close();
		return li;
	}
	
	
	public int getNewSolId() throws SQLException {
		int new_id = 0;
		Statement st = null;
		ResultSet rs = null;
		
		getTempConnection();
		st = cn.createStatement();
		String query_get_new_id="SELECT MAX(sol_id) as id from dts_sol";
		rs = st.executeQuery(query_get_new_id);
		
		System.out.println("value of RS is= " + rs);
		if(rs != null) {
			System.out.println("in the rs != null");
			try {
				System.out.println("after try");
				while(rs.next()){
					System.out.println("in rs now");
					new_id=rs.getInt("id");
					System.out.println("new id got is="+new_id);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Exceptio caught of no recodrs present");
				return 1;
			}
		}
		return new_id + 1;
	}
	
	
	public int editSolBasicInfo(SolInfo obj) throws ParseException, SQLException {
		int i = 0;
		int sol_id = obj.getSol_id();
		String sol_title = obj.getSol_title();
		String sol_desc = obj.getSol_desc();
		String sol_date1 = obj.getSol_date();
		
		Statement st = null;
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Calendar calendar = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date sol_date2 = null;
		
		sol_date2 = df.parse(sol_date1);
        System.out.println("date imput is:" +sol_date2);
        // = "date imput is:Thu Dec 24 00:00:00 CET 2009"
        
        calendar.setTime(sol_date2);
	    java.sql.Timestamp sol_date = new java.sql.Timestamp(calendar.getTime().getTime());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	   int status= obj.getStatus();
	   
	   getTempConnection();
	   st = cn.createStatement();
	   String qry_editSolInfo="update dts_sol set sol_title='"+sol_title+"',sol_desc='"+sol_desc+"'," +
				"sol_date='"+sol_date+"',status="+status+" where sol_id="+sol_id+"";
		i = st.executeUpdate(qry_editSolInfo);
		
		return i;
	}
	
	
	public int deleteSol(int sol_id) throws SQLException {
		int i = 0;
		Statement st = null;
		
		System.out.println("in DAO delete sol");
		
		getTempConnection();
		st = cn.createStatement();
		String qry_deleteSol="delete from dts_sol where sol_id="+sol_id+"";
		i = st.executeUpdate(qry_deleteSol);
		
		return i;
	}
	

}
