package ca.paruvendu.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.security.Role;
@Transactional
@Repository
public class UserRoleDAO implements IUserRoleDAO {

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		
	}

}
