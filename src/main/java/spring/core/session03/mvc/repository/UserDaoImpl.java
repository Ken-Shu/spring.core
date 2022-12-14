package spring.core.session03.mvc.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import spring.core.session03.mvc.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private List<User> users = new CopyOnWriteArrayList<User>(); 
	
	@Override
	public void add(User user) {
		System.out.println("Repository(Dao) - add user : " + user);
		users.add(user);
	}

	@Override
	public List<User> findAll() {

		return users;
	}
	
}
