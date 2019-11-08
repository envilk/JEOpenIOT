package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.IoTDevice;
import entities.Usr;

@Stateless
public class IoTDeviceDAO {
		
	    // Injected database connection:
		@PersistenceContext(unitName="JEOpenIOT")
	    private EntityManager em;
		
	    // Stores a new Iot device:
	    public void storeDevice(IoTDeviceDAO iotdevice) throws NamingException {
	        em.persist(iotdevice);
	    }
	    
	    // Removes an existent Iot device:
	    public void removeDevice(IoTDeviceDAO iotdevice) {
	    	em.remove(iotdevice);
	    }

	    // Retrieves all the Iot devices:
		@SuppressWarnings("unchecked")
		public List<IoTDeviceDAO> getAllDevices() {
	        Query query = em.createQuery("SELECT I FROM IoTDevice I");
	        List<IoTDeviceDAO> devices = new ArrayList<IoTDeviceDAO>();
	        devices = query.getResultList();
	        return devices;
	    }
	    
	    // Get device by ID
	    public IoTDeviceDAO getDevice(Long id) {
	    	IoTDeviceDAO device = em.find(IoTDeviceDAO.class, id);
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
		public void createAccessForUser(Long id, Long uid) {
			TypedQuery<Usr> query = em.createNamedQuery("INSERT INTO access_iotdev_user (iotdevice_fk, user_fk) "
					+ "VALUES (':id', ':uid')", Usr.class);
			query.setParameter("id", id).setParameter("uid", uid).getResultList();
			query.executeUpdate();
	    }

	    
}
