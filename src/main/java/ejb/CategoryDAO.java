package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Category;

@Stateless
public class CategoryDAO {
	

    // Injected database connection:
	@PersistenceContext(unitName="JEOpenIOT")
    private EntityManager em;
	
    // Stores a new category:
    public void storeCategroy(Category cat) throws NamingException {
        em.persist(cat);
    }
    
    // Removes an existent category:
    public void removeCategory(Category cat) {
    	em.remove(cat);
    }

    // Retrieves all the categories:
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategroy() {
        Query query = em.createQuery("SELECT C FROM Category C");
        List<Category> cats = new ArrayList<Category>();
        cats = query.getResultList();
        return cats;
    }
    
    // Get categroy by ID
    public Category getCategory(Long id) {
    	Category cat = em.find(Category.class, id);
    	return cat;
    }
	
}
