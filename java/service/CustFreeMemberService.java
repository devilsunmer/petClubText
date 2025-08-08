package service;

import model.CustFreeMember;

public interface CustFreeMemberService {
	// create
	boolean addCustFreeMember(CustFreeMember custFreeMember);

	// read
	CustFreeMember checkCustFreeMember(String custName);
	boolean freeMemberId(String custName);
	String allFreeMember();

	// update
	boolean changeCustFreeMember(CustFreeMember custFreeMember);

	// delete
	boolean deleteCustFreeMember(CustFreeMember custFreeMember);
}
