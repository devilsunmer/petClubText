package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BossDao;
import model.Boss;
import util.DbConnection;

public class BossDaoImpl implements BossDao {
	public static void main(String[] args) {

	}

	Connection connection = DbConnection.getDb();

	@Override
	public void addBoss(Boss boss) {
		String sql="insert into boss(bossName,bossUsername,bossPassword) values(?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, boss.getBossName());
			preparedStatement.setString(2, boss.getBossUsername());
			preparedStatement.setString(3, boss.getBossPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Boss> allBoss() {
		String sql="select * from boss";
		List<Boss> bossList=new ArrayList<>();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Boss boss=new Boss();
				boss.setIdBoss(resultSet.getInt("idBoss"));
				boss.setBossName(resultSet.getString("bossName"));
				boss.setBossUsername(resultSet.getString("bossUsername"));
				boss.setBossPassword(resultSet.getString("bossPassword"));
				bossList.add(boss);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bossList;
	}

	@Override
	public Boss selectId(Integer id) {
		String sql="select * from boss where idBoss=?";
		Boss boss=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				{
				boss=new Boss();
				boss.setIdBoss(resultSet.getInt("idBoss"));
				boss.setBossName(resultSet.getString("bossName"));
				boss.setBossUsername(resultSet.getString("bossUsername"));
				boss.setBossPassword(resultSet.getString("bossPassword"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return boss;
	}

	@Override
	public Boss selectBoss(String bossUsername, String bossPassword) {
		String sql = "select * from boss where bossUsername=? and bossPassword=?";
		Boss boss = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bossUsername);
			preparedStatement.setString(2, bossPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				boss = new Boss();
				boss.setIdBoss(resultSet.getInt("idBoss"));
				boss.setBossName(resultSet.getString("bossName"));
				boss.setBossUsername(resultSet.getString("bossUsername"));
				boss.setBossPassword(resultSet.getString("bossPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boss;
	}

	@Override
	public Boss updateBoss(Boss boss) {
		String sql = "update boss set bossName=?,bossUsername=?,bossPassword=?" + " where idBoss=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (selectId(boss.getIdBoss()) != null) {
				preparedStatement.setString(1, boss.getBossName());
				preparedStatement.setString(2, boss.getBossUsername());
				preparedStatement.setString(3, boss.getBossPassword());
				preparedStatement.setInt(4, boss.getIdBoss());
				preparedStatement.executeUpdate();
				return boss;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boss deleteBoss(Boss boss) {
		String sql = "delete from boss where idBoss=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (selectId(boss.getIdBoss()) != null) {
				preparedStatement.setInt(1, boss.getIdBoss());
				preparedStatement.executeUpdate();
				return boss;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
