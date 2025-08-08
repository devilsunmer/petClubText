package service;

import model.CustMember;

public interface CustMemberService {
	//create
	boolean addCustMember(CustMember custMember);

	//read
	CustMember login(String custUsername,String custPassword);
	CustMember viewMemberName(String custUsername);
	String allMember();

	//update
	boolean changeCustMember(CustMember custMember);

	//delete
	boolean deleteCustMember(CustMember custMember);
}
