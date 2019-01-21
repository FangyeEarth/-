package cn.wuye.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -900113775893236839L;
	private String create_user;
	private Date create_time;
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "BaseEntity [create_user=" + create_user + ", create_time=" + create_time + "]";
	}
	
	
	
	
}
