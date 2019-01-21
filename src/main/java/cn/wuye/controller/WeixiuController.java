package cn.wuye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.wuye.entity.WeixiuVO;
import cn.wuye.service.WeixiuService;
import cn.wuye.util.ResponseResult;

@RestController
@RequestMapping("/weixiu")
public class WeixiuController extends BaseController{
	
	@Autowired
	private WeixiuService weixiuService;
	
	@RequestMapping("/findJindu.do")
	public ResponseResult<List<WeixiuVO>> findJindu(){	
		List<WeixiuVO> data=weixiuService.getJindu();
		return new ResponseResult<List<WeixiuVO>>(SUCCESS,data);
	}
	
	@GetMapping("/weixiuJindu.do")
	public ResponseResult<Void> weixiu(@RequestParam("id")String uid){
		weixiuService.setJindu(Integer.valueOf(uid));
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	
	
}
