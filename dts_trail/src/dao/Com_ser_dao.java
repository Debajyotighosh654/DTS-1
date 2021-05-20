package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ucon.Uconnection;

import bean.Com_ser_fileInfo;

public class Com_ser_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	

	public int insertNewFile(Com_ser_fileInfo obj) {
		// TODO Auto-generated method stub

		long file_id=0;
		String file_desc=null;
		String file_name=null;
		long file_size=0;
		Statement st=null;
		String user_name=null;
		//ResultSet rs=null;
		int i=0;
		
		file_id=obj.getFile_id();
		file_name=obj.getFile_name();
		file_size=obj.getFile_size();
		file_desc=obj.getFile_desc();
		user_name=obj.getUser_name();
		//generate new msg id
		
		//generate timestamp
//		java.util.Date dt = new java.util.Date();
//		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time = sdf.format(dt);
//		System.out.println(time);
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("After creating st");
		String query_file_add="insert into dts_common_server (file_id, file_name, file_size, file_desc, updated_by) values("+file_id+",'"+file_name+"',"+file_size+",'"+file_desc+"','"+user_name+"')";
		
		try {
			i=st.executeUpdate(query_file_add);
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
	public long getNewFileId()  {
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
		String query_new_file_id="select MAX(file_id) from dts_common_server ";
		try {
			rs=st.executeQuery(query_new_file_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null){
			
			try {
				while(rs.next()){
					newId=rs.getInt("MAX(file_id)");
					
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
	public int deleteFile(int file_id) {
		// TODO Auto-generated method stub
		int i=0;
		Statement st=null;
		
		System.out.println("in DAO delete file");
		
		getTempConnection();
		try {
			st= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry_deleteFile="delete from dts_common_server where file_id="+file_id+"";
		
		
		try {
			i=st.executeUpdate(qry_deleteFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
