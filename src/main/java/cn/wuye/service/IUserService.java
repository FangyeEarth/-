package cn.wuye.service;

import javax.servlet.http.HttpSession;

import cn.wuye.entity.User;
import cn.wuye.service.ex.DuplicateKeyException;
import cn.wuye.service.ex.InsertException;
import cn.wuye.service.ex.PasswordNotMatchException;
import cn.wuye.service.ex.SecretAnswerNotMatchException;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserNotFoundException;
import cn.wuye.service.ex.identity_idNotMatchException;

/**
 * 处理用户数据的业务层接口
 * @author soft01
 *
 */
public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user 用户信息
	 * @return 返回成功注册的用户数据
	 * @throws DuplicateKeyException 
	 * @throws InsertException 插入数据过程出现未知错误
	 * 如果事先并不清楚可能抛出哪些异常，甚至还没有创建对应的异常类，可以直接编写业务方法的代码，后续再补充抛出异常的代码
	 */
	User reg(User user,HttpSession session) throws DuplicateKeyException,InsertException;
	
	/**
	 * 用户登录方法
	 * @param username
	 * @param password
	 * @return 返回成功登录的用户数据
	 * @throws UserNotFoundException 用户名未找到异常
	 * @throws PasswordNotMatchException 密码不正确异常
	 */
	User login(String username,String password,Integer identity_id) throws UserNotFoundException,PasswordNotMatchException,identity_idNotMatchException;
	
	/**
	 * 修改密码方法
	 * @param old_password
	 * @param new_password
	 * @param uid
	 * @param session
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 * @throws PasswordNotMatchException
	 */
	void updatePassword(String old_password,String new_password,Integer uid,HttpSession session) throws UserNotFoundException,UpdateException,PasswordNotMatchException;
	
	/**
	 * 根据密保修改密码
	 * @param secret_answer
	 * @param new2password
	 * @param session
	 * @throws SecretAnswerNotMatchException
	 * @throws UserNotFoundException
	 */
	void updatePasswordBySecret(String secret_answer,String new2password,HttpSession session) throws SecretAnswerNotMatchException,UserNotFoundException;
	
	
	
	/**
	 * 管理员添加用户权限
	 * @param user
	 * @param username 管理员的名字
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	void guanliaddnew(User user,String username) throws DuplicateKeyException,InsertException;
	
	
	/**
	 * 管理员根据用户名删除权限
	 * @param username
	 * @throws UpdateException
	 * @throws UserNotFoundException
	 */
	void deleteUserByGM(String username) throws UpdateException,UserNotFoundException;
	
	
	/**
	 * 管理员根据用户名修改用户数据
	 * @param user
	 * @throws UpdateException
	 * @throws UserNotFoundException
	 */
	void changeInfoByGM(User user,HttpSession session) throws UpdateException,UserNotFoundException;
	
	User getByUsername(String username);


	
}
















