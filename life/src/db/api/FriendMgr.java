package db.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.*;

public class FriendMgr
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
	
	public int checkFriendExists(String Id, String friendId) {
		try {
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}
			
			PreparedStatement pstmt = null;
			String query = "select * from friend where id=? and friend_id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);
			pstmt.setString(2, friendId);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs==null || rs.next() == false) {
				//Available
				return 0;
			}
			else {
				//Using
				return 1;
			}
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	public ArrayList<FriendBean> getFriendList(String id) {
		try {
			Connection con = getConnection();	
			if(con ==null) {
				System.out.println("getConnection 오류");
			}
			
			PreparedStatement pstmt = null;
			ArrayList<FriendBean> friends = new ArrayList<FriendBean>();
			String query = "select friend_id from friend where id=?;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs == null) {
				System.out.println("팔로우 없음");
			}
			
			while(rs.next()) {
				FriendBean friend = new FriendBean();
				friend.setFriendId(rs.getString("friend_id"));
				
				friends.add(friend);
			}
		
			return friends;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<FriendBean> getMyList(String friendId) {
		try {
			Connection con = getConnection();	
			if(con ==null) {
				System.out.println("getConnection 오류");
			}
			
			PreparedStatement pstmt = null;
			ArrayList<FriendBean> friends = new ArrayList<FriendBean>();
			String query = "select id from friend where friend_id=?;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, friendId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs == null) {
				System.out.println("팔로잉 없음");
			}
			
			while(rs.next()) {
				FriendBean friend = new FriendBean();
				friend.setId(rs.getString("id"));
				
				friends.add(friend);
			}
		
			return friends;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String addFriend(String id, String friendId) throws Exception{
		Connection con = getConnection();	
		if(con ==null) {
			System.out.println("getConnection 오류");
		}
		
		PreparedStatement pstmt = null;
		String query = "insert into friend(id,friend_id) values(?,?);";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setString(2, friendId);
		
		pstmt.executeQuery();
		
		return id;
		
	}
	
	
	
	public int removeFriend(String friendId) throws Exception{
		Connection con = getConnection();	
		if(con ==null) {
			System.out.println("getConnection 오류");
		}
		
		PreparedStatement pstmt = null;
		String query = "delete from friend where friend_id=?;";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, friendId);
		pstmt.executeUpdate();
		
		return 0;
	}
}
