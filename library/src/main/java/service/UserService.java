package service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.UserModel;
import repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userrepository;
	public void addUser(UserModel usermodel) {
		userrepository.addUser(usermodel);
	}
	public boolean loginUser(UserModel usermodel) {
		return userrepository.loginUser(usermodel);
	}
	
}
