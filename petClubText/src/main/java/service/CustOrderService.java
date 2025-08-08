package service;

import java.util.List;

import model.CustOrder;

public interface CustOrderService {
	// create
	boolean addCustOrder(CustOrder custOrder);

	// read
	List<CustOrder> viewOrder();

	// update
	boolean changeCustOrder(CustOrder custOrder);

	// delete
	boolean deleteCustOrder(CustOrder custOrder);
}
