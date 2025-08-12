package controller.boss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.member.LoginUI;
import model.Boss;
import util.ImageInPanel;
import util.StringTool;
import util.Tool;
import util.forPrint.PrintTool;

public class BossLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField excelFieldName;
	private JTextField excelSheetName;
	final JList<String> porterView = new JList<>();
	/**
	 * @wbp.nonvisual location=678,189
	 */
	private final JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BossLoginUI frame = new BossLoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BossLoginUI() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Boss boss = (Boss) Tool.readFiled("boss.txt");
//		String bossName = "text";
		JLabel bossMessage = new JLabel(/*bossName*/ boss.getBossName() + "" + "老闆歡迎回來" );
		bossMessage.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		bossMessage.setBounds(287, 32, 137, 38);
		contentPane.add(bossMessage);

		JLabel bossMessage_1 = new JLabel("可查看");
		bossMessage_1.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		bossMessage_1.setBounds(21, 80, 96, 38);
		contentPane.add(bossMessage_1);

		JLabel memberCount = new JLabel("");
		memberCount.setForeground(new Color(0, 128, 64));
		memberCount.setHorizontalAlignment(SwingConstants.CENTER);
		memberCount.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberCount.setBounds(195, 111, 152, 38);
		contentPane.add(memberCount);

		JLabel freeMemberCount = new JLabel("");
		freeMemberCount.setForeground(new Color(255, 128, 0));
		freeMemberCount.setHorizontalAlignment(SwingConstants.CENTER);
		freeMemberCount.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		freeMemberCount.setBounds(195, 182, 152, 38);
		contentPane.add(freeMemberCount);

		JLabel staffCount = new JLabel("");
		staffCount.setForeground(new Color(0, 128, 64));
		staffCount.setHorizontalAlignment(SwingConstants.CENTER);
		staffCount.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffCount.setBounds(195, 243, 152, 38);
		contentPane.add(staffCount);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 319, 237, 66);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);

		scrollPane.setViewportView(porterView);
		porterView.setVisible(false);

		JButton enterMoction = new JButton("");
		enterMoction.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterMoction.setBounds(301, 328, 123, 38);
		contentPane.add(enterMoction);
		enterMoction.setVisible(false);

		JButton excelButton = new JButton("報表匯出");
		excelButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		excelButton.setBounds(10, 221, 123, 38);
		contentPane.add(excelButton);

		JButton printButton = new JButton("報表列印");
		printButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		printButton.setBounds(10, 271, 123, 38);
		contentPane.add(printButton);

		JLabel excelPut = new JLabel("報表檔案名稱:");
		excelPut.setHorizontalAlignment(SwingConstants.CENTER);
		excelPut.setForeground(new Color(0, 128, 64));
		excelPut.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		excelPut.setBounds(176, 195, 101, 38);
		contentPane.add(excelPut);
		excelPut.setVisible(false);

		JLabel excelPut2 = new JLabel("報表名稱：");
		excelPut2.setHorizontalAlignment(SwingConstants.CENTER);
		excelPut2.setForeground(new Color(0, 128, 64));
		excelPut2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		excelPut2.setBounds(187, 221, 101, 38);
		contentPane.add(excelPut2);
		excelPut2.setVisible(false);

		excelFieldName = new JTextField();
		excelFieldName.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		excelFieldName.setBounds(282, 207, 96, 21);
		contentPane.add(excelFieldName);
		excelFieldName.setColumns(10);
		excelFieldName.setVisible(false);

		excelSheetName = new JTextField();
		excelSheetName.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		excelSheetName.setBounds(282, 233, 96, 21);
		contentPane.add(excelSheetName);
		excelSheetName.setColumns(10);
		excelSheetName.setVisible(false);

		/************************* Button\QWWQ/ *******************************/

		JButton memberManager = new JButton("會員管理");
		memberManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReadMemberUI frame = new ReadMemberUI();
				frame.setVisible(true);
				dispose();
			}
		});
		memberManager.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberManager.setBounds(10, 124, 123, 38);
		contentPane.add(memberManager);

		JButton staffManager = new JButton("寵物員工管理");
		staffManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StaffManagerUI frame = new StaffManagerUI();
				frame.setVisible(true);
				dispose();
			}
		});
		staffManager.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManager.setBounds(10, 172, 123, 38);
		contentPane.add(staffManager);

		excelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				porterView.setListData(StringTool.excelList());
				porterView.setVisible(true);
				scrollPane.setVisible(true);
				enterMoction.setVisible(true);
				excelPut.setVisible(true);
				excelPut2.setVisible(true);
				excelFieldName.setVisible(true);
				excelSheetName.setVisible(true);
				enterMoction.setText("確認匯出");
			}
		});
//		System.out.println(Arrays.toString(StringTool.excelList()));

		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] orderPrintList = PrintTool.getAvailableReports();
				porterView.setListData(orderPrintList);
				porterView.setVisible(true);
				scrollPane.setVisible(true);
				enterMoction.setVisible(true);
				enterMoction.setText("確認列印");

			}
		});

		enterMoction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = enterMoction.getText();
				String ExcelFieldName = excelFieldName.getText();
				String ExcelSheetName = excelSheetName.getText();
				String selectedReport = porterView.getSelectedValue(); // 獲取用戶選擇的報表
				String[] orderPrintList = PrintTool.getAvailableReports();
				if (text.equals("確認匯出")) {
					String excelname = ExcelFieldName + ".xls";
					boolean checkExcelName = Arrays.stream(orderPrintList)
							.anyMatch(existing -> existing.equalsIgnoreCase(excelname));
					if (selectedReport == null || selectedReport.isEmpty()) {
						JOptionPane.showMessageDialog(null, "請選擇一個報表", "錯誤", JOptionPane.ERROR_MESSAGE);
						excelFieldName.setText("");
						excelSheetName.setText("");
						return;
					} else if (checkExcelName) {
						JOptionPane.showMessageDialog(null, "請重新輸出一個報表名稱", "錯誤", JOptionPane.ERROR_MESSAGE);
						excelFieldName.setText("");
						return;
					} else {
						StringTool.useTitleNameForReport(selectedReport, ExcelFieldName, ExcelSheetName);
					}
					excelFieldName.setText("");
					excelSheetName.setText("");
					excelFieldName.setVisible(false);
					excelSheetName.setVisible(false);
					excelPut.setVisible(false);
					excelPut2.setVisible(false);
				} else if (text.equals("確認列印")) {
					if (selectedReport == null || selectedReport.isEmpty()) {
						JOptionPane.showMessageDialog(null, "請先從清單選擇報表檔案", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						File dir = new File("reporter"); // 建議用正確資料夾
						if (!dir.exists()) {
							dir.mkdirs();
						}

						String fileName = "reporter/" + selectedReport;
						File file = new File(fileName);
						if (!file.exists()) {
							JOptionPane.showMessageDialog(null, "檔案不存在：" + file.getAbsolutePath());
							return;
						}
						// 先預覽報表，使用PrintTool新增的previewReport方法
						boolean confirmed = PrintTool.previewReport(file.getAbsolutePath());
						if (!confirmed) {
							JOptionPane.showMessageDialog(null, "已取消列印");
							return;
						}
						// 預覽後確認，開始列印
						if (selectedReport.endsWith(".xls")) {
							PrintTool.printExcelAsTable(file.getAbsolutePath());
						} else if (selectedReport.endsWith(".txt")) {
							String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
							PrintTool.printText(content);
						} else {
							JOptionPane.showMessageDialog(null, "不支援的檔案格式：" + selectedReport);
						}
						JOptionPane.showMessageDialog(null, "開始列印報表", "指令輸出", JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "列印時發生錯誤：" + ex.getMessage());
					}
				}
			}
		});

		JButton loginOutButton = new JButton("登出");
		loginOutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI frame = new LoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		loginOutButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		loginOutButton.setBounds(10, 22, 123, 38);
		contentPane.add(loginOutButton);

		JButton leave = new JButton("離開");
		leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		leave.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		leave.setBounds(154, 22, 123, 38);
		contentPane.add(leave);

		ImageInPanel imagePhoto = new ImageInPanel("/images/money.jpg");
		imagePhoto.setBounds(0, 0, 434, 407);
		contentPane.add(imagePhoto);
		imagePhoto.setLayout(new BorderLayout(0, 0));
	}
}
