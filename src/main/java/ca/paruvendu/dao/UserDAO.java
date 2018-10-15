package ca.paruvendu.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.Carad;
import ca.paruvendu.domain.User;
import ca.paruvendu.domain.security.UserRole;

@Transactional
@Repository
public class UserDAO implements IUserDAO {
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findOne(Long id) {
		Query query = this.entityManager.createQuery("From User u where u.id=:id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
	}

	@Override
	public User findByUserName(String username) {
		logger.info("findByUserName--> "+ username);
		Query query = this.entityManager.createQuery("FROM User u where u.username=:username");
		User user =null;
		query.setParameter("username", username);
		try{
			user = (User)query.getSingleResult();
		   }
			catch (NoResultException nre){
				logger.info("NoResultException No user name --> "+ username);
	       }
		
        return user; 
	}
	
	@Override
	public User findByEmail(String email) {
		Query query = this.entityManager.createQuery("FROM User u WHERE u.email =:email");
		query.setParameter("email", email);
	    User user=null;
	    
		try{
			user = (User) query.getSingleResult();
		   }
			catch (NoResultException nre){
				logger.info("NoResultException No email --> "+ email);
	       }
		return user;
	}

	@Override
	public List<User> findAll() {
		String hql = "FROM User";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	  public  User save (User user){
		   entityManager.persist(user);
		   return findByUserName(user.getUsername());
	  }

	@Override
	public User createUser(User user, Set<UserRole> userRole) {
		// TODO Auto-generated method stub
		return null;
	};
}
