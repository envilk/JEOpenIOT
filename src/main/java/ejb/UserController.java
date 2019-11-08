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

	private String uName;
	private String fName;
	private String iName;
	private String uPassword;
	private String address;
	private int phoneNumber;
	
	private String passRepeat;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassRepeat() {
		return passRepeat;
	}

	public void setPassRepeat(String passRepeat) {
		this.passRepeat = passRepeat;
	}

	public String register() throws NamingException {
		if(this.uPassword != null && this.uPassword.equals(passRepeat))
			this.userDao.storeUser(createUsr());
		return "UserProfile";
	}
	
	public String save() throws NamingException {
		if(this.uName != null)
			this.userDao.saveUser(createUsr());
		return "UserProfile";
	}
	
	public String removeUser() {
		if(this.uName != null)
			this.userDao.removeUser();
		return "Login";
	}
	
	private Usr createUsr() {
		Usr usr = new Usr();
		usr.setId(this.userDao.getLastUserId().getId()+1);
		usr.setAddress(address);
		usr.setfName(fName);
		usr.setiName(iName);
		usr.setPhoneNumber(phoneNumber);
		usr.setuName(uName);
		usr.setuPassword(uPassword);
		return usr;
	}
	
	public Usr getUserByUname(String uname) {
		return this.userDao.getUserByUName(uname);
	}
	
}
