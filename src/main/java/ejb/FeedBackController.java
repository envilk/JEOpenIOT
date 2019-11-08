package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import entities.FeedBack;

@Named(value = "feedBackController")
@RequestScoped
public class FeedBackController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Injected DAO EJB:
	@EJB
	private FeedBackDAO feedBackDao;
	
	@EJB
	private IoTDeviceDAO iotdeviceDAO;
	
	private String comment;
	private float rating;
	private int id;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String createFeedBack(int iotdevice) throws NamingException {
		this.feedBackDao.storeFeedback(createFB(), iotdevice);
		//this.feedBackDao.storeFBdevice(id, iotdevice);
		return "DeviceProfile";
	}
	
	private FeedBack createFB() {
		FeedBack fb = new FeedBack();
		id = this.feedBackDao.getLastFeedBackId().getId()+1;
		fb.setId(id);
		fb.setComment(comment);
		fb.setRating(rating);;
		return fb;
	}
	
	public List<FeedBack> getFeedBackOfADevice(int id){
		return this.feedBackDao.getFeedBackOfADevice(id);
	}

}
