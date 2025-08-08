package util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.impl.CustOrderDaoImpl;
import model.CustOrder;

public class ToolByStaffFood {

	public static void main(String[] args) {
		String foodStr = "1根狗骨頭, 3根狗骨頭, 2包貓尾草, 一包麵包蟲";
        Map<String, Integer> result = viewStaffFood(foodStr);
        System.out.println(result); // 顯示結果
	}

	private static CustOrderDaoImpl custOrderDaoImpl = new CustOrderDaoImpl();

	// 將中文數字轉換為阿拉伯數字
	public static int chineseToNumber(String chinese) {
		Map<String, Integer> chineseDigits = new HashMap<>();
		chineseDigits.put("一", 1);
		chineseDigits.put("二", 2);
		chineseDigits.put("三", 3);
		chineseDigits.put("四", 4);
		chineseDigits.put("五", 5);
		chineseDigits.put("六", 6);
		chineseDigits.put("七", 7);
		chineseDigits.put("八", 8);
		chineseDigits.put("九", 9);
		chineseDigits.put("十", 10);
		if (chineseDigits.containsKey(chinese)) {
			return chineseDigits.get(chinese);
		}
		return -1; // 無法轉換
	}

	public static Map<String, Integer> viewStaffFood(String foodStr) {
		// 使用 Map 儲存食物名稱和對應的總數
		Map<String, Integer> foodCountMap = new HashMap<>();
		// 正則表達式，匹配數字後跟食物名稱
		Pattern pattern = Pattern.compile("(\\d+|[一二三四五六七八九十]+)(根|包|只|條)?([\\u4e00-\\u9fa5]+)"); // 匹配數字或中文數字後面是單位(根、包、條等)，後面是食物名稱
		Matcher matcher = pattern.matcher(foodStr);
		// 循環匹配字串，並將食物名稱和數字進行累加
		while (matcher.find()) {
			String quantityStr = matcher.group(1); // 數量部分
			String foodName = matcher.group(3); // 食物名稱部分
			int quantity = 0;
			// 如果是中文數字
			if (quantityStr.matches("[一二三四五六七八九十]+")) {
				quantity = chineseToNumber(quantityStr);
			} else {
				// 如果是數字
				quantity = Integer.parseInt(quantityStr);
			}
			// 如果 Map 中已經有這個食物名稱，則累加數量，否則初始化為數字
			foodCountMap.put(foodName, foodCountMap.getOrDefault(foodName, 0) + quantity);
		}
		return foodCountMap;
	}

	// 根據訂單金額計算員工總收入
	public static double calculateStaffIncome(double workHours, double foodAmount) {
        // 計算工作收入：訂單時間（小時） * 每小時工資
        double workIncome = workHours * 300;
        // 返回總收入：工作收入 + 食物收入
        return workIncome + foodAmount;
    }

    // 根據訂單金額計算食物數量
    public static Integer calculateFoodQuantity(double staffIncome, String food) {
        int quantity = 0;
        if ("狗骨頭".equals(food)) {
            // 假設每 10 單位收入能換得 1 份狗骨頭
            quantity = (int) (staffIncome / 10);
        } else if ("貓尾草".equals(food)) {
            // 假設每 10 單位收入能換得 2 份貓尾草
            quantity = (int) (staffIncome / 5);  // 每 5 單位收入能換 2 份貓尾草
        }
        return quantity;
    }

    // 根據收入換算食物
    public static String getStaffFoodForIncome(double staffIncome) {
        if (staffIncome >= 10) {
            return "狗骨頭";  // 假設員工收入大於 10 就換得狗骨頭
        } else {
            return "貓尾草";  // 否則換得貓尾草
        }
    }

    // 根據食物名稱獲取價格
    public static double getPriceForFood(String foodName) {
        switch (foodName) {
            case "貓薄荷":
                return 5.0;  // 假設價格是 5
            case "麵包蟲":
                return 2.0;// 假設價格是 2
            case  "大米":
            	return 1.0;
            default:
                return 0.0;  // 其他食物的價格設為 0
        }
    }

    // 假設這個方法用來根據訂單獲取員工名稱（這部分可以根據實際情況更改）
    public static String getStaffNameForOrder(CustOrder order) {
        // 假設每個訂單都有一個員工名稱字段
        return "員工" + order.getCustOrderStaff();  // 這裡用訂單ID模擬員工名稱
    }



//     計算每個訂單的金額（食物的價格 * 訂單數量）
    public static double calculateOrderAmount(CustOrder custOrder) {
        double price =10;
        return price;
    }
}
