package ca.paruvendu.repository;

import org.springframework.data.repository.CrudRepository;

import ca.paruvendu.domain.Gift;

public interface GiftRepository extends CrudRepository<Gift,Long>  {

}
