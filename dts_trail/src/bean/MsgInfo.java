package bean;

public class MsgInfo {

	 String msg_title;
	 String msg_desc;
	 String attch_file_name;
	 long attch_file_size;
	 String from_user;
	 String to_user;
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public String getMsg_desc() {
		return msg_desc;
	}
	public void setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
	}
	public String getAttch_file_name() {
		return attch_file_name;
	}
	public void setAttch_file_name(String attch_file_name) {
		this.attch_file_name = attch_file_name;
	}
	
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public long getAttch_file_size() {
		return attch_file_size;
	}
	public void setAttch_file_size(long attch_file_size) {
		this.attch_file_size = attch_file_size;
	}
}
