package dao;

import java.util.List;

import model.CustOrder;

public interface CustOrderDao {
	// create
	public void addCustOrder(CustOrder custOrder);

	// read
	public List<CustOrder> allCustOrder();
	public CustOrder selectId(Integer id);
	public CustOrder selectSome(String custOrderName);

	// update
	public CustOrder updateSome(CustOrder custOrder);

	// delete
	public CustOrder deletesome(CustOrder custOrder);

}
