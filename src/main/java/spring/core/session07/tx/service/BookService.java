package spring.core.session07.tx.service;

import spring.core.session07.tx.exception.InsufficientAmount;
import spring.core.session07.tx.exception.InsufficientQuantity;

public interface BookService {
	// 買一本書         誰要買 哪本書
	void buyOne(Integer wid , Integer bid) throws InsufficientQuantity,InsufficientAmount;
}
