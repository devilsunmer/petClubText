package dao;

import java.util.List;

import model.StaffMember;

public interface StaffMemberDao {
	// create
	public void addStaffMember(StaffMember staffMember);

	// read
	public List<StaffMember> allStaffMember();
	public StaffMember selectId(Integer id);
	public StaffMember selectSome(String staffMemberName);

	// update
	public StaffMember updateSome(StaffMember staffMember);

	// delete
	public StaffMember deletesome(StaffMember staffMember);
}
