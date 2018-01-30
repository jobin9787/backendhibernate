package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.Carad;
import ca.paruvendu.domain.Search;

public interface ICaradDAO {
	
	
	public	Carad save(Carad carad);
	public List<Carad> findAll();
	public Carad findById(String id);
	public List<Carad> findByKeyword(Search search);

}
