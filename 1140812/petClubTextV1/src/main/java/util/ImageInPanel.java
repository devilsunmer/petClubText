package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageInPanel extends JPanel {

    private Image image;

    public ImageInPanel(String imagePath) {
        // 加載圖片
        image = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 繪製圖片到面板，這裡會將圖片繪製到 (0, 0) 位置
        g.drawImage(image, 0, 0, this);  // 繪製圖片，this 是 ImageObserver
    }
}