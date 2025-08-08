package service;

import model.CustOrder;

public interface CustOrderService {
	// create
	boolean addCustOrder(CustOrder custOrder);

	// read
	String viewOrder();

	// update
	boolean changeCustOrder(CustOrder custOrder);

	// delete
	boolean deleteCustOrder(CustOrder custOrder);
}
