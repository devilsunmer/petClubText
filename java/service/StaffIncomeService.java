package service;

import model.StaffIncome;

public interface StaffIncomeService {
	//create
	void addStaffIncome();
	StaffIncome addStaffIncome(StaffIncome staffIncome);

	//read
	String viewStaffIncome();
	String viewStaffName(String staffName);
	String viewStaffFood();

	//update
	boolean changeStaffIncome(StaffIncome staffIncome);
	StaffIncome addStaffFood(StaffIncome staffIncome);

	//delete
	boolean deleteStaffIncome(StaffIncome staffIncome);
}
