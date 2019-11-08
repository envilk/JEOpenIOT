package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
	
	public List<IoTDevice> getIotdevicesOfAUser() {
		int uid = Integer.parseInt((String) SessionUtils.getUserId());
		return this.iotdeviceDao.getAllDeviceOfAUser(uid);
	}
}
