package bean;

public class SolInfo {
	private int sol_id;
	private String sol_title;
	private String sol_desc;
	private long bug_id;
	private int status;
	private String sol_date;
	
	public long getBug_id() {
		return bug_id;
	}
	public void setBug_id(long bug_id) {
		this.bug_id = bug_id;
	}
	public String getSol_date() {
		return sol_date;
	}
	public void setSol_date(String sol_date) {
		this.sol_date = sol_date;
	}
	public int getSol_id() {
		return sol_id;
	}
	public void setSol_id(int sol_id) {
		this.sol_id = sol_id;
	}
	public String getSol_title() {
		return sol_title;
	}
	public void setSol_title(String sol_title) {
		this.sol_title = sol_title;
	}
	public String getSol_desc() {
		return sol_desc;
	}
	public void setSol_desc(String sol_desc) {
		this.sol_desc = sol_desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
		
	
}
