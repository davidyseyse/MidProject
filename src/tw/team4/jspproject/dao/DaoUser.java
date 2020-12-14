package tw.team4.jspproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.team4.jspproject.javabean.User;

public class DaoUser {
	private Connection conn;
	
	public boolean createConnection() throws NamingException, SQLException {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/connSQLServerAzure");		
		conn = ds.getConnection();
		if (!conn.isClosed()){
			return true;
		}
		return false;
	}
	
	public User queryUser(String userId, String userPassword) throws SQLException {
		
		String sql = "select * from Users where userId=? and userPassword=?";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setString(1,userId);
		state.setString(2,userPassword);
		ResultSet rs = state.executeQuery();
		if (rs.next()) {
			
			int id = rs.getInt("id");
			String name = rs.getString("userName");
			User u = new User(id,userId,userPassword,name);
			return u;
			
		}
			
		return null;
		
	}
	
	public void close() throws SQLException {
		if(conn!=null) {
			conn.close();
		}else {
			System.out.println("no connection");
		}
	}
}
