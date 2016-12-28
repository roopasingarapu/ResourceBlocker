package com.blocker.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.blocker.bean.LoginBean;
/**
*this class is used for session validations.
*/
public class Invalidate extends ActionSupport  {
	
	private static final long serialVersionUID = 1L;
	private LoginBean beabObj = new LoginBean();
private String response;
/**
*this method is used for session validations.
*@return response.
*/
	public final String sessionInvalid()  {
	Map<String, Object> session = ActionContext.getContext().getSession();
	session.put("Userid", beabObj.getUserID());
	session.put("Password", beabObj.getPassword());
	session.put("Usertype", beabObj.getUserType());
	  String uSERID = (String) session.get("userID");
	  String pASSWORD = (String) session.get("password");
	  String uSERTYPE = (String) session.get("userType");


	  if (uSERID == null || pASSWORD == null || uSERTYPE == null)  {
		  addActionError("Your session was expired, Please Login again");
		response = "session";
	  } else {
		  response = "pass";
	  }
	return response;
	}
}
