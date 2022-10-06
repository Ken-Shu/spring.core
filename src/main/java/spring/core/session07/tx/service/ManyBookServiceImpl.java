package spring.core.session07.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.core.session07.tx.exception.InsufficientAmount;
import spring.core.session07.tx.exception.InsufficientQuantity;

@Service
public class ManyBookServiceImpl implements ManyBookService{
	
	@Autowired
	private BookService bookService;

	
	@Override
	public void buyMany(Integer wid, Integer... bids) throws InsufficientQuantity , InsufficientAmount {
		for(Integer bid : bids) {
			bookService.buyOne(wid, bid);
		}
	}
}
