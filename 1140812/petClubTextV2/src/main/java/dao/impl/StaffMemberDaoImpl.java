package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StaffMemberDao;
import model.StaffMember;
import util.DbConnection;

public class StaffMemberDaoImpl implements StaffMemberDao{

	public static void main(String[] args) {
		/*
		StaffMember staffMember=new StaffMember();
		staffMember.setIdStaff(3);
		new StaffMemberDaoImpl().deletesome(staffMember);
		*/


		/*
		StaffMember staffMember=new StaffMember();
		staffMember.setStaffName("妹妹");
		staffMember.setId(2);
		new StaffMemberDaoImpl().updateSome(staffMember);
		*/

//		System.out.println(new StaffMemberDaoImpl().selectSome("姐姐"));
//		System.out.println(new StaffMemberDaoImpl().allStaffMember().toString());
//		new StaffMemberDaoImpl().addStaffMember(new StaffMember("測試2"));
	}

	public static Connection connection=DbConnection.getDb();

	@Override
	public void addStaffMember(StaffMember staffMember) {
		String sql="insert into staff_member(staffName) values(?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, staffMember.getStaffName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<StaffMember> allStaffMember() {
		String sql="select * from staff_member";
		List<StaffMember> staffMemberList=new ArrayList<>();
		StaffMember staffMember=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				staffMember=new StaffMember();
				staffMember.setIdStaff(resultSet.getInt("idStaff"));
				staffMember.setStaffName(resultSet.getString("staffName"));
				staffMemberList.add(staffMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffMemberList;
	}

	@Override
	public StaffMember selectId(Integer id) {
		String sql="select * from staff_member where idStaff=?";
		StaffMember somestaff=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				somestaff=new StaffMember();
				somestaff.setIdStaff(resultSet.getInt("idStaff"));
				somestaff.setStaffName(resultSet.getString("staffName"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return somestaff;
	}

	@Override
	public StaffMember selectSome(String staffMemberName) {
		String sql="select * from staff_member where staffName=?";
		StaffMember someStaff=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, staffMemberName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				someStaff=new StaffMember();
				someStaff.setIdStaff(resultSet.getInt("idStaff"));
				someStaff.setStaffName(resultSet.getString("staffName"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return someStaff;
	}

	@Override
	public StaffMember updateSome(StaffMember staffMember) {
		String sql="update staff_member set staffName=? where idStaff=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(staffMember.getIdStaff())!=null)
			{
				preparedStatement.setString(1, staffMember.getStaffName());
				preparedStatement.setInt(2, staffMember.getIdStaff());
				preparedStatement.executeUpdate();
				return staffMember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StaffMember deletesome(StaffMember staffMember) {
		String sql="delete from staff_member where idStaff=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(selectId(staffMember.getIdStaff())!=null)
			{
				preparedStatement.setInt(1, staffMember.getIdStaff());
				preparedStatement.executeUpdate();
				return staffMember;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
