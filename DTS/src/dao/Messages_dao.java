package dao;

import java.sql.*;
import java.util.*;

import bean.MsgInfo;
import ucon.Uconnection;

public class Messages_dao {
	
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
	
	
	public List retriveNewMessages(String db_username) throws SQLException {
		List li = null;
		
		String status = null;
		Statement st = null;
		ResultSet rs = null;
		String project_id, project_name;
		String msg_title = null;
		String msg_id = null;
		String msg_desc = null;
		String attach_file_name = null;
		int attach_file_size = 0;
		String from_user = null;
		Timestamp time = null;
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		java.util.Date dt = new java.util.Date();
		dt.setHours(0);
		dt.setMinutes(0);
		dt.setSeconds(0);
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDay = sdf.format(dt);
		System.out.println(currentDay);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		getTempConnection();
		st = cn.createStatement();
		String query_new_msg="select msg_id, msg_title, msg_desc, attach_file_name,attach_file_size, from_user, time from dts_msg where time>'"+currentDay+"' and to_user='"+db_username+"'";
		rs = st.executeQuery(query_new_msg);
		
		if(rs != null) {
			li = new ArrayList();
			while(rs.next()) {
				msg_id = rs.getString("msg_id");
				msg_title = rs.getString("msg_title");
				msg_desc = rs.getString("msg_desc");
				attach_file_name = rs.getString("attch_file_name");
				attach_file_size = rs.getInt("attch_file_size");
				from_user = rs.getString("from_user");
				time = rs.getTimestamp("time");
				
				System.out.println(msg_id);
				System.out.println(msg_title);
				System.out.println(msg_desc);
				System.out.println(attach_file_name);
				System.out.println(attach_file_size);
				System.out.println(from_user);
				
				li.add(msg_id);
				li.add(msg_title);
				li.add(msg_desc);
				li.add(attach_file_name);
				li.add(attach_file_size);
				li.add(from_user);
				li.add(time);
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
	
	
	public List retriveAllMessages(String db_username) throws SQLException {
		List li = null;
		String status = null;
		String project_id, project_name;
		String msg_title = null;
		String msg_id = null;
		String msg_desc = null;
		String attach_file_name = null;
		long attach_file_size = 0;
		String from_user = null;
		Timestamp time = null;
		Statement st = null;
		ResultSet rs = null;
		
		getTempConnection();
		st = cn.createStatement();
		String query_all_msg = "select msg_id, msg_title, msg_desc, attach_file_name,attach_file_size, from_user, time from dts_msg where to_user='"+db_username+"'";
		rs = st.executeQuery(query_all_msg);
		
		if(rs!=null) {
			li=new ArrayList();
			while(rs.next()) {
				msg_id = rs.getString("msg_id");
				msg_title = rs.getString("msg_title");
				msg_desc = rs.getString("msg_desc");
				attach_file_name = rs.getString("attach_file_name");
				attach_file_size = rs.getInt("attach_file_size");
				from_user = rs.getString("from_user");
				time = rs.getTimestamp("time");
				
				System.out.println(msg_id);
				System.out.println(msg_title);
				System.out.println(msg_desc);
				System.out.println(attach_file_name);
				System.out.println(attach_file_size);
				System.out.println(from_user);
				
				li.add(msg_title);
				li.add(msg_desc);
				li.add(attach_file_name);
				li.add(attach_file_size);
				li.add(from_user);
				li.add(time);
			}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		cn.close();
		return li;
	}
	
	
	public int insertNewMsg(MsgInfo obj) throws SQLException {
		String msg_title = null;
		long msg_id = 0;
		String msg_desc = null;
		String attach_file_name = null;
		long attach_file_size = 0;
		String to_user = null;
		String from_user = null;
		int i = 0;
		Statement st = null;
		
		msg_title = obj.getMsg_title();
		msg_desc = obj.getMsg_desc();
		attach_file_name = obj.getAttach_file_name();
		attach_file_size = obj.getAttach_file_size();
		to_user = obj.getTo_user();
		from_user = obj.getFrom_user();
		
		System.out.println(msg_title);
		System.out.println(msg_desc);
		System.out.println(attach_file_name);
		System.out.println(attach_file_size);
		System.out.println(to_user);
		System.out.println(from_user);
		
		msg_id=getNewMsgId();
		System.out.println("msg id go in insert fun is " + msg_id);
		
		getTempConnection();
		st = cn.createStatement();
		System.out.println("After creating st");
		String query_msg_add="insert into dts_msg (msg_id, msg_title, msg_desc, attach_file_name, attach_file_size, from_user, to_user ) values("+msg_id+",'"+msg_title+"','"+msg_desc+"','"+attach_file_name+"',"+attach_file_size+",'"+from_user+"','"+to_user+"')";
		i = st.executeUpdate(query_msg_add);
		
		System.out.println("Inserted after query");
		System.out.println("value of i= "+ i);
		
		cn.close();
		return i;
	}


	private long getNewMsgId() throws SQLException {
		// TODO Auto-generated method stub
		Statement st = null;
		ResultSet rs = null;
		long newId = 0;
		
		System.out.println("in newMsgId");
		
		getTempConnection();
		st = cn.createStatement();
		String query_new_msg_id="select MAX(msg_id) from dts_msg ";
		rs = st.executeQuery(query_new_msg_id);
		
		if(rs != null) {
			while(rs.next()) {
				newId = rs.getInt("MAX(msg_id)");
				System.out.println(newId);
			}
		}
		cn.close();
		newId = newId + 1;
		System.out.println("new id returning from fun= " + newId);
		return newId;
	}

}
