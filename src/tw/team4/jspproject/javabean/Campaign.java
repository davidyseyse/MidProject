package tw.team4.jspproject.javabean;

import java.sql.Timestamp;

public class Campaign {
	private int id;
	private String title;
	private String description;
	private Timestamp startTime;
	private Timestamp endTime;
	private boolean status;
	private int type;
	
	public Campaign(String title, String description, Timestamp startTime, Timestamp endTime, boolean status) 
	{
		super();
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;

	}
	
	public Campaign(int id,String title, String description, Timestamp startTime, Timestamp endTime, boolean status) 
	{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;

	}
	
	
	public Campaign() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
