package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import model.UserModel;
import service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
	
	@Autowired
	UserModel usermodel;
	@Autowired
	UserService userservice;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserModel usermodel) {
		LocalDateTime now = LocalDateTime.now();
		usermodel.setlastloginTime(null);
		usermodel.setregistrationTime(now);
		usermodel.setpassWord(usermodel.getpassWord());
		usermodel.setphoneNumber(usermodel.getphoneNumber());
		usermodel.setuserName(usermodel.getuserName());
		userservice.addUser(usermodel);
		return "User added";
	}
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/checkLogin")
	public ResponseEntity<String> checklogin(HttpServletResponse response, @RequestBody String sessionId) {
		String sessionIdValue = "";
		try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sessionId);
        
        sessionIdValue = jsonNode.get("sessionId").asText();
        System.out.println("Received sessionId: " + sessionIdValue);
		}
        catch (Exception e) {
            System.err.println("Error connecting to Redis: " + e.getMessage());
        }
		if (stringRedisTemplate.hasKey("spring:session:sessions:"+sessionIdValue)) {
			System.out.println("already login");
            return ResponseEntity.ok("already login");
        } else {
        	System.out.println("user not not login");
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not login");
        }
		
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/loginUser")
	public Map<String, String> login(HttpServletRequest request, @RequestBody UserModel usermodel) {
        Boolean login= userservice.loginUser(usermodel);
        request.getSession().setAttribute(request.getSession().getId(), "login");
        Map<String, String> response = new HashMap<>();
        if(login) {
        	System.out.println("user exist login");
            response.put("sessionId", request.getSession().getId());
        	return response;
        }
        else
        {
        	System.out.println("user not exist or pwd wrong");
        	response.put("sessionId", "");
        	return response;
        }  
		
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/logoutUser")
	public ResponseEntity<String> logout(HttpServletResponse response, @RequestBody String sessionId) {
		String sessionIdValue = "";
		try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sessionId);
        
        sessionIdValue = jsonNode.get("sessionId").asText();
        System.out.println("Received sessionId: " + sessionIdValue);
		}
        catch (Exception e) {
            System.err.println("Error connecting to Redis: " + e.getMessage());
        }
		if(stringRedisTemplate.hasKey("spring:session:sessions:"+sessionIdValue))
		{
			stringRedisTemplate.delete("spring:session:sessions:"+sessionIdValue);
			return ResponseEntity.ok("logout");
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not login");
		}
	}
}
