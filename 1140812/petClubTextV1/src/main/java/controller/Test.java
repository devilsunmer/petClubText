package controller;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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


	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showOptionDialog(Test.this, "請選擇報表檔案或執行列印", "檔案操作", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new String[] { "選擇檔案", "列印報表", "取消" }, "選擇檔案");
				if (option == 0) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("選擇報表檔案");
					fileChooser.setFileFilter(new FileNameExtensionFilter("*.xls", "xls"));

					int result = fileChooser.showOpenDialog(Test.this);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						JOptionPane.showMessageDialog(Test.this, "選擇的檔案：" + selectedFile.getAbsolutePath());
					}else {
						JOptionPane.showMessageDialog(Test.this, "未找到檔案，請確認是否有報表匯出");	
					}
				} else if (option == 1) {
					JOptionPane.showMessageDialog(Test.this, "報表列印中！");
					
				}else {
					JOptionPane.showMessageDialog(Test.this, "列印已取消");
				}
			}
		});
		contentPane.add(btnNewButton);

	}

}
