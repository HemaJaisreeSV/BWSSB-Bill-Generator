package com.bwssb.water.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil 
{
	static 
	{
		try 
		{
			// Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}

	public static Connection getDBconnection() 
	{
		try 
		{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "Jaisree5120"); // Establishing
				
			return myConn;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
