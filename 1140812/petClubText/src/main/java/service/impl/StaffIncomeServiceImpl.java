package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.impl.CustOrderDaoImpl;
import dao.impl.StaffIncomeDaoImpl;
import model.CustOrder;
import model.StaffIncome;
import service.StaffIncomeService;
import util.ToolByStaffFood;

public class StaffIncomeServiceImpl implements StaffIncomeService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private static CustOrderDaoImpl custOrderDaoImpl = new CustOrderDaoImpl();
	private static StaffIncomeDaoImpl staffIncomeDaoImpl=new StaffIncomeDaoImpl();

	@Override
	public StaffIncome addStaffIncome(StaffIncome staffIncome) {
		staffIncomeDaoImpl.add(staffIncome);
		return null;
	}
	
	@Override
	public void addStaffIncome() {
	    // 假設這裡返回的是單一訂單（你可能會用不同的方式獲取單一訂單）
//	    List<CustOrder> custOrder = custOrderDaoImpl.allCustOrder();
//
//	    // 計算每個訂單的總收入
//	    double totalAmount = ToolByStaffFood.calculateOrderAmount(custOrder);
//
//	    // 計算員工收入：根據工作時間計算
//	    double staffIncome = ToolByStaffFood.calculateStaffIncome(custOrder.getCustOrderTimeRecoder(), totalAmount);
//
//	    // 根據收入換算可以獲得的食物數量
//	    String staffFood = ToolByStaffFood.getStaffFoodForIncome(staffIncome);
//
//	    // 創建 StaffIncome 物件
//	    StaffIncome staffIncomeObj = new StaffIncome();
//	    staffIncomeObj.setStaffName(ToolByStaffFood.getStaffNameForOrder(custOrder));  // 設置員工姓名
//	    staffIncomeObj.setStaffFood(staffFood);  // 設置食物名稱
//	    staffIncomeObj.setStaffIncome(staffIncome);  // 設置員工收入
//
//	    // 保存 StaffIncome 物件到資料庫
//	    staffIncomeDaoImpl.add(staffIncomeObj);
	}

	@Override
	public StaffIncome addStaffFood(StaffIncome staffIncome) {
		return staffIncomeDaoImpl.updateStaffFood(staffIncome);
	}
	
	@Override
	public String viewStaffIncome() {
		List<StaffIncome> staffIncomeList=staffIncomeDaoImpl.selectAll();
		String show="";
		for(StaffIncome o:staffIncomeList)
		{
			show=show+"寵物員工姓名："+o.getStaffName()
				+"\t賺入薪水："+o.getStaffIncome()
				+"\t目前可換零食:"+o.getStaffFood()+"\n";
		}
		return show;
	}

	@Override
	public String viewStaffName(String staffName) {
		StaffIncome some=staffIncomeDaoImpl.selectByName(staffName);
		String show="寵物員工姓名："+some.getStaffName()
		+"\t賺入薪水："+some.getStaffIncome()
		+"\t可換零食:"+some.getStaffFood();

		return show;
	}

	@Override
	public String viewStaffFood() {
		List<StaffIncome> staffIncomeList=staffIncomeDaoImpl.selectAll();
	    Map<String, Integer> totalFoodCount = new HashMap<>();  // 用來儲存每種食物的累積數
		for(StaffIncome staffIncome:staffIncomeList)
		{
			String food=staffIncome.getStaffFood();
			Map<String,Integer> foodCountMap=ToolByStaffFood.viewStaffFood(food);
			for (Map.Entry<String, Integer> foodCountEntry : foodCountMap.entrySet())
			{
				totalFoodCount.put(foodCountEntry.getKey(), totalFoodCount.getOrDefault(foodCountEntry.getKey(),0)+foodCountEntry.getValue());
		    }
		}

		StringBuilder result = new StringBuilder("目前零食累積:\n");
	    for (Map.Entry<String, Integer> entry : totalFoodCount.entrySet())
	    {
	        result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
	    }
	    return result.toString();
	}

	@Override
	public boolean changeStaffIncome(StaffIncome staffIncome) {
		if(staffIncome!=null)
		{
			staffIncomeDaoImpl.updateIncome(staffIncome);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStaffIncome(StaffIncome staffIncome) {
		if(staffIncome!=null)
		{
			staffIncomeDaoImpl.deleteIncome(staffIncome);
			return true;
		}
		return false;
	}



}
