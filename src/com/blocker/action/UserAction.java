
package com.blocker.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.blocker.bean.UserBean;
import com.blocker.dao.UserDAO;

public class UserAction extends ActionSupport implements ModelDriven<UserBean> {

	private static final long serialVersionUID = 1L;
	private UserBean beanObj = new UserBean();

		public final UserBean getModel()  {
			return beanObj;
		}
		/**
		   * this method is used to add lookups.
		   * @return the string success/invalid/failure/input
		   * @throws Exception when any checked exceptions are caught
		   */
	public final String execute() throws Exception  {
	UserDAO daoObj = new UserDAO();
	String result = daoObj.userDAO(beanObj);
	if (result.equals("success")) {

		return "success";
	} else if (result.equals("invalid")) {
		addActionError("reservation was not done");
		return "error";
	} else {
		addActionError("Reservation was not done");
		return "error";
	}
}
}
