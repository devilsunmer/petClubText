package model;

import java.io.Serializable;

public class CustFreeMember  implements Serializable{
	Integer idFreeMember;
	String custFreeMemberName;
	String custFreeMemberPhone;
	public CustFreeMember() {
		super();
	}
	public CustFreeMember(String custFreeMemberName, String custFreeMemberPhone) {
		super();
		this.custFreeMemberName = custFreeMemberName;
		this.custFreeMemberPhone = custFreeMemberPhone;
	}
	public Integer getIdFreeMember() {
		return idFreeMember;
	}
	public void setIdFreeMember(Integer idFreeMember) {
		this.idFreeMember = idFreeMember;
	}
	public String getCustFreeMemberName() {
		return custFreeMemberName;
	}
	public void setCustFreeMemberName(String custFreeMemberName) {
		this.custFreeMemberName = custFreeMemberName;
	}
	public String getCustFreeMemberPhone() {
		return custFreeMemberPhone;
	}
	public void setCustFreeMemberPhone(String custFreeMemberPhone) {
		this.custFreeMemberPhone = custFreeMemberPhone;
	}
	@Override
	public String toString()
	{
		return "ID:"+this.getIdFreeMember()
		+"\t訪客姓名:"+this.getCustFreeMemberName()
		+"\t訪客電話:"+this.getCustFreeMemberPhone()+"\n";
	}

}
