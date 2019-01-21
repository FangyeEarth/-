package cn.wuye.mapper;

import java.util.List;

import cn.wuye.entity.WeixiuVO;

public interface WeixiuMapper {
	
	List<WeixiuVO> findByJindu();
	
	Integer setJindu(Integer uid);
	
}
