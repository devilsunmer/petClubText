package util;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.StaffMember;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import service.impl.StaffMemberServiceImpl;

public class Tool {

	/** 存取物件檔案 */
	public static void saveFiled(Object object, String fileName) {
		try {
			File folder = new File("repoter");
	        if (!folder.exists()) {
	            folder.mkdirs(); // 建立多層資料夾
	        }
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(object);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 讀取物件檔案 */
	public static Object readFiled(String fileName) {
		Object object = null;
		if(new File("repoter",fileName)!=null) {
			try {
				FileInputStream fileInputStream = new FileInputStream(new File("repoter",fileName));
				try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
					object = objectInputStream.readObject();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return object;
	}

	
	/** 寵物放置照片設定 */
	public static ImageIcon staffPhoto(String photoResourse, Class<?> className) {
		// 放在JLabel裡面
		java.net.URL imageUrl = className.getResource(photoResourse);
		if (imageUrl == null) {
			System.err.println("圖片路徑錯誤，找不到圖片：" + photoResourse);
			java.net.URL unknowUrl = className.getResource("/images/unKnow.jpg");
			if (unknowUrl != null) {
				ImageIcon defaultIcon = new ImageIcon(unknowUrl);
				Image unknowImage = defaultIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
				return new ImageIcon(unknowImage);
			} else {
				System.err.println("也找不到預設圖片！");
				return null;
			}
		}
		ImageIcon staffPhoto = new ImageIcon(imageUrl);
	    Image imageSet = staffPhoto.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	    return new ImageIcon(imageSet);
	}
 
	/** 顯示可以選擇陪伴的寵物 */
	public static String[] orderStaff() {
		List<StaffMember> cute=new StaffMemberServiceImpl().viewStaff();
		String lala =cute.toString();
		lala = lala.replaceAll("^[\\[\\n]+", "");
		String[] lada = lala.split("員工姓名:");
		List<String> staffNames = new ArrayList<>(); // 用來儲存所有的員工姓名
		for (String element : lada) {
			String staffList = element.replaceAll("ID:\\d+\\s*", "").replaceAll(",", "").replace("]", "").trim();
			staffNames.add(staffList);
		}
		return staffNames.toArray(new String[0]);
	}

	/** 小時鐘 */
	public static void updateDateTime(JLabel label) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentDateTime = sdf.format(new Date());
		label.setText(currentDateTime);
	}

	/** 日期選擇 */
	public static void DateChoose(JPanel dateChooseArea) {
		UtilDateModel orderDateModel = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "今天");
		properties.put("text.month", "月");
		properties.put("text.year", "年");
		JDatePanelImpl orderDatePanel = new JDatePanelImpl(orderDateModel); // 只傳入 UtilDateModel
		JDatePickerImpl orderDatePicker = new JDatePickerImpl(orderDatePanel, null);
		orderDatePicker.getJFormattedTextField().setFont(new Font("微軟正黑體", Font.BOLD, 18));
		dateChooseArea.add(orderDatePicker);
	}

}
