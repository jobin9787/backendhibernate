package ca.paruvendu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.Appad;
import ca.paruvendu.domain.Carad;
import ca.paruvendu.domain.Search;


@Transactional
@Repository
public class AppadDAO implements IAppadDAO {

	Logger logger = LoggerFactory.getLogger(AppadDAO.class);
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public Appad save(Appad appad) {
		  entityManager.persist(appad);
		  return findById(appad.getId());
	}

	@Override
	public List<Appad> findAll() {
		 String hql = "FROM Appad as appad";
		return (List<Appad>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Appad findById(String id) {
		Query query = this.entityManager.createQuery("From Appad a where a.id=:id");
        query.setParameter("id", id);
        return (Appad) query.getSingleResult();
	}

	@Override
	public List<Appad> findByKeyword(Search search) {

		StringBuilder sb = new StringBuilder();		
		sb.append("From Appad a where 1=1 ");
		if(search.getElement1()!=null)
		 	 sb.append("and a.room =:room");
		
		 if(search.getElement2()!=null)
		 {
			 switch(search.getElement2()){
			 case "1":  sb.append(" and a.postalcode like 'H1A%'");
			         break;
			 case "2":  sb.append(" and a.postalcode like 'H2A%' or a.postalcode like 'H1Z%'");
	         break;
			 case "5":  sb.append(" and a.postalcode like 'H1H%' or a.postalcode like 'H1G%'");
	         break;
			 
			 	 
			 }
		 }
		 
		 if(search.getElement3()!=null)
		 {
			 sb.append(" and a.price >=:minprice");
		 }
		 
		 
		 if(search.getElement4()!=null)
		 {
			 sb.append(" and a.price <=:maxprice");
		 }
		 
		Query query = this.entityManager.createQuery(sb.toString());
		
		if(search.getElement1()!=null)
			query.setParameter("room", search.getElement1());
		
		
		 if(search.getElement3()!=null)
		   query.setParameter("minprice",Integer.parseInt(search.getElement3()) );
		 
		 
		 
		 if(search.getElement4()!=null)
		   query.setParameter("maxprice", Integer.parseInt(search.getElement4()) );
			
		 
		
		  return (List<Appad>) query.getResultList();
	}



}
