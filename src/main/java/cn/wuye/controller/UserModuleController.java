package cn.wuye.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.wuye.entity.UserModule;
import cn.wuye.service.UserModuleService;
import cn.wuye.util.ResponseResult;

@RestController
@RequestMapping("/userModule")
public class UserModuleController extends BaseController{
	
	@Autowired
	private UserModuleService userModuleService;
	
	@PostMapping("/Submitbaoxiu.do")
	public ResponseResult<Void> SubmitBaoxiu(@RequestParam("baoxiu")String baoxiu,HttpSession session){
		userModuleService.SubmitBaoxiu(baoxiu, session);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/selectAll.do")
	public ResponseResult<UserModule> selectAll(HttpSession session){
		UserModule data=userModuleService.selectAll(session);
		return new ResponseResult<UserModule>(SUCCESS,data);
	}
	
	@RequestMapping("/recharge.do")
	public ResponseResult<Void> recharge(@RequestParam("rech")String rech,@RequestParam("recharge")String recharge,HttpSession session){
		UserModule user=new UserModule();
		if("1".equals(rech)) {
			user.setWater_rate(recharge);
		}
		if("2".equals(rech)) {
			user.setPower_rate(recharge);
		}
		if("3".equals(rech)) {
			user.setGas_rate(recharge);
		}
		if("4".equals(rech)) {
			user.setProperty_rate(recharge);
		}
		System.err.println("控制器："+user);
		userModuleService.rechMoney(user, session);
		System.err.println("控制器层结束");
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	
}
