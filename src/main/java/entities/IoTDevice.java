package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="iotdevice")
@NamedQuery(name="IoTDevice.findAll", query="SELECT i FROM IoTDevice i")
public class IoTDevice {
	
	public static final String FIND_ALL = "IoTDevice.findAll";

	public IoTDevice() {}
	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "yourTableGeneratorIoTDev",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGeneratorIoTDev")
	private int id;

	private String dName;
	private String URL;
	private String serviceDesc;
	private float globalRating;

	//SOURCE
	@ManyToMany(mappedBy ="iotdevCategorized")
	private ArrayList<Category> categories;

	//SOURCE
	@OneToMany(mappedBy = "feedbackIotdevice")
	private ArrayList<FeedBack> listOfFeedback;

	//TARGET
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "devicesOwned")
	private Usr userOwner;

	//TARGET
	@ManyToMany
	@JoinTable(name = "access_iotdev_user",
	joinColumns = @JoinColumn(name = "iotdevice_fk"),
	inverseJoinColumns = @JoinColumn(name = "user_fk"))
	private ArrayList<Usr> userAccess;

	//SOURCE
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iotdev")
	private State state;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public float getGlobalRating() {
		return globalRating;
	}

	public void setGlobalRating(float globalRating) {
		this.globalRating = globalRating;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<FeedBack> getListOfFeedback() {
		return listOfFeedback;
	}

	public void setListOfFeedback(ArrayList<FeedBack> listOfFeedback) {
		this.listOfFeedback = listOfFeedback;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
