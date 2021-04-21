package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	public ScoreDAO() throws ClassNotFoundException
	{
		conn = DBConn.getConnection();
	}
	
	public int add(ScoreDTO dto) throws SQLException
	{
		// 결과값 담을 변수 (적용된 행의 개수)
		int result = 0;
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문 
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
								 + " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
								 , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 작업 객체를 활용하여 쿼리문 전달 
		result = stmt.executeUpdate(sql);
		
		// 리소스 반납
		stmt.close();
		
		// 결과값 반환 (적용된 행의 개수)
		return result;
	}
	
	public int count() throws SQLException
	{
		// 결과값 변수 
		int result = 0;
		
		// 작업 객체 
		Statement stmt = conn.createStatement();
		
		// 쿼리문
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 작업 객체를 활용하여 쿼리문 전달
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet에 값이 들어있으면 int 값을 받아와서 result 변수에 담음 
		if (rs.next())
			result = rs.getInt("COUNT");
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 결과값 반환
		return result;
	}
	
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 결과값 변수 
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문
		String sql = "SELECT SID, NAME, KOR, ENG, MAT FROM TBL_SCORE ORDER BY SID";
		
		// 작업 객체를 활용하여 쿼리문 전달
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		return result;
	}
	
}
















































