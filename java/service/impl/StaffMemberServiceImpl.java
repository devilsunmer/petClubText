package service.impl;

import java.util.List;

import dao.impl.StaffMemberDaoImpl;
import model.StaffMember;
import service.StaffMemberService;

public class StaffMemberServiceImpl implements StaffMemberService{

	public static void main(String[] args) {
//		StaffMember staffMember=new StaffMember();
//		staffMember.setIdStaff(5);
//		new StaffMemberServiceImpl().deleteStaffMember(staffMember);
		/*
		StaffMember staffMember=new StaffMember();
		staffMember.setStaffName("紐乃");
		staffMember.setIdStaff(5);
		new StaffMemberServiceImpl().changeStaffMember(staffMember);
		*/
//		System.out.println(new StaffMemberServiceImpl().viewStaff());
//		new StaffMemberServiceImpl().addStaffMember(new StaffMember("泡芙2"));
	}

	StaffMemberDaoImpl staffMemberDaoImpl=new StaffMemberDaoImpl();

	@Override
	public boolean addStaffMember(StaffMember staffMember) {
		if(staffMember!=null)
		{
			staffMemberDaoImpl.addStaffMember(staffMember);
			return true;
		}
		return false;
	}

	@Override
	public String viewStaff() {
		List<StaffMember> staffMemberList=staffMemberDaoImpl.allStaffMember();
		if(staffMemberList!=null && !staffMemberList.isEmpty())
		{
			return staffMemberList.toString();
		}
		return null;
	}

	@Override
	public boolean changeStaffMember(StaffMember staffMember) {
		if(staffMember!=null)
		{
			staffMemberDaoImpl.updateSome(staffMember);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStaffMember(StaffMember staffMember) {
		if(staffMember!=null)
		{
			staffMemberDaoImpl.deletesome(staffMember);
			return true;
		}
		return false;
	}

}
