package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.GiveFree;
import ca.paruvendu.domain.Search;

public interface IGiveFreeDAO {
	
	public GiveFree save(GiveFree giveFree);
	public List<GiveFree> findAll();
	public GiveFree findById(String id);
	public List<GiveFree>   findByKeyword(Search search);
	
}
