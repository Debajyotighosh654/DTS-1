package bean;

public class MsgInfo {
	
	String msg_title;
	String msg_desc;
	String attach_file_name;
	long attach_file_size;
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
	public String getAttach_file_name() {
		return attach_file_name;
	}
	public void setAttach_file_name(String attach_file_name) {
		this.attach_file_name = attach_file_name;
	}
	public long getAttach_file_size() {
		return attach_file_size;
	}
	public void setAttach_file_size(long attach_file_size) {
		this.attach_file_size = attach_file_size;
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
	
}
