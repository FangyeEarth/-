package cn.wuye.entity;

import java.io.Serializable;

public class User extends BaseEntity implements Serializable{                                            
	private static final long serialVersionUID = 7095951443770957600L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private Integer age;
	private String phone;
	private String email;
	private Integer isDelete;
	private Integer identity_id;
	private Integer secret_id;
	private String secret_answer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIdentity_id() {
		return identity_id;
	}
	public void setIdentity_id(Integer identity_id) {
		this.identity_id = identity_id;
	}
	public Integer getSecret_id() {
		return secret_id;
	}
	public void setSecret_id(Integer secret_id) {
		this.secret_id = secret_id;
	}
	public String getSecret_answer() {
		return secret_answer;
	}
	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", age="
				+ age + ", phone=" + phone + ", email=" + email + ", isDelete=" + isDelete + ", identity_id="
				+ identity_id + ", secret_id=" + secret_id + ", secret_answer=" + secret_answer + "]";
	}
	
	
	
	
	
	
}
