package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.Carad;

public interface ICaradDAO {
	
	
	public	Carad save(Carad carad);
	public List<Carad> findAll();
	public Carad findById(String id);

}
