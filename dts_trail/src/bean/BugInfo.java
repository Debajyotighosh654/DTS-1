package bean;

public class BugInfo {
	private int bug_id;
	private String bug_title;
	private String bug_desc;
	private String start_date;
	private String end_date;
	private String bug_to;
	private String bug_from;
	private String project_id;
	private int status;
	private String priority;
	public int getBug_id() {
		return bug_id;
	}
	public void setBug_id(int bug_id) {
		this.bug_id = bug_id;
	}
	public String getBug_title() {
		return bug_title;
	}
	public void setBug_title(String bug_title) {
		this.bug_title = bug_title;
	}
	public String getBug_desc() {
		return bug_desc;
	}
	public void setBug_desc(String bug_desc) {
		this.bug_desc = bug_desc;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getBug_to() {
		return bug_to;
	}
	public void setBug_to(String bug_to) {
		this.bug_to = bug_to;
	}
	public String getBug_from() {
		return bug_from;
	}
	public void setBug_from(String bug_from) {
		this.bug_from = bug_from;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
