package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.ServletException;

import entities.IoTDevice;

@Named(value = "iotdeviceController")
@RequestScoped
public class IoTDeviceController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Injected DAO EJB:
	@EJB
	private IoTDeviceDAO iotdeviceDao;
	
	private int iotdevice;
	
	public int getIotdevice() {
		return iotdevice;
	}

	public void setIotdevice(int iotdevice) {
		this.iotdevice = iotdevice;
	}

	private String dName;
	private String URL;
	private String serviceDesc;
	private float globalRating;
	
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

	public List<IoTDevice> getIotdevicesOfAUser() {
		int uid = Integer.parseInt((String) SessionUtils.getUserId());
		return this.iotdeviceDao.getAllDeviceOfAUser(uid);
	}

	public List<IoTDevice> getIotdevicesAccessAllowedOfAUser() {
		int uid = Integer.parseInt((String) SessionUtils.getUserId());
		return this.iotdeviceDao.getAllDeviceAccessAllowedOfAUser(uid);
	}

	public String seeDevice(int id) throws ServletException, IOException {
		IoTDevice iotdevice = this.iotdeviceDao.getDevice(id);
		setIotdevice(id);
		setdName(iotdevice.getdName());
		setGlobalRating(iotdevice.getGlobalRating());
		setServiceDesc(iotdevice.getServiceDesc());
		setURL(iotdevice.getURL());
		return "DeviceProfile";
	}
}
