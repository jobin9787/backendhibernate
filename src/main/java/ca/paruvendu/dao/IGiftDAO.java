package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.Gift;

public interface IGiftDAO {

	Gift findOne(long id);

	Gift save(Gift gift);

	void delete(long id);

	List<Gift> findAll();

}
