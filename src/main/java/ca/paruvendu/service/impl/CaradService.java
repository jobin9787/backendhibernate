package ca.paruvendu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.paruvendu.dao.ICaradDAO;
import ca.paruvendu.domain.Carad;
import ca.paruvendu.service.ICaradService;

@Service
public class CaradService implements ICaradService {

	@Autowired
	ICaradDAO caradDAO;
	
	@Override
	public Carad save(Carad carad) {
		// TODO Auto-generated method stub
		return caradDAO.save(carad);
	}

	@Override
	public List<Carad> findAll() {
		// TODO Auto-generated method stub
		return caradDAO.findAll();
	}

	@Override
	public Carad findById(String id) {
		// TODO Auto-generated method stub
		return caradDAO.findById(id);
	}

}
