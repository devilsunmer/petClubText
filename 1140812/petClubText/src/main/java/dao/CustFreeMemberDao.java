package dao;

import java.util.List;

import model.CustFreeMember;

public interface CustFreeMemberDao {
	//create
	public void addCustFree(CustFreeMember custFreeMember);

	//read
	public List<CustFreeMember> allCustFree();
	public CustFreeMember selectId(Integer id);
	public CustFreeMember selectSome(String custFreeMemberName);

	//update
	public CustFreeMember updateSome(CustFreeMember custFreeMember);

	//delete
	public CustFreeMember deletesome(CustFreeMember custFreeMember);


}
