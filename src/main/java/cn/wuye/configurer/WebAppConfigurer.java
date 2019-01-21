package cn.wuye.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.wuye.interceptor.LoginInterceptor;


@SuppressWarnings("deprecation")
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//需要拦截,黑名单
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**").addPathPatterns("/web/**").addPathPatterns("/weixiu/**")
		//不需要拦截，白名单	
		.excludePathPatterns("/user/reg.do")
		.excludePathPatterns("/user/login.do")
		.excludePathPatterns("/web/login.html")
		.excludePathPatterns("/web/reg.html");
		
	}
	
}
