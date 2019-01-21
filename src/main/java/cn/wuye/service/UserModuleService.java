package cn.wuye.service;

import javax.servlet.http.HttpSession;

import cn.wuye.entity.UserModule;
import cn.wuye.service.ex.DuplicateKeyException;
import cn.wuye.service.ex.InsertException;
import cn.wuye.service.ex.NullValueException;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserModuleNotFoundException;

/**
 *  处理用户模块缴费以及报修的业务层
 * @author soft01
 *
 */

public interface UserModuleService {
	
	UserModule addUM(UserModule userModule,HttpSession session) throws InsertException,DuplicateKeyException;
	
	void SubmitBaoxiu(String baoxiu,HttpSession session) throws UpdateException,NullValueException;
	
	UserModule selectAll(HttpSession session) throws UserModuleNotFoundException;
	
	void rechMoney(UserModule user,HttpSession session) throws UserModuleNotFoundException,UpdateException;
	
}
