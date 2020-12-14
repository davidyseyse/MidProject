package tw.team4.jspproject.javabean;

public class QueryBean {
	
	private String queryStr;
	private String type;
	
	public QueryBean(String queryStr, String type) {
		super();
		this.queryStr = queryStr;
		this.type = type;
	}
	
	public QueryBean() {
		
	}
	
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}