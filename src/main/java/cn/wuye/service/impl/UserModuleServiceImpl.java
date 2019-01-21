package cn.wuye.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuye.entity.UserModule;
import cn.wuye.mapper.UserModuleMapper;
import cn.wuye.service.UserModuleService;
import cn.wuye.service.ex.DuplicateKeyException;
import cn.wuye.service.ex.InsertException;
import cn.wuye.service.ex.NullValueException;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserModuleNotFoundException;

@Service
public class UserModuleServiceImpl implements UserModuleService{
	
	@Autowired
	private UserModuleMapper userModuleMapper;
	
	


	@Override
	public void rechMoney(UserModule user, HttpSession session) throws UserModuleNotFoundException, UpdateException {
		UserModule data=findByUid(Integer.valueOf(session.getAttribute("uid").toString()));
		if(data==null) {
			throw new UserModuleNotFoundException("用户信息未找到");
		}
		user.setUid(Integer.valueOf(session.getAttribute("uid").toString()));
		if(user.getWater_rate()!=null&&user.getWater_rate()!="") {
			Integer waterRate=Integer.valueOf(user.getWater_rate());
			Integer old_water_rate=Integer.valueOf(data.getWater_rate());
			String new_water_rate=waterRate+old_water_rate+"";
			user.setWater_rate(new_water_rate);
		}
		if(user.getPower_rate()!=null&&user.getPower_rate()!="") {
			String PowerRate=Integer.valueOf(user.getPower_rate())+Integer.valueOf(data.getPower_rate())+"";
			user.setPower_rate(PowerRate);
		}
		if(user.getGas_rate()!=null&&user.getGas_rate()!="") {
			String gas_rate=Integer.valueOf(user.getGas_rate())+Integer.valueOf(data.getGas_rate())+"";
			user.setGas_rate(gas_rate);
		}
		if(user.getProperty_rate()!=null&&user.getProperty_rate()!="") {
			String property_rate=Integer.valueOf(user.getProperty_rate())+Integer.valueOf(data.getProperty_rate())+"";
			user.setProperty_rate(property_rate);
		}
		user.setCreate_time(new Date());
		recharge(user);
		System.err.println("业务层："+user);
	}
	
	
	
	@Override
	public UserModule selectAll(HttpSession session) throws UserModuleNotFoundException {
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		UserModule data=findByUid(uid);
		if(data==null) {
			throw new UserModuleNotFoundException("用户信息未找到");
		}
		return data;
	}
	
	
	@Override
	public void SubmitBaoxiu(String baoxiu,HttpSession session) throws UpdateException,NullValueException {
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		baoxiu(baoxiu, uid);
	}
	
	@Override
	public UserModule addUM(UserModule user,HttpSession session) throws InsertException {
		UserModule data=findByUid(Integer.valueOf(session.getAttribute("User_id").toString()));
		if(data!=null) {
			throw new DuplicateKeyException("已经有了该用户");
		}
		user.setUid(Integer.valueOf(session.getAttribute("User_id").toString()));
		user.setCreate_user(session.getAttribute("User_name").toString());
		user.setGas_rate("0");
		user.setPower_rate("0");
		user.setWater_rate("0");
		user.setProperty_rate("0");
		user.setCreate_time(new Date());
		create(user);
		return user;
	}
	
	
	private void create(UserModule user) {
		Integer row= userModuleMapper.addUM(user);
		if(row!=1) {
			throw new InsertException("插入异常");
		}
	}	
	private UserModule findByUid(Integer uid) {
		return userModuleMapper.findByUid(uid);	
	}
	private void baoxiu(String baoxiu,Integer uid) {
		Integer row=userModuleMapper.baoxiu(baoxiu, uid);
		if(row!=1) {
			throw new UpdateException("报修过程出现异常");
		}
	}
	private void recharge(UserModule user) {
		Integer row =userModuleMapper.recharge(user);
		if(row!=1) {
			throw new UpdateException("充值过程出现异常");
		}	
	}





	
	
}
