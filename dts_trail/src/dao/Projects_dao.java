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

import bean.PrjInfo;

import ucon.Uconnection;

public class Projects_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	public List retriveProjectList(String db_username) throws SQLException{
		
		List li=null;
		
		
		String status= null;
		Statement st=null;
		ResultSet rs=null;
		String project_id, project_name;
		getTempConnection();
		st= cn.createStatement();
		int project_sts=0;
		String query_prj="select DISTINCT project_id, project_name, status from dts_project where user_name='"+db_username+"'";
		rs=st.executeQuery(query_prj);
		if(rs!=null){
			li=new ArrayList();
			while(rs.next()){
				project_id=rs.getString("project_id");
				project_name=rs.getString("project_name");
				project_sts=rs.getInt("status");
				li.add(project_id);
				li.add(project_name);
				li.add(project_sts);
				}
		}
		
		return li;
	}
	
	public List retriveProjectSts(String db_username) throws SQLException {
		// TODO Auto-generated method stub
		
		List li=null;
		Statement st=null;
		ResultSet rs=null;
		getTempConnection();
		st= cn.createStatement();
		int project_sts=0;
		String query_prj_sts="SELECT status from dts_project_status where project_id in(select DISTINCT project_id from dts_project where user_name='"+db_username+"')";
		rs=st.executeQuery(query_prj_sts);
		if(rs!=null){
			li=new ArrayList();
			while(rs.next()){
				project_sts=rs.getInt("status");
			
				li.add(project_sts);
				
				}
		}
		System.out.println("Prj stats list is");
		Iterator it =li.listIterator();
		while(it.hasNext()){
		System.out.println(it.next());
		}
		return li;
	}
public List retriveAllProjectDataPerUser(String db_username) throws SQLException{
		
		List li=null;
		
		
		
		Statement st=null;
		ResultSet rs=null;
		String project_id, project_name, project_details;
	/*	Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp start_date = new java.sql.Timestamp(calendar.getTime().getTime());
		
	  //  Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp end_date = new java.sql.Timestamp(calendar.getTime().getTime());
		*/
		
		Timestamp start_date,end_date;
	    getTempConnection();
		st= cn.createStatement();
		int project_sts=0;
		String query_prj_alldata="select DISTINCT project_id, project_name, project_details, status, start_date, end_date from dts_project where user_name='"+db_username+"'";
		rs=st.executeQuery(query_prj_alldata);
		if(rs!=null){
			li=new ArrayList();
			while(rs.next()){
				project_id=rs.getString("project_id");
				project_name=rs.getString("project_name");
				project_details=rs.getString("project_details");
				start_date=rs.getTimestamp("start_date");
				end_date=rs.getTimestamp("end_date");
				project_sts=rs.getInt("status");
				li.add(project_id);
				li.add(project_name);
				li.add(project_details);
				li.add(start_date);
				li.add(end_date);
				li.add(project_sts);
				}
		}
		
		return li;
	}
public List retriveAllProjectDataAdmin(String db_username) throws SQLException{
	
	List li=null;
	
	
	
	Statement st=null;
	ResultSet rs=null;
	String project_id, project_name, project_details;
/*	Calendar calendar = Calendar.getInstance();
    java.sql.Timestamp start_date = new java.sql.Timestamp(calendar.getTime().getTime());
	
  //  Calendar calendar = Calendar.getInstance();
    java.sql.Timestamp end_date = new java.sql.Timestamp(calendar.getTime().getTime());
	*/
	
	Timestamp start_date,end_date;
    getTempConnection();
	st= cn.createStatement();
	int project_sts=0;
	String query_prj_alldata="select DISTINCT project_id, project_name, project_details, status, start_date, end_date from dts_project";
	rs=st.executeQuery(query_prj_alldata);
	if(rs!=null){
		li=new ArrayList();
		while(rs.next()){
			project_id=rs.getString("project_id");
			project_name=rs.getString("project_name");
			project_details=rs.getString("project_details");
			start_date=rs.getTimestamp("start_date");
			end_date=rs.getTimestamp("end_date");
			project_sts=rs.getInt("status");
			li.add(project_id);
			li.add(project_name);
			li.add(project_details);
			li.add(start_date);
			li.add(end_date);
			li.add(project_sts);
			}
	}
	
	return li;
}
	public int insertNewProject(PrjInfo obj){
		int i=0;
		Statement st=null;
		
		String project_id=obj.getProject_id();
		String project_name=obj.getProject_name();
		String project_details=obj.getProject_details();
		String client_id= obj.getClient_id();
		int status= obj.getStatus();
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
		
		String user_name=obj.getUser_name();
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_insertPrjInfo="insert into dts_project (project_id,project_name, project_details, client_id, status, start_date, end_date, user_name)" +
				"values('"+project_id+"','"+project_name+"','"+project_details+"','"+client_id+"',"+status+",'"+start_date+"','"+
				end_date+"','"+user_name+"')";
		
		
		try {
			i=st.executeUpdate(qry_insertPrjInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public String getNewProjectId(){
		String new_id=null;
		Statement st=null;
		ResultSet rs=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_get_new_id="SELECT CONCAT('PMPRJ',LPAD(MAX(SUBSTR(project_id,6,10))+1,5,0)) as id from dts_project";
		
		try {
			rs=st.executeQuery(query_get_new_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			try {
				while(rs.next()){
					new_id=rs.getString("id");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new_id;
	}
public List getProjInfoEdit(String project_id) {
		// TODO Auto-generated method stub
		List li=null;
		
		
		String qry_collectProjectData="select DISTINCT project_id, project_name, project_details, client_id, status, start_date, end_date from dts_project where project_id='"+project_id+"'";
		
		Statement st=null;
		ResultSet rs=null;
		String project_name, project_details, client_id;
		Timestamp start_date,end_date;
		int project_sts;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs=st.executeQuery(qry_collectProjectData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rs!=null){
			li=new ArrayList();
			try {
				while(rs.next()){
					project_id=rs.getString("project_id");
					project_name=rs.getString("project_name");
					project_details=rs.getString("project_details");
					client_id=rs.getString("client_id");
					start_date=rs.getTimestamp("start_date");
					end_date=rs.getTimestamp("end_date");
					project_sts=rs.getInt("status");
					li.add(project_id);
					li.add(project_name);
					li.add(project_details);
					li.add(client_id);
					li.add(start_date);
					li.add(end_date);
					li.add(project_sts);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Iterator it=li.listIterator();
		System.out.println("in collectedit info fun.. data are");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	
		return li;
	}
public int editProjectBasicInfo(PrjInfo obj) {
	// TODO Auto-generated method stub
	String project_id=obj.getProject_id();
	String project_name= obj.getProject_name();
	String project_details= obj.getProject_details();
	String start_date1= obj.getStart_date();
	String end_date1= obj.getEnd_date();
	int status= obj.getStatus();
	int i=0;
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
	
    Statement st=null;
	
	getTempConnection();
	try {
		st= cn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String qry_editPrjInfo="update dts_project set project_name='"+project_name+"',project_details='"+project_details+"',status="+status+", " +
			"start_date='"+start_date+"', end_date='"+end_date+"' where project_id='"+project_id+"'";
	
	
	try {
		i=st.executeUpdate(qry_editPrjInfo);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return i;
}
public List getProjTeamInfo(String project_id) {
	// TODO Auto-generated method stub
	List li=null;
	
	
	String qry_collectProjectTeamData="select user_name from dts_project where project_id='"+project_id+"'";
	
	Statement st=null;
	ResultSet rs=null;
	String user_name;
	getTempConnection();
	try {
		st= cn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		rs=st.executeQuery(qry_collectProjectTeamData);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(rs!=null){
		li=new ArrayList();
		try {
			while(rs.next()){
				user_name=rs.getString("user_name");
				li.add(user_name);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	return li;


}
public int deleteProjectMemember(String user_name, String project_id) {
	// TODO Auto-generated method stub
	int i=0;
	Statement st=null;
	
	System.out.println("in DAO delete project memeber");
	
	getTempConnection();
	try {
		st= cn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String qry_deletePrjMem="delete from dts_project where user_name='"+user_name+"' and project_id='"+project_id+"'";
	
	
	try {
		i=st.executeUpdate(qry_deletePrjMem);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return i;
}
public int closeProject(String project_id) {
	// TODO Auto-generated method stub
	
	
	int i=0;
	Statement st=null;
	
	
	
	getTempConnection();
	try {
		st= cn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String qry_closePrj="insert dts_close_project select * from dts_project where project_id='"+project_id+"'";
	String qry_closePrj2="delete from dts_project where project_id='"+project_id+"'";
	
	try {
		i=st.executeUpdate(qry_closePrj);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		i=st.executeUpdate(qry_closePrj2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return i;
}





}
