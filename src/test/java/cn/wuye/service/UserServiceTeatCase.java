package cn.wuye.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wuye.entity.User;
import cn.wuye.service.IUserService;
import cn.wuye.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTeatCase {
	
	@Autowired
	private IUserService iUserService;
	
	
	@Test
	public void login() {

		String username="root";
		String password="1234";
		Integer identity_id=1;
		User user=iUserService.login(username,password, identity_id);
		System.err.println(user);
	}
	
	
	
	
	
	
	
	
}
