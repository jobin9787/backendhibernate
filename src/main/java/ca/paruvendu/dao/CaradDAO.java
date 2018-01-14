package ca.paruvendu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ca.paruvendu.domain.Carad;

public class CaradDAO implements ICaradDAO {

	
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Carad save(Carad carad) {
		// TODO Auto-generated method stub
		  entityManager.persist(carad);
		  
		  return findById(carad.getId());
	}

	@Override
	public List<Carad> findAll() {
		 String hql = "FROM Carad as carad";
			return (List<Carad>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Carad findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
