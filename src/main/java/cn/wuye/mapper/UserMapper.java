package cn.wuye.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wuye.entity.User;
/**
 * 处理用户数据的持久层
 */

public interface UserMapper {
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	
	
	/**
	 * 根据用户名查找用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 修改密码
	 * @param password
	 * @param uid
	 * @return
	 */
	Integer updatePassword(@Param("password")String password,@Param("uid")Integer uid);
	
	/**
	 * 管理员删除用户权限
	 * @param username
	 * @return
	 */
	Integer deleteUser(String username);
	
	/**
	 * 管理员修改用户信息
	 * @param age
	 * @param phone
	 * @param email
	 * @param create_user
	 * @param create_time
	 * @param username
	 * @return
	 */
	Integer changeInfoByGM(User user);
	
	/**
	 * 管理员查看用户信息
	 * @return
	 */
	List<User> findByGM();
	
	
	
}
