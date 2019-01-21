package cn.wuye.service;

import java.util.List;

import cn.wuye.entity.WeixiuVO;

public interface WeixiuService {
	
	
	List<WeixiuVO> getJindu();
	
	void setJindu(Integer uid);
}
