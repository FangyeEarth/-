package cn.wuye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wuye.mapper")
public class wuyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(wuyeApplication.class, args);
	}

}

