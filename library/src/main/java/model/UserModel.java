package model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class UserModel {
	private int id;
	private String phoneNumber;
	private String passWord;
	private String userName;
	private LocalDateTime registrationTime;
	private LocalDateTime lastLoginTime;
	private String Salt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}
	
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastloginTime) {
		this.lastLoginTime = lastloginTime;
	}
	
	public String getsalt() {
		return Salt;
	}
	public void setsalt(String salt) {
		this.Salt = salt;
	}
}
