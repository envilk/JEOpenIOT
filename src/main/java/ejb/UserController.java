package ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import entities.Usr;

@Named(value = "userController")
@RequestScoped
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Injected DAO EJB:
	@EJB
	private UserDAO userDao;
	
	private Usr user;
	
	private String passRepeat;

	public String register() throws NamingException {
		if(this.user != null && this.user.getuPassword().equals(passRepeat))
			this.userDao.storeUser(this.user);
		return "UserProfile";
	}
	
	public String save() throws NamingException {
		if(this.user != null)
			this.userDao.saveUser(this.user);
		return "UserProfile";
	}

	public Usr getUser() {
		if (this.user == null) {
			user = new Usr();
		}
		return user;
	}
	
	public Usr getUserByUname(String uname) {
		return this.userDao.getUserByUName(uname);
	}
	
}
