package ca.paruvendu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.paruvendu.dao.IAppadDAO;
import ca.paruvendu.domain.Appad;
import ca.paruvendu.domain.Search;
import ca.paruvendu.service.IAppadService;

@Service
public class AppadService implements IAppadService {

	@Autowired
	private IAppadDAO appDao;
	@Override
	public Appad save(Appad appad) {
		// TODO Auto-generated method stub
		return appDao.save(appad);
	}

	@Override
	public List<Appad> findAll() {
		// TODO Auto-generated method stub
		return appDao.findAll();
	}

	@Override
	public Appad findById(String id) {
		// TODO Auto-generated method stub
		return appDao.findById(id);
	}
	
	@Override
	public List<Appad> findByKeyword(Search search) {
		// TODO Auto-generated method stub
		return appDao.findByKeyword(search);
	}

}
