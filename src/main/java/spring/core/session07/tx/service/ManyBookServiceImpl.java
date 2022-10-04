package spring.core.session07.tx.service;

import org.springframework.stereotype.Service;

@Service
public class ManyBookServiceImpl implements ManyBookService{
	
	private BookService bookService;

	@Override
	public void buyMany(Integer wid, Integer... bids) {
		for(Integer bid : bids) {
			bookService.buyOne(wid, bid);
		}
	}
}
