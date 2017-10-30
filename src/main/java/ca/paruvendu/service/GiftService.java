package ca.paruvendu.service;

import java.util.List;

import ca.paruvendu.domain.Gift;

public interface GiftService {
	
	public List<Gift> findAll();
	public Gift findById(long id);
	public Gift save(Gift gift);
	public Gift blurrySearch(String descr);
	public void removeGift(long id);
	
	

}
