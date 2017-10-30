package ca.paruvendu.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.paruvendu.domain.Gift;
import ca.paruvendu.service.GiftService;

@RestController
@RequestMapping("/gift")
public class GiftController {
	
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Gift addGiftPost(@RequestBody Gift gift){
		return giftService.save(gift);
	}
	

}
