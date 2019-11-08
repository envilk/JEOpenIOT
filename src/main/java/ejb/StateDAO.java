package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.State;

@Stateless
public class StateDAO {

    // Injected database connection:
	@PersistenceContext(unitName="JEOpenIOT")
    private EntityManager em;
	
    // Stores a new state:
    public void storeState(State state) throws NamingException {
        em.persist(state);
    }
    
    // Removes an existent state:
    public void removeState(State state) {
    	em.remove(state);
    }

    // Retrieves all the states:
	@SuppressWarnings("unchecked")
	public List<State> getAllStates() {
        Query query = em.createQuery("SELECT s FROM State s");
        List<State> states = new ArrayList<State>();
        states = query.getResultList();
        return states;
    }
    
    // Get state by ID
    public State getState(Long id) {
    	State state = em.find(State.class, id);
    	return state;
    }
	
}
