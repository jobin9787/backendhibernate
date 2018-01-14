package ca.paruvendu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.Gift;
@Transactional
@Repository
public class GiftDAO implements IGiftDAO {

	@Override
	public Gift findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gift save(Gift gift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Gift> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
