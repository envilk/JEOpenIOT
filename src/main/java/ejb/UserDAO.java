package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Usr;

@Stateless
public class UserDAO {

	// Injected database connection:
	@PersistenceContext(unitName="JEOpenIOT")
	private EntityManager em;

	// Stores a new user:
	public void storeUser(Usr user) throws NamingException {
		em.persist(user);
	}

	// Save a user:
	public void saveUser(Usr user) throws NamingException {
		Usr user2 = getUserByUName(SessionUtils.getUserName());
		Query query = em.createNativeQuery(
				"UPDATE Usr SET uName ='"+user.getuName()+"', iName ='"+user.getiName()+"', fName ='"+user.getfName()+"',"
						+ " address ='"+user.getAddress()+"', phoneNumber ="+user.getPhoneNumber()+", uPassword ='"+user.getuPassword()
						+ "' WHERE id="+user2.getId());
		query.executeUpdate();
		SessionUtils.destroySession();
	}

	// Removes an existent user:
	public void removeUser() {
		Usr user = getUserByUName(SessionUtils.getUserName());
		em.remove(user);
		SessionUtils.destroySession();
	}

	// Retrieves all the users:
	@SuppressWarnings("unchecked")
	public List<Usr> getAllUsers() {
		Query query = em.createQuery("SELECT U FROM Usr U");
		List<Usr> users = new ArrayList<Usr>();
		users = query.getResultList();
		return users;
	}

	// Get User by ID
	public Usr getUser(Long id) {
		Usr user = em.find(Usr.class, id);
		return user;
	}

	// Get User by user name
	public Usr getUserByUName(String username) throws NoResultException{
		/*TypedQuery<Usr> query = em.createQuery("SELECT U FROM Usr U WHERE U.uName = :username", Usr.class);
    	return query.setParameter("username", username).getSingleResult();*/
		Query query = em.createNativeQuery("SELECT * FROM Usr as U WHERE U.uName LIKE '"+username+"'", Usr.class);
		return (Usr) query.getSingleResult();
	}

	public Usr getLastUserId() {
		Query query = em.createNativeQuery("select * from Usr where ID in (select max(ID) from Usr)", Usr.class);
		return (Usr) query.getSingleResult();
	}

}
