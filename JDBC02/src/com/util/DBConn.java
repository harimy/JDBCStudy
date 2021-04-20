/*=============================================
   DBConn.java
   - 예외 처리 방법을 try ~ catch → throws
=============================================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	private static Connection dbconn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbconn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.159:1521:xe";
			
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbconn = DriverManager.getConnection(url, user, pwd);
			
		}
		
		return dbconn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbconn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbconn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbconn;
	}
	
	public static void close() throws ClassNotFoundException, SQLException
	{
		if (dbconn != null)
		{
			if (!dbconn.isClosed())
				dbconn.close();
		}
		
		dbconn = null;
	}
}
