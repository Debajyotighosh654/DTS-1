package dao;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

import bean.PrjInfo;
import ucon.Uconnection;

public class Projects_dao {
	
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
	
	
	public List retriveProjectList(String db_username) throws SQLException {
		List li = null;
		String status = null;
		Statement st = null;
		ResultSet rs = null;
		String project_id, project_name;
		int project_sts = 0;
		getTempConnection();
		
		st = cn.createStatement();
		String query_prj="select DISTINCT project_id, project_name, status from dts_project where user_name='"+db_username+"'";
		rs = st.executeQuery(query_prj);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				project_id = rs.getString("project_id");
				project_name = rs.getString("project_name");
				project_sts = rs.getInt("status");
				li.add(project_id);
				li.add(project_name);
				li.add(project_sts);
			}
		}
		return li;
	}
	
	
	public List retriveProjectSts(String db_username) throws SQLException {
		List li = null;
		Statement st = null;
		ResultSet rs = null;
		int project_sts = 0;
		getTempConnection();
		
		st = cn.createStatement();
		String query_prj_sts = "SELECT status from dts_project_status where project_id in(select DISTINCT project_id from dts_project where user_name='"+db_username+"')";
		rs = st.executeQuery(query_prj_sts);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				project_sts = rs.getInt("status");
				li.add(project_sts);
			}
		}
		System.out.println("prj stats list is");
		Iterator it = li.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		return li;
	}
	
	
	public List retriveAllProjectDataPerUser(String db_username) throws SQLException {
		List li = null;
		Statement st = null;
		ResultSet rs = null;
		String project_id, project_name, project_details;
		Timestamp start_date,end_date;
		int project_sts = 0;
		getTempConnection();
		
		st = cn.createStatement();
		String query_prj_alldata="select DISTINCT project_id, project_name, project_details, status, start_date, end_date from dts_project where user_name='"+db_username+"'";
		rs = st.executeQuery(query_prj_alldata);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				project_id = rs.getString("project_id");
				project_name = rs.getString("project_name");
				project_details = rs.getString("project_details");
				start_date = rs.getTimestamp("start_date");
				end_date = rs.getTimestamp("end_date");
				project_sts = rs.getInt("status");
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
	
	
	public List retriveAllProjectDataAdmin(String db_username) throws SQLException {
		List li = null;
		Statement st = null;
		ResultSet rs = null;
		String project_id, project_name, project_details;
		Timestamp start_date,end_date;
		int project_sts = 0;
		getTempConnection();
		
		st= cn.createStatement();
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
	
	
	public int insertNewProject(PrjInfo obj) throws SQLException, ParseException {
		int i = 0;
		Statement st = null;
		
		String project_id = obj.getProject_id();
		String project_name = obj.getProject_name();
		String project_details=obj.getProject_details();
		String client_id= obj.getClient_id();
		int status= obj.getStatus();
		String start_date1=obj.getStart_date();
		String end_date1=obj.getEnd_date();
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Calendar calender = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date2 = null;
		java.util.Date end_date2 = null;
		
		start_date2 = df.parse(start_date1);
		end_date2 = df.parse(end_date1);
		System.out.println("date input is : " + start_date2);
		System.out.println("date input is : " + end_date2);
		// = "date input is:Thu Dec 24 00:00:00 IST 2020"
		
		calender.setTime(start_date2);
		java.sql.Timestamp start_date = new java.sql.Timestamp(calender.getTime().getTime());
		
		calender.setTime(end_date2);
		java.sql.Timestamp end_date = new java.sql.Timestamp(calender.getTime().getTime());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		String user_name = obj.getUser_name();
		
		getTempConnection();
		st = cn.createStatement();
		String qry_insertPrjInfo="insert into dts_project (project_id,project_name, project_details, client_id, status, start_date, end_date, user_name)" + "values('"+project_id+"','"+project_name+"','"+project_details+"','"+client_id+"',"+status+",'"+start_date+"','"+end_date+"','"+user_name+"')";
		i = st.executeUpdate(qry_insertPrjInfo);
		
		return i;
	}
	
	
	public String getNewProjectId() throws SQLException {
		String new_id = null;
		Statement st = null;
		ResultSet rs = null;
		getTempConnection();
		
		st = cn.createStatement();
		String query_get_new_id = "SELECT CONCAT('PMPRJ',LPAD(MAX(SUBSTR(project_id,6,10))+1,5,0)) as id from dts_project";
		rs = st.executeQuery(query_get_new_id);
		
		if(rs != null) {
			while(rs.next()) {
				new_id = rs.getString("id");
			}
		}
		return new_id;
	}
	
	
	public List getProjInfoEdit(String project_id) throws SQLException {
		List li = null;
		Statement st = null;
		ResultSet rs = null;
		String project_name, project_details, client_id;
		Timestamp start_date, end_date;
		int project_sts;
		getTempConnection();
		
		st = cn.createStatement();
		String qry_collectProjectData="select DISTINCT project_id, project_name, project_details, client_id, status, start_date, end_date from dts_project where project_id='"+project_id+"'";
		rs = st.executeQuery(qry_collectProjectData);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				project_id = rs.getString("project_id");
				project_name = rs.getString("project_name");
				project_details = rs.getString("project_details");
				client_id = rs.getString("client_id");
				start_date = rs.getTimestamp("start_date");
				end_date = rs.getTimestamp("end_date");
				project_sts = rs.getInt("status");
				li.add(project_id);
				li.add(project_name);
				li.add(project_details);
				li.add(client_id);
				li.add(start_date);
				li.add(end_date);
				li.add(project_sts);
			}
		}
		Iterator it = li.listIterator();
		System.out.println("in collect edit info fun.. data are");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		return li;
	}
	
	
	public int editProjectBasicInfo(PrjInfo obj) throws SQLException,  ParseException  {
		String project_id = obj.getProject_id();
		String project_name = obj.getProject_name();
		String project_details = obj.getProject_details();
		String start_date1 = obj.getStart_date();
		String end_date1 = obj.getEnd_date();
		int status = obj.getStatus();
		int i = 0;
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Calendar calender = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date2 = null;
		java.util.Date end_date2 = null;
		
		start_date2 = df.parse(start_date1);
		end_date2 = df.parse(end_date1);
		System.out.println("date input is : " + start_date2);
		System.out.println("date input is : " + end_date2);
		
		calender.setTime(start_date2);
		java.sql.Timestamp start_date = new java.sql.Timestamp(calender.getTime().getTime());
		calender.setTime(end_date2);
		java.sql.Timestamp end_date = new java.sql.Timestamp(calender.getTime().getTime());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Statement st = null;
		getTempConnection();
		st = cn.createStatement();
		String qry_editPrjInfo = "update dts_project set project_name='"+project_name+"',project_details='"+project_details+"',status="+status+", " +
												"start_date='"+start_date+"', end_date='"+end_date+"' where project_id='"+project_id+"'";
		i = st.executeUpdate(qry_editPrjInfo);
		
		return i;
	}
	
	
	public List getProjTeamInfo(String project_id) throws SQLException {
		List li = null;
		Statement st = null;
		ResultSet rs = null;
		String user_name;
		getTempConnection();
		
		st = cn.createStatement();
		String qry_collectProjectTeamData="select user_name from dts_project where project_id='"+project_id+"'";
		rs = st.executeQuery(qry_collectProjectTeamData);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				user_name = rs.getString("user_name");
				li.add(user_name);
			}
		}
		return li;
	}
	
	
	public int deleteProjectMemember(String user_name, String project_id) throws SQLException {
		int i = 0;
		Statement st = null;
		System.out.println("in DAO delete project member");
		getTempConnection();
		
		st = cn.createStatement();
		String qry_deletePrjMem="delete from dts_project where user_name='"+user_name+"' and project_id='"+project_id+"'";
		i = st.executeUpdate(qry_deletePrjMem);
		
		return i;
	}
	
	
	public int closeProject(String project_id) throws SQLException {
		int i = 0;
		Statement st = null;
		getTempConnection();
		
		st = cn.createStatement();
		//String qry_closePrj="insert dts_close_project select * from dts_project where project_id='"+project_id+"'";
		String qry_closePrj2="delete from dts_project where project_id='"+project_id+"'";
		//i = st.executeUpdate(qry_closePrj);
		i = st.executeUpdate(qry_closePrj2);
		
		return i;
	}

}
