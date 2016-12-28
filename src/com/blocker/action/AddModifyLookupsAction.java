
package com.blocker.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.blocker.bean.LookupsBean;
import com.blocker.dao.AdminDAO;

public class AddModifyLookupsAction extends ActionSupport implements ModelDriven<LookupsBean> {


	private static final long serialVersionUID = 1L;
	/**
	 * this variable stores an object of LookupsBean.
	 */
	private LookupsBean beanObj = new LookupsBean();

	/**
	 * @return the beanObj
	 */
	public final LookupsBean getBeanObj() {
		return beanObj;
	}

	/**
	 *@param beanObj1 the beanObj to set
	 */

	public final void setBeanObj(final LookupsBean beanObj1) {
		this.beanObj = beanObj1;
	}

     /**
	 *@return beanObj
	 */

	public final LookupsBean getModel()  {
		return beanObj;
	}

	/**
	   * this method is used to add lookups.
	   * @return the string success/invalid/failure/input
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String add()throws Exception  {
		AdminDAO daoObj = new AdminDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
		String result = daoObj.addLookupsDAO(beanObj);
		session.put("logged-in", "true");
		session.put("type", beanObj.getResourceType());
		session.put("location", beanObj.getResourceLocation());

		if (result.equals("success"))  {
		  addActionError("Lookup Added Successfully ");
		  return "success";
	    }  else if (result.equals("invalid"))  {

		  addActionError("Invalid fields");
		  return "invalid";
	  }  else  {
		  addActionError("Lookup not added");
		  return "failure";
	  }

}
	/**
	   * this method is used to modify the lookups.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */

	public final String modifyLookups()throws Exception  {

AdminDAO daoObj = new AdminDAO();
		String result = daoObj.modifyLookupsDAO(beanObj);
Map<String, Object> session = ActionContext.getContext().getSession();
		 if (result.equals("success")) {
			 session.put("Obj", beanObj);
		return "success";
		 } else  {
			addActionError("Location not modified");
			return "failure";
		}
	}

	/**
	 * method used to display the Modified Lookups details.
	 * @return the string success/invalid/failure/input
	 * @throws Exception when any checked exceptions are caught
	 */

	public final String doModifyLookups()throws Exception  {

		LookupsBean bean;
		AdminDAO daoObj = new AdminDAO();

         Map<String, Object> session = ActionContext.getContext().getSession();
		 bean = (LookupsBean) session.get("Obj");
		 String result = daoObj.modifyLookupsDAO1(beanObj, bean);
		 if (result.equals("success"))  {
			 session.put("Obj1", beanObj);
		return "success";
		}  else if (result.equals("failure"))  {
				addActionError("The location already exists");
			return "failure";
			}  else {
			addActionError("Action failed");
		return "failure";
		}

	}

}
