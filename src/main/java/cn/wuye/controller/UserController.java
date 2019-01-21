package cn.wuye.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.wuye.entity.User;
import cn.wuye.service.IUserService;
import cn.wuye.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	/**
	 * 所有的控制器类本身都不实现功能，而是通过Service组件来实现，所以，应该声明IUserService
	 */
	@Autowired
	private IUserService userService;
	
	/**
	 * 设计“处理注册数据”的请求
	 * 请求路径：/user/reg.do
	 * 请求方式：post
	 * 请求参数：User
	 * 响应数据（成功时）：无
	 */
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> reg(User user,HttpSession session) {
		userService.reg(user,session);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 管理员添加用户权限功能
	 * @param user
	 * @return
	 */
	@PostMapping("/guanliaddnew.do")
	public ResponseResult<Void> guanliaddnew(User user,HttpSession session) {
		String username=session.getAttribute("username").toString();
		userService.guanliaddnew(user, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	/**
	 * 请求路径：/user/login.do
	 * 请求类型：post
	 * 请求参数：username(*)，password(*)
	 * 响应数据：无
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("/login.do")
	public ResponseResult<Void> login(HttpServletResponse response,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("identity_id")Integer identity_id,HttpSession session){
		User user=userService.login(username, password,identity_id);
		//将相关信息存入到Session
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("identity_id", user.getIdentity_id());
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 修改密码
	 * @param old_password
	 * @param new_password
	 * @param session
	 * @return
	 */
	@PostMapping("/update_password.do")
	public ResponseResult<Void> updatePassword(@RequestParam("old_password")String old_password,@RequestParam("new_password")String new_password,HttpSession session){
		Integer uid=Integer.valueOf(getUid(session));
		userService.updatePassword(old_password, new_password, uid, session);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	/**
	 * 使用密保修改密码
	 * @param secret_answer
	 * @param new2password
	 * @param session
	 * @return
	 */
	@PostMapping("/update_passwordbysecret.do")
	public ResponseResult<Void> updatePasswordBySecret(@RequestParam("secret_answer")String secret_answer,@RequestParam("new2password")String new2password,HttpSession session){
		userService.updatePasswordBySecret(secret_answer, new2password, session);	
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 管理员删除用户方法
	 * @param del_username
	 * @return
	 */
	@PostMapping("/deleteUserByGM.do")
	public ResponseResult<Void> deleteUserByGM(@RequestParam("del_username")String del_username){
		userService.deleteUserByGM(del_username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/changeInfo.do")
	public ResponseResult<Void> changeInfoByGM(User user,HttpSession session){	
		userService.changeInfoByGM(user, session);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	@RequestMapping("/getSecret_id.do")
	public ResponseResult<User> getSecret_id(HttpSession session){
		String username=session.getAttribute("username").toString();
		User data=userService.getByUsername(username);
		return new ResponseResult<User>(SUCCESS,data);
	}
	
	
	
	
}
