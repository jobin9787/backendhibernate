package ca.paruvendu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.paruvendu.dao.IGiveFreeDAO;
import ca.paruvendu.domain.GiveFree;
import ca.paruvendu.service.IGiveFreeService;

@Service
public class GiveFreeServiceImpl implements IGiveFreeService {
	@Autowired
	IGiveFreeDAO giveFreeDAO;

	@Override
	public GiveFree save(GiveFree giveFree) {
		
		return giveFreeDAO.save(giveFree);
	}

	@Override
	public List<GiveFree> findAll() {
		
		return giveFreeDAO.findAll();
	}

	@Override
	public GiveFree findById(String id) {
		
		return giveFreeDAO.findById(id);
	}

}
