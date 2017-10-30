package ca.paruvendu.repository;

import org.springframework.data.repository.CrudRepository;

import ca.paruvendu.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

}
