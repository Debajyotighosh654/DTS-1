package bean;

public class FileInfo {
	private long file_id;
	private String file_name;
	private long file_size;
	private String file_desc;
	private long bug_id;
	
	public long getBug_id() {
		return bug_id;
	}
	public void setBug_id(long bug_id) {
		this.bug_id = bug_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_desc() {
		return file_desc;
	}
	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}
	public long getFile_id() {
		return file_id;
	}
	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}
	
}
