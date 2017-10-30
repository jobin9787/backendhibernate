package ca.paruvendu.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import ca.paruvendu.domain.User;
import ca.paruvendu.domain.security.UserRole;

@Service
public interface UserService {
	
	public User createUser(User user , Set<UserRole> userRole);

}
