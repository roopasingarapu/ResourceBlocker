
package com.blocker.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.blocker.bean.ReservationsBean;
import com.blocker.dao.AdminDAO;

public class ApproveRejectResAction extends ActionSupport implements ModelDriven<ReservationsBean>  {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * this variable stores an object of ReservationsBean.
	 */
	private ReservationsBean beanObj = new ReservationsBean();

	/**
	 * @return the beanObj
	 */

	public final ReservationsBean getModel()  {
		return beanObj;
	}
	/**
	 * method used to approve or reject reservation.
	 * @return the string success
	 * @throws Exception when any checked exceptions are caught
	 */
		public final String showApproveReject() throws Exception  {
			AdminDAO daoObj = new AdminDAO();
			String result = daoObj.showApproveRejectDAO(beanObj);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("reservation", beanObj);
			if (result.equals("success"))  {
				return "success";
			}  else  {
				addActionError("not updated try again");
				return "error";
			}

		}

		/**
		 * method used to display the approval status.
		 * @return the string success/error
		 * @throws Exception when any checked exceptions are caught
		*/
		public final String approveRejectRes() throws Exception  {
			AdminDAO daoObj = new AdminDAO();

			String result = daoObj.approveRejectDAO(beanObj);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("Approved", beanObj.getApproved());

			if (result.equals("success"))  {
				addActionError("approved");
				return "success";
			}  else if (result.equals("invalid")) {
				addActionError("not updated try again");
				return "error";
			}  else  {
				addActionError("not updated try again");
				return "error";
			}

		}

	}
