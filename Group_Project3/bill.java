package Group_Project3;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BillPaymentPanel extends JFrame {
    private JLabel totalBillLabel;
    public static String sts = null;

    public BillPaymentPanel(int total_amt) {
        setTitle("Bill Payment");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        totalBillLabel = new JLabel("Total Bill Amount:" + total_amt); // Replace with your actual bill amount
        panel.add(totalBillLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton upiButton = new JButton("UPI");
        upiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add UPI payment logic here
                JOptionPane.showMessageDialog(null, "UPI Payment selected");
            }
        });

        JButton cashOnDeliveryButton = new JButton("Cash on Delivery");
        cashOnDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add Cash on Delivery payment logic here
                JOptionPane.showMessageDialog(null, "Cash on Delivery selected");
            }
        });

        JButton cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add cancel payment logic here
                JOptionPane.showMessageDialog(null, "Payment Cancelled");
            }
        });

        JButton check_your_luck = new JButton("Take a lucky spin");
        check_your_luck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = ImageIO
                            .read(new File("C:\\Java language\\java programs\\src\\JDBC\\src\\Party_Face_Emoji.png"));

                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());

                    JLabel imageLabel = new JLabel(new ImageIcon(image));
                    panel.add(imageLabel, BorderLayout.CENTER);

                    FileReader fr1 = new FileReader(
                            "C:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
                    BufferedReader bf1 = new BufferedReader(fr1);
                    String line = bf1.readLine();
                    int count = 0;
                    while (line != null) {
                        count++;
                        line = bf1.readLine();
                    }
                    int min = 1;
                    int num = 0;
                    int max = count;
                    int range = max - min + 1;
                    for (int i = 1; i <= max; i++) {
                        num = (int) (Math.random() * range) + min;

                    }
                    FileReader fr2 = new FileReader(
                            "C:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
                    BufferedReader bf2 = new BufferedReader(fr2);
                    String line2;
                    int temp = 0;

                    while (temp != num) {
                        line2 = bf2.readLine();
                        temp++;
                        sts = line2;
                    }
                    if (sts.equals("")) {

                    } else {
                        JLabel textLabel = new JLabel("You have won "+ sts);
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);
                        JOptionPane.showMessageDialog(null, panel, "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonPanel.add(upiButton);
        buttonPanel.add(cashOnDeliveryButton);
        buttonPanel.add(cancelPaymentButton);
        buttonPanel.add(check_your_luck);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void billing(int total_amount) {
        SwingUtilities.invokeLater(() -> {
            new BillPaymentPanel(total_amount);
        });
    }
}
