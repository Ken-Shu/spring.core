package spring.core.session03.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.core.session03.mvc.model.User;
import spring.core.session03.mvc.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	// 自動綁定 自動會尋找有實作 UserDao 介面的類別 並且建立該類別的實體物件
	// 以本案來說會自動找到 UserDaoImpl 類別 ， 並且自動透過 new UserDaoImpl() 建立物件	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void append(User user) {
		// 驗證 User 資料
		System.out.println("Service - append user : " + user);
		// 新增 User
		userDao.add(user); 
	}

	@Override
	public List<User> queryAll() {
		
		return userDao.findAll(); // 查詢 user
	}
	

}
