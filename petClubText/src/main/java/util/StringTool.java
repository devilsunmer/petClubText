package util;

import java.util.List;

import javax.swing.JOptionPane;

import model.CustFreeMember;
import model.CustMember;
import model.CustOrder;
import model.StaffMember;
import service.impl.CustFreeMemberServiceImpl;
import service.impl.CustMemberServiceImpl;
import service.impl.CustOrderServiceImpl;
import service.impl.StaffMemberServiceImpl;

public class StringTool {
	/** 開始前介紹or補充 */
	public static String showRules() {
		String rules = "首先，分為兩種模式進行。\n" + "第一種視為客人，可以進行時間上的預約、投餵零食或是撫慰心靈等消費。\n" + "第二種視為員工，可以進行時間控管，或是選擇休息。\n"
				+ "最後特別的是，\n" + "老闆模式是在員工系統中直接使用密碼登入，可以看到員工的各式狀況、客人預約或是消費等紀錄。";
		rules = rules + "";

		return rules;
	}

	/** 暫存Member **/
	private static CustMember custMember;

	/** 暫放Member */
	public static void saveMember(String Name, String phone) {
		if (Name != null && !Name.trim().isEmpty() && phone != null && !phone.trim().isEmpty()) {
			// 如果傳入的資料有效，創建新的 CustFreeMember 並賦值給 custMember
			custMember = new CustMember(Name, phone);
		} else {
			// 如果傳入資料無效，則不設置 custMember 或將其設為 null
			custMember = null;
		}
	}

	/** 呼叫Member */
	public static CustMember getMember() {
		return custMember;
	}

	/** 暫存freeMember **/
	private static CustFreeMember custFreeMember;

	/** 暫放freeMember->Member */
	public static void saveFree(String Name, String phone) {
		custFreeMember = new CustFreeMember(Name, phone);
	}

	/** 呼叫freeMember->Member */
	public static CustFreeMember getFree() {
		return custFreeMember;
	}

	/** comobox換算時間成double */
	public static double convertToHours(String selectedItem) {
		switch (selectedItem) {
		case "半小時":
			return 0.5;
		case "一小時":
			return 1.0;
		case "一小時半":
			return 1.5;
		case "兩小時":
			return 2.0;
		default:
			return 0.0; // 如果選擇是空的，則返回 0.0
		}
	}

	public static double hoursSave(Double hours) {
		return hours;
	}

	/** 帳號密碼正規表示法 */
	public static boolean isUserPass(String UserPass) {
		// 至少 1 個英文字母、1 個數字，且長度為 1-8 個字元
		String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{1,8}$";
		return UserPass.matches(regex);
	}

	/** 帳號密碼正規表示法 */
	public static boolean isFileName(String UserPass) {
		// 至少 1 個英文字母、1 個數字，且長度為 1-8 個字元
		String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{1,8}$";
		return UserPass.matches(regex);
	}

	/** 建立staff檔案自動找尋的String[] */
	public static String staffNameForPhoto(String getStaffName, Class<?> className) {
		String first = "/images/" + getStaffName;
		String[] third = { ".jpg", ".JPG", ".png" };
		for (String out : third) {
			String path = first + out; // 拼接檔案路徑
			java.net.URL imageUrl = className.getResource(path); // 檢查該路徑的資源是否存在
			if (imageUrl != null) {
				return path; // 如果找到了，返回完整路徑
			}
		}
		return "/images/unKnow.jpg";
	}

	public static String[] excelList() {
		return new String[] { "顧客消費訂單", "寵物員工薪資", "會員資料", "潛在顧客資料", "寵物員工資料" };
	}

	/** 根據選擇的報表名稱去製作相關欄位 */
	public static String[] getTitleNamesForReport(String reportName) {
		switch (reportName) {
		case "顧客消費訂單":
			return new String[] { "訂單編號", "顧客姓名", "預約日期", "預約時長", "消費金額", "會員類型" };
		case "寵物員工薪資":
			return new String[] { "寵物員工編號", "寵物員工名字", "寵物員工賺進金額", "寵物員工獎勵零食" };
		case "會員資料":
			return new String[] { "顧客會員ID", "顧客會員名稱", "顧客會員帳號", "顧客會員密碼", "顧客電話" };
		case "潛在顧客資料":
			return new String[] { "潛在顧客編號", "潛在顧客名稱", "潛在顧客聯繫電話" };
		case "寵物員工資料":
			return new String[] { "寵物員工編號", "寵物員工名字" };
		default:
			return new String[] {}; // 若無對應的報表返回空陣列
		}
	}

	/** 根據選擇的報表名稱去輸出相關報表 */
	public static void useTitleNameForReport(String reportName, String fileName, String sheetName) {
		ExcelCreat excelCreate = new ExcelCreat();
		switch (reportName) {
		case "顧客消費訂單":
			List<CustOrder> custOrderList = new CustOrderServiceImpl().viewOrder();
			for (CustOrder order : custOrderList) {
				excelCreate.insertCustOrderMoneyValue(order, fileName, sheetName); // 生成報表
			}

			break;
		case "寵物員工薪資":
			List<StaffMember> staffList = new StaffMemberServiceImpl().viewStaff();
			for (StaffMember order : staffList) {
				excelCreate.insertStaffMemberValue(order, fileName, sheetName);
			}
			break;
		case "會員資料":
			List<CustMember> memberList = new CustMemberServiceImpl().allMember();
			for (CustMember order : memberList) {
				excelCreate.insertCustMemberValue(order, fileName, sheetName);
			}

			break;
		case "潛在顧客資料":
			List<CustFreeMember> freeMemberList = new CustFreeMemberServiceImpl().allFreeMember();
			for (CustFreeMember order : freeMemberList) {
				excelCreate.insertCustFreeMemberValue(order, fileName, sheetName);
			}

			break;
		case "寵物員工資料":
			List<StaffMember> staffMemberList = new StaffMemberServiceImpl().viewStaff();
			for (StaffMember order : staffMemberList) {
				excelCreate.insertStaffMemberValue(order, fileName, sheetName);
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "報表未找到", "報表提示", JOptionPane.INFORMATION_MESSAGE);
			break;
		}

	}

}
