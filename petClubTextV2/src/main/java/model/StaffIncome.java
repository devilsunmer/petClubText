package model;

import java.io.Serializable;

public class StaffIncome implements Serializable{
	Integer idStaffIncome;
	String staffName;
	Double staffIncome;
	String staffFood;
	public StaffIncome() {
		super();
	}
	
	public StaffIncome(String staffName, Double staffIncome) {
		super();
		this.staffName = staffName;
		this.staffIncome = staffIncome;
	}
	
	public StaffIncome(String staffName, String staffFood) {
		super();
		this.staffName = staffName;
		this.staffFood = staffFood;
	}
	public Integer getIdStaffIncome() {
		return idStaffIncome;
	}
	public void setIdStaffIncome(Integer idStaffIncome) {
		this.idStaffIncome = idStaffIncome;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Double getStaffIncome() {
		return staffIncome;
	}
	public void setStaffIncome(Double staffIncome) {
		this.staffIncome = staffIncome;
	}
	public String getStaffFood() {
		return staffFood;
	}
	public void setStaffFood(String staffFood) {
		this.staffFood = staffFood;
	}

}
