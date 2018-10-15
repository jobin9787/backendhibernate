package ca.paruvendu.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ca.paruvendu.dao.IUserDAO;
import ca.paruvendu.dao.IUserRoleDAO;
import ca.paruvendu.domain.User;
import ca.paruvendu.domain.security.UserRole;
import ca.paruvendu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

 
	@Autowired
	private IUserDAO usreDAO;
	
	@Autowired
	private IUserRoleDAO roleRepository;
	
//	@Transactional
	public User createUser(User user, Set<UserRole> userRole) {
		User localUser = usreDAO.findByUserName (user.getUsername());
		
        if(localUser!=null){
        	logger.info("User {} already exist" + user.getUsername());
        }else{
        	
        	for(UserRole ur : userRole)
        	{
        		roleRepository.save(ur.getRole());
        	}
        	
        	user.getUserRoles().addAll(userRole);
        	
        	localUser=usreDAO.save(user);
        }
		
		user.getUserRoles().addAll(userRole);
    	
		 localUser=usreDAO.save(user);
        
        return localUser;
	}

	@Override
	public User findByUserName(String username) {
		return usreDAO.findByUserName(username);
	}

	@Override
	public User findByEmail(String email) {
		return usreDAO.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return usreDAO.save(user);
	}

	@Override
	public User findOne(Long id) {
		return usreDAO.findOne(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return usreDAO.findAll();
	}
	
	

}
