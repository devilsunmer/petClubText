package dao;

import java.util.List;

import model.StaffIncome;

public interface StaffIncomeDao {
	//create;
	public void addStaffIncome();
	public void add(StaffIncome staffIncome);

	//read
	public List<StaffIncome> selectAll();
	public StaffIncome selectById(Integer id);
	public StaffIncome selectByName(String staffName);
	public StaffIncome selectByFood(String staffFood);

	//update
	public StaffIncome updateIncome(StaffIncome staffIncome);
	public StaffIncome updateStaffFood(StaffIncome staffIncome);

	//delete
	public StaffIncome deleteIncome(StaffIncome staffIncome);


}
