package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="usr")
public class Usr {
	
	public Usr() {}

	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "yourTableGeneratorUsr",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGeneratorUsr")
	private int id;

	private String uName;
	private String fName;
	private String iName;
	private String uPassword;
	private String address;
	private int phoneNumber;

	//SOURCE
	@OneToMany(mappedBy = "userOwner")
	private ArrayList<IoTDevice> devicesOwned;

	//SOURCE
	@ManyToMany(mappedBy ="userAccess")
	private ArrayList<IoTDevice> accessDevices;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public ArrayList<IoTDevice> getDevicesOwned() {
		return devicesOwned;
	}
	public void setDevicesOwned(ArrayList<IoTDevice> devicesOwned) {
		this.devicesOwned = devicesOwned;
	}
	public ArrayList<IoTDevice> getAccessDevices() {
		return accessDevices;
	}
	public void setAccessDevices(ArrayList<IoTDevice> accessDevices) {
		this.accessDevices = accessDevices;
	}


}
