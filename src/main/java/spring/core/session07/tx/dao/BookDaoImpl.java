package spring.core.session07.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.core.session07.tx.exception.InsufficientAmount;
import spring.core.session07.tx.exception.InsufficientQuantity;

@Repository
public class BookDaoImpl implements BookDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getBookPrice(Integer bid) {
		String sql = "select price from book where bid=?";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class , wid);
	}

	// 減去庫存
	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity{ // amount 欲採購的數量
		// 1. 先確認該書籍的目前的庫存量
		Integer currentamount = getStockAmount(bid);
		// 2. 判斷庫存是否足夠 購買
		if(currentamount < amount) {
			String msg = String.format("書號 : %d 庫存不足 , 目前庫存 : %d , 欲採購的數量 : %d\n", bid,currentamount,amount);
			throw new InsufficientQuantity(msg);
		}
		// 3. 若庫存足夠 進行庫存修改作業
		String sql ="update stock set amount=amount-? where bid=?";
		
		return jdbcTemplate.update(sql,amount,bid);
	}

	// 減去餘額
	@Override
	public Integer updatWallet(Integer wid, Integer money) throws InsufficientAmount{ // 書本的金額
		// 1. 先確認 該客戶目前的帳戶餘額
		Integer currentmoney = getWalletMoney(wid);
		// 2. 判斷是否有足夠的錢購買
		if(currentmoney < money) {
			String msg = String.format("錢包號 : %d 餘額不足 , 目前餘額 : %d , 欲採購的金額 : %d\n", wid,currentmoney,money);
			throw new InsufficientAmount(msg);			
		}
		// 3. 若金額足夠 進行餘額修改作業
		String sql = "update wallet set money=money-? where wid=?";
		return jdbcTemplate.update(sql , money,wid);
	}
}
