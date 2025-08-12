package service;

import java.util.List;

import model.StaffMember;

public interface StaffMemberService {
	// create
	boolean addStaffMember(StaffMember staffMember);

	// read
	List<StaffMember> viewStaff();

	// update
	boolean changeStaffMember(StaffMember staffMember);

	// delete
	boolean deleteStaffMember(StaffMember staffMember);

}
