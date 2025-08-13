package service;

import java.util.List;

import model.CustFreeMember;

public interface CustFreeMemberService {
	// create
	boolean addCustFreeMember(CustFreeMember custFreeMember);

	// read
	CustFreeMember checkCustFreeMember(String custName);
	boolean freeMemberId(String custName);
	List<CustFreeMember> allFreeMember();

	// update
	boolean changeCustFreeMember(CustFreeMember custFreeMember);

	// delete
	boolean deleteCustFreeMember(CustFreeMember custFreeMember);
}
