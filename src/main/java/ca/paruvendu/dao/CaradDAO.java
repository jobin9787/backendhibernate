package ca.paruvendu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.Carad;
import ca.paruvendu.domain.Search;

@Transactional
@Repository
public class CaradDAO implements ICaradDAO {

	Logger logger = LoggerFactory.getLogger(CaradDAO.class);
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Carad save(Carad carad) {
		// TODO Auto-generated method stub
//		this.entityManager.getTransaction().begin();
	    entityManager.persist(carad);
//	    entityManager.getTransaction().commit();
//	    entityManager.close();  
		  return findById(carad.getId());
	}

	@Override
	public List<Carad> findAll() {
		 String hql = "FROM Carad as carad";
			return (List<Carad>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Carad findById(String id) {
		Query query = this.entityManager.createQuery("From Carad c where c.id=:id");
        query.setParameter("id", id);
        return (Carad) query.getSingleResult();
	}

	@Override
	public List<Carad> findByKeyword(Search search) {
		StringBuilder sb = new StringBuilder();		
		sb.append("From Carad c where 1=1 ");
		
		if(search.getElement1()!=null)
	 	 sb.append("and c.make =:make");
	 
		if(search.getElement2()!=null)
		 sb.append(" and c.model =:model");
		
				
		if(search.getElement3()!=null)
		 sb.append(" and c.price >=:element3");
		
		if(search.getElement4()!=null)
			sb.append(" and c.price <=:element4");
		
		if(search.getElement5()!=null)
			sb.append(" and c.kilometers >=:element5");
		
		if(search.getElement6()!=null)
			 sb.append(" and c.kilometers <=:element6");
		
		if(search.getElement7()!=null)
			sb.append(" and c.year >=:element7");
		
		if(search.getElement8()!=null)
			 sb.append(" and c.year <=:element8");
		
		if(search.getElement9()!=null)
			 sb.append(" and c.soldby =:element9");
		
		Query query = this.entityManager.createQuery(sb.toString());
		
		if(search.getElement1()!=null)
		query.setParameter("make", search.getElement1());
		
		if(search.getElement2()!=null)
		query.setParameter("model", search.getElement2());
		
		if(search.getElement3()!=null)
			query.setParameter("element3",  Integer.parseInt(search.getElement3()));
			
		if(search.getElement4()!=null)
			query.setParameter("element4", Integer.parseInt(search.getElement4()));
			
		if(search.getElement5()!=null)
			query.setParameter("element5", Integer.parseInt(search.getElement5()));
			
		if(search.getElement6()!=null)
			query.setParameter("element6", Integer.parseInt(search.getElement6()));
				
		if(search.getElement7()!=null)
			query.setParameter("element7", Integer.parseInt(search.getElement7()));
			
		if(search.getElement8()!=null)
			query.setParameter("element8", Integer.parseInt(search.getElement8()));
		
		if(search.getElement9()!=null)
			query.setParameter("element9", search.getElement9());
	 	
		logger.info(query.toString());
		 return (List<Carad>) query.getResultList();
	}

}
