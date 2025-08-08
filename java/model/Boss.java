package model;

import java.io.Serializable;

public class Boss  implements Serializable{
	Integer idBoss;
	String bossName;
	String bossUsername;
	String bossPassword;
	public Boss() {
		super();
	}
	public Boss(String bossName, String bossUsername, String bossPassword) {
		super();
		this.bossName = bossName;
		this.bossUsername = bossUsername;
		this.bossPassword = bossPassword;
	}
	public Integer getIdBoss() {
		return idBoss;
	}
	public void setIdBoss(Integer idBoss) {
		this.idBoss = idBoss;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public String getBossUsername() {
		return bossUsername;
	}
	public void setBossUsername(String bossUsername) {
		this.bossUsername = bossUsername;
	}
	public String getBossPassword() {
		return bossPassword;
	}
	public void setBossPassword(String bossPassword) {
		this.bossPassword = bossPassword;
	}
	@Override
	public String toString() {
		return "老闆姓名："+this.getBossName()
			+"\n老闆帳號："+this.getBossUsername()
			+"\t老闆密碼："+this.getBossPassword()+"\n";
	}

}
