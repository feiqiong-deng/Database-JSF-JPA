package databank.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import databank.model.PersonPojo;

@Singleton
public class PersonService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LogManager.getLogger();

	@PersistenceContext(name = "PU_DataBank")
	protected EntityManager em;
	
	public PersonService() {
		
	}
	
	public List< PersonPojo> readAllPeople() {
		LOG.debug( "reading all people");
//		//use the named JPQL query from the PersonPojo class to grab all the people
		TypedQuery< PersonPojo> allPeopleQuery = em.createNamedQuery( PersonPojo.PERSON_FIND_ALL, PersonPojo.class);
//		//execute the query and return the result/s.
		return allPeopleQuery.getResultList();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery< PersonPojo> cq = cb.createQuery( PersonPojo.class);
//		cq.select( cq.from( PersonPojo.class));
//		return em.createQuery( cq).getResultList();
	}
	
	@Transactional
	public PersonPojo createPerson( PersonPojo person) {
		LOG.debug( "creating a person = {}", person);
		em.persist( person);
		return person;
	}

	public PersonPojo readPersonById( int personId) {
		LOG.debug( "read a specific person = {}", personId);
		return em.find( PersonPojo.class, personId);
	}

	@Transactional
	public PersonPojo updatePerson( PersonPojo personWithUpdates) {
		LOG.debug( "updating a specific person = {}", personWithUpdates);
		
		try {
			PersonPojo mergedPersonPojo = em.merge( personWithUpdates);
			return mergedPersonPojo;
		} catch(Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	@Transactional
	public void deletePersonById( int personId) {
		LOG.debug( "deleting a specific personID = {}", personId);
		PersonPojo person = readPersonById( personId);
		LOG.debug( "deleting a specific person = {}", person);
		if ( person != null) {
			em.refresh( person);
			em.remove( person);
		}
	}
	
}
