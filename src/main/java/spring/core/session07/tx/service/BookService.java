package spring.core.session07.tx.service;


public interface BookService {
	// 買一本書         誰要買 哪本書
	void buyOne(Integer wid , Integer bid);
}
