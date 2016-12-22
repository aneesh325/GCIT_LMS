package com.gcit.lms.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	
	public static Connection openConnection() throws SQLException
	{	
		
		String db ="jdbc:mysql://localhost/library";
	  String user = "root";
	   String pass = "gandalf325";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	Connection	conn = DriverManager.getConnection(db, user, pass);
		return conn;
	 
	}		
	
}
