
package com.blocker.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.blocker.dao.AdminDAO;

public class LookupsAutocompleter extends ActionSupport  {
	
	private static final long serialVersionUID = 1L;
	private List<String> state;
	/**
	   * this method is used to retrieve all the locations.
	   * @return the string success
	   * @throws Exception when any checked exceptions are caught
	   */
	public final String execute() throws Exception  {
		Map<String, Object> session = ActionContext.getContext().getSession();
		  AdminDAO daoObj = new AdminDAO();
		  state = daoObj.fetchLocation();
		  session.put("State", state);
	  	  return "success";
	  }
	/**
	   * this method is used to get the state.
	   * @return the string state
	   */
	  public final List<String> getState()  {
		  return state;
	  }

}
