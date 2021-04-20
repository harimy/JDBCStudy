/*=====================
   MemberDAO.java
=====================*/

// Database 에 Access 하는 기능
// → DBConn 활용

// 데이터를 입력하는 기능 → insert

// 인원 수를 확인하는 기능 
// → 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → select

// 전체 리스트 조회하는 기능
// → 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → select

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

public class MemberDAO_harim
{
	// 주요 변수 선언 → DB 연결 객체
	private Connection conn;
	
	// 생성자 정의
	public MemberDAO_harim() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → select 
	public int add(MemberDTO dto) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		conn = DBConn.getConnection();
		
		dto.setSid("MEMBERSEQ.NEXTVAL");
		
		for (int i = 1; ; i++)
		{
			System.out.printf("이름 전화번호 입력(%s) : ", i);
			dto.setName(sc.next());
			
			// 반복의 조건을 무너뜨리는 코드
			if (dto.getName().equals("."))
				break;

			dto.setTel(sc.next());
			
			
			if (conn != null)
			{

				Statement stmt = conn.createStatement();
				String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
						+ " VALUES(%s, '%s', '%s')", dto.getSid(), dto.getName(), dto.getTel());
				
				result = stmt.executeUpdate(sql);
				
				if (result > 0)
					System.out.println("회원 정보 입력 완료~!!!");
			}
		}//end for loop
		
		sc.close();
		DBConn.close();
		
		return result;
		
	}
	
	// 메소드 정의 → 전체 인원수 확인 기능 → select (count) 
	public int count() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		int result=0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
	
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
			result = rs.getInt("COUNT");
			// getInt() → 컬럼명을 주면 int 반환 
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 메소드 정의 → 전체 리스트 조회 기능 → select
	public ArrayList<MemberDTO> lists() throws ClassNotFoundException, SQLException
	{
		ArrayList<MemberDTO> arrList = new ArrayList<MemberDTO>();
		
		conn = DBConn.getConnection();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			arrList.add(dto);
		}
		rs.close();
		DBConn.close();
		
		return arrList;
		
	}
	
	
}































