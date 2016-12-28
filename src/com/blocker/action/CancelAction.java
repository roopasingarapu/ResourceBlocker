
package com.blocker.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.blocker.bean.CancelBean;

import com.blocker.dao.AdminDAO;

public class CancelAction extends ActionSupport implements ModelDriven<CancelBean> {
	private static final long serialVersionUID = 1L;
	private CancelBean beanObj = new CancelBean();
	/**
	 * @return the beanObj
	 */
	public final CancelBean getModel()  {
		return beanObj;
	}

	private int reservationID;
	/**
	 * @return the reservationID
	 */
	public final int getReservationID() {
		return reservationID;
	}

	/**
	 * @param reservationID1 the reservationID to set
	 */
	public final void setReservationID(final int reservationID1) {
		this.reservationID = reservationID1;
	}

	/**
	   * this method is used to cancel the reservation.
	   * @return the string success/error
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String cancelRes() throws Exception  {
		AdminDAO daoObj = new AdminDAO();
		String result = daoObj.cancelDAO(beanObj);
		if (result.equals("success"))  {
			addActionError("cancelled");
			return "success";
		}  else if (result.equals("invalid"))  {
			addActionError("not updated try again");
			return "error";
		}  else  {
			addActionError("not updated try again");
			return "error";
		}

	}

}
