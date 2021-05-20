package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.LoginInfo;
import bean.UserInfo;

import ucon.Uconnection;

public class User_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	
	public String getNewId(String role_name){
		String new_id=null;
		
		Statement st=null;
		ResultSet rs=null;
		String st_role=null;
		String query_get_new_id=null;
		if(role_name.equals("ADMIN")){
			st_role="ADM";
			query_get_new_id="SELECT CONCAT('PM"+st_role+"',LPAD(MAX(SUBSTR(user_name,6,10))+1,5,0)) as new_id from dts_login where role_name='"+role_name+"'";
		}
		else if(role_name.equals("SE")||role_name.equals("SSE")||role_name.equals("PM")||role_name.equals("PPM")
				
				||role_name.equals("DA")||role_name.equals("SDA")||role_name.equals("TL")
				||role_name.equals("DM")||role_name.equals("HRM")){
			st_role="EMP";
			query_get_new_id="SELECT CONCAT('PM"+st_role+"',LPAD(MAX(SUBSTR(user_name,6,10))+1,5,0)) as new_id from dts_login where role_name not in('ADMIN','CL')";
		}
		else if(role_name.equals("CL")){
			st_role="CLT";
			query_get_new_id="SELECT CONCAT('PM"+st_role+"',LPAD(MAX(SUBSTR(user_name,6,10))+1,5,0)) as new_id from dts_login where role_name='"+role_name+"'";
		}
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			rs=st.executeQuery(query_get_new_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			try {
				while(rs.next()){
					new_id=rs.getString("new_id");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new_id;
				
	}
	public int insertNewUser(UserInfo userObj){
		int i=0;
		Statement st=null;
		long id=0;
		
		
		id=getNewIdForUser();
		String user_name=userObj.getUser_name();
		String first_name=userObj.getFirst_name();
		String middle_name=userObj.getMiddle_name();
		String last_name=userObj.getLast_name();
		String email=userObj.getEmail();
		String phone_no=userObj.getPhone_no();
		String alternate_no=userObj.getAlternate_no();
		String emergency_no=userObj.getEmergency_no();
		String perm_resd_add=userObj.getPerm_resd_add();
		String curr_resd_add=userObj.getCurr_resd_add();
		String perm_wrk_add=userObj.getPerm_wrk_add();
		String curr_wrk_add=userObj.getCurr_wrk_add();
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_insertUserInfo="insert into dts_emp_details (id,user_name, first_name, middle_name, last_name, email, phone_no, alternate_no, " +
				"emergency_no,perm_resd_add,curr_resd_add, perm_wrk_add,curr_wrk_add )" +
				"values("+id+",'"+user_name+"','"+first_name+"','"+middle_name+"','"+last_name+"','"+email+"',"+
				phone_no+","+alternate_no+","+emergency_no+",'"+
				perm_resd_add+"','"+curr_resd_add+"','"+perm_wrk_add+"','"+curr_wrk_add+"')";
		
		
		try {
			i=st.executeUpdate(qry_insertUserInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public long getNewIdForUser(){
		long id=0;
		
		Statement st=null;
		ResultSet rs=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String qry_getMaxId="select MAX(id) as id from dts_emp_details";
		
		try {
			rs=st.executeQuery(qry_getMaxId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			try {
				while(rs.next()){
					id=rs.getInt("id");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		id=id+1;
		return id;
	}
	public List getUserInfoEdit(String user_name){
		
		List li=null;
		
		String qry_collectLoginData="select password from dts_login where user_name='"+user_name+"'";
		String qry_collectUserData="select first_name, middle_name, last_name, email, phone_no,alternate_no, emergency_no,perm_resd_add,curr_resd_add, perm_wrk_add, curr_wrk_add from dts_emp_details where user_name='"+user_name+"'";
		
		Statement st=null;
		ResultSet rs=null;
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs=st.executeQuery(qry_collectLoginData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		li=new ArrayList();
		li.add(user_name);
		if(rs!=null){
			try {
				while(rs.next()){
					String password=rs.getString("password");
					li.add(password);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			rs=st.executeQuery(qry_collectUserData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			try {
				while(rs.next()){
					String first_name=rs.getString("first_name");
					String middle_name=rs.getString("middle_name");
					String last_name=rs.getString("last_name");
					String email=rs.getString("email");
					String phone_no=rs.getString("phone_no");
					String alternate_no=rs.getString("alternate_no");
					String emergency_no=rs.getString("emergency_no");
					String perm_resd_add=rs.getString("perm_resd_add");
					String curr_resd_add=rs.getString("curr_resd_add");
					String perm_wrk_add=rs.getString("perm_wrk_add");
					String curr_wrk_add=rs.getString("curr_wrk_add");
					
					
					li.add(first_name);
					li.add(middle_name);
					li.add(last_name);
					li.add(email);
					li.add(phone_no);
					li.add(alternate_no);
					li.add(emergency_no);
					li.add(perm_resd_add);
					li.add(curr_resd_add);
					li.add(perm_wrk_add);
					li.add(curr_wrk_add);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return li;
	}

	public int updateUser(UserInfo userObj) {
		
		int i=0;
		Statement st=null;
		
		getTempConnection();
		// TODO Auto-generated method stub
		String user_name=userObj.getUser_name();
		String first_name=userObj.getFirst_name();
		String middle_name=userObj.getMiddle_name();
		String last_name=userObj.getLast_name();
		String email=userObj.getEmail();
		String phone_no=userObj.getPhone_no();
		String alternate_no=userObj.getAlternate_no();
		String emergency_no=userObj.getEmergency_no();
		String perm_resd_add=userObj.getPerm_resd_add();
		String curr_resd_add=userObj.getCurr_resd_add();
		String perm_wrk_add=userObj.getPerm_wrk_add();
		String curr_wrk_add=userObj.getCurr_wrk_add();
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_updateUserInfo="update dts_emp_details set first_name=+'"+first_name+"', middle_name='"+middle_name+"', last_name='"+last_name+"', email='"+email+"', phone_no="+phone_no+", alternate_no="+phone_no+", " +
				"emergency_no="+emergency_no+",alternate_no="+alternate_no+",perm_resd_add='"+perm_resd_add+"',curr_resd_add='"+curr_resd_add+"', perm_wrk_add='"+perm_wrk_add+"',curr_wrk_add= '"+curr_wrk_add+"'" +
				"where user_name='"+user_name+"'";
				
		
		try {
			i=st.executeUpdate(qry_updateUserInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int deleteUser(String user_name) {
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
		String qry_deleteUserInfo="delete from dts_emp_details where user_name='"+user_name+"'";
		
		try {
			i=st.executeUpdate(qry_deleteUserInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
