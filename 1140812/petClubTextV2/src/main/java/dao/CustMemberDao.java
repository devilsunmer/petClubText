package dao;

import java.util.List;

import model.CustMember;

public interface CustMemberDao {
	//create
	public void addMember(CustMember custmember);

	//read
	public List<CustMember> selectAll();
	public CustMember selectId(Integer id);
	public CustMember selectCust(String custUsername,String custPassword);//用來確認是否有帳號密碼
	public CustMember selectCust(String custUsername);//用來展示使用者名稱

	//update
	public CustMember updateSome(CustMember custmember);//用來改帳號密碼的部分


	//delete
	public CustMember deleteSome(CustMember custmember);


}
