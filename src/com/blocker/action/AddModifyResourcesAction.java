
package com.blocker.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.blocker.bean.ResourcesBean;
import com.blocker.dao.AdminDAO;

public class AddModifyResourcesAction extends ActionSupport implements ModelDriven<ResourcesBean>  {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * this variable stores an object of ResourcesBean.
	 */
	private ResourcesBean beanObj = new ResourcesBean();
	private String userID;
	private String groupID;


	/**
	 * @return the groupId
	 */
	public final String getGroupID() {
		return groupID;
	}


	/**
	 * @param groupID1 the groupID to set
	 */
	public final void setGroupID(final String groupID1)  {
		this.groupID = groupID1;
	}


	/**
	 * @return the userID
	 */
	public final String getUserID() {
		return userID;
	}


	/**
	 * @param userID1 the userID to set
	 */
	public final void setUserID(final String userID1) {
		this.userID = userID1;
	}

	/**
	  *@return beanObj
	  */

	public final ResourcesBean getModel()  {
		return beanObj;
	}

	/**
	   * this method is used to add resources.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String addResources()throws Exception  {
		AdminDAO daoObj = new AdminDAO();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String result = daoObj.addResourcesDAO(beanObj);
		session.put("logged-in", "true");
		session.put("resourceID", beanObj.getResourceID());
		session.put("resourceName", beanObj.getResourceName());
		session.put("resourceType", beanObj.getResourceType());
		session.put("resourceLocation", beanObj.getResourceLocation());
		session.put("resourceCapacity", beanObj.getResourceCapacity());
		session.put("status", beanObj.getStatus());

		if (result.equals("success"))  {
		  addActionError("Resource Added Successfully ");
		  return "success";
	    }  else if (result.equals("invalid"))  {

		  addActionError("Invalid fields");
		  return "invalid";
	  }  else  {
		  addActionError("Resource not added");
		  return "failure";
	  }
}

	/**
	   * this method is used to get the resource details.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */

	public final String getResourceDetails() throws Exception  {

		AdminDAO daoObj = new AdminDAO();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String result = daoObj.detailResourcesDAO(beanObj);
		session.put("objRes", beanObj);
		  daoObj.getGroupID(this);
		  session.put("groupId", groupID);

		if (result.equals("success"))  {
		  addActionError("Resource Added Successfully ");
		  return "success";
	    }  else  {
		  addActionError("Resource not added");
		  return "failure";
	  }
	}
	/**
	   * this method is used to get the reservation details.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String reserveDetails()throws Exception  {
		if (beanObj.getStatus().equals("free"))  {
			return "success";
		}  else  {
			return "failure";
		}
	}
	/**
	   * this method is used to get the resource details.
	   * @return the string success/invalid/failure
	   *
	   */
	 public final String addLoc()  {
		  Map<String, Object> session = ActionContext.getContext().getSession();
		  session.put("object", beanObj);
		  return "success";
	  }
	 /**
	   * this method is used to modify the resource details.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */
	 public final String getModifyResources() throws Exception  {
		 	AdminDAO daoObj = new AdminDAO();
			Map<String, Object> session = ActionContext.getContext().getSession();
			String result = daoObj.getModifyResourcesDAO(beanObj);
			session.put("resObj", beanObj);
			if (result.equals("success"))  {
				return "success";
			}  else  {
				return "failure";

			}
	 }
	 /**
	   * this method is used to display the modified resource details.
	   * @return the string success/invalid/failure
	   * @throws Exception when any checked exceptions are caught
	   */
	 public final String getModifiedResources() throws Exception  {

		 	AdminDAO daoObj = new AdminDAO();
			Map<String, Object> session = ActionContext.getContext().getSession();
			String result = daoObj.getModifiedResourcesDAO(beanObj);
			session.put("resourceObj", beanObj);
			if (result.equals("success"))  {
				return "success";
			}  else  {
				return "failure";

			}
	 }


}
