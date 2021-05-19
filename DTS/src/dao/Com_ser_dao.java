package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Com_ser_fileInfo;
import ucon.Uconnection;

public class Com_ser_dao {
	Uconnection mycon = new Uconnection();
	Connection cn = null;
		
	public void  getTempConnection() throws ClassNotFoundException, SQLException {
			cn = mycon.open();
	}
	
	public int insertNewFile(Com_ser_fileInfo obj) throws ClassNotFoundException, SQLException {
		long file_id = 0;
		String file_desc = null;
		String file_name = null;
		long file_size = 0;
		Statement st = null;
		String user_name = null;
		
		int i=0;
		
		file_id = obj.getFile_id();
		file_name = obj.getFile_name();
		file_size = obj.getFile_size();
		file_desc = obj.getFile_desc();
		user_name = obj.getUser_name();
		
		getTempConnection();
		st = cn.createStatement();
		System.out.println("After creating st");
		String query_file_add = "insert into dts_common_server (file_id, file_name, file_size, file_desc, updated_by) values("+file_id+",'"+file_name+"',"+file_size+",'"+file_desc+"','"+user_name+"')";
		i = st.executeUpdate(query_file_add);
		
		System.out.println("Inserted after query");
		System.out.println("value of i = " + i);
		
		cn.close();
		return i;
	}
	
	public long getNewFileId() throws ClassNotFoundException, SQLException {
		Statement st = null;
		ResultSet rs = null;
		long newId = 0;
		System.out.println("in newMsgId");
		
		getTempConnection();
		st = cn.createStatement();
		String query_new_file_id = "select MAX(file_id) from dts_common_server ";
		rs = st.executeQuery(query_new_file_id);
		
		if(rs != null) {
			while(rs.next()) {
				newId = rs.getInt("MAX(file_id)");
				System.out.println(newId);
			}
		}
		
		cn.close();
		
		newId = newId+1;
		System.out.println("new id returning from fun = " + newId);
		return newId;
	}
	
	public int deleteFile(int file_id) throws ClassNotFoundException, SQLException {
		int i = 0;
		Statement st = null;
		
		System.out.println("in DAO delete file");
		
		getTempConnection();
		st = cn.createStatement();
		String qry_deleteFile = "delete from dts_common_server where file_id="+file_id+"";
		i = st.executeUpdate(qry_deleteFile);

		return i;
	}

}
