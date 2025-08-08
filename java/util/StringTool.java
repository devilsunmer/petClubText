package util;

import model.CustFreeMember;

public class StringTool {
	/** 開始前介紹or補充 */
	public static String showRules() {
		String rules = "首先，分為兩種模式進行。\n" + "第一種視為客人，可以進行時間上的預約、投餵零食或是撫慰心靈等消費。\n" + "第二種視為員工，可以進行時間控管，或是選擇休息。\n"
				+ "最後特別的是，\n" + "老闆模式是在員工系統中直接使用密碼登入，可以看到員工的各式狀況、客人預約或是消費等紀錄。";
		rules = rules + "";

		return rules;
	}

	
	/**暫存**/
	private static CustFreeMember custFreeMember;
	/**暫放freeMember->Member*/
	public static void saveFree(String Name,String phone)
	{
		custFreeMember=new CustFreeMember(Name,phone);
	}
	/**呼叫freeMember->Member*/
	public static CustFreeMember getFree()
	{
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

	public static double hoursSave(Double hours)
	{
		return hours;
	}

	/**帳號密碼正規表示法*/
	public static boolean isUserPass(String UserPass) {
		// 至少 1 個英文字母、1 個數字，且長度為 1-8 個字元
		String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{1,8}$";
		return UserPass.matches(regex);
	}
	
	/**帳號密碼正規表示法*/
	public static boolean isFileName(String UserPass) {
		// 至少 1 個英文字母、1 個數字，且長度為 1-8 個字元
		String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{1,8}$";
		return UserPass.matches(regex);
	}

	/**建立staff檔案自動找尋的String[]*/
	public static String staffNameForPhoto(String getStaffName,Class<?> className) {
		String first="/images/"+getStaffName;
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
	
	public static String[] excelList()
	{
		return new String[] {"顧客消費訂單","寵物員工薪資","會員資料","潛在顧客資料","寵物員工資料"};
	}

}
