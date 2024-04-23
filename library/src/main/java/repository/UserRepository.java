package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.UserModel;

@Repository
public class UserRepository {
	
	public static String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
	
	public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public class UserModelRowMapper implements RowMapper<UserModel> {
	    
	    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	        UserModel user = new UserModel();
	        user.setPassWord(rs.getString("Password"));
	        user.setsalt(rs.getString("Salt"));
	        // 还可以设置其他属性...
	        return user;
	    }
	}
	
	
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	public void addUser(UserModel usermodel) {
		System.out.println("EXCUTE INSERT USER");
		int saltLength = 16;
		String salt = generateSalt(saltLength);
		String hashed_password = sha256(usermodel.getPassWord()+salt);
		jdbctemplate.update("INSERT INTO User(Phone_number, Password, User_name, Registration_time, Last_login_time, Salt)"+"VALUES(?,?,?,?,?,?)"
		,usermodel.getPhoneNumber(),hashed_password,usermodel.getUserName(),usermodel.getRegistrationTime(),usermodel.getLastLoginTime(),salt);
	}
	
	public boolean loginUser(UserModel usermodel) {
		System.out.println("EXCUTE LOGIN USER");
		UserModel user = jdbctemplate.queryForObject("SELECT Password, Salt FROM User WHERE User_name=?", new UserModelRowMapper(),usermodel.getUserName());
		if (user.getPassWord().equals(sha256(usermodel.getPassWord()+user.getsalt())))
		{
			return true;
		}
		return false;
	}

}
