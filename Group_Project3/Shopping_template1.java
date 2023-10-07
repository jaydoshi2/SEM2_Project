package Group_Project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.sql.*;

import javax.swing.*;

public class Shopping_template1 {
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("Shopping center");

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
                    String selectedGender = "";
                    if (maleRadioButton.isSelected()) {
                        selectedGender = "male";
                    }
                    if (femaleRadioButton.isSelected()) {
                        selectedGender = "female";
                    }
                    if (rb.get(0).isSelected()) {
                        Category2.displaying_items_by_gender_and_category("Clothing", selectedGender);
                    } else if (rb.get(1).isSelected()) {
                        Category2.displaying_items_by_gender_and_category("Accessories", selectedGender);

                    } else if (rb.get(2).isSelected()) {
                        Category2.displaying_items_by_gender_and_category("Footwear", selectedGender);
                        
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

class Category2 {
    static Scanner sc = new Scanner(System.in);
    static Connection con = null;

    public static void displaying_items_by_gender_and_category(String category2, String Gender) {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres",
                    "1234");
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

    public static void select_items_with_subcategory(String Gender, String Category) throws Exception {
        String show_sub = "select subcategory  from " + Category + " where gender=? group by subcategory";
        PreparedStatement ps = con.prepareStatement(show_sub);
        ps.setString(1, Gender);
        ResultSet rs = ps.executeQuery();
        JDialog dialog = new JDialog();
        dialog.setTitle("subcategories");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(200, 200);

        Hashtable<String, JRadioButton> subcats = new Hashtable<>();
        JPanel radiobox_panel = new JPanel();

        radiobox_panel.setLayout(new BoxLayout(radiobox_panel, BoxLayout.Y_AXIS));

        while (rs.next()) {
            String text = rs.getString(1);
            JRadioButton rButton = new JRadioButton(text);
            subcats.put(rs.getString(1), rButton);
            radiobox_panel.add(rButton);
        }
        for (String subcategory : subcats.keySet()) {
            if (subcats.get(subcategory).isSelected()) {
                String subcategory_ouput = "Select *from " + Category + " where subcategory = ? and gender = ?";
                PreparedStatement ps2 = con.prepareStatement(subcategory_ouput);
                ps2.setString(1, subcategory);
                ps2.setString(2, Gender);
                printData(Category, ps2);
                dialog.dispose();
            }
        }
        // Shopping_template1.panel.add(dialog);
        dialog.setVisible(true);
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
            String itemText = "id: " + rs.getInt(1) + " Subcategory: " + rs.getString(2) + " Product: " +
                    rs.getString(3) + " Gender: " + rs.getString(4) + " Brand: " + rs.getString(5) +
                    " Size: " + rs.getString(6) + " Color: " + rs.getString(7) + " Price: " + rs.getDouble(8);
            JCheckBox checkBox = new JCheckBox(itemText);
            items.put(rs.getInt(1), checkBox);
            checkBoxPanel.add(checkBox);
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
