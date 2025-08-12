package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustMemberDao;
import model.CustMember;
import util.DbConnection;

public class CustMemberDaoImpl implements CustMemberDao{

	public static void main(String[] args) {
		/*
		CustMember custmember=new CustMember();
		custmember.setId(4);
		System.out.println(new CustMemberDaoImpl().deleteSome(custmember));
		*/


		/*
		CustMember custmember=new CustMember();
		custmember.setCustMemberName("劉陽明");
		custmember.setCustUsername("useruser");
		custmember.setCustPassword("456");
		custmember.setId(3);
		System.out.println(new CustMemberDaoImpl().updateSome(custmember));
		*/

//		System.out.println(new CustMemberDaoImpl().selectCust("user1","123"));
//		System.out.println(new CustMemberDaoImpl().selectCust("user1"));
//		System.out.println(new CustMemberDaoImpl().selectId(2).toString());
//		System.out.println(new CustMemberDaoImpl().selectAll());
//		new CustMemberDaoImpl().addMember(new CustMember("test","user3","123","0911111111"));
	}

	public static Connection connection=DbConnection.getDb();

	@Override
	public void addMember(CustMember custmember) {
		String sql="insert into cust_member(custMemberName,custUsername,custPassword,custMemberPhone) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custmember.getCustMemberName());
			preparedStatement.setString(2, custmember.getCustUsername());
			preparedStatement.setString(3, custmember.getCustPassword());
			preparedStatement.setString(4, custmember.getCustMemberPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CustMember> selectAll() {
		List<CustMember> custmemberList=new ArrayList<>();
		CustMember someone=null;
		String sql="select * from cust_member";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				someone=new CustMember();
				someone.setIdMember(resultSet.getInt("idMember"));
				someone.setCustMemberName(resultSet.getString("custMembername"));
				someone.setCustUsername(resultSet.getString("custUsername"));
				someone.setCustPassword(resultSet.getString("custPassword"));
				someone.setCustMemberPhone(resultSet.getString("custMemberPhone"));
				custmemberList.add(someone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return custmemberList;
	}

	@Override
	public CustMember selectId(Integer id) {
		String sql="select * from cust_member where idMember=?";
		CustMember someone=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someone=new CustMember();
				someone.setIdMember(resultSet.getInt("idMember"));
				someone.setCustMemberName(resultSet.getString("custMembername"));
				someone.setCustUsername(resultSet.getString("custUsername"));
				someone.setCustPassword(resultSet.getString("custPassword"));
				someone.setCustMemberPhone(resultSet.getString("custMemberPhone"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public CustMember selectCust(String custUsername, String custPassword) {
		String sql="select * from cust_member where custUsername=? and custPassword=?";
		CustMember someone=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custUsername);
			preparedStatement.setString(2, custPassword);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someone=new CustMember();
				someone.setIdMember(resultSet.getInt("idMember"));
				someone.setCustMemberName(resultSet.getString("custMembername"));
				someone.setCustUsername(resultSet.getString("custUsername"));
				someone.setCustPassword(resultSet.getString("custPassword"));
				someone.setCustMemberPhone(resultSet.getString("custMemberPhone"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public CustMember selectCust(String custUsername) {
		String sql="select * from cust_member where custUsername=?";
		CustMember someone=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, custUsername);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someone=new CustMember();
				someone.setIdMember(resultSet.getInt("idMember"));
				someone.setCustMemberName(resultSet.getString("custMembername"));
				someone.setCustUsername(resultSet.getString("custUsername"));
				someone.setCustPassword(resultSet.getString("custPassword"));
				someone.setCustMemberPhone(resultSet.getString("custMemberPhone"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someone;
	}

	@Override
	public CustMember updateSome(CustMember custmember) {
		String sql="update cust_member set custMemberName=?,custUsername=?,custPassword=?,custMemberPhone=? where idMember=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custmember.getIdMember())!=null)
			{
				preparedStatement.setString(1, custmember.getCustMemberName());
				preparedStatement.setString(2, custmember.getCustUsername());
				preparedStatement.setString(3, custmember.getCustPassword());
				preparedStatement.setString(4, custmember.getCustMemberPhone());
				preparedStatement.setInt(5, custmember.getIdMember());
				preparedStatement.executeUpdate();
				return custmember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustMember deleteSome(CustMember custmember) {
		String sql="delete from cust_member where idMember=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(custmember.getIdMember())!=null)
			{
				preparedStatement.setInt(1, custmember.getIdMember());
				preparedStatement.executeUpdate();
				return custmember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}







}
