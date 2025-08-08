package service.impl;

import java.util.List;

import dao.impl.CustMemberDaoImpl;
import model.CustMember;
import service.CustMemberService;

public class CustMemberServiceImpl implements CustMemberService{

	public static void main(String[] args) {
		CustMember custMember=new CustMember();
		custMember.setIdMember(5);
		new CustMemberServiceImpl().deleteCustMember(custMember);


		/*
		CustMember custMember=new CustMember();
		custMember.setCustMemberName("陰天");
		custMember.setCustUsername("badday");
		custMember.setCustPassword("0000");
		custMember.setCustMemberPhone("09565656565656");
		custMember.setIdMember(5);
		new CustMemberServiceImpl().changeCustMember(custMember);
		*/

//		System.out.println(new CustMemberServiceImpl().allMember());
//		System.out.println(new CustMemberServiceImpl().viewMemberName("ocean"));
//		System.out.println(new CustMemberServiceImpl().login("useruser", "123"));
//		new CustMemberServiceImpl().addCustMember(new CustMember("海洋","ocean","12345","0989898989"));
	}

	CustMemberDaoImpl custMemberDaoImpl=new CustMemberDaoImpl();

	@Override
	public boolean addCustMember(CustMember custMember) {
		if(custMember!=null)
		{
			custMemberDaoImpl.addMember(custMember);
			return true;
		}
		return false;
	}

	@Override
	public CustMember login(String custUsername, String custPassword) {
		return custMemberDaoImpl.selectCust(custUsername, custPassword);
	}

	@Override
	public CustMember viewMemberName(String custUsername) {
		return custMemberDaoImpl.selectCust(custUsername);
	}

	@Override
	public List<CustMember> allMember() {
		return custMemberDaoImpl.selectAll();
	}

	@Override
	public boolean changeCustMember(CustMember custMember) {
		if(custMember!=null)
		{
			custMemberDaoImpl.updateSome(custMember);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustMember(CustMember custMember) {
		if(custMember!=null)
		{
			custMemberDaoImpl.deleteSome(custMember);
			return true;
		}
		return false;
	}

}
