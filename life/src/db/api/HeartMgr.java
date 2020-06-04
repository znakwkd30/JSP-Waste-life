package db.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.bean.HeartBean;
import db.bean.MemberBean;
import db.bean.ProjectBean;

public class HeartMgr
{
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3307/map";
	
	public Connection getConnection() {
		Connection con = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			con = DriverManager.getConnection(URL, "root", "111111");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public int firstHeart(String id, int timelineId) {
		try {
			Connection con = getConnection();
			if(con==null) {
				throw new Exception("DB INIT FAILED");
			}
			
			PreparedStatement pstmt = null;
			HeartBean heart = new HeartBean();
			
			String query = "insert into heart(memberId, timelineId) values(?,?);";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, timelineId);
			
			ResultSet rs = pstmt.executeQuery();
			
			return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public int plusHeart(String id, int timelineId) {
		try {
			Connection con = getConnection();
			if(con==null) {
				throw new Exception("DB INIT FAILED");
			}
			
			PreparedStatement pstmt = null;
			HeartBean heart = new HeartBean();
			
			String query = "select * from heart where id=? and timelineId=?;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, timelineId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
			
			return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}
}
