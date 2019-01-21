package cn.wuye.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuye.entity.WeixiuVO;
import cn.wuye.mapper.WeixiuMapper;
import cn.wuye.service.WeixiuService;
import cn.wuye.service.ex.UpdateException;
import cn.wuye.service.ex.UserModuleNotFoundException;

@Service
public class WexiuServiceImpl implements WeixiuService{
	
	@Autowired
	private WeixiuMapper weixiuMapper;
	
	@Override
	public List<WeixiuVO> getJindu() {
		List<WeixiuVO> data=findByJindu();
		if(data==null) {
			throw new UserModuleNotFoundException("没有需要维修项目");
		}
		return data;
	}
	
	@Override
	public void setJindu(Integer uid) {
		setJINDU(uid);
	}
	
	
	private void setJINDU(Integer uid) {
		Integer row=weixiuMapper.setJindu(uid);
		if(row!=1) {
			throw new UpdateException("维修过程出现未知异常");
		}
	}
	
	private List<WeixiuVO> findByJindu(){
		return weixiuMapper.findByJindu();
	}







	
}
