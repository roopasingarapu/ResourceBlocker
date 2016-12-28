
package com.blocker.bean;

public class UsersBean {
	private String userID;
	private String groupID;
	private String password;
	private int loginStatus;

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
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	/**
	 * @param password1 the password to set
	 */
	public final void setPassword(final String password1) {
		this.password = password1;
	}
	/**
	 * @return the loginStatus
	 */
	public final int getLoginStatus() {
		return loginStatus;
	}
	/**
	 * @param loginStatus1 the loginStatus to set
	 */
	public final void setLoginStatus(final int loginStatus1) {
		this.loginStatus = loginStatus1;
	}

}
