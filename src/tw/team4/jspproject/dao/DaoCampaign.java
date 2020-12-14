package tw.team4.jspproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.team4.jspproject.javabean.Campaign;
import tw.team4.jspproject.javabean.QueryBean;

public class DaoCampaign {
	private Connection conn;
	
	//建立連線
	public boolean createConnection() throws NamingException, SQLException {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/connSQLServerAzure");		
		conn = ds.getConnection();
		if (!conn.isClosed()){
			return true;
		}
		return false;
	}
	
	//新增活動
	public void insert(Campaign camp) throws SQLException {
		String sqlstr = "insert into campaign(Title,Description,StartTime,EndTime,Type,Status)"
				+ "values(?,?,?,?,?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1,camp.getTitle());
		state.setString(2,camp.getDescription());
		state.setTimestamp(3, camp.getStartTime());
		state.setTimestamp(4, camp.getEndTime());
		state.setInt(5,camp.getType());
		state.setBoolean(6,camp.getStatus());
		state.execute();
		
		state.close();
	}
	
	//原先用來顯示所有活動的，目前使用query()替代
	public ArrayList<Campaign> queryAll() throws SQLException {
	
		ArrayList<Campaign> list = new ArrayList<Campaign>();
		
		String sqlstr = "select * from campaign";
		
		PreparedStatement state = conn.prepareStatement(sqlstr);
		ResultSet res = state.executeQuery();

		while(res.next()) {
			int id = res.getInt("Id");
			String title = res.getString("Title");
			String description = res.getString("Description");
			Timestamp startTime = res.getTimestamp("StartTime");
			Timestamp endTime = res.getTimestamp("EndTime");
			int type = res.getInt("Type");	
			boolean status = res.getBoolean("Status");
			Campaign camp = new Campaign(id,title,description,startTime,endTime,status);
			list.add(camp);	
		}
		
		state.close();
		
		return list;
	}
	
	//查詢活動、顯示所有活動
	public ArrayList<Campaign> query(QueryBean query) throws SQLException {
		
		ArrayList<Campaign> list = new ArrayList<Campaign>();
		
		//如果查詢輸入為空，則不執行sql查詢，並回傳null
		if(!query.getQueryStr().equals("")) {
			String sqlstr="select * from Campaign where Title  like ? or Description like ?";
			
			//獲取查看類型，依類型給予不同的sql語法
			if(query.getType().equals("title")) {
				//依標題查詢
				sqlstr = "select * from Campaign where Title like ? ";
			}else if(query.getType().equals("description")	) {
				//依內容查詢
				sqlstr = "select * from Campaign where Description like ? ";
			}else if(query.getType().equals("showAll")){
				//查詢所有活動
				sqlstr = "select * from Campaign";
			}
			
			PreparedStatement state = conn.prepareStatement(sqlstr);
			
			//如果查詢類型是標題或內容設定一個參數
			if(query.getType().equals("title") || query.getType().equals("description")) {
				state.setString(1,"%" + query.getQueryStr() + "%"); 
			}
						
			//如果查詢類型是所有則設定兩個參數
			if(query.getType().equals("all")) {
				state.setString(1,"%" + query.getQueryStr() + "%"); 
				state.setString(2,"%" + query.getQueryStr() + "%");
			}
			
			ResultSet res = state.executeQuery();
			while(res.next()) {
				
				String title = res.getString("title");
				String description = res.getString("description");
				Timestamp startTime = res.getTimestamp("startTime");
				Timestamp endTime = res.getTimestamp("endTime");
				int id = res.getInt("id");
				boolean status = res.getBoolean("status");
				Campaign camp = new Campaign(id,title,description,startTime,endTime,status);
				list.add(camp);	
				
			}
			
			state.close();
			return list;
		}else {
			return null;
		}
	}
	
	//藉由id查詢資料，回傳一個bean
	public Campaign queryById(int QueryId) throws SQLException {
		String sqlstr = "select * from where id = ?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1,QueryId); 
		
		ResultSet res = state.executeQuery();
		
		if(res.next()) {
			String title = res.getString("title");
			String description = res.getString("description");
			Timestamp startTime = res.getTimestamp("startTime");
			Timestamp endTime = res.getTimestamp("endTime");
			int id = res.getInt("id");
			boolean status = res.getBoolean("status");
			Campaign camp = new Campaign(id,title,description,startTime,endTime,status);
			state.close();
			return camp;
		}
		state.close();
		return null;		
	}
	
	//刪除活動
	public int delete(int id) throws SQLException {
		
		String sqlstr = "delete from Campaign where id = ?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		int affectedRow = state.executeUpdate();
		
		state.close();
		
		return affectedRow;
	}
	
	
	public void close() throws SQLException {
		if(conn!=null) {
			conn.close();
		}
	}
	
}
