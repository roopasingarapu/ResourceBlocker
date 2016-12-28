

package com.blocker.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.TreeSet;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.blocker.bean.ReservationsBean;
import com.blocker.connection.DBconnect;
import com.blocker.dao.AdminDAO;

public class AutoCompleter extends ActionSupport {

	private static final long serialVersionUID = 1L;
		private String resourceType;
	    private String resourceLocation;
		/**
		 * @return the resourceType
		 */
		public final String getResourceType() {
			return resourceType;
		}

		/**
		 * @param resourceType1 the resourceType to set
		 */
		public final void setResourceType(final String resourceType1) {
			this.resourceType = resourceType1;
		}

		/**
		 * @return the resourceLocation
		 */
		public final String getResourceLocation() {
			return resourceLocation;
		}
		/**
		 * @param resourceLocation1 the resourceLocation to set
		 */
		public final void setResourceLocation(final String resourceLocation1) {
			this.resourceLocation = resourceLocation1;
		}

		private String userID;



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

		private List<String> resId;

		/**
		   * this method is used to retrieve all the resource ID.
		   * @return the string success/invalid
		   * @throws Exception when any checked exceptions are caught
		   */
		public final String resourceIdAuto() throws Exception  {
			  AdminDAO daoObj = new AdminDAO();
			  resId = daoObj.fetchResId();
			  Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("ResId", resId);
			  return "success";
		}
		/**
		   * this method is used to retrieve the resource ID.
		   * @return resId
		   */
		public final List<String> getResId()  {
			  return resId;
		}

		private List<String> location;

		/**
		   * this method is used to retrieve all the resource locations.
		   * @return the string success
		   * @throws Exception when any checked exceptions are caught
		   */

		public final String autoLocation() throws Exception  {

			  Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("resType", resourceType);
			  location = fetchResLocation();
			  session.put("Location", location);

			  return "success";
		}
		/**
		   * this method is used to retrieve the resource location.
		   * @return location
		   */
		public final List<String> getLocation()  {
			  return location;
		}

		private TreeSet<String> type;
		/**
		   * this method is used to retrieve all the resource types.
		   * @return the string success
		   * @throws Exception when any checked exceptions are caught
		   */

		public final String autoType() throws Exception  {
			  AdminDAO daoObj = new AdminDAO();
			  type = daoObj.fetchType();
			  Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("Type", type);
			  return "success";
		}

		/**
		   * this method is used to retrieve the resource type.
		   * @return type
		   */

		public final TreeSet<String> getType()  {
			  return type;
		}

		private List<String> userId;

		/**
		   * this method is used to retrieve all the user ID.
		   * @return the string success
		   * @throws Exception when any checked exceptions are caught
		   */


		public final String retrieveUserId() throws Exception  {
			  AdminDAO daoObj = new AdminDAO();
			  Map<String, Object> session = ActionContext.getContext().getSession();
			 userId = daoObj.fetchUserId();
			  session.put("UserId", userId);

		  	  return "success";
		  }

		/**
		   * this method is used to retrieve the user ID.
		   * @return userID
		   */

		  public final List<String> getUserId()  {
			  return userId;
		  }

		  private ArrayList<String> resLocationList = new ArrayList<String>();
		  private Connection connection;
		  private PreparedStatement preparedStatement;
		  private ResultSet resultSet;

		  /**
		   * this method is used to retrieve the user ID.
		   * @return the string success
		   */

		  public final ArrayList<String> fetchResLocation()  {
				try  {
					connection = DBconnect.getConnection();
					preparedStatement = connection.prepareStatement("select resourceLocation from lookups_details where resourceType=?");
					preparedStatement.setString(1, getResourceType());

					resultSet = preparedStatement.executeQuery();
					while (resultSet.next())  {
						resLocationList.add((String) resultSet.getString("resourceLocation"));
					}

				}  catch (Exception exception)  {
					exception.printStackTrace();

				}
				return resLocationList;
		  }

			private List<String> resName;

			/**
			   * this method is used to retrieve all the resource names.
			   * @return the string success
			   * @throws Exception when any checked exceptions are caught
			   */

			public final String retrieveResName() throws Exception  {
				  AdminDAO daoObj = new AdminDAO();
				  resName = daoObj.fetchResName();
				  Map<String, Object> session = ActionContext.getContext().getSession();
				  session.put("userid", userID);

				  return "success";
			}
			/**
			   * this method is used to retrieve the reservation name.
			   * @return the string success
			   */
			public final List<String> getResName()  {
				  return resName;
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
		   * this method is used to retrieve the locations.
		   * @return the string success
		   */
		  public final String addLoc()  {
			  Map<String, Object> session = ActionContext.getContext().getSession();
			  session.put("Location", resourceLocation);
			  return "success";
		  }

		 private List<Integer> reservationIdList;

	/**
	 * @return the reservationIdList
	 */
	public final List<Integer> getReservationIdList() {
		return reservationIdList;
	}
	/**
	   * this method is used to retrieve all the resource names.
	   * @return the string success
	   * @throws Exception when any checked exceptions are caught
	   */

	public final String execute() throws Exception  {
	 AdminDAO daoObj = new AdminDAO();
	 reservationIdList = daoObj.getReservationIdDAO();
	 Map<String, Object> session = ActionContext.getContext().getSession();
	  session.put("ReservationID", reservationID);
	  session.put("List", reservationIdList);
		return "success";
	  }
	/**
	   * this method is used to retrieve all the resource names.
	   * @return the string success
	   * @throws Exception when any checked exceptions are caught
	   */

	public final String showCancel()throws Exception  {

		 ReservationsBean resBean = new ReservationsBean();
		 Map<String, Object> session = ActionContext.getContext().getSession();
		 	resBean.setReservationID(reservationID);
			AdminDAO daoObj = new AdminDAO();
			daoObj.showCancelDAO(resBean);
		  session.put("ResBean", resBean);
		  return "success";
	}

private List<Integer> reservationIdList1;


/**
 * @return the reservationIdList1
 */
public final List<Integer> getReservationIdList1() {
	return reservationIdList1;
}
/**
 * this method is used to retrieve all the resource names.
 * @return the string success
 * @throws Exception when any checked exceptions are caught
 */
public final String execute1() throws Exception  {
	Map<String, Object> session = ActionContext.getContext().getSession();
	  AdminDAO daoObj = new AdminDAO();
 reservationIdList1 = daoObj.getReservationIdCancDAO();
 session.put("ApproveList", reservationIdList1);
	return "success";
  }

private List<Integer> reservationIdList2;
/**
 * @return the reservationIdList2
 */
public final List<Integer> getReservationIdList2() {
	return reservationIdList2;
}
/**
 * this method is used to retrieve all the reservation IDs based on approved status.
 * @return the string success
 * @throws Exception when any checked exceptions are caught
 */

public final String cancelUser() throws Exception  {
	ReservationsBean beanObj = new ReservationsBean();
	 Map<String, Object> session = ActionContext.getContext().getSession();
	 String id = (String) session.get("userID");
	 beanObj.setUserID(id);
	 AdminDAO daoObj = new AdminDAO();
	 reservationIdList2 = daoObj.getUserReservationIdDAO(beanObj);
	 session.put("IdList", reservationIdList2);

	  session.put("ReservationID", reservationID);
		return "success";
	  }
}

