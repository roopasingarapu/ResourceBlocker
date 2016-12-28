
package com.blocker.action;

import java.util.Map;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.blocker.bean.UsersBean;
import com.blocker.dao.AdminDAO;

public class AddModifyUsersAction extends ActionSupport implements ModelDriven<UsersBean>  {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * this variable stores an object of UsersBean.
	 */
	private UsersBean beanObj = new UsersBean();
	/**
	 * @return the beanObj
	 */
	public final UsersBean getModel()  {
		return beanObj;
	}
	/**
	   * this method is used to add users.
	   * @return the string success/invalid/failure/input
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String addUsers() throws Exception  {
		AdminDAO daoObj = new AdminDAO();

		String result = daoObj.addUsersDAO(beanObj);
		 Map<String, Object> session = ActionContext.getContext().getSession();
		 session.put("obj", beanObj);

		 if (result.equals("success"))  {
			 session.put("userId", beanObj.getUserID());
		return "success";
		}  else if (result.equals("invalid"))  {
			addActionError("user not added try again");
			return "invalid";
		}  else  {
			addActionError("user not added try again.......");
			return "failure";
		}

	}

	/**
	   * this method is used to modify the users.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */

	public final String modifyUsers()throws Exception  {

		AdminDAO daoObj = new AdminDAO();
		String result = daoObj.modifyUsersDAO(beanObj);
		 Map<String, Object> session = ActionContext.getContext().getSession();

		 if (result.equals("success"))  {
			 session.put("Obj", beanObj);
		return "success";
		}  else  {
			addActionError("No data found");
		return "failure";
		}

	}
	/**
	 * method used to display the Modified Users details.
	 * @return the string success/invalid/failure/input
	 * @throws Exception when any checked exceptions are caught
	 */
	public final String doModifyUsers()throws Exception  {


		AdminDAO daoObj = new AdminDAO();
		 Map<String, Object> session = ActionContext.getContext().getSession();
		 String result = daoObj.modifyUsersDAO1(beanObj);
		 if (result.equals("success")) {
			 session.put("Obj1", beanObj);
		return "success";
		} else  {
			addActionError("No data found");
		return "failure";
		}

	}
}
