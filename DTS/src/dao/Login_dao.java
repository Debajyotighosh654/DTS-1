package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.LoginInfo;
import ucon.Uconnection;

public class Login_dao {
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
	
	public int doInsert (LoginInfo loginObj)  {
		String user_name = loginObj.getUser_name();
		String password = loginObj.getPassword();
		String role_name = loginObj.getRole_name();
		getTempConnection();
		
		Statement st = null;
			try {
				st = cn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 0;
			
			String qry_insrt_loginInfo="insert into dts_login(user_name, password, role_name) values ('"+user_name+"','"+password+"','"+role_name+"')";
			try {
				i = st.executeUpdate(qry_insrt_loginInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return i;
	}
	
	public int doUpdate(LoginInfo loginObj) {
		int i = 0;
		Statement st = null;
		
		getTempConnection();
		String user_name = loginObj.getUser_name();
		String password = loginObj.getPassword();
		
		try {
			st = cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String qry_update_loginInfo="update dts_login set password='"+password+"' where user_name='"+user_name+"'";
		
		try {
			i = st.executeUpdate(qry_update_loginInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public int doDelete(String user_name) {
		int i = 0;
		Statement st = null;
		
		getTempConnection();
		
		try {
			st = cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String qry_deleteUserInfo="delete from dts_login where user_name='"+user_name+"'";
		
		try {
			i = st.executeUpdate(qry_deleteUserInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public String doselect (LoginInfo obj) throws SQLException {
		String status = null;
		Statement st = null;
		ResultSet rs = null;
		
		String check_username="select user_name from dts_login " +
				"where user_name='"+obj.getUser_name()+"'";
		String check_password="select password from dts_login " +
				"where user_name='"+obj.getUser_name()+"'";
		String check_role="select role_name from dts_login " +
				"where user_name='"+obj.getUser_name()+"' and password='"+obj.getPassword()+"'";
		
		getTempConnection();
		
		st = cn.createStatement();
		String db_username=null;
		String db_password=null;
		String db_rolename=null;
		
		rs = st.executeQuery(check_username);
		System.out.println(rs);
		
		if(rs!=null) {
			while (rs.next()) {
				db_username = rs.getString("user_name");
			}
			
			if(db_username != null) {
				if(db_username.equals(obj.getUser_name())) {
					
					rs = st.executeQuery(check_password);
					if(rs != null) {
						while(rs.next()) {
							db_password = rs.getString("password");
						}
						
						if(db_password.equals(obj.getPassword())) {
							rs = st.executeQuery(check_role);
							if(rs != null) {
								while(rs.next()) {
									db_rolename = rs.getString("role_name");
								}
								
								if(db_rolename.equals("ADMIN")) {
									status = "ADMIN";
								}
								if(db_rolename.equals("CL")){ status = "CL";	 }
								if(db_rolename.equals("SE")){ status = "SE"; }
								if(db_rolename.equals("SSE")){ status ="SSE"; }
								if(db_rolename.equals("DA")){ status ="DA"; }
								if(db_rolename.equals("SDA")){ status ="SDA"; }
								if(db_rolename.equals("TL")){ status ="TL"; }
								if(db_rolename.equals("PM")){ status ="PM"; }
								if(db_rolename.equals("PPM")){ status ="PPM"; }
								if(db_rolename.equals("DM")){ status ="DM"; }
								if(db_rolename.equals("HRM")){ status ="HRM"; }
								
							}
							else {
								status = "Problem in DataBase";
							}
						}
						else {
							status = "Wrong Password";
						}
					}
					else {
						status = "Problem in DataBase";
					}
				}
				else {
					status = "Wrong UserName";
				}
			}
			else {
				status = "Wrong UserName";
			}
		}
		
		else {
			status = "Problem in DataBase";
		}
		
		return status;
	}
	
	public List doselectAll() {
		List li = new ArrayList();
		
		return li;
	}

}
