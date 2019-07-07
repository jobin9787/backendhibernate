package ca.paruvendu.service;

import java.util.List;

import ca.paruvendu.domain.GiveFree;

public interface IGiveFreeService {
	
	public GiveFree save(GiveFree giveFree);
	public List<GiveFree> findAll();
	public GiveFree findById(String id);

}
