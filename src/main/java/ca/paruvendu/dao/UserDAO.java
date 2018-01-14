package ca.paruvendu.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		String hql = "FROM User as user WHERE user.id = ?";
		return (User) entityManager.createQuery(hql).setParameter(1, id).getSingleResult();
//		return entityManager.find(User.class,id);
	}

	@Override
	public User findByUserName(String username) {
		String hql = "FROM User as user WHERE user.username = ?";
		return (User) entityManager.createQuery(hql).setParameter(1, username).getSingleResult();
	}
	
	@Override
	public User findByEmail(String email) {
		String hql = "FROM User as user WHERE user.email = ?";
		logger.info(hql.toString());
		Object obj = entityManager.createQuery(hql).setParameter(1, email).getSingleResult();
//		return (User) entityManager.createQuery(hql).setParameter(1, email).getSingleResult();
		return (User) obj;
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
