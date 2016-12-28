
package com.blocker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.TreeSet;

import com.blocker.action.AddModifyResourcesAction;

import com.blocker.bean.CancelBean;
import com.blocker.bean.ExportBean;
import com.blocker.bean.LookupsBean;

import com.blocker.bean.ReservationsBean;
import com.blocker.bean.ResourcesBean;
import com.blocker.bean.UsersBean;
import com.blocker.connection.DBconnect;
/**
 * this class is used to perform admin side actions(Add/modify lookups/users... etc.).
 * @return the string success/invalid/failure/input
 * @throws Exception when any checked exceptions are caught
 */
public class AdminDAO {

	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Statement statement;
	private ArrayList<String> locationList = new ArrayList<String>();
	private ArrayList<String> userIdList = new ArrayList<String>();
	private ArrayList<String> resIdList = new ArrayList<String>();
	private TreeSet<String> resTypeList = new TreeSet<String>();
	private ArrayList<String> resNameList = new ArrayList<String>();
	private ArrayList<Integer> reservationIdList = new ArrayList<Integer>();
	private ArrayList<Integer> reservationIdList2 = new ArrayList<Integer>();
	private ArrayList<ReservationsBean> resList = new ArrayList<ReservationsBean>();

	/**
	 * this method is used to adding lookups into database.
	 * @return the string success/invalid/failure/input
	 * @throws Exception when any exceptions are caught
	 * @param beanObj to set
	 */
	public final String addLookupsDAO(final LookupsBean beanObj) throws Exception  {

		connection = DBconnect.getConnection();
		int flag = 0;
		boolean check = false;
		String response1;

		try  {
			//beanObj.setLookupId(generateId());

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT resourceLocation from lookups_details");

			while (resultSet.next())  {
				if (resultSet.getString(1).equals(beanObj.getResourceLocation()))  {

					flag = 0;
				}  else  {
					flag = 1;
				}
			}
			if (flag != 0)  {

				preparedStatement = connection.prepareStatement("insert into lookups_details values(?,?)");

				preparedStatement.setString(1, beanObj.getResourceType());
				preparedStatement.setString(2, beanObj.getResourceLocation());
				int result = preparedStatement.executeUpdate();
					if (result != 0)  {
						check = true;
					}
		}
		} catch (Exception exception)  {
			connection.close();
			exception.printStackTrace();
		}

		if (check)  {
			response1 = "success";
		}  else  {
			response1 = "failure";
		}
		return response1;
}
	/**
	 * this method is used to retrieve lookups from database and modify them.
	 * @return the string success/invalid/failure/input
	 * @throws Exception when any exceptions are caught
	 * @param beanObj to set
	 */
public final String modifyLookupsDAO(final LookupsBean beanObj)throws Exception {

		String result = "error";
		connection = DBconnect.getConnection();
		try  {
		preparedStatement = connection.prepareStatement("SELECT * from lookups_details where resourceLocation=?");
		preparedStatement.setString(1, beanObj.getResourceLocation());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next())  {

				beanObj.setResourceLocation(resultSet.getString(2));
				beanObj.setResourceType(resultSet.getString(1));
				result = "success";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			result = "failure";
		}
	return result;
	}
/**
 * this method is used to modify lookups and update them into database.
 * @return the string success/invalid/failure/input
 * @param beanObj to set
 * @param bean to set
 */
	public final String modifyLookupsDAO1(final LookupsBean beanObj , final LookupsBean bean) {

		boolean flag = false;
		String response = "error";

		try  {
		connection = DBconnect.getConnection();
		preparedStatement = connection.prepareStatement("SELECT resourceLocation from lookups_details where resourceLocation=?");
		preparedStatement.setString(1, beanObj.getResourceLocation());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next())  {

			  flag = true;
			  response = "failure";
		}

		if (!flag)  {
			preparedStatement = connection.prepareStatement("UPDATE lookups_details SET resourceType=?, resourceLocation=? where resourceLocation=?");
			preparedStatement.setString(1, beanObj.getResourceType());
			preparedStatement.setString(2, beanObj.getResourceLocation());
			preparedStatement.setString(3, bean.getResourceLocation());
			int result = preparedStatement.executeUpdate();
			if (result != 0)  {
				response = "success";
			}

		}
		}  catch (Exception e)  {
			 // System.out.println("--Rolling Back");
			  //DBconnect.rollbackTransaction();
			  e.printStackTrace();
			  response = "error";
		  }  finally {
			  try  {
				  preparedStatement.close();
				  connection.close();
			  } catch (Exception e)  {
				  e.printStackTrace();
			  }
		  }
		return response;

	}
	/**
	 * this method is used to add users and insert into database.
	 * @return the string success/invalid/failure/input
	 * @param beanObj to set
	 */
	public final String addUsersDAO(final UsersBean beanObj)  {

			try {
				connection = DBconnect.getConnection();
				preparedStatement = connection.prepareStatement("insert into user_details values(?,?,?,?)");
				
				preparedStatement.setString(1, beanObj.getUserID());
				preparedStatement.setString(2, beanObj.getGroupID());
				preparedStatement.setString(3, beanObj.getPassword());
				preparedStatement.setInt(4, beanObj.getLoginStatus());
			    int result = preparedStatement .executeUpdate();
			    PreparedStatement preparedStatement2 = connection.prepareStatement("insert into login_credentials values(?,?,'U')");
			    preparedStatement2.setString(1, beanObj.getUserID());
			    preparedStatement2.setString(2, beanObj.getPassword());
			    int result1 = preparedStatement2.executeUpdate();

			    /*PreparedStatement preparedStatement1 = connection.prepareStatement("select userId from user_details");
			    resultSet = preparedStatement1.executeQuery();
			    String id = "";
				if (resultSet.next()) {
					id = resultSet.getString(1);
				}*/
			    

			if (result != 0 && result1 != 0) {
				return "success";
			} else  {
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
	/**
	 * this method is used to retrieve users from database and modify them.
	 * @return the string success/invalid/failure/input
	 * @throws Exception when any exceptions are caught
	 * @param beanObj to set
	 */
	public final String modifyUsersDAO(final UsersBean beanObj) throws Exception  {
		connection = DBconnect.getConnection();
		try  {
		preparedStatement = connection.prepareStatement("SELECT * from user_details where userId=?");
		preparedStatement.setString(1, beanObj.getUserID());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next())  {

			beanObj.setUserID(resultSet.getString(1));
			beanObj.setGroupID(resultSet.getString(2));
			beanObj.setPassword(resultSet.getString(3));
			beanObj.setLoginStatus(resultSet.getInt(4));
		}
		return "success";
		} catch (Exception exception)  {
			exception.printStackTrace();
			return "failure";
		}
	}
	/**
	 * this method is used to modify users and update into database.
	 * @return the string success/invalid/failure/input
	 * @param beanObj to set
	 */
public final String modifyUsersDAO1(final UsersBean beanObj) {

		String response = "failure";
		try  {
		connection = DBconnect.getConnection();

		preparedStatement = connection.prepareStatement("UPDATE user_details SET groupId=?, password=?,loginStatus=? where userId=?");
			preparedStatement.setString(1, beanObj.getGroupID());
			preparedStatement.setString(2, beanObj.getPassword());
			preparedStatement.setInt(3, beanObj.getLoginStatus());
			preparedStatement.setString(4, beanObj.getUserID());

			int result = preparedStatement.executeUpdate();
			if (result != 0)  {
				response = "success";
			}
		} catch (Exception e)  {
			 // System.out.println("--Rolling Back");
			  //DBconnect.rollbackTransaction();
			  e.printStackTrace();
		  }  finally  {
			  try {
				  preparedStatement.close();
				  connection.close();
			  } catch (Exception e)  {
				  e.printStackTrace();
			  }
		  }
		return response;

	}
/**
 * this method is used to add resources and insert into database.
 * @return the string success/invalid/failure/input
 * @throws Exception when any exceptions are caught
 * @param beanObj to set
 */
public final String addResourcesDAO(final ResourcesBean beanObj) throws Exception {
	boolean check = false;
	connection = DBconnect.getConnection();
	try  {
	preparedStatement = connection.prepareStatement("INSERT into resource_details values(?,?,?,?,?,?)");
	preparedStatement.setString(1, beanObj.getResourceID());
	preparedStatement.setString(2, beanObj.getResourceName());
	preparedStatement.setString(3, beanObj.getResourceType());
	preparedStatement.setString(4, beanObj.getResourceLocation());
	preparedStatement.setString(5, beanObj.getResourceCapacity());
	preparedStatement.setString(6, beanObj.getStatus());
	int result = preparedStatement.executeUpdate();
	if (result != 0)  {
		check = true;
	}
}   catch (Exception exception)  {
				connection.close();
				exception.printStackTrace();
			}

	if (check) {
		return "success";
	} else {
		return "failure";
	}

}
/**
 * this method is used to retrieve resources from database and modify them.
 * @return the string success/invalid/failure/input
 * @throws Exception when any exceptions are caught
 * @param beanObj to set
 */
public final String getModifyResourcesDAO(final ResourcesBean beanObj) throws Exception  {
		int check = 0;
		connection = DBconnect.getConnection();
		preparedStatement = connection.prepareStatement("SELECT * from resource_details where resourceID=?");
		preparedStatement.setString(1, beanObj.getResourceID());

		resultSet = preparedStatement.executeQuery();
		if (resultSet.next())  {
			beanObj.setResourceID(resultSet.getString(1));
			beanObj.setResourceName(resultSet.getString(2));
			beanObj.setResourceType(resultSet.getString(3));
			beanObj.setResourceLocation(resultSet.getString(4));
			beanObj.setResourceCapacity(resultSet.getString(5));
			beanObj.setStatus(resultSet.getString(6));
			check = 1;
		}
		if (check == 1) {
			return "success";
		} else {
			return "failure";
		}
	}
/**
 * this method is used to modify resources and update them into database.
 * @return the string success/invalid/failure/input
 * @throws Exception when any exceptions are caught
 * @param beanObj to set
 */
public final String getModifiedResourcesDAO(final ResourcesBean beanObj) throws Exception  {

	connection = DBconnect.getConnection();
	String response;
	preparedStatement = connection.prepareStatement("UPDATE resource_details SET resourceName=?,resourceCapacity=?,Status=? where resourceId=?");
	preparedStatement.setString(1, beanObj.getResourceName());
	preparedStatement.setString(2, beanObj.getResourceCapacity());
	preparedStatement.setString(3, beanObj.getStatus());
	preparedStatement.setString(4, beanObj.getResourceID());


	int result = preparedStatement.executeUpdate();
	if (result != 0)  {
		response = "success";
	}  else {
		response = "failure";
	}
	return response;
}
/**
 * this method is used to retrieve the reservations to approve/reject.
 * @return the string success/invalid/failure/input
 * @throws Exception when any exceptions are caught
 * @param beanObj to set
 */
	public final String showApproveRejectDAO(final ReservationsBean beanObj) throws Exception  {
		connection = DBconnect.getConnection();

		try  {
		preparedStatement = connection.prepareStatement("SELECT * from reservation_details where reservationId=?");
		preparedStatement.setInt(1, beanObj.getReservationID());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next())  {

			beanObj.setReservationID(resultSet.getInt(1));
			beanObj.setResourceID(resultSet.getString(2));
			beanObj.setUserID(resultSet.getString(3));
			beanObj.setGroupID(resultSet.getString(4));
			beanObj.setStartDate(resultSet.getDate(5));
			beanObj.setEndDate(resultSet.getDate(6));
			beanObj.setType(resultSet.getString(7));
			beanObj.setStartTime(resultSet.getString(8));
			beanObj.setEndTime(resultSet.getString(9));
			beanObj.setReason(resultSet.getString(11));
			beanObj.setReservationDuration(resultSet.getString(12));
		}
		return "success";
		} catch (Exception exception)  {
			exception.printStackTrace();
			return "failure";
		}
	}
	/**
	 * this method is used to approve/reject.
	 * @return the string success/invalid/failure/input
	 * @param beanObj to set
	 */
	public final String approveRejectDAO(final ReservationsBean beanObj) {

		try {
			connection = DBconnect.getConnection();
			preparedStatement = connection.prepareStatement("update reservation_details set approved=? where reservationId=?");
			preparedStatement.setString(1, beanObj.getApproved());
			preparedStatement.setInt(2, beanObj.getReservationID());
			int rsult = preparedStatement.executeUpdate();

			if (rsult != 0) {
					return "success";
			} else  {
				return "invalid";
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			String groupId = null;
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
	   * this method is used to cancel reservation.
	   * @return the string success/invalid/failure/input
	   * @param beanObj to set
	   */

	public final String cancelDAO(final CancelBean beanObj) {
		try {

			connection = DBconnect.getConnection();

		    preparedStatement = connection.prepareStatement("delete from reservation_details  where reservationId=?");
			preparedStatement.setInt(1, beanObj.getReservationID());
			int rsult = preparedStatement.executeUpdate();

			if (rsult != 0) {
					return "success";

			} else  {
				return "invalid";
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			String groupId = null;
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
	   * this method is used to show the details of the cancelled reservation.
	   * @return the string success/invalid/failure/input
	   * @param beanObj to set
	   */
public final String showCancelDAO(final ReservationsBean beanObj) {
	try {

		connection = DBconnect.getConnection();
		preparedStatement = connection.prepareStatement("select * from reservation_details  where reservationId=?");
		preparedStatement.setInt(1, beanObj.getReservationID());
		resultSet = preparedStatement.executeQuery();


		if (resultSet.next()) {
			beanObj.setReservationID(resultSet.getInt(1));
			beanObj.setResourceID(resultSet.getString(2));
			beanObj.setUserID(resultSet.getString(3));
			beanObj.setGroupID(resultSet.getString(4));
			beanObj.setStartDate(resultSet.getDate(5));
			beanObj.setEndDate(resultSet.getDate(6));
			beanObj.setType(resultSet.getString(7));
			beanObj.setStartTime(resultSet.getString(8));
			beanObj.setEndTime(resultSet.getString(9));
			beanObj.setApproved(resultSet.getString(10));
			beanObj.setReason(resultSet.getString(11));
			beanObj.setReservationDuration(resultSet.getString(12));
				return "success";

		}  else  {
			return "invalid";
		}
	}  catch (Exception exception) {
		exception.printStackTrace();
		String groupId = null;
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
 * this method is used to export the details into excel sheet.
 * @return the string success/invalid/failure/input
 *
 */

public final ArrayList<ReservationsBean> getReserve() {


	try {
		connection = DBconnect.getConnection();
		String sql = "select * from reservation_details";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

		    int reservationId = rs.getInt("reservationId");
			String resourceId = rs.getString("resourceId");
    		String userid = rs.getString("userid");
    		String groupid = rs.getString("groupid");
    		String type = rs.getString("type");
    		Date startdate = rs.getDate("startdate");
    		Date enddate = rs.getDate("enddate");
    		String starttime = rs.getString("starttime");
    		String endtime = rs.getString("endtime");
    		String approved = rs.getString("approved");
    		String reason = rs.getString("reason");
    		String reservationduration = rs.getString("reservationduration");

			ReservationsBean view = new ReservationsBean();
			view.setReservationID(reservationId);
			view.setResourceID(resourceId);
			view.setUserID(userid);
			view.setGroupID(groupid);
			view.setType(type);
			view.setStartDate(startdate);
			view.setEndDate(enddate);
			view.setStartTime(starttime);
			view.setEndTime(endtime);
			view.setApproved(approved);
			view.setReason(reason);
			view.setReservationDuration(reservationduration);

			resList.add(view);

		}

	} catch (Exception exception) {

		exception.printStackTrace();

	}

	return resList;
}

/**
 * this method is used to retrieve the resource details.
 * @return the string success/invalid/failure/input
 * @throws Exception when any checked exceptions are caught
 * @param beanObj to set
 */
	public final String detailResourcesDAO(final ResourcesBean beanObj) throws Exception  {
		int check = 0;
		connection = DBconnect.getConnection();
		preparedStatement = connection.prepareStatement("SELECT * from resource_details where resourceName=?");
		preparedStatement.setString(1, beanObj.getResourceName());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next())  {
			beanObj.setResourceID(resultSet.getString(1));
			beanObj.setResourceName(resultSet.getString(2));
			beanObj.setResourceType(resultSet.getString(3));
			beanObj.setResourceLocation(resultSet.getString(4));
			beanObj.setResourceCapacity(resultSet.getString(5));
			beanObj.setStatus(resultSet.getString(6));
			check = 1;
		}
		if (check == 1) {
			return "success";
		} else {
			return "failure";
		}
	}
	/**
	   * this method is used to retrieve the user IDs.
	   * @return the string success/invalid/failure/input
	   *
	   */
	public final ArrayList<String> fetchUserId()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select userId from user_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				userIdList.add(resultSet.getString("userId"));
			}

		}  catch (Exception exception)  {
			exception.printStackTrace();

		}
		return userIdList;

	}
	/**
	   * this method is used to retrieve all reservation IDs from the database.
	   * @return the string success/invalid/failure/input
	   */
	public final ArrayList<Integer> fetchReservationId()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT reservationID from reservation_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				reservationIdList.add((Integer) resultSet.getInt("reservationID"));
			}

		}  catch (Exception exception)  {
			exception.printStackTrace();

		}
		return reservationIdList;

	}
	/**
	   * this method is used to retrieve resource ID.
	   * @return the string success/invalid/failure/input
	   */
	public final ArrayList<String> fetchResId()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select resourceId from resource_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				resIdList.add((String) resultSet.getString("resourceId"));
			}

		}  catch (Exception exception)  {
			exception.printStackTrace();

		}
		return resIdList;

	}
	/**
	   * this method is used to fetch location from the database.
	   * @return the string success/invalid/failure/input
	   */

	public final ArrayList<String> fetchLocation()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select resourceLocation from lookups_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				locationList.add((String) resultSet.getString("resourceLocation"));

			}

		} catch (Exception exception)  {
			exception.printStackTrace();

		}
		return locationList;

	}
	/**
	   * this method is used to fetch the type.
	   * @return the string success/invalid/failure/input
	   */
	public final TreeSet<String> fetchType()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select resourceType from lookups_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				resTypeList.add((String) resultSet.getString("resourceType"));
			}

		} catch (Exception exception)  {
			exception.printStackTrace();

		}
		return resTypeList;

	}
	/**
	   * this method is used to fetch the resource name from the database.
	   * @return the string success/invalid/failure/input
	   */
	public final ArrayList<String> fetchResName()  {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select resourceName from resource_details";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				resNameList.add((String) resultSet.getString("resourceName"));
			}

		} catch (Exception exception)  {
			exception.printStackTrace();

		}
		return resNameList;

	}
	/**
	   * this method is used to retrieve the groupID.
	   * @return the string success/invalid/failure/input
	   * @param obj to set
	   */
	public final String getGroupID(final AddModifyResourcesAction obj)  {
		try  {
			connection = DBconnect.getConnection();
			preparedStatement = connection.prepareStatement("select groupId from user_details where userId=?");
			preparedStatement.setString(1, obj.getUserID());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())  {
				obj.setGroupID(resultSet.getString("groupId"));
			}

		} catch (Exception exception)  {
			exception.printStackTrace();
			return "failure";
		}
		return "success";

	}
	/**
	   * this method is used to retrieve the reservation details from the database.
	   * @return the string success/invalid/failure/input
	   */
	public final List<Integer> getReservationIdDAO() {
		try  {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select reservationId from reservation_details where approved='Yes' ";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				reservationIdList.add((int) resultSet.getInt("reservationId"));
			}

		} catch (Exception exception)  {
			exception.printStackTrace();
		}
		return reservationIdList;
	}
	/**
	   * this method is used to get reservation IDs .
	   * @return the string success/invalid/failure/input
	   * @param beanObj to set
	   */
	public final List<Integer> getUserReservationIdDAO(final ReservationsBean beanObj) {
		try {
			connection = DBconnect.getConnection();
			preparedStatement = connection.prepareStatement("select reservationId from reservation_details where approved='Yes'and userId=? ");
			preparedStatement.setString(1, beanObj.getUserID());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())  {
				reservationIdList2.add((int) resultSet.getInt("reservationId"));
			}

		} catch (Exception exception)  {
			exception.printStackTrace();
		}
		return reservationIdList2;
	}
	/**
	   * this method is used to add lookups.
	   * @return the string success/invalid/failure/input
	   */
public final List<Integer> getReservationIdCancDAO() {

		try {
			connection = DBconnect.getConnection();
			statement = connection.createStatement();
			String sql = "select reservationId from reservation_details where approved = 'Pending'";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())  {
				reservationIdList.add((Integer) resultSet.getInt("reservationId"));
			}
		} catch (Exception exception)  {
			exception.printStackTrace();
		}
		return reservationIdList;
	}

}
