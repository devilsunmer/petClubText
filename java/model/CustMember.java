package model;

import java.io.Serializable;

public class CustMember  implements Serializable{
	Integer idMember;
	String custMemberName;
	String custUsername;
	String custPassword;
	String custMemberPhone;
	public CustMember() {
		super();
	}
	public CustMember(String custMemberName, String custUsername, String custPassword, String custMemberPhone) {
		super();
		this.custMemberName = custMemberName;
		this.custUsername = custUsername;
		this.custPassword = custPassword;
		this.custMemberPhone = custMemberPhone;
	}
	public Integer getIdMember() {
		return idMember;
	}
	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}
	public String getCustMemberName() {
		return custMemberName;
	}
	public void setCustMemberName(String custMemberName) {
		this.custMemberName = custMemberName;
	}
	public String getCustUsername() {
		return custUsername;
	}
	public void setCustUsername(String custUsername) {
		this.custUsername = custUsername;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public String getCustMemberPhone() {
		return custMemberPhone;
	}
	public void setCustMemberPhone(String custMemberPhone) {
		this.custMemberPhone = custMemberPhone;
	}
	@Override
	public String toString()
	{
		return "ID:"+this.getIdMember()
			+"\t會員姓名:"+this.getCustMemberName()
			+"\n會員帳號:"+this.getCustUsername()
			+"\t會員密碼:"+this.getCustPassword()
			+"\n會員電話:"+this.getCustMemberPhone()+"\n";
	}


}
