
package com.blocker.bean;

import java.util.Date;

public class ReservationsBean {

	private int reservationID;
	private String resourceID;
	private String userID;
	private String groupID;
	private String type;
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private String reason;
	private String reservationDuration;
	private String approved;
	/**
	 * @return the approved
	 */
	public final String getApproved() {
		return approved;
	}
	/**
	 * @param approved1 the approved to set
	 */
	public final void setApproved(final String approved1) {
		this.approved = approved1;
	}
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
	 * @return the resourceID
	 */
	public final String getResourceID() {
		return resourceID;
	}
	/**
	 * @param resourceID1 the resourceID to set
	 */
	public final void setResourceID(final String resourceID1) {
		this.resourceID = resourceID1;
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
	 * @return the groupID
	 */
	public final String getGroupID() {
		return groupID;
	}
	/**
	 * @param groupID1 the groupID to set
	 */
	public final void setGroupID(final String groupID1) {
		this.groupID = groupID1;
	}
	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}
	/**
	 * @param type1 the type to set
	 */
	public final void setType(final String type1) {
		this.type = type1;
	}

	/**
	 * @return the startDate
	 */
	public final Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate1 the startDate to set
	 */
	public final void setStartDate(final Date startDate1) {
		this.startDate = startDate1;
	}
	/**
	 * @return the endDate
	 */
	public final Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate1 the endDate to set
	 */
	public final void setEndDate(final Date endDate1) {
		this.endDate = endDate1;
	}
	/**
	 * @return the startTime
	 */
	public final String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime1 the startTime to set
	 */
	public final void setStartTime(final String startTime1) {
		this.startTime = startTime1;
	}
	/**
	 * @return the endTime
	 */
	public final String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime1 the endTime to set
	 */
	public final void setEndTime(final String endTime1) {
		this.endTime = endTime1;
	}
	/**
	 * @return the reason
	 */
	public final String getReason() {
		return reason;
	}
	/**
	 * @param reason1 the reason to set
	 */
	public final void setReason(final String reason1) {
		this.reason = reason1;
	}
	/**
	 * @return the reservationDuration
	 */
	public final String getReservationDuration() {
		return reservationDuration;
	}
	/**
	 * @param reservationDuration1 the reservationDuration to set
	 */
	public final void setReservationDuration(final String reservationDuration1) {
		this.reservationDuration = reservationDuration1;
	}

}
