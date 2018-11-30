package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.Appad;
import ca.paruvendu.domain.Search;

public interface IAppadDAO {
	
	
	public	Appad save(Appad appad);
	public List<Appad> findAll();
	public Appad findById(String id);
	public List<Appad> findByKeyword(Search search);

}
