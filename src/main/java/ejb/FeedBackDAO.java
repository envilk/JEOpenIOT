package ejb;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.FeedBack;

@Stateless
public class FeedBackDAO {
	
    // Injected database connection:
	@PersistenceContext(unitName="JEOpenIOT")
    private EntityManager em;
	
    // Stores a new Feed back:
    public void storeFeedback(FeedBack fb) throws NamingException{
        em.persist(fb);
    }
    
    // Removes an existent feedback:
    public void removeFeedback(FeedBack fb) {
    	em.remove(fb);
    }
    
    // Get feedback by ID
    public FeedBack getFeedback(Long id) {
    	FeedBack fb = em.find(FeedBack.class, id);
    	return fb;
    }

}
