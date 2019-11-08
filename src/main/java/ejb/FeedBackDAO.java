package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.FeedBack;

@Stateless
public class FeedBackDAO {
	
    // Injected database connection:
	@PersistenceContext(unitName="JEOpenIOT")
    private EntityManager em;
	
    // Stores a new Feed back:
    public void storeFeedback(FeedBack fb/*, int id*/) throws NamingException{
    	em.persist(fb);
    	/*Query query = em.createNativeQuery("INSERT INTO IoTDevice VALUES ("+fb.getId()+", "
    			+fb.getComment()+", "+fb.getRating()+", "+id+")");
		query.executeUpdate();*/
    }
    
    // Removes an existent feedback:
    public void removeFeedback(FeedBack fb) {
    	em.remove(fb);
    }
    
    // Get feedback by ID
    public FeedBack getFeedback(int id) {
    	FeedBack fb = em.find(FeedBack.class, id);
    	return fb;
    }
    
    public FeedBack getLastFeedBackId() {
		Query query = em.createNativeQuery("select * from FeedBack where ID in (select max(ID) from FeedBack)", FeedBack.class);
		return (FeedBack) query.getSingleResult();
	}
    
    public void storeFBdevice(int fbid, int iotid) {
    Query query = em.createNativeQuery(
			"UPDATE FeedBack SET listOfFeedback="+iotid
					+ " WHERE id="+fbid);
	query.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
	public List<FeedBack> getFeedBackOfADevice(int id) {
    	Query query = em.createNativeQuery("select * from FeedBack where listOfFeedback="+id, FeedBack.class);
		List<FeedBack> list= new ArrayList<FeedBack>(query.getResultList());
		return list;
    }

}
