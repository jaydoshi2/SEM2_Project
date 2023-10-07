package Group_Project3;

import javax.swing.*;
import java.awt.*;

 class ImageInFrameExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image in Frame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);

        // Create a JPanel to hold the image
        JPanel photo = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Java language\\java programs\\src\\JDBC\\src\\group_icon (1).jpg"); // Replace with your image file path
        JLabel imageLabel = new JLabel(imageIcon);
    
        imageLabel.setBounds(100, 100, 111, 160); // X, Y, Width, Height
        photo.add(imageLabel);
        frame.add(photo);

        frame.setVisible(true);
    }
}
