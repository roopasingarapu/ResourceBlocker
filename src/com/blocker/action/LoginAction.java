
package com.blocker.action;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.blocker.bean.LoginBean;
import com.blocker.dao.LoginDAO;

public class LoginAction extends ActionSupport implements ModelDriven<LoginBean>  {

	private static final long serialVersionUID = 1L;
	private LoginBean beanObj = new LoginBean();
	
	public final LoginBean getModel()  {
		return beanObj;
	}
	/**
	   * this method is used to provide authentication.
	   * @return the string change/error/invalid/user/admin/input/success
	   * @throws Exception when retrieving userID from
	   * database fails
	   */
	public final String login() throws Exception  {
		LoginDAO daoObj = new LoginDAO();
        String result = daoObj.loginDAO(beanObj);

		if (result.equals("change"))  {
			result = "change";
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("logged-in", "true");
			session.put("userID", beanObj.getUserID());
			session.put("password", beanObj.getPassword());
			session.put("userType", beanObj.getUserType());

			return result;
		}  else if (result.equals("admin"))  {
			result = "admin";

			Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("logged-in", "true");
			  session.put("userID", beanObj.getUserID());
			  session.put("password", beanObj.getPassword());
			  session.put("userType", beanObj.getUserType());
			return result;
		}  else if (result.equals("user")) {
			result = "user";

			Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("logged-in", "true");
			  session.put("userID", beanObj.getUserID());
			  session.put("password", beanObj.getPassword());
			  session.put("userType", beanObj.getUserType());
			return result;
		} else if (result.equals("invalid"))  {
			result = "invalid";
			 addActionError("Invalid user name or password! Please try again!");

			 return result;
	    }  else  {
			result = "error";
			addActionError("SQL server problem due to Exception, Please try again!");
			return result;
		}
	}

	/**
	   * this method gets validates user details.
	   * @return the string change/error/invalid/user/admin
	   * @throws Exception when retrieving userId from
	   * database fails
	   */
	public final String changePassword() throws Exception  {
		String userID;
		String userType;
		String password;
		LoginDAO daoObj = new LoginDAO();

		Map<String, Object> session = ActionContext.getContext().getSession();
		  userID = (String) session.get("userID");
		  password = (String) session.get("password");
		  userType = (String) session.get("userType");
		  beanObj.setUserID(userID);
		  beanObj.setUserType(userType);
		  beanObj.setPassword(password);

		  if (userID == null || password == null || userType == null)  {
			  addActionError("Your session was expired, Please Login again");
			return "input";
		  }

		  String result = daoObj.changePasswordDAO(beanObj);
		  if (result.equals("success"))  {
			  addActionError("Your Password was successfully changed, Please login by using new passowrd");
			  return "success";
		  }   else if (result.equals("user"))  {
			  return "user";
		  }  else if (result.equals("invalid"))  {

			  addActionError("Password not changed try again");
			  return "failure";
		  } else  {
			  addActionError("Password not changed try again");
			  return "failure";
		  }
	}

}
