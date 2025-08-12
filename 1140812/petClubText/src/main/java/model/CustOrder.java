package model;

import java.io.Serializable;

public class CustOrder  implements Serializable{
	Integer idOrder;
	String custOrderName;
	String custOrderTime;
	Double custOrderTimeRecoder;
	String custOrderStaff;
	public CustOrder() {
		super();
	}


	public CustOrder(String custOrderName, String custOrderTime, Double custOrderTimeRecoder, String custOrderStaff) {
		super();
		this.custOrderName = custOrderName;
		this.custOrderTime = custOrderTime;
		this.custOrderTimeRecoder = custOrderTimeRecoder;
		this.custOrderStaff = custOrderStaff;
	}


	public Integer getIdOrder() {
		return idOrder;
	}


	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}


	public String getCustOrderName() {
		return custOrderName;
	}


	public void setCustOrderName(String custOrderName) {
		this.custOrderName = custOrderName;
	}


	public String getCustOrderTime() {
		return custOrderTime;
	}


	public void setCustOrderTime(String custOrderTime) {
		this.custOrderTime = custOrderTime;
	}


	public Double getCustOrderTimeRecoder() {
		return custOrderTimeRecoder;
	}


	public void setCustOrderTimeRecoder(Double custOrderTimeRecoder) {
		this.custOrderTimeRecoder = custOrderTimeRecoder;
	}


	public String getCustOrderStaff() {
		return custOrderStaff;
	}


	public void setCustOrderStaff(String custOrderStaff) {
		this.custOrderStaff = custOrderStaff;
	}


	@Override
	public String toString()
	{
		return "ID:"+this.getIdOrder()+"\t訂單客戶姓名:"+this.getCustOrderName()
			+"\n訂單預約日期："+this.getCustOrderTime()+"\t訂單預約時間:"+this.getCustOrderTimeRecoder()
			+"小時\n已預約:"+this.getCustOrderStaff()+" 服務\n";
	}



}
