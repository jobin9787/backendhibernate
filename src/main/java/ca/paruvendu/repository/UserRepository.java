package ca.paruvendu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.paruvendu.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findAll(); 
	
	

}
