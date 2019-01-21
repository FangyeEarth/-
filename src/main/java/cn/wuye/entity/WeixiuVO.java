package cn.wuye.entity;

import java.io.Serializable;

public class WeixiuVO extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 8512286153707709413L;
	private String username;
	private String phone;
	private String baoxiu;
	private String jindu;
	private Integer uid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBaoxiu() {
		return baoxiu;
	}
	public void setBaoxiu(String baoxiu) {
		this.baoxiu = baoxiu;
	}
	public String getJindu() {
		return jindu;
	}
	public void setJindu(String jindu) {
		this.jindu = jindu;
	}
	@Override
	public String toString() {
		return "WeixiuVO [username=" + username + ", phone=" + phone + ", baoxiu=" + baoxiu + ", jindu=" + jindu
				+ ", uid=" + uid + "]";
	}
	

	
	
	
	
}
