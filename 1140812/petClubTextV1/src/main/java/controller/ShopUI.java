package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.member.AddMemberUI;
import controller.member.LoginUI;
import model.CustFreeMember;
import model.CustMember;
import model.CustOrder;
import service.impl.CustFreeMemberServiceImpl;
import service.impl.CustOrderServiceImpl;
import util.ImageInPanel;
import util.StringTool;
import util.Tool;

public class ShopUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField freeMemberNameField;
	private JTextField freeMemberPhoneField;
	private Double hoursChoose = null;
	CustMember custMember=StringTool.getMember();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ShopUI frame = new ShopUI();
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
	public ShopUI() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel staffName_1 = new JLabel("預約時間：");
		staffName_1.setBounds(230, 198, 77, 38);
		staffName_1.setForeground(new Color(0, 64, 0));
		staffName_1.setHorizontalAlignment(SwingConstants.CENTER);
		staffName_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		contentPane.add(staffName_1);

		JLabel staffName = new JLabel("寵物照片");
		staffName.setBounds(69, 200, 77, 38);
		staffName.setForeground(new Color(0, 64, 0));
		staffName.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(staffName);

		JComboBox time = new JComboBox();
		time.setBounds(307, 209, 82, 23);
		time.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = (String) time.getSelectedItem();
					if (selectedItem != null && !selectedItem.isEmpty()) {
						Double hours = StringTool.convertToHours(selectedItem);
						hoursChoose = hours;
						StringTool.hoursSave(hoursChoose);
						JOptionPane.showMessageDialog(null, "選擇的時間是: " + selectedItem);
					}
				}
			}
		});
		time.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		time.setModel(new DefaultComboBoxModel(new String[] { "", "半小時", "一小時", "一小時半", "兩小時" }));
		contentPane.add(time);

		JLabel staffName_2 = new JLabel("預約日期：");
		staffName_2.setBounds(230, 144, 77, 38);
		staffName_2.setForeground(new Color(0, 64, 0));
		staffName_2.setHorizontalAlignment(SwingConstants.CENTER);
		staffName_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		contentPane.add(staffName_2);

		JLabel petPhoto = new JLabel("");
		petPhoto.setBounds(10, 40, 188, 171);
		contentPane.add(petPhoto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 240, 188, 107);
		contentPane.add(scrollPane);

		JList<String> list = new JList<>(Tool.orderStaff());
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // 防止多次觸發
					String selectedName = list.getSelectedValue();
					if(selectedName!=null)
					{
						String staffPhoto=StringTool.staffNameForPhoto(selectedName, this.getClass());
						petPhoto.setIcon(Tool.staffPhoto(staffPhoto, this.getClass()));
						String showStaff="這是："+selectedName+"！";
						staffName.setText(showStaff);
						JOptionPane.showMessageDialog(null, "選擇陪伴的寵物是: " + selectedName);
					}
				}
			}
		});
		list.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JLabel dayMessage = new JLabel("");
		dayMessage.setBounds(229, 330, 232, 36);
		dayMessage.setForeground(new Color(0, 0, 128));
		dayMessage.setHorizontalAlignment(SwingConstants.CENTER);
		dayMessage.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		contentPane.add(dayMessage);
		Tool.updateDateTime(dayMessage);

		JLabel staffName_2_1 = new JLabel("預約電話：");
		staffName_2_1.setBounds(398, 102, 77, 38);
		contentPane.add(staffName_2_1);
		staffName_2_1.setForeground(new Color(0, 64, 0));
		staffName_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		staffName_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		freeMemberPhoneField = new JTextField();
		freeMemberPhoneField.setBounds(475, 114, 96, 21);
		contentPane.add(freeMemberPhoneField);
		freeMemberPhoneField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		freeMemberPhoneField.setColumns(10);

		JLabel staffName_2_2 = new JLabel("預約姓名：");
		staffName_2_2.setBounds(217, 102, 77, 38);
		contentPane.add(staffName_2_2);
		staffName_2_2.setForeground(new Color(0, 64, 0));
		staffName_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		staffName_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		freeMemberNameField = new JTextField();
		freeMemberNameField.setBounds(294, 110, 96, 21);
		contentPane.add(freeMemberNameField);
		freeMemberNameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		freeMemberNameField.setColumns(10);

		JPanel dateChooseArea = new JPanel();
		dateChooseArea.setBounds(255, 151, 300, 100);
		dateChooseArea.setLayout(new FlowLayout());
		contentPane.add(dateChooseArea);
		Tool.DateChoose(dateChooseArea);

		JLabel memberMessage = new JLabel("");
		memberMessage.setHorizontalAlignment(SwingConstants.CENTER);
		memberMessage.setSize(197, 38);
		memberMessage.setLocation(278, 93);
		memberMessage.setBackground(new Color(128, 0, 0));
		memberMessage.setForeground(new Color(255, 128, 192));
		memberMessage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		contentPane.add(memberMessage, BorderLayout.NORTH);

		JLabel staffName_3 = new JLabel("可愛寵物會館");
		staffName_3.setBackground(new Color(64, 0, 0));
		staffName_3.setHorizontalAlignment(SwingConstants.CENTER);
		staffName_3.setForeground(new Color(255, 128, 192));
		staffName_3.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 25));
		staffName_3.setBounds(30, 10, 181, 38);
		contentPane.add(staffName_3);
		/************************* Button\QWWQ/ *******************************/

		JButton loginOutButton = new JButton("登出");
		loginOutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(custMember!=null)
				{
					 File file = new File("member.txt");
			            if (file.exists()) {
			                boolean deleted = file.delete();
			                if (deleted) {
								JOptionPane.showMessageDialog(null, "已確認登出會員" );
			                } else {
								JOptionPane.showMessageDialog(null, "登出會員失敗" );
			                }
			            }
				}
			}
		});
		loginOutButton.setBounds(459, 10, 118, 31);
		loginOutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		contentPane.add(loginOutButton);

		JButton clear = new JButton("清除");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				freeMemberPhoneField.setText(null);
				freeMemberNameField.setText(null);
				time.setSelectedIndex(1);
				
			}
		});
		clear.setBounds(397, 265, 118, 31);
		contentPane.add(clear);
		clear.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JButton leave = new JButton("離開");
		leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});
		leave.setBounds(397, 310, 118, 31);
		contentPane.add(leave);
		leave.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JButton freeMemberOrderButton = new JButton("下訂單");
		freeMemberOrderButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				String freeMemberName=freeMemberNameField.getText();
				String freeMemberPhone=freeMemberPhoneField.getText();
				String orderDate=dayMessage.getText();
				Double orderTimeDouble=StringTool.convertToHours((String)time.getSelectedItem());
				String orderStaff=list.getSelectedValue().toString();
				CustFreeMember custFreeMember=new CustFreeMember(freeMemberName,freeMemberPhone);
				if(custMember!=null)
				{
					String memberName=custMember.getCustMemberName();
					CustOrder custOrder=new CustOrder(memberName,orderDate,orderTimeDouble,orderStaff);
					new CustOrderServiceImpl().addCustOrder(custOrder);
					Tool.saveFiled(custOrder, "custMember.txt");
					JOptionPane.showMessageDialog(null, "感謝"+custMember.getCustMemberName()+"預約寶貝們陪伴！" );
				}else if(custFreeMember!=null&&custMember==null)
				{
					CustOrder custOrder=new CustOrder(freeMemberName,orderDate,orderTimeDouble,orderStaff);
					new CustFreeMemberServiceImpl().addCustFreeMember(custFreeMember);
					new CustOrderServiceImpl().addCustOrder(custOrder);
					Tool.saveFiled(custOrder,"custFreeOrder.txt");
					freeMemberPhoneField.setText(null);
					freeMemberNameField.setText(null);
					time.setSelectedIndex(1);
					JOptionPane.showMessageDialog(null, "謝謝"+custMember.getCustMemberName()+"預約寶貝陪伴！" );
				}else {
					JOptionPane.showMessageDialog(null, "請重新輸入訂單" );
				}
			}
		});
		freeMemberOrderButton.setBounds(263, 265, 118, 31);
		contentPane.add(freeMemberOrderButton);
		freeMemberOrderButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JButton createMember = new JButton("變成會員");
		createMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMemberUI frame = new AddMemberUI();
				frame.setVisible(true);
				dispose();
			}
		});
		createMember.setBounds(263, 310, 118, 31);
		contentPane.add(createMember);
		createMember.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JButton loginButton = new JButton("登入");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI frame = new LoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		loginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		loginButton.setBounds(307, 10, 118, 31);
		contentPane.add(loginButton);
		
		if(custMember!=null)
		{
			loginButton.setVisible(false);
            loginOutButton.setVisible(true);
            memberMessage.setVisible(true);
            freeMemberNameField.setVisible(false);
            freeMemberPhoneField.setVisible(false);
            staffName_2_2.setVisible(false);
            staffName_2_1.setVisible(false);
            memberMessage.setText("歡迎！會員：" + custMember.getCustMemberName());
		}else {
			loginButton.setVisible(true);
            loginOutButton.setVisible(false);
            memberMessage.setVisible(false);
            freeMemberNameField.setText("");
            freeMemberNameField.setVisible(true);
            freeMemberPhoneField.setText("");
            freeMemberPhoneField.setVisible(true);
            staffName_2_2.setVisible(true);
            staffName_2_1.setVisible(true);
		}




		ImageInPanel imagePhoto = new ImageInPanel("/images/background.png");
		imagePhoto.setBounds(0, 0, 601, 394);
		contentPane.add(imagePhoto);
		imagePhoto.setLayout(new BorderLayout(0, 0));


	}
}
