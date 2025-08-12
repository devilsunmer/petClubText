package controller.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.ShopUI;
import model.CustMember;
import service.impl.CustMemberServiceImpl;
import util.ImageInPanel;
import util.StringTool;

public class AddMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField memberName;
	private JTextField memberUsername;
	private JTextField memberPassword;
	private JTextField memberPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddMemberUI frame = new AddMemberUI();
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
	public AddMemberUI() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("接下來，請填入基本會員資料。");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 10, 213, 43);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("會員姓名：");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 63, 70, 43);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("會員帳號：");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(23, 106, 70, 43);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("會員密碼：");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(23, 150, 70, 43);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("會員電話：");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_3.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(23, 194, 70, 43);
		contentPane.add(lblNewLabel_1_3);

		memberName = new JTextField();
		memberName.setBounds(96, 73, 107, 29);
		contentPane.add(memberName);
		memberName.setColumns(10);

		memberUsername = new JTextField();
		memberUsername.setColumns(10);
		memberUsername.setBounds(96, 116, 107, 29);
		contentPane.add(memberUsername);

		memberPassword = new JTextField();
		memberPassword.setColumns(10);
		memberPassword.setBounds(96, 159, 107, 29);
		contentPane.add(memberPassword);

		memberPhone = new JTextField();
		memberPhone.setColumns(10);
		memberPhone.setBounds(96, 208, 107, 29);
		contentPane.add(memberPhone);

		JLabel message = new JLabel("");
		message.setForeground(new Color(64, 0, 64));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		message.setBounds(207, 93, 135, 43);
		contentPane.add(message);

		/************************* Button\QWWQ/ *******************************/

		JButton creatMember = new JButton("創立會員");
		creatMember.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				String custMemberName = (memberName.getText() != null && !memberName.getText().trim().isEmpty())
						? memberName.getText()
						: null;
				String custMemberUsername = (memberUsername.getText() != null
						&& !memberUsername.getText().trim().isEmpty()) ? memberUsername.getText() : null;
				String custMemberPassword = (memberPassword.getText() != null
						&& !memberPassword.getText().trim().isEmpty()) ? memberPassword.getText() : null;
				String custMemberPhone = (memberPhone.getText() != null && !memberPhone.getText().trim().isEmpty())
						? memberPhone.getText()
						: null;
				CustMemberServiceImpl memberService = new CustMemberServiceImpl();
				if (custMemberName == null) {
					message.setText("請輸入姓名");
				}

				if (custMemberUsername == null) {
					message.setText("請輸入帳號");
				} else if (custMemberUsername == memberService.viewMemberName(custMemberUsername).getCustUsername()) {
					message.setText("帳號已被使用");
				} else if (!StringTool.isUserPass(custMemberUsername)) {
					message.setText("請確認帳號是否有英文、數字");
				} else {
					message.setText("帳號可以使用");
				}

				if (custMemberPassword == null) {
					message.setText("請輸入密碼");
				} else if (!StringTool.isUserPass(custMemberPassword)) {
					message.setText("請確認帳號是否有英文、數字");
				} else {
					message.setText("密碼可以使用");
				}

				if (custMemberPhone == null) {
					message.setText("請輸入電話");
				}else if (!StringTool.isPhone(custMemberPhone)) {
					message.setText("請確認電話輸入正確");
				}

				CustMember custMember = new CustMember(custMemberName, custMemberUsername, custMemberPassword,
						custMemberPhone);
				if (custMember != null) {
					memberService.addCustMember(custMember);
					JOptionPane.showMessageDialog(null, "新增會員成功", "新增會員提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "創立會員失敗", "新增會員提示", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		creatMember.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		creatMember.setBounds(223, 150, 100, 23);
		contentPane.add(creatMember);

		JButton clear = new JButton("重新填寫");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				memberName.setText(null);
				memberUsername.setText(null);
				memberPassword.setText(null);
				memberPhone.setText(null);
			}
		});
		clear.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		clear.setBounds(223, 183, 100, 23);
		contentPane.add(clear);

		JButton leave = new JButton("回上一頁");
		leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShopUI frame = new ShopUI();
				frame.setVisible(true);
				dispose();
			}
		});
		leave.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		leave.setBounds(223, 214, 100, 23);
		contentPane.add(leave);

		ImageInPanel imagePhoto = new ImageInPanel("/images/background2.jpg");
		imagePhoto.setBounds(0, 0, 366, 394);
		contentPane.add(imagePhoto);
		imagePhoto.setLayout(new BorderLayout(0, 0));
	}

}
