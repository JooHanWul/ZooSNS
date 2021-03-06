package com.pinguin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pinguin.Database.DBconn;
import com.pinguin.dto.pinguinDto;

public class pinguinDao {

	DBconn dbconn = DBconn.getInstance();

	private pinguinDto dto = null;

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	static ArrayList<String> fList = null;

	String sql;

	// 로그인 체크
	public int login(String uId, String uPw) {
		int check = 0;
		try {
			conn = dbconn.getConn();
			stmt = conn.createStatement();
			sql = "select * from custom where id='" + uId + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = null;
				if (id.equals(uId)) {
					check = 1;
					sql = "select * from custom where id='" + uId + "' and pw=MD5_CRIPT('" + uPw + "')";
					// System.out.println(sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						pw = rs.getString("pw");
					}
					System.out.println(pw);
					if (pw == null) {
						check = -1;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt, rs);
		}
		System.out.println("로그인 체크: " + check);
		return check;
	}

	public pinguinDto userInfo(String uId, String uPw) {
		String id, pw, name, gender, birth, profileImg;
		int no;
		try {
			conn = dbconn.getConn();
			stmt = conn.createStatement();
			sql = "select * from custom where id='" + uId + "' and pw=MD5_CRIPT('" + uPw + "')";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("id");
				pw = rs.getString("pw");
				name = rs.getString("name");
				gender = rs.getString("gender");
				birth = rs.getString("birth");
				no = rs.getInt("no");
				profileImg = rs.getString("profileImg");
				dto = new pinguinDto(id, pw, name, gender, birth, no, profileImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt, rs);
		}

		return dto;
	}

	// 회원가입
	public boolean join(String id, String pw, String name, String birth, String gender) {
		boolean check = false;
		try {
			conn = dbconn.getConn();
			sql = "insert into custom(id, pw, name, birth, gender, no) values(?,MD5_CRIPT(?),?,?,?,MEMNO.NEXTVAL)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, gender);
			int r = pstmt.executeUpdate();
			if (r > 0) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt);
		}
		return check;
	}

	public void updateProfile(String profileImg, String id) {
		try {
			conn = dbconn.getConn();
			sql = "update custom set PROFILEIMG=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profileImg);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt);
		}
	}

	public ArrayList<String[]> freundList(String uId) {
		ArrayList<String[]> freundList = new ArrayList<>();
		String[] IDnName = null;
		
		try {
			conn = dbconn.getConn();
			sql = "select id, name,PROFILEIMG from custom";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				IDnName = new String[3];
				IDnName[0] = rs.getString(1);
				IDnName[1] = rs.getString(2);
				IDnName[2] = rs.getString(3);
				freundList.add(IDnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt);
		}
		
		return freundList;
	}
	
	public String[] freundInfo(String id) {
		String[] f = new String[2];
		
		try {
			conn = dbconn.getConn();
			sql = "select name, PROFILEIMG from custom where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				f[0] = rs.getString(1);
				f[1] = rs.getString(2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.close(conn, pstmt);
		}
		
		return f;
	}

	public boolean update(String id, String pw, String newPw, String name) {
		boolean check = true;
		
		try {
			conn = dbconn.getConn();
			sql = "update custom set pw=MD5_CRIPT(?), name=? where id=? and MD5_CRIPT(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, name);
			pstmt.setString(3, id);
			pstmt.setString(4, pw);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		} finally {
			dbconn.close(conn, pstmt);
		}
		return check;
	}

}