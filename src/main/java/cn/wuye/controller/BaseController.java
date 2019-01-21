package cn.wuye.controller;
/**
 * 当前项目中所有控制器的基类
 * @author soft01
 *
 */

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wuye.service.ex.DuplicateKeyException;
import cn.wuye.service.ex.InsertException;
import cn.wuye.service.ex.NullValueException;
import cn.wuye.service.ex.PasswordNotMatchException;
import cn.wuye.service.ex.SecretAnswerNotMatchException;
import cn.wuye.service.ex.ServiceException;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserModuleNotFoundException;
import cn.wuye.service.ex.UserNotFoundException;
import cn.wuye.service.ex.identity_idNotMatchException;
import cn.wuye.util.ResponseResult;

public abstract class BaseController {
	//正确响应时的代号----------200
	public static final Integer SUCCESS=200;
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer state=null;
		if(e instanceof DuplicateKeyException) {
			//400-违反了unique约束的异常
			state=400;
		}else if(e instanceof InsertException) {
			//500-插入数据异常
			state=500;
		}else if(e instanceof UserNotFoundException) {
			//401-用户名未找到
			state=401;
		}else if(e instanceof PasswordNotMatchException) {
			//402-密码不匹配
			state=402;
		}else if(e instanceof SecretAnswerNotMatchException) {
			//403-密保答案不匹配
			state=403;
		}else if(e instanceof UpdateException) {
			//501-修改过程出现异常
			state=501;
		}else if(e instanceof identity_idNotMatchException) {
			//406-登录者身份不匹配
			state=406;
		}else if(e instanceof NullValueException) {
			//空值异常
			state=407;
		}else if(e instanceof UserModuleNotFoundException) {
			//用户模块未找到个人用户
			state=408;
		}
		return new ResponseResult<>(state,e);
   }
	
	
	protected String getUid(HttpSession session) {
		return session.getAttribute("uid").toString();
	}
	
}