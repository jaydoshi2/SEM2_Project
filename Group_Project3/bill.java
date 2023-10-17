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
    static int total_amt = 0;

    public BillPaymentPanel(int total_amt) {
        this.total_amt = total_amt;
        setTitle("Bill Payment");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton upiButton = new JButton("UPI");
        upiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon upi = new ImageIcon("D:\\Java language\\java programs\\src\\JDBC\\src\\Upi.jpg");
                int width = upi.getIconWidth();
                int height = upi.getIconHeight();
                JDialog upi_rep = new JDialog(Shopping_template1.frame, "Jay doshi upi id");
                upi_rep.setSize(width, height);
                JLabel jp = new JLabel(upi);
                upi_rep.add(jp, BorderLayout.CENTER);
                upi_rep.setVisible(true);
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
                    BufferedImage happy_image = ImageIO
                            .read(new File("D:\\Java language\\java programs\\src\\JDBC\\src\\Party_Face_Emoji.png"));
                    BufferedImage sad_image = ImageIO
                            .read(new File("D:\\Java language\\java programs\\src\\JDBC\\src\\sad_emoji.png"));

                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());

                    FileReader fr1 = new FileReader(
                            "D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
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
                            "D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
                    BufferedReader bf2 = new BufferedReader(fr2);
                    String line2;
                    int temp = 0;

                    while (temp != num) {
                        line2 = bf2.readLine();
                        temp++;
                        sts = line2;
                    }
                    bf1.close();
                    bf2.close();
                    if (sts.equals("")) {

                        JLabel imageLabel = new JLabel(new ImageIcon(sad_image));
                        panel.add(imageLabel, BorderLayout.CENTER);

                        JLabel textLabel = new JLabel("UNFORTUNATELY YOU HAVE WON NOTHIG ");
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);
                        JOptionPane.showMessageDialog(null, panel, "UNLUCKY!", JOptionPane.PLAIN_MESSAGE);
                    } else {

                        JLabel imageLabel = new JLabel(new ImageIcon(happy_image));
                        panel.add(imageLabel, BorderLayout.CENTER);
                        JLabel textLabel = new JLabel("You have won " + sts);
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);
                        JOptionPane.showMessageDialog(null, panel, "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        if (sts.equals("Brad's deal") || sts.equals("60% off official")
                                || sts.equals("Shopper Appraise")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt
                                    - (0.6 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Backpage Frebbies") || sts.equals("Mommy saves big")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt
                                    - (0.4 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Flat 90% Off") || sts.equals("Luxurious Frugal")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt
                                    - (0.9 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("INR 250 Off")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - 250);
                        } else if (sts.equals("Flat 70% off (Fastrack)") || sts.equals("Global15")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt
                                    - (0.7 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Flat 30") || sts.equals("Fare Borrow")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt
                                    - (0.3 * BillPaymentPanel.total_amt));
                        }
                    }
                    check_your_luck.setVisible(false);
                    totalBillLabel = new JLabel("Total Bill Amount:" + total_amt); // Replace with your actual bill
                                                                                   // amount
                    panel.add(totalBillLabel, BorderLayout.NORTH);
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
