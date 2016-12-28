
package com.blocker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.blocker.action.DateConvert;
import com.blocker.bean.UserBean;
import com.blocker.connection.DBconnect;

/**
 * This action class allows the user to access the application.
 */
public class UserDAO {
	private Connection connection;
	/**
	   * this method is used to reserve the resources.
	   * @return the string success/invalid/failure/input
	   * @param beanObj to set
	   */
	public final String userDAO(final UserBean beanObj)  {

		try {
					connection = DBconnect.getConnection();
					String n = beanObj.getGroupID();
					PreparedStatement preparedStatement = connection
							.prepareStatement("insert into reservation_details(resourceId,userid,groupid,startdate,enddate,type,starttime,endtime,approved,reason,reservationduration) values(?,?,?,?,?,?,?,?,?,?,?)");
					preparedStatement.setString(1, beanObj.getResourceID());
					preparedStatement.setString(2, beanObj.getUserID());
					preparedStatement.setString(3, beanObj.getGroupID());

					preparedStatement.setDate(4, DateConvert.convertDate(beanObj.getStartDate()));
					preparedStatement.setDate(5, DateConvert.convertDate(beanObj.getEndDate()));
					preparedStatement.setString(6, beanObj.getType());
					preparedStatement.setString(7, beanObj.getStartTime());
					preparedStatement.setString(8, beanObj.getEndTime());
					if (n.equalsIgnoreCase("A")) {
						preparedStatement.setString(9, "Yes");
					} else {
						preparedStatement.setString(9, "Pending");
					}

					preparedStatement.setString(10, beanObj.getReason());
					if (beanObj.getType().equalsIgnoreCase("short")) {
						preparedStatement.setString(11, "2hours");
					} else {
						preparedStatement.setString(11, " 5hours ");
					}
					int rsult = preparedStatement.executeUpdate();
					if (rsult != 0) {
					    return "success";
					} else {
						return "invalid";
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					return "invalid";
				} finally {
					try {
						connection.close();
					} catch (final Exception exception) {
						exception.printStackTrace();
					}
				}
			}

	}



