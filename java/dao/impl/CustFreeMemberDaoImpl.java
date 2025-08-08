package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustFreeMemberDao;
import model.CustFreeMember;
import util.DbConnection;

public class CustFreeMemberDaoImpl implements CustFreeMemberDao{

	public static void main(String[] args) {
		CustFreeMember custFreeMember=new CustFreeMember();
		custFreeMember.setIdFreeMember(2);
		new CustFreeMemberDaoImpl().deletesome(custFreeMember);

		/*
		CustFreeMember custFreeMember=new CustFreeMember();
		custFreeMember.setCustFreeMemberName("狗狗大王");
		custFreeMember.setCustFreeMemberPhone("0956565656");
		custFreeMember.setIdFreeMember(4);
		new CustFreeMemberDaoImpl().updateSome(custFreeMember);
		*/

//		System.out.println(new CustFreeMemberDaoImpl().selectSome("王"));
//		System.out.println(new CustFreeMemberDaoImpl().selectId(4).toString());
//		System.out.println(new CustFreeMemberDaoImpl().allCustFree().toString());
//		new CustFreeMemberDaoImpl().addCustFree(new CustFreeMember("王可汗","0977777777"));
	}

	public static Connection connection=DbConnection.getDb();

	@Override
	public void addCustFree(CustFreeMember custFreeMember) {
		String sql="insert into cust_free_member(custFreeMemberName,custFreeMemberPhone) values(?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custFreeMember.getCustFreeMemberName());
			preparedStatement.setString(2, custFreeMember.getCustFreeMemberPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CustFreeMember> allCustFree() {
		String sql="select * from cust_free_member";
		List<CustFreeMember> custFreeMemberList=new ArrayList<>();
		CustFreeMember custFreeMember=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				custFreeMember=new CustFreeMember();
				custFreeMember.setIdFreeMember(resultSet.getInt("idFreeMember"));
				custFreeMember.setCustFreeMemberName(resultSet.getString("custFreeMemberName"));
				custFreeMember.setCustFreeMemberPhone(resultSet.getString("custFreeMemberPhone"));
				custFreeMemberList.add(custFreeMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custFreeMemberList;
	}

	@Override
	public CustFreeMember selectId(Integer id) {
		String sql="select * from cust_free_member where idFreeMember=?";
		CustFreeMember someone=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someone=new CustFreeMember();
				someone.setIdFreeMember(resultSet.getInt("idFreeMember"));
				someone.setCustFreeMemberName(resultSet.getString("custFreeMemberName"));
				someone.setCustFreeMemberPhone(resultSet.getString("custFreeMemberPhone"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public CustFreeMember selectSome(String custFreeMemberName) {
		String sql="select * from cust_free_member where custFreeMemberName=?";
		CustFreeMember someone=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custFreeMemberName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someone=new CustFreeMember();
				someone.setIdFreeMember(resultSet.getInt("idFreeMember"));
				someone.setCustFreeMemberName(resultSet.getString("custFreeMemberName"));
				someone.setCustFreeMemberPhone(resultSet.getString("custFreeMemberPhone"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public CustFreeMember updateSome(CustFreeMember custFreeMember) {
		String sql="update cust_free_member set custFreeMemberName=?,custFreeMemberPhone=? where idFreeMember=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custFreeMember.getIdFreeMember())!=null)
			{
				preparedStatement.setString(1, custFreeMember.getCustFreeMemberName());
				preparedStatement.setString(2, custFreeMember.getCustFreeMemberPhone());
				preparedStatement.setInt(3, custFreeMember.getIdFreeMember());
				preparedStatement.executeUpdate();
				return custFreeMember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustFreeMember deletesome(CustFreeMember custFreeMember) {
		String sql="delete from cust_free_member where idFreeMember=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custFreeMember.getIdFreeMember())!=null)
			{
				preparedStatement.setInt(1, custFreeMember.getIdFreeMember());
				preparedStatement.executeUpdate();
				return custFreeMember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
