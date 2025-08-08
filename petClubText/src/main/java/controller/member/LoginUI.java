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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.ShopUI;
import controller.boss.BossLoginUI;
import model.Boss;
import model.CustMember;
import service.impl.BossServiceImpl;
import service.impl.CustMemberServiceImpl;
import util.ImageInPanel;
import util.StringTool;
import util.Tool;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameFiled;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("帳號：");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 113, 42, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密碼：");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 145, 42, 30);
		contentPane.add(lblNewLabel_1);

		usernameFiled = new JTextField();
		usernameFiled.setBounds(89, 121, 96, 21);
		contentPane.add(usernameFiled);
		usernameFiled.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(89, 153, 96, 22);
		contentPane.add(passwordField);

		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		message.setBounds(37, 10, 172, 30);
		contentPane.add(message);
		
		/************************* Button\QWWQ/ *******************************/


		JButton loginButton = new JButton("登入");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Username=usernameFiled.getText();
				String Password=new String(passwordField.getPassword());
				CustMember memberLogin=new CustMemberServiceImpl().login(Username, Password);
				if(memberLogin!=null)
				{
					Tool.saveFiled(memberLogin,"member.txt" );
					StringTool.saveMember(memberLogin.getCustMemberName(),memberLogin.getCustMemberPhone());
					ShopUI frame = new ShopUI();
					frame.setVisible(true);
					dispose();
				}else {
					message.setText("請確認是否為會員");
					message.setForeground(new Color(255,0,0));
				}
			}
		});
		loginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		loginButton.setBounds(24, 195, 87, 23);
		contentPane.add(loginButton);

		JButton newMemberButton = new JButton("創建");
		newMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMemberUI addMemberUI=new AddMemberUI();
				addMemberUI.setVisible(true);
				dispose();
			}
		});
		newMemberButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		newMemberButton.setBounds(140, 195, 87, 23);
		contentPane.add(newMemberButton);

		JButton bossButton = new JButton("後台登入");
		bossButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Username=usernameFiled.getText();
				String Password=new String(passwordField.getPassword());
				Boss boss=new Boss();
				if(new BossServiceImpl().login(Username, Password)!=null)
				{
					Tool.saveFiled(boss, "boss.txt");
					BossLoginUI frame = new BossLoginUI();
					frame.setVisible(true);
					dispose();
				}else {
					message.setText("請確認是否為工作人員");
					message.setForeground(new Color(255,0,0));

				}
			}
		});
		bossButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		bossButton.setBounds(10, 228, 116, 23);
		contentPane.add(bossButton);

		ImageInPanel imagePhoto = new ImageInPanel("/images/login.jpg");
		imagePhoto.setBounds(0, 0, 249, 261);
		contentPane.add(imagePhoto);
		imagePhoto.setLayout(new BorderLayout(0, 0));

	}
}
