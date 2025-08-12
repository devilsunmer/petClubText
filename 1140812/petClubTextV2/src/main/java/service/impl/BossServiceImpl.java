package service.impl;

import dao.impl.BossDaoImpl;
import model.Boss;
import service.BossService;

public class BossServiceImpl implements BossService{
	BossDaoImpl BossDaoImpl=new BossDaoImpl();

	@Override
	public boolean addBoss(Boss boss) {
		if(boss!=null)
		{
			BossDaoImpl.addBoss(boss);
			return true;
		}
		return false;
	}

	@Override
	public Boss login(String bossUsername, String bossPassword) {
		return BossDaoImpl.selectBoss(bossUsername, bossPassword);
	}

	@Override
	public String allBoss() {
		return BossDaoImpl.allBoss().toString();
	}

	@Override
	public boolean changeBoss(Boss boss) {
		if(boss!=null)
		{
			BossDaoImpl.updateBoss(boss);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBoss(Boss boss) {
		if(boss!=null)
		{
			BossDaoImpl.deleteBoss(boss);
			return true;
		}
		return false;
	}

}
