package controller.boss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Boss;
import model.StaffIncome;
import model.StaffMember;
import service.impl.StaffIncomeServiceImpl;
import service.impl.StaffMemberServiceImpl;
import util.ImageInPanel;
import util.Tool;

import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class StaffManagerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newStaffName;
	private JTextField deleteStaffId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManagerUI frame = new StaffManagerUI();
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
	public StaffManagerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Boss boss=(Boss)Tool.readFiled("boss.txt");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(13, 52, 455, 285);
		contentPane.add(tabbedPane);

		JPanel staffManage = new JPanel();
		staffManage.setBackground(new Color(255, 255, 255));
		staffManage.setLayout(null);

		JPanel staffFood = new JPanel();
		staffFood.setBackground(new Color(255, 255, 255));
		staffFood.setBounds(0, 0, 450, 256);
		tabbedPane.add(staffFood);
		staffFood.setLayout(null);

		tabbedPane.addTab("寵物員工查詢", staffManage);
		tabbedPane.addTab("寵物員工食物管理", staffFood);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 19, 315, 228);
		staffManage.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel bossMessage_1 = new JLabel("新增員工：");
		bossMessage_1.setBounds(36, 20, 70, 20);
		bossMessage_1.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManage.add(bossMessage_1);

		newStaffName = new JTextField();
		newStaffName.setBounds(26, 49, 96, 21);
		staffManage.add(newStaffName);
		newStaffName.setColumns(10);

		JLabel bossMessage_1_1 = new JLabel("刪除員工ID：");
		bossMessage_1_1.setBounds(26, 127, 85, 20);
		bossMessage_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManage.add(bossMessage_1_1);

		deleteStaffId = new JTextField();
		deleteStaffId.setBounds(19, 157, 96, 38);
		deleteStaffId.setColumns(10);
		staffManage.add(deleteStaffId);

		JLabel bossMessage = new JLabel(boss.getBossName() + "" + "老闆歡迎回來");
		bossMessage.setForeground(new Color(0, 255, 255));
		bossMessage.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		bossMessage.setBounds(13, 10, 199, 38);
		contentPane.add(bossMessage);

		JScrollPane viewStaffIcome = new JScrollPane();
		viewStaffIcome.setBounds(10, 10, 430, 97);
		staffFood.add(viewStaffIcome);

		JTextArea viewStaffIcomeArea = new JTextArea();
		viewStaffIcome.setViewportView(viewStaffIcomeArea);
		viewStaffIcomeArea.append(new StaffIncomeServiceImpl().viewStaffIncome());
		viewStaffIcomeArea.revalidate();
		viewStaffIcomeArea.repaint();

		JComboBox<String> staffNameCom = new JComboBox<>();
		String[] staff = Tool.orderStaff();
		for (String staffName : staff) {
			staffNameCom.addItem(staffName);
		}
		staffNameCom.setBounds(20, 150, 87, 37);
		staffFood.add(staffNameCom);

		JLabel bossMessage_2 = new JLabel("寵物員工獎勵零食紀錄：");
		bossMessage_2.setHorizontalAlignment(SwingConstants.CENTER);
		bossMessage_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		bossMessage_2.setBounds(10, 115, 162, 37);
		staffFood.add(bossMessage_2);

		JSpinner foodMath = new JSpinner();
		foodMath.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		foodMath.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		foodMath.setBounds(240, 150, 41, 37);
		staffFood.add(foodMath);

		JComboBox food = new JComboBox();
		food.setModel(new DefaultComboBoxModel(new String[] { "", "貓薄荷", "麵包蟲", "大米" }));
		food.setBounds(324, 150, 87, 37);
		staffFood.add(food);

		String[] unitname = new String[] { "", "束", "把", "匙" };

		JLabel foodUnit = new JLabel();
		foodUnit.setHorizontalAlignment(SwingConstants.CENTER);
		foodUnit.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		foodUnit.setBounds(284, 150, 33, 37);
		staffFood.add(foodUnit);
		switch (food.getSelectedItem().toString()) {
		case "":
			foodUnit.setText(unitname[0]);
			break;
		case "貓薄荷":
			foodUnit.setText(unitname[1]); // "束"
			break;
		case "麵包蟲":
			foodUnit.setText(unitname[2]); // "把"
			break;
		case "大米":
			foodUnit.setText(unitname[3]); // "匙"
			break;
		default:
			foodUnit.setText(""); // 如果没有选中食物，清空单位
			break;
		}

		JLabel foodUnit_1 = new JLabel("小寶貝可以獎勵：");
		foodUnit_1.setHorizontalAlignment(SwingConstants.CENTER);
		foodUnit_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		foodUnit_1.setBounds(117, 150, 131, 37);
		staffFood.add(foodUnit_1);

		/************************* Button\QWWQ/ *******************************/

		StaffMember staffMember = new StaffMember();
		StaffMemberServiceImpl staffMemberServiceImpl = new StaffMemberServiceImpl();

		JButton memberManager = new JButton("新增員工");
		memberManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String StaffName = newStaffName.getText();
				StaffMember staffMember = new StaffMember(StaffName);
				staffMemberServiceImpl.addStaffMember(staffMember);
			}
		});
		memberManager.setBounds(26, 80, 89, 29);
		memberManager.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManage.add(memberManager);

		JButton staffManager = new JButton("刪除員工");
		staffManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer ID = Integer.parseInt(deleteStaffId.getText());
				StaffMember staffMember = new StaffMember();
				staffMember.setIdStaff(ID);
				staffMemberServiceImpl.deleteStaffMember(staffMember);
				JOptionPane.showMessageDialog(null, "確認刪除", "寵物員工管理", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		staffManager.setBounds(26, 205, 89, 29);
		staffManager.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManage.add(staffManager);

		JButton enterIncome = new JButton("確認獎勵");
		enterIncome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String staffName = staffNameCom.getSelectedItem().toString();
				String foodCount = foodMath.getValue().toString();
				String unit = foodUnit.getText();
				String Food = food.getSelectedItem().toString();
				String staffFood = foodCount + unit + Food;
				StaffIncome staffIncomeFood = new StaffIncome(staffName, staffFood);
				new StaffIncomeServiceImpl().addStaffFood(staffIncomeFood);
				JOptionPane.showMessageDialog(null, "已添加零食", "寵物員工管理", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		enterIncome.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterIncome.setBounds(243, 197, 98, 38);
		staffFood.add(enterIncome);

		JButton clear = new JButton("清除");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				foodUnit.setText("");
				staffNameCom.setSelectedIndex(1);
				food.setSelectedIndex(1);
				foodMath.setValue(0);
			}
		});
		clear.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		clear.setBounds(106, 197, 98, 38);
		staffFood.add(clear);

		JButton backButton = new JButton("上一頁");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BossLoginUI frame = new BossLoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		backButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		backButton.setBounds(198, 10, 98, 38);
		contentPane.add(backButton);

		JButton leaveButton = new JButton("離開");
		leaveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		leaveButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		leaveButton.setBounds(335, 10, 98, 38);
		contentPane.add(leaveButton);
		
		ImageInPanel imagePhoto = new ImageInPanel("/images/background2.jpg");
		imagePhoto.setBounds(0, 0, 468, 345);
		contentPane.add(imagePhoto);
		imagePhoto.setLayout(new BorderLayout(0, 0));

	}
}
