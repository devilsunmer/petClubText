package controller.boss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.member.LoginUI;
import model.Boss;
import util.ExcelCreat;
import util.ImageInPanel;
import util.StringTool;
import util.Tool;

public class BossLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField excelFieldName;
	private JTextField excelSheetName;
	final JList<String> porterView = new JList<>();

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

		JLabel bossMessage = new JLabel(boss.getBossName() + "" + "老闆歡迎回來");
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

	    contentPane.add(scrollPane);
	    porterView.setVisible(false);

		JButton enterMoction = new JButton("");
		enterMoction.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterMoction.setBounds(301, 328, 123, 38);
		contentPane.add(enterMoction);
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
				porterView.setVisible(true);
				porterView.setListData(StringTool.excelList());
				enterMoction.setVisible(true);
				excelPut.setVisible(true);
				excelPut2.setVisible(true);
				excelFieldName.setVisible(true);
				excelSheetName.setVisible(true);
				if(StringTool.isFileName(excelFieldName.getText())) JOptionPane.showMessageDialog(null,"報表名稱皆須英文","報表提示",JOptionPane.INFORMATION_MESSAGE);
				if(StringTool.isFileName(excelSheetName.getText())) JOptionPane.showMessageDialog(null,"報表名稱皆須英文","報表提示",JOptionPane.INFORMATION_MESSAGE);
				enterMoction.setText("確認匯出");
				ExcelCreat ecelCreat=new ExcelCreat();
				String[] titleNameList=StringTool.getTitleNamesForReport(porterView.getSelectedValue());
				ecelCreat.create(excelFieldName.getText(), excelSheetName.getText(), titleNameList);
			}
		});

		
		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				porterView.setVisible(true);
				String[] orderPrintList=Tool.getAvailableReports();	
				porterView.setListData(orderPrintList);
				enterMoction.setVisible(true);
				enterMoction.setText("確認列印");
				

			}
		});

		enterMoction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = enterMoction.getText();
				String ExcelFieldName=excelFieldName.getText();
				String ExcelSheetName=excelSheetName.getText();
				String selectedReport = null; // 獲取用戶選擇的報表
				if (text.equals("確認列印 - 匯出")) {
					String[] TitleName=StringTool.getTitleNamesForReport(porterView.getSelectedValue());
	                // 檢查用戶是否選擇了報表
	                if (selectedReport != null && !selectedReport.isEmpty()) {
	                    // 根據選擇的報表名稱，選擇對應的數據並匯出
	                    ExcelCreat excelCreate = new ExcelCreat();
	                    excelCreate.create(ExcelFieldName, ExcelSheetName, TitleName);
	                    StringTool.useTitleNameForReport(selectedReport, ExcelFieldName, ExcelSheetName);
	                    // 提示報表匯出完成
	                    JOptionPane.showMessageDialog(null, selectedReport + " 報表已匯出", "匯出完成", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    // 沒有選擇報表
	                    JOptionPane.showMessageDialog(null, "請選擇一個報表", "錯誤", JOptionPane.ERROR_MESSAGE);
	                }
				} else if (text.equals("確認列印 - 列印")) {
					selectedReport=porterView.getSelectedValue();
					String printPut=""+selectedReport;
					print(selectedReport);					
					// 進行列印處理
					JOptionPane.showMessageDialog(null, "開始列印報表", "指令輸出", JOptionPane.INFORMATION_MESSAGE);
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
