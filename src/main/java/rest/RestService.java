package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ejb.SessionUtils;
import entities.IoTDevice;
import entities.IoTDevices;
import entities.Usr;

//To test rest operations use the url http://localhost:8080/Dat250Example0/rest/tweets

/**
 * @Author Enrique Vilchez and Jose Juan Peña
 * Dat250
 * 
 * REST operations examples
 * 
 */

@Path("/devices")
@Stateless
public class RestService {

	@Context
	private UriInfo uriInfo;
	
	@PersistenceContext(unitName = "JEOpenIOT")
	private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIoTDevices() {
		TypedQuery<IoTDevice> query = em.createNamedQuery(IoTDevice.FIND_ALL, IoTDevice.class);
		IoTDevices iotdevices = new IoTDevices(query.getResultList());
		return Response.ok(iotdevices.getIoTDevices()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIoTDevice(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		IoTDevice iotdevice = em.find(IoTDevice.class, idInt);
		if (iotdevice == null)
			throw new NotFoundException();
		return Response.ok(iotdevice).build();
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("{id}/registrations/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIoTDeviceRegistrations(@PathParam("id") String id) {
		Query query = em.createNativeQuery("SELECT * FROM Usr as U JOIN access_iotdev_user as A ON U.id=A.user_fk "
				+ "WHERE A.iotdevice_fk="+id);
		List <Usr> Usrs = new ArrayList<Usr>(query.getResultList());
		return Response.ok(Usrs).build();
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("{id}/registrations/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIoTDeviceRegistration(@PathParam("id") String id, @PathParam("uid") String uid) {
		Query query = em.createNativeQuery("SELECT * FROM Usr as U JOIN access_iotdev_user as A ON U.id=A.user_fk "
				+ "WHERE A.iotdevice_fk="+id+" AND U.id="+uid);
		List <Usr> Usrs = new ArrayList<Usr>(query.getResultList());
		return Response.ok(Usrs).build();
	}
	
	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIoTDevice(@PathParam("id") String id) {
		HttpServletRequest req = SessionUtils.getRequest();
		Usr usr = (Usr) req.getAttribute("usr");
		TypedQuery<Usr> query = em.createNamedQuery("INSERT INTO access_iotdev_user (iotdevice_fk, user_fk) "
				+ "VALUES (':id', ':uid')", Usr.class);
		query.setParameter("id", id).setParameter("uid", usr.getId()).getResultList();
		java.net.URI bookUri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
		return Response.created(bookUri).build();
	}
}
