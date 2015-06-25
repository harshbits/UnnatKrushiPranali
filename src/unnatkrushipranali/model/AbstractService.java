/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unnatkrushipranali.model;

import java.io.IOException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author harshbitss
 * @param <T>
 * @param <U>
 */
public abstract class AbstractService <T,U extends Serializable> implements Service<T,U> {
//public abstract class AbstractService<T extends Serializable> implements Service<T> {
    
   EntityManagerFactory emf;
    
   
   public AbstractService(String persistenceUnitName) throws IOException {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

    @Override
    public void remove(T item) {
        
        EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	em.remove(em.merge(item));
	em.getTransaction().commit();
	em.close();
       
    }

    @Override
    public void persist(T item) {
        
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public T merge(T item) {
        EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	T merged = em.merge(item);
	em.getTransaction().commit();
	em.close();
	return merged;
        
    }

    
}
