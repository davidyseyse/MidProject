package tw.team4.jspproject.javabean;

public class User {
	private int id;
	private String userId;
	private String userPassword;
	private String name;
	
	public User(int id, String userId, String userPassword, String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.userPassword = userPassword;
		this.name = name;
	}
	
	public User(String userId, String userPassword, String name) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.name = name;
	}
	
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}	
