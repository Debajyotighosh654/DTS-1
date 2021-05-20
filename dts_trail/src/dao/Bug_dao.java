package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import bean.BugInfo;

import ucon.Uconnection;

public class Bug_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	public List retriveAllBug(String db_username) {
		// TODO Auto-generated method stub
		List li=null;
		
		Statement st=null;
		ResultSet rs=null;
		String project_id;
		String bug_title=null;
		long bug_id=0;
		String bug_desc=null;
		int status=0;
		String bug_to=null;
		String priority=null;
		String bug_from=null;
		Timestamp start_date=null;
		Timestamp end_date=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_all_bug="select bug_id, bug_title, bug_desc,start_date, end_date, bug_to, bug_from, project_id, status, priority from dts_bug";
		
		try {
			rs=st.executeQuery(query_all_bug);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			li=new ArrayList();
			try {
				while(rs.next()){
					bug_id=rs.getInt("bug_id");
					bug_title=rs.getString("bug_title");
					bug_desc=rs.getString("bug_desc");
					start_date=rs.getTimestamp("start_date");
					end_date=rs.getTimestamp("end_date");
					bug_to=rs.getString("bug_to");
					bug_from=rs.getString("bug_from");
					project_id=rs.getString("project_id");
					status=rs.getInt("status");
					priority=rs.getString("priority");
					System.out.println(bug_id);
					System.out.println(bug_title);
					System.out.println(bug_desc);
					System.out.println(start_date);
					System.out.println(end_date);
					System.out.println(bug_to);
					System.out.println(bug_from);
					System.out.println(project_id);
					System.out.println(status);
					System.out.println(priority);
					li.add(bug_id);
					li.add(bug_title);
					li.add(bug_desc);
					li.add(start_date);
					li.add(end_date);
					li.add(bug_to);
					li.add(bug_from);
					li.add(project_id);
					li.add(status);
					li.add(priority);
					
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

	public List retriveBug(String db_username) {
		// TODO Auto-generated method stub
		List li=null;
		
		Statement st=null;
		ResultSet rs=null;
		String project_id;
		String bug_title=null;
		long bug_id=0;
		String bug_desc=null;
		int status=0;
		String bug_to=null;
		String bug_from=null;
		String priority=null;
		
		Timestamp start_date=null;
		Timestamp end_date=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_bug="select bug_id, bug_title, bug_desc, start_date, end_date, bug_to, bug_from, project_id, status, priority from dts_bug where project_id in(select project_id from dts_project where user_name='"+db_username+"')";
		
		try {
			rs=st.executeQuery(query_bug);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			li=new ArrayList();
			try {
				while(rs.next()){
					bug_id=rs.getInt("bug_id");
					bug_title=rs.getString("bug_title");
					bug_desc=rs.getString("bug_desc");
					start_date=rs.getTimestamp("start_date");
					end_date=rs.getTimestamp("end_date");
					bug_to=rs.getString("bug_to");
					bug_from=rs.getString("bug_from");
					project_id=rs.getString("project_id");
					status=rs.getInt("status");
					priority=rs.getString("priority");
					System.out.println(bug_id);
					System.out.println(bug_title);
					System.out.println(bug_desc);
					System.out.println(start_date);
					System.out.println(end_date);
					System.out.println(bug_to);
					System.out.println(bug_from);
					System.out.println(project_id);
					System.out.println(status);
					System.out.println(priority);
					li.add(bug_id);
					li.add(bug_title);
					li.add(bug_desc);
					li.add(start_date);
					li.add(end_date);
					li.add(bug_to);
					li.add(bug_from);
					li.add(project_id);
					li.add(status);
					li.add(priority);
					
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
	public int insertNewBug(BugInfo obj) {
		// TODO Auto-generated method stub
		int i=0;
		Statement st=null;
		
		long bug_id= obj.getBug_id();
		String bug_title=obj.getBug_title();
		String bug_desc=obj.getBug_desc();
		String priority=obj.getPriority();
		String start_date1=obj.getStart_date();
		String end_date1=obj.getEnd_date();
		
		Calendar calendar = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date2 = null;
		java.util.Date end_date2 = null;
	    try
	    {
	    	start_date2 = df.parse(start_date1);
	    	end_date2 = df.parse(end_date1);
	        System.out.println("date imput is:" +start_date2);
	        System.out.println("date imput is:" +end_date2);
	        // = "date imput is:Thu Dec 24 00:00:00 CET 2009"
	    } 
	    catch (java.text.ParseException e)
	    {
	         e.printStackTrace();
	    }
	    
	    calendar.setTime(start_date2);
	    java.sql.Timestamp start_date = new java.sql.Timestamp(calendar.getTime().getTime());

	    calendar.setTime(end_date2);
	    java.sql.Timestamp end_date = new java.sql.Timestamp(calendar.getTime().getTime());
		
		String bug_to= obj.getBug_to();
		String bug_from= obj.getBug_from();
		String project_id= obj.getProject_id();
	    int status= obj.getStatus();
	
		
		
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_insertBugInfo="insert into dts_bug (bug_id,bug_title, bug_desc, start_date, end_date, bug_to, bug_from ,project_id, status, priority)" +
				"values("+bug_id+",'"+bug_title+"','"+bug_desc+"','"+start_date+"','"+
				end_date+"','"+bug_to+"','"+bug_from+"','"+project_id+"',"+status+",'"+priority+"')";
		
		
		try {
			i=st.executeUpdate(qry_insertBugInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Sorry..Member is alreay in the Bug Team. Choose other");
			return -2;
		}
		return i;
	}
	public List getBugInfoEdit(long bug_id) {
		// TODO Auto-generated method stub
		List li=null;
		
		Statement st=null;
		ResultSet rs=null;
		String project_id=null;
		String bug_title=null;
		String bug_desc=null;
		String priority=null;
		int status=0;
		Timestamp start_date=null;
		Timestamp end_date=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_bug="select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, project_id, status, priority from dts_bug where bug_id="+bug_id+"";
		
		try {
			rs=st.executeQuery(query_bug);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			li=new ArrayList();
			try {
				while(rs.next()){
					bug_id=rs.getInt("bug_id");
					bug_title=rs.getString("bug_title");
					bug_desc=rs.getString("bug_desc");
					start_date=rs.getTimestamp("start_date");
					end_date=rs.getTimestamp("end_date");
					project_id=rs.getString("project_id");
					status=rs.getInt("status");
					priority=rs.getString("priority");
					System.out.println(bug_id);
					System.out.println(bug_title);
					System.out.println(bug_desc);
					System.out.println(start_date);
					System.out.println(end_date);
					System.out.println(project_id);
					System.out.println(status);
					System.out.println(priority);
					li.add(bug_id);
					li.add(bug_title);
					li.add(bug_desc);
					li.add(start_date);
					li.add(end_date);
					li.add(project_id);
					li.add(status);
					li.add(priority);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
	public List getBugTeamInfo(long bug_id) {
		// TODO Auto-generated method stub
		List li=null;
		
		
		String qry_collectTaskTeamData="select bug_to from dts_bug where bug_id="+bug_id+"";
		
		Statement st=null;
		ResultSet rs=null;
		String bug_to;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs=st.executeQuery(qry_collectTaskTeamData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rs!=null){
			li=new ArrayList();
			try {
				while(rs.next()){
					bug_to=rs.getString("bug_to");
					li.add(bug_to);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return li;

	}	
	public int getNewBugId(){
		int new_id=0;
		Statement st=null;
		ResultSet rs=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_get_new_id="SELECT MAX(bug_id) as id from dts_bug";
		
		try {
			rs=st.executeQuery(query_get_new_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("value of RS is="+rs);
		if(rs!=null){
			System.out.println("in the rs!=null");
			try {
				System.out.println("after try");
				while(rs.next()){
					System.out.println("in rs now");
					new_id=rs.getInt("id");
					System.out.println("new id got is="+new_id);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Exceptio caught of no recodrs present");
				return 1;
			}
		}
		return new_id+1;
	}
	public int editBugBasicInfo(BugInfo obj) {
		// TODO Auto-generated method stub
		int i=0;
		Statement st=null;
		
		long bug_id= obj.getBug_id();
		String bug_title=obj.getBug_title();
		String bug_desc=obj.getBug_desc();
		
		String start_date1=obj.getStart_date();
		String end_date1=obj.getEnd_date();
		
		Calendar calendar = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date2 = null;
		java.util.Date end_date2 = null;
	    try
	    {
	    	start_date2 = df.parse(start_date1);
	    	end_date2 = df.parse(end_date1);
	        System.out.println("date imput is:" +start_date2);
	        System.out.println("date imput is:" +end_date2);
	        // = "date imput is:Thu Dec 24 00:00:00 CET 2009"
	    } 
	    catch (java.text.ParseException e)
	    {
	         e.printStackTrace();
	    }
	    
	    calendar.setTime(start_date2);
	    java.sql.Timestamp start_date = new java.sql.Timestamp(calendar.getTime().getTime());

	    calendar.setTime(end_date2);
	    java.sql.Timestamp end_date = new java.sql.Timestamp(calendar.getTime().getTime());
		
		String bug_to= obj.getBug_to();
		String bug_from= obj.getBug_from();
		String project_id= obj.getProject_id();
	    int status= obj.getStatus();
	
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_editBugInfo="update dts_bug set bug_title='"+bug_title+"',bug_desc='"+bug_desc+"'," +
				"start_date='"+start_date+"', end_date='"+end_date+"',status="+status+" where bug_id="+bug_id+"";
		
		
		try {
			i=st.executeUpdate(qry_editBugInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int deleteBugMemember(String bug_to, long bug_id) {
		// TODO Auto-generated method stub
		int i=0;
		Statement st=null;
		
		System.out.println("in DAO delete task memeber");
		
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_deleteBugMem="delete from dts_bug where bug_to='"+bug_to+"' and bug_id="+bug_id+"";
		
		
		try {
			i=st.executeUpdate(qry_deleteBugMem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	


}
