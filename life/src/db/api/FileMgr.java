package db.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import db.bean.FileBean;

public class FileMgr {

    ///Square : Function For get Database Connection
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

	public ArrayList<FileBean> getAllProjectFiles(int projectId) {
		try {
			PreparedStatement pstmt = null;
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}
			
			String query = "select * from file where projectId=? order by timestamp DESC;";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, projectId);
			ResultSet rs = pstmt.executeQuery();

			ArrayList<FileBean> res = new ArrayList<FileBean>();
			while(rs.next()) {
				FileBean fb = new FileBean();
				fb.setId(rs.getInt("id"));
				fb.setMemo(rs.getString("memo"));
				fb.setOriginalFileName(rs.getString("originalFilename"));
				fb.setSavedFileName(rs.getString("savedFilename"));
				fb.setUploaderId(rs.getString("uploaderId"));
				fb.setProjectId(rs.getInt("projectId"));
				fb.setTimestamp(rs.getTimestamp("timestamp"));
				
				res.add(fb);
			}
			return res;
		}
		catch(Exception e) {
			return null;
		}
	}

	public FileBean getFileInformation(int fileId) {
		try {
			PreparedStatement pstmt = null;
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}
			
			String query = "select * from file where id=?;";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, fileId);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				FileBean fb = new FileBean();
				fb.setId(rs.getInt("id"));
				fb.setMemo(rs.getString("memo"));
				fb.setOriginalFileName(rs.getString("originalFilename"));
				fb.setProjectId(rs.getInt("projectId"));
				fb.setSavedFileName(rs.getString("savedFilename"));
				fb.setTimestamp(rs.getTimestamp("timestamp"));
				fb.setUploaderId(rs.getString("uploaderId"));
				return fb;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}
	}

	public int uploadFile(FileBean fb) {
		try {
			//INIT CONNECTION
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}

			String query = "insert into file (uploaderId, projectId, originalFilename, memo, savedFilename) values(?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, fb.getUploaderId());
			pstmt.setInt(2, fb.getProjectId());
			pstmt.setString(3, fb.getOriginalFileName());
			pstmt.setString(4, fb.getMemo());
			pstmt.setString(5, fb.getSavedFileName());

			//EXEC Update
			pstmt.executeUpdate();
			
			return 0;
		}
		catch(Exception e) {
			return -1;
		}
	}

	public int deleteFile(int fileId) {
		try {
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}
			
			String query = "delete from file where id=?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, fileId);
			pstmt.executeUpdate();
			return 0;
		}
		catch(Exception e) {
			return -1;
		}
	}

	public ArrayList<FileBean> searchFile(String str) {
		try {
			Connection con = getConnection();
			if(con == null) {
				throw new Exception("DB INIT FAILED");
			}
			
			String query = "select * from file where uploaderId=? or memo=? or originalFilename=?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, str);
			pstmt.setString(2, str);
			pstmt.setString(3, str);
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<FileBean> res = new ArrayList<FileBean>();

			while(rs.next()) {
				FileBean fb = new FileBean();
				fb.setId(rs.getInt("id"));
				fb.setMemo(rs.getString("memo"));
				fb.setOriginalFileName(rs.getString("originalFilename"));
				fb.setProjectId(rs.getInt("projectId"));
				fb.setSavedFileName(rs.getString("savedfilename"));
				fb.setTimestamp(rs.getTimestamp("timestamp"));
				fb.setMemo("memo");
				res.add(fb);
			}
			return res;
		}
		catch(Exception e) {
			return null;
		}
	}
}
