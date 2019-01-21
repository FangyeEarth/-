package cn.wuye.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wuye.entity.UserModule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserModuleTestCase {
	
	@Autowired
	private UserModuleMapper userModuleMapper;
	
	@Test
	public void addUM() {
		UserModule user=new UserModule();
		user.setUid(1);
		user.setBaoxiu("水管");
		user.setJindu("未完成");
		user.setWater_rate("0");
		user.setPower_rate("0");
		user.setGas_rate("0");
		user.setProperty_rate("0");
		user.setWeixiu_name("张三");
		user.setWeixiu_tel("134564");
		user.setCreate_user("root");
		user.setCreate_time(new Date());
		Integer row=userModuleMapper.addUM(user);
		System.err.println(row);
	}
	
	@Test
	public void findByUid() {
		Integer uid=1;
		UserModule user=userModuleMapper.findByUid(uid);
		System.err.println(user);	
	}
	
	@Test
	public void rech() {
		UserModule user=new UserModule();
		user.setUid(12);
		user.setGas_rate("600");
		Integer row=userModuleMapper.recharge(user);
		System.err.println(row);
		
	}
	
	
	
	
}
