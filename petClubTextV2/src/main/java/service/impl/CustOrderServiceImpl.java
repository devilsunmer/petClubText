package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.impl.CustOrderDaoImpl;
import model.CustOrder;
import service.CustOrderService;

public class CustOrderServiceImpl implements CustOrderService{

	public static void main(String[] args) {
		/*
		CustOrder custOrder=new CustOrder();
		custOrder.setIdOrder(7);
		new CustOrderServiceImpl().deleteCustOrder(custOrder);
		*/
		/*
		CustOrder custOrder=new CustOrder();
		custOrder.setCustOrderTime("09");
		custOrder.setCustOrderTimeRecoder(0.);
		custOrder.setIdOrder(7);
		new CustOrderServiceImpl().changeCustOrder(custOrder);
		*/
//		System.out.println(new CustOrderServiceImpl().viewOrder());
//		new CustOrderServiceImpl().addCustOrder(new CustOrder("海洋","2025-07-17",3.,"妹妹"));
	}

	CustOrderDaoImpl custOrderDaoImpl=new CustOrderDaoImpl();

	@Override
	public boolean addCustOrder(CustOrder custOrder) {
		if(custOrder!=null)
		{
			custOrderDaoImpl.addCustOrder(custOrder);
			return true;
		}
		return false;
	}

	@Override
	public List<CustOrder> viewOrder() {
		
		return custOrderDaoImpl.allCustOrder();
	}

	@Override
	public boolean changeCustOrder(CustOrder custOrder) {
		if(custOrder!=null)
		{
			custOrderDaoImpl.updateSome(custOrder);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustOrder(CustOrder custOrder) {
		if(custOrder!=null)
		{
			custOrderDaoImpl.deletesome(custOrder);
			return true;
		}
		return false;
	}



}
