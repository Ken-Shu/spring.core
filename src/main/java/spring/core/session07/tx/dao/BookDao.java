package spring.core.session07.tx.dao;

import spring.core.session07.tx.exception.InsufficientAmount;
import spring.core.session07.tx.exception.InsufficientQuantity;

public interface BookDao {
	Integer getBookPrice(Integer bid); 		// 根據 bid 取得書本價格
	Integer getStockAmount(Integer bid); 	// 根據 bid 取得該書本庫存數量
	Integer getWalletMoney(Integer wid);	// 根據 wid 取得 可用餘額
	Integer updateStock(Integer bid , Integer amount) throws InsufficientQuantity;	// 修改庫存 減去 amount
	Integer updatWallet(Integer wid , Integer money) throws InsufficientAmount; 	// 變更錢包餘額 減去 money
}
