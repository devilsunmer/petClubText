package dao;

import java.util.List;

import model.Boss;

public interface BossDao {
	// create
	public void addBoss(Boss boss);

	// read
	public List<Boss> allBoss();
	public Boss selectId(Integer id);
	public Boss selectBoss(String bossUsername,String bossPassword);


	// update
	public Boss updateBoss(Boss boss);

	// delete
	public Boss deleteBoss(Boss boss);
}
