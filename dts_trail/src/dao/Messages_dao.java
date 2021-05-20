package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bean.MsgInfo;

import ucon.Uconnection;

public class Messages_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	public List  retriveNewMessages(String db_username) throws SQLException{
		List li=null;
		String status= null;
		Statement st=null;
		ResultSet rs=null;
		String project_id, project_name;
		String msg_title=null;
		String msg_id=null;
		String msg_desc=null;
		String attch_file_name=null;
		int attch_file_size=0;
		String from_user=null;
		Timestamp time=null;
		getTempConnection();
		st= cn.createStatement();
	//Date date = resultSet.getTimestamp("columnname");
		//String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		java.util.Date dt = new java.util.Date();
		dt.setHours(0);
		dt.setMinutes(0);
		dt.setSeconds(0);
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDay = sdf.format(dt);
		System.out.println(currentDay);
		
	
		String query_new_msg="select msg_id, msg_title, msg_desc, attach_file_name,attach_file_size, from_user, time from dts_msg where time>'"+currentDay+"' and to_user='"+db_username+"'";
		
		rs=st.executeQuery(query_new_msg);
		if(rs!=null){
			li=new ArrayList();
			while(rs.next()){
				msg_id=rs.getString("msg_id");
				msg_title=rs.getString("msg_title");
				msg_desc=rs.getString("msg_desc");
				attch_file_name=rs.getString("attch_file_name");
				attch_file_size=rs.getInt("attch_file_size");
				from_user=rs.getString("from_user");
				time=rs.getTimestamp("time");
				
				System.out.println(msg_id);
				System.out.println(msg_title);
				System.out.println(msg_desc);
				System.out.println(attch_file_name);
				System.out.println(attch_file_size);
				System.out.println(from_user);
				li.add(msg_title);
				li.add(msg_desc);
				li.add(attch_file_name);
				li.add(attch_file_size);
				li.add(from_user);
				li.add(time);
				
				}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		cn.close();
		return li;
		
	}
	public List retriveAllMessages(String db_username) throws SQLException {
		// TODO Auto-generated method stub
		List li=null;
		String status= null;
		Statement st=null;
		ResultSet rs=null;
		String project_id, project_name;
		String msg_title=null;
		String msg_id=null;
		String msg_desc=null;
		String attch_file_name=null;
		long attch_file_size=0;
		String from_user=null;
		Timestamp time=null;
		getTempConnection();
		st= cn.createStatement();
		String query_all_msg="select msg_id, msg_title, msg_desc, attch_file_name,attch_file_size, from_user, time from dts_msg where to_user='"+db_username+"'";
		
		rs=st.executeQuery(query_all_msg);
		if(rs!=null){
			li=new ArrayList();
			while(rs.next()){
				msg_id=rs.getString("msg_id");
				msg_title=rs.getString("msg_title");
				msg_desc=rs.getString("msg_desc");
				attch_file_name=rs.getString("attch_file_name");
				attch_file_size=rs.getInt("attch_file_size");
				from_user=rs.getString("from_user");
				time=rs.getTimestamp("time");
				
				System.out.println(msg_id);
				System.out.println(msg_title);
				System.out.println(msg_desc);
				System.out.println(attch_file_name);
				System.out.println(attch_file_size);
				System.out.println(from_user);
				li.add(msg_title);
				li.add(msg_desc);
				li.add(attch_file_name);
				li.add(attch_file_size);
				li.add(from_user);
				li.add(time);
				
				}
		}
		System.out.println("Through list...");
		Iterator it=li.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		cn.close();
		return li;
	}
	public int insertNewMsg(MsgInfo obj) {
		// TODO Auto-generated method stub
		
		String msg_title=null;
		long msg_id=0;
		String msg_desc=null;
		String attch_file_name=null;
		long attch_file_size=0;
		String to_user=null;
		String from_user=null;
		
		
		
		Statement st=null;
		//ResultSet rs=null;
		int i=0;
		
		msg_title=obj.getMsg_title();
		msg_desc=obj.getMsg_desc();
		attch_file_name=obj.getAttch_file_name();
		attch_file_size=obj.getAttch_file_size();
		to_user=obj.getTo_user();
		from_user=obj.getFrom_user();
		
		
		System.out.println(msg_title);
		System.out.println(msg_desc);
		System.out.println(attch_file_name);
		System.out.println(attch_file_size);
		System.out.println(to_user);
		System.out.println(from_user);
		//generate new msg id
		
		msg_id=getNewMsgId();
		System.out.println("msg id go in inset fun is"+msg_id);
		//generate timestamp
	//	java.util.Date dt = new java.util.Date();
	//	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	String time = sdf.format(dt);
	//	System.out.println(time);
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("After creating st");
		String query_msg_add="insert into dts_msg (msg_id, msg_title, msg_desc, attch_file_name, attch_file_size, from_user, to_user ) values("+msg_id+",'"+msg_title+"','"+msg_desc+"','"+attch_file_name+"',"+attch_file_size+",'"+from_user+"','"+to_user+"')";
		
		try {
			i=st.executeUpdate(query_msg_add);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Inserted after query");
		System.out.println("value of i="+i);
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	private long getNewMsgId()  {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
		long newId=0;
		System.out.println("in newMsgId");
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query_new_msg_id="select MAX(msg_id) from dts_msg ";
		try {
			rs=st.executeQuery(query_new_msg_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			
			try {
				while(rs.next()){
					newId=rs.getInt("MAX(msg_id)");
					
					System.out.println(newId);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newId=newId+1;
		System.out.println("new id returning from fun="+newId);
		return newId;
	}
}
