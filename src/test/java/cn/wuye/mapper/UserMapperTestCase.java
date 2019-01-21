package cn.wuye.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wuye.entity.User;
import cn.wuye.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void addnew() {
		User user=new User();
		Date now=new Date();
		user.setUsername("root1");
		user.setPassword("1234");
		user.setSalt("1234");
		user.setPhone("12354678901");
		user.setEmail("1234567@qq.com");
		user.setIdentity_id(1);
		user.setSecret_answer("无");
		user.setSecret_id(1);
		user.setAge(22);
		Integer row=userMapper.addnew(user);
		System.out.println(row);	
	}
	
	
	@Test
	public void findByUsername() {
		String username="root";
		User user=userMapper.findByUsername(username);
		System.out.println(user);			
	}
	
	
}
