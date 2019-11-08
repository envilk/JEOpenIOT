package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.FeedBack;
import entities.IoTDevice;
import entities.Usr;

@Stateless
public class IoTDeviceDAO {
		
	    // Injected database connection:
		@PersistenceContext(unitName="JEOpenIOT")
	    private EntityManager em;
		
	    // Stores a new Iot device:
	    public void storeDevice(IoTDevice iotdevice) throws NamingException {
	        em.persist(iotdevice);
	    }
	    
	    // Removes an existent Iot device:
	    public void removeDevice(IoTDevice iotdevice) {
	    	em.remove(iotdevice);
	    }

	    // Retrieves all the Iot devices:
		@SuppressWarnings("unchecked")
		public List<IoTDevice> getAllDevices() {
	        Query query = em.createQuery("SELECT I FROM IoTDevice I");
	        List<IoTDevice> devices = new ArrayList<IoTDevice>();
	        devices = query.getResultList();
	        return devices;
	    }
	    
	    // Get device by ID
	    public IoTDevice getDevice(int id) {
	    	IoTDevice device = em.find(IoTDevice.class, id);
	    	return device;
	    }
	    
	    // Get all devices of a user
	    @SuppressWarnings("unchecked")
	    public List<IoTDevice> getAllDeviceOfAUser(int uid) {
	    	Query query = em.createNativeQuery("SELECT * FROM IoTDevice as I "
					+ "WHERE I.devicesOwned="+uid, IoTDevice.class);
			List <IoTDevice> devices = new ArrayList<IoTDevice>(query.getResultList());
			return devices;
	    }
	    
	    // Get all registrations of a device
	    @SuppressWarnings("unchecked")
	    public List<Usr> getAllDeviceRegistrations(Long id) {
	    	Query query = em.createNativeQuery("SELECT * FROM Usr as U JOIN access_iotdev_user as A ON U.id=A.user_fk "
					+ "WHERE A.iotdevice_fk="+id, Usr.class);
			List <Usr> Usrs = new ArrayList<Usr>(query.getResultList());
			return Usrs;
	    }
	    
	    // Get one specific registration of a device
	    @SuppressWarnings("unchecked")
		public List<Usr> getRegistrationDevice(Long id, Long uid) {
	    	Query query = em.createNativeQuery("SELECT * FROM Usr as U JOIN access_iotdev_user as A ON U.id=A.user_fk "
					+ "WHERE A.iotdevice_fk="+id+" AND U.id="+uid, Usr.class);
			List <Usr> Usrs = new ArrayList<Usr>(query.getResultList());
			return Usrs;
	    }

	    // Get one specific registration of a device
		public void createAccessForUser(int id, int uid) {
			Query query = em.createNativeQuery("INSERT INTO access_iotdev_user (iotdevice_fk, user_fk) "
					+ "VALUES ("+id+", "+uid+")");
			query.executeUpdate();
	    }
		
		// Get all devices with access allowed of a user
	    @SuppressWarnings("unchecked")
	    public List<IoTDevice> getAllDeviceAccessAllowedOfAUser(int uid) {
	    	Query query = em.createNativeQuery("SELECT Y.ID, Y.URL, Y.DNAME, Y.GLOBALRATING, Y.SERVICEDESC, Y.IOTDEV, DEVICESOWNED FROM IoTDevice as Y JOIN ACCESS_IOTDEV_USER as I  ON(y.ID = I.IOTDEVICE_FK) "
					+ "WHERE I.USER_FK="+uid, IoTDevice.class);
			List <IoTDevice> devices = new ArrayList<IoTDevice>(query.getResultList());
			return devices;
	    }
	    
	    public IoTDevice getLastIoTDeviceId() {
			Query query = em.createNativeQuery("select * from IoTDevice where ID in (select max(ID) from IoTDevice)", IoTDevice.class);
			return (IoTDevice) query.getSingleResult();
		}
	    
	 // Get all devices with access allowed of a user
        @SuppressWarnings("unchecked")
        public List<IoTDevice> getAllDeviceByName(String name) {
            
            Query query = em.createQuery("SELECT * FROM IOTDEVICE as I WHERE I.DNAME = '"+ name +"'");
            List<IoTDevice> devices = new ArrayList<IoTDevice>();
            devices = query.getResultList();
            return devices;
        }
	    
}
