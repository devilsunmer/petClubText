package util.forPrint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import model.CustFreeMember;
import model.CustMember;
import model.CustOrder;
import model.StaffIncome;
import model.StaffMember;
import service.impl.CustFreeMemberServiceImpl;
import service.impl.CustMemberServiceImpl;

public class ExcelCreat {
	static HSSFSheet sheet=null;
	HSSFWorkbook excelbook=new HSSFWorkbook();
	
	/**新增xls檔案*/
	public void create(String fileName,String sheetName,String[] titleName)
	{
		File dir = new File("reporter");  // 建議用正確資料夾名稱
		if (!dir.exists()) {
		    dir.mkdirs();
		}
		
		if (!fileName.toLowerCase().endsWith(".xls")) {
		    fileName = fileName + ".xls";
		} 
		File file = new File(fileName);
		if(!file.exists()) {
		    System.out.println("檔案不存在: " + file.getAbsolutePath());

			try {
				FileOutputStream out=new FileOutputStream(fileName);
				HSSFSheet sheet=excelbook.getSheet(sheetName);
				HSSFRow row=sheet.createRow(0);
				for(int i=0;i<titleName.length;i++)
				{
					row.createCell((short)i).setCellValue(titleName[i]);
				}
				excelbook.write(out);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**顧客消費*/
	public void insertCustOrderMoneyValue(CustOrder custOrder,String fileName,String sheetName)
	{
		
		try {
			excelbook=new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet=excelbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			HSSFRow row=sheet.createRow((short)count);
			row.createCell((short)0).setCellValue(custOrder.getIdOrder());
			row.createCell((short)1).setCellValue(custOrder.getCustOrderName());
			row.createCell((short)2).setCellValue(custOrder.getCustOrderTime());
			row.createCell((short)3).setCellValue(custOrder.getCustOrderTimeRecoder());
			Double spentMoney;
			if (new CustMemberServiceImpl().viewMemberName(custOrder.getCustOrderName())!=null) {
				spentMoney=custOrder.getCustOrderTimeRecoder()*300*0.85;
			} else if(new CustFreeMemberServiceImpl().freeMemberId(custOrder.getCustOrderName())) {
				spentMoney=custOrder.getCustOrderTimeRecoder()*300;
			} else {
				spentMoney=custOrder.getCustOrderTimeRecoder()*300*1.2;
			}
			row.createCell((short)4).setCellValue(spentMoney);
			String memberId;
			if (new CustMemberServiceImpl().viewMemberName(custOrder.getCustOrderName())!=null) {
				memberId = "會員";  // 在會員表中找到
			} else if (new CustFreeMemberServiceImpl().freeMemberId(custOrder.getCustOrderName())) {
				memberId = "非會員";  // 在非會員表中找到
			} else {
				memberId = "未知"; // 兩個表都找不到
			}
			row.createCell((short)5).setCellValue(memberId);
			FileOutputStream out;// 新增輸出檔案流
		    out = new FileOutputStream(fileName);
		    excelbook.write(out);// 把對應的Excel工作簿存碟
		    out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**寵物員工薪資表**/
	public void insertStaffWorkValue(StaffIncome staffIncome,String fileName,String sheetName)
	{
		try {

			excelbook=new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet=excelbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			HSSFRow row=sheet.createRow((short)count);
			row.createCell((short)0).setCellValue(staffIncome.getIdStaffIncome());
			row.createCell((short)1).setCellValue(staffIncome.getStaffName());
			row.createCell((short)2).setCellValue(staffIncome.getIdStaffIncome());
			row.createCell((short)3).setCellValue(staffIncome.getStaffFood());
			FileOutputStream out;// 新增輸出檔案流
		    out = new FileOutputStream(fileName);
		    excelbook.write(out);// 把對應的Excel工作簿存碟
		    out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**會員資料報表*/
	public void insertCustMemberValue(CustMember CustMember,String fileName,String sheetName)
	{
		try {
			excelbook=new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet=excelbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			HSSFRow row=sheet.createRow((short)count);
			row.createCell((short)0).setCellValue(CustMember.getIdMember());
			row.createCell((short)1).setCellValue(CustMember.getCustMemberName());
			row.createCell((short)2).setCellValue(CustMember.getCustUsername());
			row.createCell((short)3).setCellValue(CustMember.getCustPassword());
			row.createCell((short)4).setCellValue(CustMember.getCustMemberPhone());
			FileOutputStream out;// 新增輸出檔案流
		    out = new FileOutputStream(fileName);
		    excelbook.write(out);// 把對應的Excel工作簿存碟
		    out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**潛在顧客資料*/
	public void insertCustFreeMemberValue(CustFreeMember custFreeMember,String fileName,String sheetName)
	{
		try {
			excelbook=new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet=excelbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			HSSFRow row=sheet.createRow((short)count);
			row.createCell((short)0).setCellValue(custFreeMember.getIdFreeMember());
			row.createCell((short)1).setCellValue(custFreeMember.getCustFreeMemberName());
			row.createCell((short)2).setCellValue(custFreeMember.getCustFreeMemberPhone());
			FileOutputStream out;// 新增輸出檔案流
		    out = new FileOutputStream(fileName);
		    excelbook.write(out);// 把對應的Excel工作簿存碟
		    out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**寵物員工資料*/
	public void insertStaffMemberValue(StaffMember staffMember,String fileName,String sheetName)
	{
		try {
			excelbook=new HSSFWorkbook(new FileInputStream(fileName));
			HSSFSheet sheet=excelbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			HSSFRow row=sheet.createRow((short)count);
			row.createCell((short)0).setCellValue(staffMember.getIdStaff());
			row.createCell((short)1).setCellValue(staffMember.getStaffName());
			FileOutputStream out;// 新增輸出檔案流
		    out = new FileOutputStream(fileName);
		    excelbook.write(out);// 把對應的Excel工作簿存碟
		    out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
