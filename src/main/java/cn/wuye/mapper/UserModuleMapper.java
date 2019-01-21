package cn.wuye.mapper;

import org.apache.ibatis.annotations.Param;

import cn.wuye.entity.UserModule;

/**
 * 处理用户模块缴费以及报修的持久层
 * @author soft01
 *
 */
public interface UserModuleMapper {
	
	Integer addUM(UserModule user);
	
	
	UserModule findByUid(Integer uid);
	
	
	Integer baoxiu(@Param("baoxiu")String baoxiu,@Param("uid")Integer uid);
	
	
	Integer recharge(UserModule user);
	
	
}
