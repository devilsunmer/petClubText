package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustOrderDao;
import model.CustOrder;
import util.DbConnection;

public class CustOrderDaoImpl implements CustOrderDao{

	public static void main(String[] args) {
//		CustOrder custOrder=new CustOrder();
//		custOrder.setIdOrder(5);
//		new CustOrderDaoImpl().deletesome(custOrder);

		/*
		CustOrder custOrder=new CustOrder();
		custOrder.setCustOrderName("");
		custOrder.setCustOrderTime(Date.valueOf("2025-07-04"));
		custOrder.setCustOrderTimeRecoder(7.);
		custOrder.setIdOrder(5);
		new CustOrderDaoImpl().updateSome(custOrder);
		*/


//		System.out.println(new CustOrderDaoImpl().selectSome("p"));
//		System.out.println(new CustOrderDaoImpl().selectId(3).toString());
		System.out.println(new CustOrderDaoImpl().allCustOrder().toString());
//		new CustOrderDaoImpl().addCustOrder(new CustOrder("楊春梅",Date.valueOf("2025-07-25"),4.,"泡芙"));
	}

	public static Connection connection=DbConnection.getDb();

	@Override
	public void addCustOrder(CustOrder custOrder) {
		String sql="insert into cust_order(custOrderName,custOrderTime,custOrderTimeRecoder,custOrderStaff) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custOrder.getCustOrderName());
			preparedStatement.setString(2, custOrder.getCustOrderTime());
			preparedStatement.setDouble(3, custOrder.getCustOrderTimeRecoder());
			preparedStatement.setString(4, custOrder.getCustOrderStaff());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CustOrder> allCustOrder() {
		List<CustOrder> custOrderList=new ArrayList<>();
		String sql="select * from cust_order";
		CustOrder custOrder=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				custOrder=new CustOrder();
				custOrder.setIdOrder(resultSet.getInt("idOrder"));
				custOrder.setCustOrderName(resultSet.getString("custOrderName"));
				custOrder.setCustOrderTime(resultSet.getString("custOrderTime"));
				custOrder.setCustOrderTimeRecoder(resultSet.getDouble("custOrderTimeRecoder"));
				custOrder.setCustOrderStaff(resultSet.getString("custOrderStaff"));
				custOrderList.add(custOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custOrderList;
	}

	@Override
	public CustOrder selectId(Integer id) {
		String sql="select * from cust_order where idOrder=?";
		CustOrder someorder=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someorder=new CustOrder();
				someorder.setIdOrder(resultSet.getInt("idOrder"));
				someorder.setCustOrderName(resultSet.getString("custOrderName"));
				someorder.setCustOrderTime(resultSet.getString("custOrderTime"));
				someorder.setCustOrderTimeRecoder(resultSet.getDouble("custOrderTimeRecoder"));
				someorder.setCustOrderStaff(resultSet.getString("custOrderStaff"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someorder;
	}

	@Override
	public CustOrder selectSome(String custOrderName) {
		String sql="select * from cust_order where custOrderName=?";
		CustOrder someOrder=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custOrderName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someOrder=new CustOrder();
				someOrder.setIdOrder(resultSet.getInt("id"));
				someOrder.setCustOrderName(resultSet.getString("custOrderName"));
				someOrder.setCustOrderTime(resultSet.getString("custOrderTime"));
				someOrder.setCustOrderTimeRecoder(resultSet.getDouble("custOrderTimeRecoder"));
				someOrder.setCustOrderStaff(resultSet.getString("custOrderStaff"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someOrder;
	}

	@Override
	public CustOrder updateSome(CustOrder custOrder) {
		String sql="update cust_order set custOrderName=?,custOrderTime=?,CustOrderTimeRecoder=?,CustOrderStaff=? where idOrder=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custOrder.getIdOrder())!=null)
			{
				preparedStatement.setString(1, custOrder.getCustOrderName());
				preparedStatement.setString(2, custOrder.getCustOrderTime());
				preparedStatement.setDouble(3, custOrder.getCustOrderTimeRecoder());
				preparedStatement.setString(4, custOrder.getCustOrderStaff());
				preparedStatement.setInt(5, custOrder.getIdOrder());
				preparedStatement.executeUpdate();
				return custOrder;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustOrder deletesome(CustOrder custOrder) {
		String sql="delete from cust_order where idOrder=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custOrder.getIdOrder())!=null)
			{
				preparedStatement.setInt(1, custOrder.getIdOrder());
				preparedStatement.executeUpdate();
				return custOrder;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
