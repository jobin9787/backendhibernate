package ca.paruvendu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.GiveFree;
import ca.paruvendu.domain.Search;

@Transactional
@Repository
public class GiveFreeDAO implements IGiveFreeDAO {

	Logger logger = LoggerFactory.getLogger(GiveFreeDAO.class);
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public GiveFree save(GiveFree giveFree) {
		entityManager.persist(giveFree);
		return findById(giveFree.getId());
	}

	@Override
	public List<GiveFree> findAll() {
		Query query = this.entityManager.createQuery("From GiveFree");
		return query.getResultList();
	}

	@Override
	public GiveFree findById(String id) {
		Query query = this.entityManager.createQuery("From GiveFree g where g.id=:id");
		query.setParameter("id", id);
		return (GiveFree) query.getSingleResult();
	}

	@Override
	public List<GiveFree> findByKeyword(Search search) {
		// TODO Auto-generated method stub
		return null;
	}

}
