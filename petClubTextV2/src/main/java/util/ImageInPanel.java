package util;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageInPanel extends JPanel {

	private Image image;

	public ImageInPanel(String imagePath) {
		URL url = getClass().getResource(imagePath);
		if (url == null) {
			System.out.println("圖片載入失敗！");
			image = null; // 或做其他錯誤處理
		} else {
			System.out.println("圖片載入成功！路徑：" + url);
			image = new ImageIcon(url).getImage();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 繪製圖片到面板，這裡會將圖片繪製到 (0, 0) 位置
		g.drawImage(image, 0, 0, this); // 繪製圖片，this 是 ImageObserver
	}
}