
package com.blocker.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class DBconnect {

	private DBconnect() {
	}
	/**
	 *@return  String success/error
	 *
	 *@throws  Exception when retrieving user details from database
	 *
	 */
	public static Connection getConnection() throws Exception {
		try {
			FileInputStream fis = new FileInputStream("C:/Users/singarapuroopa/Desktop/dbproject/RBlocker/src/com/blocker/connection/Flexible.properties");
			System.out.println("prop set");
		 Properties prop = new Properties();
		 prop.load(fis);
		Class.forName(prop.getProperty("drivers"));
		System.out.println("before Connect");
		Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("userName"), prop.getProperty("password"));
		System.out.println("after Connect");
		return connection;
		} catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
}
