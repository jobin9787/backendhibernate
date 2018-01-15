package ca.paruvendu.service;

import java.util.List;

import ca.paruvendu.domain.Carad;

public interface ICaradService {

	public	Carad save(Carad carad);
	public List<Carad> findAll();
	public Carad findById(String id);
	
}
