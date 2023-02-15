package jukebox.dbconfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
	
	static Connection con;
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException
	{
//		loading drivers
		Class.forName("com.mysql.cj.jdbc.Driver");
//		making a connection with Database
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","A@shish2207");
		
		return con;
	}

}
