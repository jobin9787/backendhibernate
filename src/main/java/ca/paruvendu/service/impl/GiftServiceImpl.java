package ca.paruvendu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.paruvendu.domain.Gift;
import ca.paruvendu.repository.GiftRepository;
import ca.paruvendu.service.GiftService;

@Service
public class GiftServiceImpl implements GiftService {
	@Autowired
	private GiftRepository giftRepository;

	@Override
	public List<Gift> findAll() {
		List<Gift> giftList = (List<Gift>) giftRepository.findAll();
		return giftList;
	}

	@Override
	public Gift findById(long id) {
		return giftRepository.findOne(id);
		 
	}

	@Override
	public Gift save(Gift gift) {
		// TODO Auto-generated method stub
		return giftRepository.save(gift);
	}

	@Override
	public Gift blurrySearch(String descr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeGift(long id) {
		giftRepository.delete(id);

	}

}
