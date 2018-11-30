package ca.paruvendu.service;

import java.util.List;

import ca.paruvendu.domain.Appad;
import ca.paruvendu.domain.Search;

public interface IAppadService {

	public	Appad save(Appad carad);
	public  List<Appad> findAll();
	public  Appad findById(String id);
	public  List<Appad> findByKeyword(Search search);
	
}
