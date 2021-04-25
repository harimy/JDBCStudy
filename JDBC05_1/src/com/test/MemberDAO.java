package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	private Connection conn;
	
	MemberDAO()
	{
		conn = DBConn.getConnection();
	}
	
	// 직원 입력 메소드
	public int add(MemberDTO dto) throws SQLException
	{
		int result;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_EMP(EMP_ID, EMP_NAME, SSN"
				+ ", IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
				+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD')"
				+ ", (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC='%s')"
				+ ", '%s'"
				+ ", (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
				+ ", (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
				+ ", %d"
				+ ", %d )"
				, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel()
				, dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력 메소드
	public ArrayList<MemberDTO> lists() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM EMPVIEW";
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_LOC"));
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			
			result.add(dto);
		}
		stmt.close();
		rs.close();
		
		return result;
	}
	
	// 직원 전체 출력 메소드 
	public ArrayList<MemberDTO> lists(int sel) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM EMPVIEW";
		
		switch (sel)
		{
		case 1: sql += " ORDER BY EMP_ID";
			break;
		case 2: sql += " ORDER BY EMP_NAME";
			break;
		case 3: sql += " ORDER BY BUSEO_NAME";
			break;
		case 4: sql += " ORDER BY JIKWI_NAME";
			break;
		case 5: sql += " ORDER BY PAY DESC";
			break;
		}
	
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_LOC"));
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			
			result.add(dto);
		}
		stmt.close();
		rs.close();
		
		return result;
	}
	
	
	// 직원 검색 출력 메소드 
	public ArrayList<MemberDTO> search(int sel, String val) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM EMPVIEW";
		
		switch (sel)
		{
		case 1: sql += String.format(" WHERE EMP_ID = %d", Integer.parseInt(val));
			break;
		case 2: sql += String.format(" WHERE EMP_NAME = '%s'", val);
			break;
		case 3: sql += String.format(" WHERE BUSEO_NAME = '%s'", val);
			break;
		case 4: sql += String.format(" WHERE JIKWI_NAME = '%s'", val);
			break;
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_LOC"));
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			
			result.add(dto);
		}
		stmt.close();
		rs.close();
		
		return result;
	}
	
	
	// 직원 수 출력
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
			result = rs.getInt("COUNT");

		return result;
	}
	
	
	// 직원 정보 수정 메소드 
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME = '%s'"
				+ ", SSN = '%s', IBSADATE = TO_DATE('%s', 'YYYY-MM-DD')"
				+ ", CITY_ID = (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC='%s')"
				+ ", TEL = '%s'"
				+ ", BUSEO_ID = (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
				+ ", JIKWI_ID = (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
				+ ", BASICPAY = %d"
				+ ", SUDANG = %d"
				+ " WHERE EMP_ID = %d"
				, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel()
				, dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getId());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	
	// 직원 정보 삭제 메소드 
	public int remove(int id) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", id);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	// 지역 리스트 불러오기
	public ArrayList<String> searchCity()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		
		return result;
	}
	
	// 부서 리스트 불러오기
	public ArrayList<String> searchBuseo()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		return result;
	}	
	// 직위 리스트 불러오기
	public ArrayList<String> searchJikwi()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		return result;
	}
	
	// 최소 기본급 불러오기 
	public int searchBasicPay()
	{
		int result = 0;
		
		return result;
	}
	

}
