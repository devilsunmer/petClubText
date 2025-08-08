package service;

import model.StaffMember;

public interface StaffMemberService {
	// create
	boolean addStaffMember(StaffMember staffMember);

	// read
	String viewStaff();

	// update
	boolean changeStaffMember(StaffMember staffMember);

	// delete
	boolean deleteStaffMember(StaffMember staffMember);

}
