package ejb;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entities.Usr;

/**
 * 
 * @author Alejandro Rodriguez
 * Dat250 course
 *
 *Session Controller for validate an user 
 */

@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injected DAO EJB:
	@EJB
	private UserDAO userDAO;

	private String password;

	private String username = null;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String validateUsernamePassword() throws IOException {
		HttpSession session = SessionUtils.getSession();
		Usr usr = null;
		try {usr = this.userDAO.getUserByUName(this.username);}
		catch(NoResultException e) {SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");}
		if(!usr.getuPassword().equals(this.password) || usr == null)
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		session.setAttribute(Constants.USERNAME, this.username);
		session.setAttribute(Constants.USERID, String.valueOf(usr.getId()));
		return Constants.USERPROFILE;
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return Constants.LOGIN;
	}

	public String redirectLoginUserProfile() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.USERPROFILE;
	}
	
	public boolean visible() {
		return true;
	}
	
}
