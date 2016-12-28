
package com.blocker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.blocker.bean.LoginBean;
import com.blocker.connection.DBconnect;
/**
 * this class is used to perform login action.
 * @return the string success/invalid/failure/input
 * @throws Exception when any checked exceptions are caught
 */
public class LoginDAO {


	private  Connection connection;
	private static String groupId = "exception";

	/**
	   * this method is used to login.
	   * @return the string change/admin/user/invalid
	   * @param beanObj to set
	   */
	public final String loginDAO(final LoginBean beanObj) {
		try {
			connection = DBconnect.getConnection();
			PreparedStatement ps = connection.prepareStatement("select userID,password,userType from login_credentials where userID=? and password=? and userType=?");

			ps.setString(1, beanObj.getUserID());
			ps.setString(2, beanObj.getPassword());
			ps.setString(3, beanObj.getUserType());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (beanObj.getUserID().equals("uakron") &&  beanObj.getPassword().equals("uakron") && beanObj.getUserType().equals("A"))  {
					return "change";
				}  else if (rs.getString("userType").equals("A"))  {
					return "admin";
				}  else  {
					return "user";
				}
				} else  {
				return "invalid";
			}
			}  catch (Exception exception) {
			exception.printStackTrace();
			return groupId;
		} finally {
			try {
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	   * this method is used to change the password.
	   * @return the string success/invalid/user
	   * @param beanObj to set
	   */
	public final String changePasswordDAO(final LoginBean beanObj) {
		try {
			connection = DBconnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("update login_credentials set password=?  where userID=? and password=? and userType=?");
			preparedStatement.setString(1, beanObj.getNewPassword());
			preparedStatement.setString(2, beanObj.getUserID());
			preparedStatement.setString(3, beanObj.getOldPassword());
			preparedStatement.setString(4, beanObj.getUserType());
			PreparedStatement preparedStatement1 = connection.prepareStatement("update user_details set password=?  where userID=?");
			preparedStatement1.setString(1, beanObj.getNewPassword());
			preparedStatement1.setString(2, beanObj.getUserID());
			int result = preparedStatement.executeUpdate();
			preparedStatement1.executeUpdate();
			if (result != 0)  {
					if (beanObj.getUserType().equals("A")) {
						return "success";
					} else {
						return "user";
					}
					}  else  {
				return "invalid";
			}
		}  catch (Exception exception) {
			exception.printStackTrace();
			return groupId;
		} finally {
			try {
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

	}
}
