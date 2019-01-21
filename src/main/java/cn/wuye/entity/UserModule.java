package cn.wuye.entity;

import java.util.Date;

public class UserModule extends BaseEntity{

	private static final long serialVersionUID = 4772150652402527681L;	
	private Integer id;
	private Integer uid;
	private String baoxiu;
	private String jindu;
	private String water_rate;
	private String power_rate;
	private String gas_rate;
	private String property_rate;
	private String weixiu_name;
	private String weixiu_tel;
	private String create_user;
	private Date create_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getWater_rate() {
		return water_rate;
	}
	public void setWater_rate(String water_rate) {
		this.water_rate = water_rate;
	}
	public String getPower_rate() {
		return power_rate;
	}
	public void setPower_rate(String power_rate) {
		this.power_rate = power_rate;
	}
	public String getGas_rate() {
		return gas_rate;
	}
	public void setGas_rate(String gas_rate) {
		this.gas_rate = gas_rate;
	}
	public String getProperty_rate() {
		return property_rate;
	}
	public void setProperty_rate(String property_rate) {
		this.property_rate = property_rate;
	}
	public String getWeixiu_name() {
		return weixiu_name;
	}
	public void setWeixiu_name(String weixiu_name) {
		this.weixiu_name = weixiu_name;
	}
	public String getWeixiu_tel() {
		return weixiu_tel;
	}
	public void setWeixiu_tel(String weixiu_tel) {
		this.weixiu_tel = weixiu_tel;
	}
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
		return "UserModule [id=" + id + ", uid=" + uid + ", baoxiu=" + baoxiu + ", jindu=" + jindu + ", water_rate="
				+ water_rate + ", power_rate=" + power_rate + ", gas_rate=" + gas_rate + ", property_rate="
				+ property_rate + ", weixiu_name=" + weixiu_name + ", weixiu_tel=" + weixiu_tel + ", create_user="
				+ create_user + ", create_time=" + create_time + "]";
	}
	
	
	
}
