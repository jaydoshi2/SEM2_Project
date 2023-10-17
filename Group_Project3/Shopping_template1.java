package Group_Project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.sql.*;
import javax.swing.*;

public class Shopping_template1 {
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("Shopping center");
    public static String subcategories;
    public static String Category;
    public static String attrbute;
    public static String Gender;

    public static void main(String[] args) throws Exception {

        JLabel label = new JLabel("WELCOME TO LJ SHOPPING CENTER");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(400, 30, 600, 50);

        panel.setLayout(null);
        frame.setSize(1500, 1500);
        frame.setBackground(Color.CYAN);

        JLabel Gender_asking_text = new JLabel("Enter the Gender:");
        Gender_asking_text.setFont(new Font("Arial", Font.BOLD, 15));
        Gender_asking_text.setBounds(200, 150, 200, 40);

        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");

        maleRadioButton.setBackground(Color.CYAN);
        femaleRadioButton.setBackground(Color.CYAN);
        maleRadioButton.setForeground(Color.BLACK);
        femaleRadioButton.setForeground(Color.BLACK);

        maleRadioButton.setBounds(400, 160, 100, 25);
        femaleRadioButton.setBounds(500, 160, 100, 25);

        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(maleRadioButton);
        buttongroup.add(femaleRadioButton);

        JButton submitButton = new JButton("Submit");

        submitButton.setBounds(450, 550, 150, 50);

        JLabel choose_cat = new JLabel("Choose the Category");
        choose_cat.setFont(new Font("Arial", Font.BOLD, 15));
        choose_cat.setBounds(170, 200, 250, 20);

        ArrayList<JRadioButton> rb = new ArrayList<>();
        rb.add(0, new JRadioButton("Clothing"));
        rb.add(1, new JRadioButton("Accessories"));
        rb.add(2, new JRadioButton("Footwear"));

        rb.get(0).setBounds(360, 200, 100, 20);
        rb.get(1).setBounds(460, 200, 100, 20);
        rb.get(2).setBounds(560, 200, 100, 20);

        rb.get(0).setBackground(Color.CYAN);
        rb.get(1).setBackground(Color.CYAN);
        rb.get(2).setBackground(Color.CYAN);

        ButtonGroup br = new ButtonGroup();
        br.add(rb.get(0));
        br.add(rb.get(1));
        br.add(rb.get(2));

        JLabel subcategory_label = new JLabel("Do you want to choose the subcategory??");
        subcategory_label.setFont(new Font("Arial", Font.BOLD, 15));
        subcategory_label.setBounds(160, 230, 399, 40);
        JToggleButtonExample.main(null);

        JButton display_cart = new JButton("Display Cart");
        display_cart.setBounds(900, 430, 200, 50);

        JButton proceed_bill = new JButton("Proceed to bill");
        proceed_bill.setBounds(900, 530, 200, 50);

        proceed_bill.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int total_amount = 0;
                try {
                    new Category2();
                    String get_items = "Select * from " + Login_template.user_name;
                    PreparedStatement pst = Category2.con.prepareStatement(get_items);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        total_amount += rs.getInt(9);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                BillPaymentPanel.billing(total_amount);


            }

        });

        JButton check_transactioins = new JButton("Check your previous transactions");
        check_transactioins.setBounds(900, 330, 300, 50);

        check_transactioins.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FileOpenerExample.main1();
            }

        });
        display_cart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dislpaying_cart = "Select * from " + Login_template.user_name;
                    new Category2();
                    PreparedStatement pst = Category2.con.prepareStatement(dislpaying_cart);
                    ResultSet rst = pst.executeQuery();
                    Hashtable<Integer, JCheckBox> items = new Hashtable<>();
                    JDialog dialog = new JDialog();
                    dialog.setTitle(Login_template.user_name + " CART");
                    dialog.setModal(true);
                    dialog.setLayout(new BorderLayout());
                    dialog.setSize(800, 600);

                    JPanel checkBoxPanel = new JPanel();
                    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

                    while (rst.next()) {
                        String itemText = String.format(
                                ("cart_id " + rst.getInt(1) + " id:" + rst.getInt(2) + " Subcategory: "
                                        + rst.getString(3) + " Product: "
                                        + rst.getString(4) + " Gender: " + rst.getString(5) + " Brand: "
                                        + rst.getString(6)
                                        + " Size: " + rst.getString(7) + " Color: " + rst.getString(8) + " Prize: "
                                        + rst.getDouble(9)));

                        JCheckBox checkBox = new JCheckBox(itemText);
                        items.put(rst.getInt(1), checkBox);
                        checkBoxPanel.add(checkBox);

                        JLabel separator = new JLabel(
                                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        checkBoxPanel.add(separator);
                    }

                    JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
                    JButton delete_item = new JButton("Delete from Cart");

                    delete_item.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            boolean flag = true;
                            for (Integer ids : items.keySet()) {
                                if (items.get(ids).isSelected()) {
                                    String deleting_item = "Delete from " + Login_template.user_name
                                            + " where cart_id = ?";
                                    try {
                                        new Category2();
                                        PreparedStatement deleting_statement = Category2.con
                                                .prepareStatement(deleting_item);
                                        deleting_statement.setInt(1, ids);
                                        if (deleting_statement.executeUpdate() > 0) {
                                            flag = true;
                                        }
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                        JOptionPane.showMessageDialog(
                                                Shopping_template1.frame,
                                                "Failed",
                                                "Warning",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            }
                            if (flag) {
                                JOptionPane.showMessageDialog(Shopping_template1.frame,
                                        "Items Deleted from " + Login_template.user_name + " cart",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                dialog.dispose();
                            }
                        }

                    });

                    dialog.add(scrollPane, BorderLayout.CENTER);
                    dialog.add(delete_item, BorderLayout.SOUTH);

                    dialog.setVisible(true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

        });

        panel.add(check_transactioins);
        panel.add(proceed_bill);
        panel.add(display_cart);
        panel.add(subcategory_label);
        panel.add(choose_cat);
        panel.add(rb.get(0));
        panel.add(rb.get(1));
        panel.add(rb.get(2));
        panel.add(label);
        frame.add(Gender_asking_text);
        frame.add(maleRadioButton);
        frame.add(femaleRadioButton);
        frame.add(submitButton);
        frame.add(panel).setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (maleRadioButton.isSelected()) {
                        Shopping_template1.Gender = "male";
                    }
                    if (femaleRadioButton.isSelected()) {
                        Shopping_template1.Gender = "female";
                    }
                    if (rb.get(0).isSelected()) {

                        if (JToggleButtonExample.button.isSelected()) {
                            Category2.displaying_items_by_subcategory("Clothing", Shopping_template1.Gender);

                        } else {
                            Category2.displaying_items_by_gender_and_category("Clothing", Shopping_template1.Gender);
                        }

                    } else if (rb.get(1).isSelected()) {

                        if (JToggleButtonExample.button.isSelected()) {
                            Category2.displaying_items_by_subcategory("Accessories", Shopping_template1.Gender);
                        } else {
                            Category2.displaying_items_by_gender_and_category("Accessories", Shopping_template1.Gender);
                        }

                    } else if (rb.get(2).isSelected()) {

                        if (JToggleButtonExample.button.isSelected()) {
                            Category2.displaying_items_by_subcategory("Footwear", Shopping_template1.Gender);
                        } else {
                            Category2.displaying_items_by_gender_and_category("Footwear", Shopping_template1.Gender);
                        }

                    }

                } catch (Exception e2) {

                }
            }
        });
    }
}

class Category2 {
    static Scanner sc = new Scanner(System.in);
    static Connection con = null;

    public Category2() throws Exception {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres",
                "1234");
    }

    public static void displaying_items_by_gender_and_category(String category2, String Gender) {
        try {
            new Category2();
            String selectiog_product = "select * from " + category2 + " where gender = ? ";
            PreparedStatement ps2 = con.prepareStatement(selectiog_product);
            ps2.setString(1, Gender);
            printData(category2, ps2);
        } catch (NoSuchElementException e) {
            System.out.println("thank you");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void displaying_items_by_subcategory(String category, String Gender) throws Exception {
        new Category2();
        String show_sub = "select subcategory  from " + category + " where gender=? group by subcategory";
        JDialog displaying_subcat1 = new JDialog(Shopping_template1.frame, "Select subcategory", true);
        displaying_subcat1.setLayout(new FlowLayout());
        PreparedStatement ps = con.prepareStatement(show_sub);
        ps.setString(1, Gender);
        ResultSet rs = ps.executeQuery();
        Vector<String> subcategories = new Vector<>();

        while (rs.next()) {
            subcategories.add(rs.getString(1));
        }
        displaying_subcat1.setSize(250, 100);
        JButton okButton = new JButton("OK");
        JComboBox<String> subcategories_box = new JComboBox<>(subcategories);
        displaying_subcat1.setLocation(350, 300);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the combo box
                String selected_subcategory = (String) subcategories_box.getSelectedItem();
                String subcategory_ouput = "Select *from " + category + " where subcategory = ? and gender = ?";
                try {
                    PreparedStatement ps = con.prepareStatement(subcategory_ouput);
                    ps.setString(1, selected_subcategory);
                    ps.setString(2, Gender);
                    printData(category, ps);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                displaying_subcat1.dispose(); // Close the dialog
            }
        });
        displaying_subcat1.add(okButton);
        displaying_subcat1.add(subcategories_box);
        displaying_subcat1.setVisible(true);
    }

    public static void printData(String category2, PreparedStatement ps2) throws Exception {
        ResultSet rs = ps2.executeQuery();
        Hashtable<Integer, JCheckBox> items = new Hashtable<>();
        JDialog dialog = new JDialog();
        dialog.setTitle("Products");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 600);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        while (rs.next()) {
            String itemText = String.format(
                    "ID: %2d  Subcategory: %-10s  Product: %-20s  Gender: %-6s  Brand: %-15s  Size: %-5s  Color: %-10s  Price: %.2f",
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getDouble(8));

            JCheckBox checkBox = new JCheckBox(itemText);
            items.put(rs.getInt(1), checkBox);
            checkBoxPanel.add(checkBox);

            JLabel separator = new JLabel(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            checkBoxPanel.add(separator);
        }

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);

        JButton closeButton = new JButton("ADD TO CART");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                String storedProcedure = "";
                for (Integer ids : items.keySet()) {
                    if (items.get(ids).isSelected()) {
                        if (category2.equals("Clothing")) {
                            storedProcedure = " CALL insert_clothing(?,?) ";
                        } else if (category2.equals("Accessories")) {
                            storedProcedure = " CALL insert_accessories(?,?); ";
                        } else {
                            storedProcedure = " CALL insert_footwear(?,?) ";
                        }
                        CallableStatement callableStatement = null;
                        try {
                            callableStatement = con.prepareCall(storedProcedure);
                            callableStatement.setString(1, Login_template.user_name);
                            callableStatement.setInt(2, ids);
                            if (callableStatement.execute()) {
                                flag = true;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    Shopping_template1.frame,
                                    "Failed",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(Shopping_template1.frame, "Items added to cart", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
        });

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}

class FileOpenerExample {

    public static void main1() {
        // Create a JFrame

        // Create a JButton

        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser(
                "C:\\Java language\\java programs\\src\\JDBC\\" + Login_template.user_name);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            openFile(selectedFile);
        }
    }

    public static void openFile(File file) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(file);
            } else {
                System.out.println("Desktop is not supported on this platform");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }
}
