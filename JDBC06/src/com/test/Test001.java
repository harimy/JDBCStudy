/*=========================
   Test001.java
   - 쿼리문 전송 실습 1
=========================*/

package com.test;

import java.sql.Connection;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					/* 1
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '전혜림', '010-2222-2222')";
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					
					stmt.close();
					DBConn.close();
					*/
					
					// 2
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '장서현', '010-3333-3333')";
					
					Statement stmt = conn.createStatement();
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					
					stmt.close();
					DBConn.close();
					
					//-- 위의 1, 2번에서 Statement 와 sql 의 위치를 바꿔서 정의해도 
					//   문제없이 잘 실행이 된다는 것을 확인하였음
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
