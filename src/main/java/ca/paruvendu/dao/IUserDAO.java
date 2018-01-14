package ca.paruvendu.dao;

import java.util.List;
import java.util.Set;

import ca.paruvendu.domain.User;
import ca.paruvendu.domain.security.UserRole;

public interface IUserDAO {
	
	public User createUser(User user , Set<UserRole> userRole);
    public User findByUserName(String userName);
    public  User findByEmail(String email);
    public User save (User user);
    public User findOne(Long id);
	List<User> findAll();

}
