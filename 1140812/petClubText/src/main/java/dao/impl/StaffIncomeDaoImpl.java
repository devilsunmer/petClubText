package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StaffIncomeDao;
import model.StaffIncome;
import util.DbConnection;

public class StaffIncomeDaoImpl implements StaffIncomeDao {

	public static void main(String[] args) {
//		StaffIncomeDaoImpl staffIncomeDao = new StaffIncomeDaoImpl();
//		staffIncomeDao.addStaffIncome();
	}

	Connection connection = DbConnection.getDb();

	public void addStaffIncome() {
		String sql = "SELECT sm.staffName, SUM(co.custOrderTimeRecoder * 300) AS totalIncome " +
                "FROM pet_club.staff_member sm " +
                "LEFT JOIN pet_club.cust_order co ON sm.staffName = co.custOrderStaff " +
                "GROUP BY sm.staffName";
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // 迭代結果並插入每個員工的薪水
	        while (resultSet.next()) {
	            String staffName = resultSet.getString("staffName");
	            Double totalIncome = resultSet.getDouble("totalIncome");
	            if (totalIncome == null || totalIncome == 0) {
	                totalIncome = 0.0; // 設置為 0
	            }
	            // 創建 StaffIncome 物件並設定其資料
	            StaffIncome staffIncome = new StaffIncome();
	            staffIncome.setStaffName(staffName);
	            staffIncome.setStaffIncome(totalIncome);
	            staffIncome.setStaffFood(null);
	            // 調用 insertStaffIncome 來插入資料
	            add(staffIncome);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void add(StaffIncome staffIncome) {
	    String sql = "INSERT INTO staff_income (staffName, staffIncome,staffFood) VALUES (?, ?,?)";
	    
	    try {
	    	if (staffIncome.getStaffIncome() == null) 
	    	{
	    		staffIncome.setStaffIncome(0.0);  // 如果是 null，設置為 0.0
	    	}
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, staffIncome.getStaffName());
	        preparedStatement.setDouble(2, staffIncome.getStaffIncome());
	        preparedStatement.setString(3, staffIncome.getStaffFood());

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public List<StaffIncome> selectAll() {
		String sql = "select * from staff_income";
		List<StaffIncome> staffIncomeList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StaffIncome staffIncome = new StaffIncome();
				staffIncome.setIdStaffIncome(resultSet.getInt("idStaffIncome"));
				staffIncome.setStaffName(resultSet.getString("staffName"));
				staffIncome.setStaffIncome(resultSet.getDouble("staffIncome"));
				staffIncome.setStaffFood(resultSet.getString("staffFood"));
				staffIncomeList.add(staffIncome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffIncomeList;
	}

	@Override
	public StaffIncome selectById(Integer id) {
		String sql = "select * from staff_icome where idStaffIncome=?";
		StaffIncome someone = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				someone = new StaffIncome();
				someone.setIdStaffIncome(resultSet.getInt("idStaffIncome"));
				someone.setStaffName(resultSet.getString("staffName"));
				someone.setStaffIncome(resultSet.getDouble("staffIncome"));
				someone.setStaffFood(resultSet.getString("staffFood"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public StaffIncome selectByName(String staffName) {
		String sql = "select * from staff_icome where staffName=?";
		StaffIncome someone = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, staffName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				someone = new StaffIncome();
				someone.setIdStaffIncome(resultSet.getInt("idStaffIncome"));
				someone.setStaffName(resultSet.getString("staffName"));
				someone.setStaffIncome(resultSet.getDouble("staffIncome"));
				someone.setStaffFood(resultSet.getString("staffFood"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public StaffIncome selectByFood(String staffFood) {
		String sql = "select * from staff_icome where staffFood=?";
		StaffIncome someone = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, staffFood);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				someone = new StaffIncome();
				someone.setIdStaffIncome(resultSet.getInt("idStaffIncome"));
				someone.setStaffName(resultSet.getString("staffName"));
				someone.setStaffIncome(resultSet.getDouble("staffIncome"));
				someone.setStaffFood(resultSet.getString("staffFood"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public StaffIncome updateIncome(StaffIncome staffIncome) {
		String sql = "update staff_icome set staffIncome=? where staffName=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (selectById(staffIncome.getIdStaffIncome()) != null) {
				preparedStatement.setDouble(1, staffIncome.getStaffIncome());
				preparedStatement.setString(2, staffIncome.getStaffName());
				preparedStatement.executeUpdate();
				return staffIncome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public StaffIncome updateStaffFood(StaffIncome staffIncome) {
		String sql = "update staff_icome set staffFood=? where staffName=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (selectById(staffIncome.getIdStaffIncome()) != null) {
				preparedStatement.setString(1, staffIncome.getStaffFood());
				preparedStatement.setString(2, staffIncome.getStaffName());
				preparedStatement.executeUpdate();
				return staffIncome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StaffIncome deleteIncome(StaffIncome staffIncome) {
		String sql = "delete from staff_icome where idStaffIncome=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (selectById(staffIncome.getIdStaffIncome()) != null) {
				preparedStatement.setInt(1, staffIncome.getIdStaffIncome());
				preparedStatement.executeUpdate();
				return staffIncome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
