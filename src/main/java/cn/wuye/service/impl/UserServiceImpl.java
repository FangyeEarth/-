package cn.wuye.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.wuye.entity.User;
import cn.wuye.entity.UserModule;
import cn.wuye.mapper.UserMapper;
import cn.wuye.service.IUserService;
import cn.wuye.service.UserModuleService;
import cn.wuye.service.ex.DuplicateKeyException;
import cn.wuye.service.ex.InsertException;
import cn.wuye.service.ex.PasswordNotMatchException;
import cn.wuye.service.ex.SecretAnswerNotMatchException;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserNotFoundException;
import cn.wuye.service.ex.identity_idNotMatchException;
/**
 * 处理用户数据的业务层的实现类
 * @author 张继升
 *
 */
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserModuleService userModuleService;
	
	
	
	@Override
	public void changeInfoByGM(User user,HttpSession session) throws UpdateException, UserNotFoundException {
		User data=getByUsername(user.getUsername());
		if(data==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(data.getIsDelete()==1) {
			throw new UserNotFoundException("用户已删除");
		}
		user.setCreate_time(new Date());
		user.setCreate_user(session.getAttribute("username").toString());
		changeInfo(user);	
	}
	
	

	@Override
	public void deleteUserByGM(String username) throws UpdateException,UserNotFoundException {
		User data=findByUsername(username);
		if(data==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(data.getIsDelete()==1) {
			throw new UserNotFoundException("用户已删除");
		}
		deleteUser(username);
	}
	
	
	
	@Override
	public void updatePasswordBySecret(String secret_answer,String new2password , HttpSession session)
			throws SecretAnswerNotMatchException, UserNotFoundException {
		User data=userMapper.findByUsername(session.getAttribute("username").toString().trim());
		if(data==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(data.getIsDelete()==1) {
			throw new UserNotFoundException("用户不存在");
		}
		if(!data.getSecret_answer().equals(secret_answer)) {
			throw new SecretAnswerNotMatchException("密保答案不正确");
		}
		String salt=data.getSalt();
		String new2Md5Password=getMd5Password(new2password, salt);
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		updatePassword(new2Md5Password, uid);
		System.err.println("修改密码成功");				
	}
	
	
	@Override
	public void updatePassword(String old_password, String new_password, Integer uid,HttpSession session)
			throws UserNotFoundException, UpdateException,PasswordNotMatchException {
		User data=userMapper.findByUsername(session.getAttribute("username").toString().trim());
		if(data==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(data.getIsDelete()==1) {
			throw new UserNotFoundException("用户不存在");
		}
		String salt=data.getSalt();
		String oldMd5Password=getMd5Password(old_password, salt);
		if(data.getPassword().equals(oldMd5Password)) {
			String newMd5Password=getMd5Password(new_password, salt);	
			updatePassword(newMd5Password, uid);
			System.err.println("修改密码成功");
		}else {
			throw new PasswordNotMatchException("原密码不正确,请验证密保答案");		
		}	
	}	
	
	
	
	@Override
	public User login(String username, String password,Integer identity_id) throws UserNotFoundException, PasswordNotMatchException,identity_idNotMatchException {
		//判断参数username查询数据
		User data=userMapper.findByUsername(username);
		if(data!=null) {
			//否：非null，根据用户名找到了数据，取出盐值
			String salt=data.getSalt();
			//对参数password执行加密
			String Md5Password=getMd5Password(password,salt);
			//判断密码是否匹配
			if(Md5Password.equals(data.getPassword())) {
				//是：匹配，密码正确，则判断是否被删除
				if(data.getIsDelete()==1) {
					//是：已被删除，则抛出UserNotFoundException或自定义“用户被删除异常”
					throw new UserNotFoundException("用户被删除");
				}
				//否：没被删除，则登录成功，将第一步中查询的用户数据中的盐值和密码设置为null，返回用户数据
				data.setSalt(null);
				data.setPassword(null);
				if(data.getIdentity_id().equals(identity_id)) {
					return data;
				}else {
					throw new identity_idNotMatchException("身份不正确");
				}
			}else{
				//否：不匹配，密码错误，则抛出PasswordNotMatchException
				throw new PasswordNotMatchException("密码错误");
			}	
		}else {
			//是：为 null，用户名不存在，则抛出UserNotFoundException
			throw new UserNotFoundException("登录失败"+username+"不存在");
		}
	}



	@Override
	public void guanliaddnew(User user,String username) throws DuplicateKeyException, InsertException {
		User data=findByUsername(user.getUsername());
		if(data!=null) {
			throw new DuplicateKeyException("注册失败，"+user.getUsername()+"用户名已被占用");
		}
		user.setIsDelete(0);
		user.setCreate_user(username);
		user.setCreate_time(new Date());
		String salt=UUID.randomUUID().toString();
		String srcPassword=user.getPassword();
		String md5Password=getMd5Password(srcPassword,salt);
		user.setPassword(md5Password);
		user.setSalt(salt);
		addnew(user);	
	}
	
	

	@Override
	public User reg(User user,HttpSession session) throws DuplicateKeyException, InsertException {
		//根据尝试注册的用户名查询用户数据
		User data=findByUsername(user.getUsername());
		//判断查询到的数据是否为null
		if(data==null) {
			//是：用户名不存在，允许注册，则执行注册，返回注册的用户对象,处理密码加密
			//补充非用户提交的数据
			user.setIsDelete(0);//是否已经删除：否
			//4项日志
			Date now=new Date();		
			user.setCreate_user(user.getUsername());
			user.setCreate_time(now);			
			//-----------------------------------------------------------------------------------------------------------	
			//加密1.获取随机的uuid作为盐值
			String salt=UUID.randomUUID().toString();		
			//加密2.获取用户提交的原始密码
			String srcPassword=user.getPassword();
			//加密3.基于原始密码和盐值执行加密，获取加密后的密码
			String md5Password=getMd5Password(srcPassword,salt);
			//加密4.将加密后的密码封装再user对象中
			user.setPassword(md5Password);
			//加密5.将盐值封装在user对象中
			user.setSalt(salt);
			//执行注册
			addnew(user);
			if(user.getIdentity_id()==1) {
				session.setAttribute("User_id", user.getId());
				session.setAttribute("User_name", user.getUsername());
				UserModule userModule=new UserModule();
				userModuleService.addUM(userModule,session);
			}
			return user;
		}else {
			//否：用户名已被占用，抛出DuplicateKeyException异常
			throw new DuplicateKeyException("注册失败，"+user.getUsername()+"用户名已被占用");
		}
	}
	
	
	
	@Override
	public User getByUsername(String username) {
		User data=findByUsername(username);
		data.setPassword(null);
		data.setSalt(null);
		return data;
	}
	
	
	
	
	
	/**
	 * 将密码加密成md5格式
	 * @param srcPassword 用户输入的密码（未加密）
	 * @param salt 盐
	 * @return 加密完成的密码
	 */
	private String getMd5Password(String srcPassword,String salt) {
		//自由设计规则：       盐值 拼接 原密码 拼接 盐值
		String str=salt+srcPassword+salt;
		//循环执行10次摘要计算，返回摘要结果
		for(int i=0;i<10;i++) {
			str=DigestUtils.md5DigestAsHex(str.getBytes());
		}
		//返回摘要结果
		return str;
	}


	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @throws InsertException
	 */
	private void addnew(User user) {
		Integer rows=userMapper.addnew(user);
		if(rows!=1) {
			throw new InsertException("增加用户数据时出现未知错误");
		}	
	}

	/**
	 * 根据用户名查找用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);				
	}
	
	
	private void updatePassword(String password,Integer uid) {
		Integer row=userMapper.updatePassword(password, uid);
		if(row!=1) {
			throw new UpdateException("修改过程出现错误");
		}
	}

	
	private void deleteUser(String username) {
		Integer row=userMapper.deleteUser(username);
		if(row!=1) {
			throw new UpdateException("删除过程出现错误");
		}
	}
	
	private void changeInfo(User user) {
		Integer row=userMapper.changeInfoByGM(user);
		if(row!=1) {
			throw new UpdateException("修改过程出现错误");
		}
	}
	
	/**
	 * 管理员查询数据
	 * @return
	 */
	private List<User> findByGM() {
		return userMapper.findByGM();
	}
	
































}
