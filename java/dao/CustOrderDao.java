package dao;

import model.CustOrder;

public interface CustOrderDao {
	// create
	public void addCustOrder(CustOrder custOrder);

	// read
	public CustOrder allCustOrder();
	public CustOrder selectId(Integer id);
	public CustOrder selectSome(String custOrderName);

	// update
	public CustOrder updateSome(CustOrder custOrder);

	// delete
	public CustOrder deletesome(CustOrder custOrder);

}
