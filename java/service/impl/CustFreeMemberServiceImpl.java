package service.impl;

import java.util.List;

import dao.impl.CustFreeMemberDaoImpl;
import model.CustFreeMember;
import service.CustFreeMemberService;

public class CustFreeMemberServiceImpl implements CustFreeMemberService {

	public static void main(String[] args) {
		CustFreeMember custFreeMember=new CustFreeMember();
		custFreeMember.setIdFreeMember(4);
		new CustFreeMemberServiceImpl().deleteCustFreeMember(custFreeMember);

		/*
		CustFreeMember custFreeMember=new CustFreeMember();
		custFreeMember.setCustFreeMemberPhone("0918765243");
		custFreeMember.setIdFreeMember(4);
		new CustFreeMemberServiceImpl().changeCustFreeMember(custFreeMember);
		*/
//		System.out.print(new CustFreeMemberServiceImpl().allFreeMember());
//		System.out.println(new CustFreeMemberServiceImpl().checkCustFreeMember("王"));
//		new CustFreeMemberServiceImpl().addCustFreeMember(new CustFreeMember("閻羅王", "0944444444"));
	}

	CustFreeMemberDaoImpl custFreeMemberDaoImpl = new CustFreeMemberDaoImpl();

	@Override
	public boolean addCustFreeMember(CustFreeMember custFreeMember) {
		boolean addMemberWork = false;
		if (custFreeMember != null) {
			custFreeMemberDaoImpl.addCustFree(custFreeMember);
			addMemberWork = true;
		}
		return addMemberWork;
	}

	@Override
	public CustFreeMember checkCustFreeMember(String custName) {
		return custFreeMemberDaoImpl.selectSome(custName);
	}

	@Override
	public String allFreeMember() {
		List<CustFreeMember> custFreeMemberList=custFreeMemberDaoImpl.allCustFree();

		if(custFreeMemberList!=null && !custFreeMemberList.isEmpty())
		{
			StringBuilder stringBuild=new StringBuilder();
			for(CustFreeMember freeMember:custFreeMemberList)
			{
				stringBuild.append(freeMember.toString());
			}
			return stringBuild.toString();
		}

		return null;

	}

	@Override
	public boolean changeCustFreeMember(CustFreeMember custFreeMember) {
		if(custFreeMember!=null)
		{
			custFreeMemberDaoImpl.updateSome(custFreeMember);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustFreeMember(CustFreeMember custFreeMember) {
		if(custFreeMember!=null)
		{
			custFreeMemberDaoImpl.deletesome(custFreeMember);
			return true;
		}
		return false;
	}

	@Override
	public boolean freeMemberId(String custFreeMember) {
		if(custFreeMember!=null)
		{
			custFreeMemberDaoImpl.selectSome(custFreeMember);
			return true;
		}
		return false;
	}

}
