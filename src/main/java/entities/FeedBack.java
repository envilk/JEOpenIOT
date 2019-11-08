package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="feedback")
public class FeedBack {
	
	public FeedBack() {}
	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "yourTableGeneratorFeedB",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGeneratorFeedB")
	private int id;
	
	private String comment;
	private float rating;
	
	//TARGET
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "listOfFeedback")
	private IoTDevice feedbackIotdevice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public IoTDevice getFeedbackIotdevice() {
		return feedbackIotdevice;
	}
	public void setFeedbackIotdevice(IoTDevice feedbackIotdevice) {
		this.feedbackIotdevice = feedbackIotdevice;
	}
	
	
}
