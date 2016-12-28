
package com.blocker.bean;

public class PasswordBean {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	/**
	 * @return the oldPassword
	 */
	public final String getOldPassword() {
		return oldPassword;
	}
	/**
	 * @param oldPassword1 the oldPassword to set
	 */
	public final void setOldPassword(final String oldPassword1) {
		this.oldPassword = oldPassword1;
	}
	/**
	 * @return the newPassword
	 */
	public final String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword1 the newPassword to set
	 */
	public final void setNewPassword(final String newPassword1) {
		this.newPassword = newPassword1;
	}
	/**
	 * @return the confirmPassword
	 */
	public final String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword1 the confirmPassword to set
	 */
	public final void setConfirmPassword(final String confirmPassword1) {
		this.confirmPassword = confirmPassword1;
	}

}
