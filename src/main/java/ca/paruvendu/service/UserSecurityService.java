package ca.paruvendu.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.paruvendu.domain.User;
import ca.paruvendu.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	
	private static final Logger logger =  LoggerFactory.
			       getLogger(UserSecurityService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws 
	UsernameNotFoundException
	
	{
		User user = userRepository.findByUsername(username);
		if(null == user){
			logger.warn("User {} not found ", username);
			throw new UsernameNotFoundException("username "+ username +" not found");
		}
			return user;
	}
	

}
