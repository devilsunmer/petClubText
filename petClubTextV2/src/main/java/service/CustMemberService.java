package service;

import java.util.List;

import model.CustMember;

public interface CustMemberService {
	//create
	boolean addCustMember(CustMember custMember);

	//read
	CustMember login(String custUsername,String custPassword);
	CustMember viewMemberName(String custUsername);
	List<CustMember> allMember();

	//update
	boolean changeCustMember(CustMember custMember);

	//delete
	boolean deleteCustMember(CustMember custMember);
}
