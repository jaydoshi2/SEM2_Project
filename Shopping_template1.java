package JDBC.src.Group_Project3;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Shopping_template1 {
    // Declare static fields that will be shared across the class
    public static JPanel panel = new JPanel();  // Main panel to hold the UI components
    public static JFrame frame = new JFrame("Shopping center");  // Main window (frame)
    public static String subcategories;  // Placeholder for selected subcategories
    public static String Category;  // Placeholder for selected category
    public static String attrbute;  // Placeholder for attributes
    public static String Gender;  // Placeholder for selected gender

    public static void main(String[] args) throws Exception {

        // Create a welcome label with specific font settings and bounds
        JLabel label = new JLabel("WELCOME TO LJ SHOPPING CENTER");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(400, 30, 600, 50);

        // Set the layout for the panel and frame size
        panel.setLayout(null);  // Use null layout for absolute positioning
        frame.setSize(1500, 1500);  // Set frame size
        frame.setBackground(Color.CYAN);  // Set frame background color

        // Create label for gender selection
        JLabel Gender_asking_text = new JLabel("Enter the Gender:");
        Gender_asking_text.setFont(new Font("Arial", Font.BOLD, 15));
        Gender_asking_text.setBounds(200, 150, 200, 40);

        // Create radio buttons for gender options
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");

        // Customize radio button appearance
        maleRadioButton.setBackground(Color.CYAN);
        femaleRadioButton.setBackground(Color.CYAN);
        maleRadioButton.setForeground(Color.BLACK);
        femaleRadioButton.setForeground(Color.BLACK);

        // Set positions for the gender radio buttons
        maleRadioButton.setBounds(400, 160, 100, 25);
        femaleRadioButton.setBounds(500, 160, 100, 25);

        // Group the gender radio buttons so only one can be selected at a time
        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(maleRadioButton);
        buttongroup.add(femaleRadioButton);

        // Create a submit button and set its size
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(450, 550, 150, 50);

        // Create label for category selection
        JLabel choose_cat = new JLabel("Choose the Category");
        choose_cat.setFont(new Font("Arial", Font.BOLD, 15));
        choose_cat.setBounds(170, 200, 250, 20);

        // Create radio buttons for the category options (Clothing, Accessories, Footwear)
        ArrayList<JRadioButton> rb = new ArrayList<>();
        rb.add(0, new JRadioButton("Clothing"));
        rb.add(1, new JRadioButton("Accessories"));
        rb.add(2, new JRadioButton("Footwear"));

        // Set bounds and background color for the category radio buttons
        rb.get(0).setBounds(360, 200, 100, 20);
        rb.get(1).setBounds(460, 200, 100, 20);
        rb.get(2).setBounds(560, 200, 100, 20);
        rb.get(0).setBackground(Color.CYAN);
        rb.get(1).setBackground(Color.CYAN);
        rb.get(2).setBackground(Color.CYAN);

        // Group the category radio buttons so only one can be selected at a time
        ButtonGroup br = new ButtonGroup();
        br.add(rb.get(0));
        br.add(rb.get(1));
        br.add(rb.get(2));

        // Create a label for subcategory selection
        JLabel subcategory_label = new JLabel("Do you want to choose the subcategory??");
        subcategory_label.setFont(new Font("Arial", Font.BOLD, 15));
        subcategory_label.setBounds(160, 230, 399, 40);

        // Main class for the toggle button (subcategory selection)
        JToggleButtonExample.main(null);

        // Create a button to display the cart contents
        JButton display_cart = new JButton("Display Cart");
        display_cart.setBounds(900, 430, 200, 50);

        // Create a button to proceed to billing
        JButton proceed_bill = new JButton("Proceed to bill");
        proceed_bill.setBounds(900, 530, 200, 50);

        // Set action for the "Proceed to bill" button to calculate the total amount and display the bill
        proceed_bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int total_amount = 0;  // Initialize total amount
                try {
                    new Category2();  // Create a new instance of the Category2 class
                    String get_items = "Select * from " + Login_template.user_name;  // SQL query to get cart items
                    PreparedStatement pst = Category2.con.prepareStatement(get_items);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        total_amount += rs.getInt(9);  // Sum the prices of the items
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                BillPaymentPanel.billing(total_amount);  // Pass the total amount to the billing function
            }
        });

        // Create a button to check previous transactions
        JButton check_transactioins = new JButton("Check your previous transactions");
        check_transactioins.setBounds(900, 330, 300, 50);

        // Add action listener to the transactions button to display past transactions
        check_transactioins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOpenerExample.main1();  // Open previous transactions using FileOpenerExample
            }
        });

        // Add action listener to the cart display button to show the items in the cart
        display_cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dislpaying_cart = "Select * from " + Login_template.user_name;  // SQL query to get cart items
                    new Category2();  // Create a new instance of the Category2 class
                    PreparedStatement pst = Category2.con.prepareStatement(dislpaying_cart);
                    ResultSet rst = pst.executeQuery();
                    Hashtable<Integer, JCheckBox> items = new Hashtable<>();
                    JDialog dialog = new JDialog();  // Create a dialog to display cart
                    dialog.setTitle(Login_template.user_name + " CART");
                    dialog.setModal(true);
                    dialog.setLayout(new BorderLayout());
                    dialog.setSize(800, 600);

                    JPanel checkBoxPanel = new JPanel();  // Panel to hold checkboxes for each cart item
                    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

                    while (rst.next()) {
                        // Create a checkbox for each cart item with its details
                        String itemText = String.format(
                            "cart_id " + rst.getInt(1) + " id:" + rst.getInt(2) + " Subcategory: "
                            + rst.getString(3) + " Product: "
                            + rst.getString(4) + " Gender: " + rst.getString(5) + " Brand: "
                            + rst.getString(6) + " Size: " + rst.getString(7) + " Color: " + rst.getString(8)
                            + " Prize: " + rst.getDouble(9));
                        JCheckBox checkBox = new JCheckBox(itemText);
                        items.put(rst.getInt(1), checkBox);
                        checkBoxPanel.add(checkBox);

                        // Add separator between items
                        JLabel separator = new JLabel(
                            "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        checkBoxPanel.add(separator);
                    }

                    // Scrollable pane for the cart items
                    JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
                    JButton delete_item = new JButton("Delete from Cart");

                    // Action for deleting selected items from the cart
                    delete_item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            boolean flag = true;
                            for (Integer ids : items.keySet()) {
                                if (items.get(ids).isSelected()) {
                                    String deleting_item = "Delete from " + Login_template.user_name + " where cart_id = ?";
                                    try {
                                        new Category2();  // Create a new instance of Category2 class
                                        PreparedStatement deleting_statement = Category2.con.prepareStatement(deleting_item);
                                        deleting_statement.setInt(1, ids);  // Set cart item id for deletion
                                        if (deleting_statement.executeUpdate() > 0) {
                                            flag = true;
                                        }
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                        // Show warning if deletion fails
                                        JOptionPane.showMessageDialog(
                                            Shopping_template1.frame,
                                            "Failed",
                                            "Warning",
                                            JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            }
                            // Show success message if items were deleted
                            if (flag) {
                                JOptionPane.showMessageDialog(Shopping_template1.frame,
                                    "Items Deleted from " + Login_template.user_name + " cart",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                                dialog.dispose();  // Close the dialog after deletion
                            }
                        }
                    });

                    // Add scrollable panel and delete button to the dialog
                    dialog.add(scrollPane, BorderLayout.CENTER);
                    dialog.add(delete_item, BorderLayout.SOUTH);
                    dialog.setVisible(true);  // Show the dialog
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Add all components to the main panel
        panel.add(label);
        panel.add(Gender_asking_text);
        panel.add(maleRadioButton);
        panel.add(femaleRadioButton);
        panel.add(choose_cat);
        panel.add(rb.get(0));
        panel.add(rb.get(1));
        panel.add(rb.get(2));
        panel.add(subcategory_label);
        panel.add(JToggleButtonExample.toggleButton1);
        panel.add(submitButton);
        panel.add(check_transactioins);
        panel.add(display_cart);
        panel.add(proceed_bill);

        // Add panel to the frame and make the frame visible
        frame.add(panel);
        frame.setVisible(true);

        // Action listener for gender selection (male/female)
        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gender = "Male";
            }
        });
        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gender = "Female";
            }
        });

        // Action listener for the submit button to proceed based on selected category and attributes
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assign category based on the selected radio button
                if (rb.get(0).isSelected()) {
                    Category = "Clothing";
                } else if (rb.get(1).isSelected()) {
                    Category = "Accessories";
                } else if (rb.get(2).isSelected()) {
                    Category = "Footwear";
                }

                attrbute = JToggleButtonExample.toggleButton1.isSelected() ? "Yes" : "No";
                try {
                    Category2.main(null);  // Call the main method of Category2 class
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

class Category2 {
    // Global scanner and database connection variables
    static Scanner sc = new Scanner(System.in);
    static Connection con = null;

    // Constructor for establishing a connection with the PostgreSQL database
    public Category2() throws Exception {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");
    }

    // Method to display items based on selected category and gender
    public static void displaying_items_by_gender_and_category(String category2, String Gender) {
        try {
            // Establish database connection
            new Category2();

            // SQL query to select items from a specific category where the gender matches the user input
            String selecting_product = "select * from " + category2 + " where gender = ? ";

            // Prepared statement to avoid SQL injection
            PreparedStatement ps2 = con.prepareStatement(selecting_product);
            ps2.setString(1, Gender);

            // Call method to display items fetched from database
            printData(category2, ps2);
        } catch (NoSuchElementException e) {
            // Handle case where no items are found
            System.out.println("Thank you");
        } catch (Exception e1) {
            // General exception handling
            e1.printStackTrace();
        }
    }

    // Method to display items by subcategory, filtered by category and gender
    public static void displaying_items_by_subcategory(String category, String Gender) throws Exception {
        // Establish database connection
        new Category2();

        // SQL query to select subcategories from a category based on gender, grouping them
        String show_sub = "select subcategory  from " + category + " where gender=? group by subcategory";

        // Create a dialog box to show the subcategories
        JDialog displaying_subcat1 = new JDialog(Shopping_template1.frame, "Select subcategory", true);
        displaying_subcat1.setLayout(new FlowLayout());

        // Prepared statement to execute the SQL query
        PreparedStatement ps = con.prepareStatement(show_sub);
        ps.setString(1, Gender);
        ResultSet rs = ps.executeQuery();

        // Store subcategories in a vector
        Vector<String> subcategories = new Vector<>();
        while (rs.next()) {
            subcategories.add(rs.getString(1));
        }

        // Set the size of the dialog box
        displaying_subcat1.setSize(250, 100);

        // Button for submitting the subcategory selection
        JButton okButton = new JButton("OK");

        // ComboBox for displaying the subcategory options
        JComboBox<String> subcategories_box = new JComboBox<>(subcategories);

        // Set location for dialog box
        displaying_subcat1.setLocation(350, 300);

        // Action listener for the OK button when a subcategory is selected
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected subcategory
                String selected_subcategory = (String) subcategories_box.getSelectedItem();

                // SQL query to select items from the selected subcategory and gender
                String subcategory_output = "Select * from " + category + " where subcategory = ? and gender = ?";

                try {
                    // Prepared statement to avoid SQL injection
                    PreparedStatement ps = con.prepareStatement(subcategory_output);
                    ps.setString(1, selected_subcategory);
                    ps.setString(2, Gender);

                    // Call method to display items fetched from database
                    printData(category, ps);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // Close the dialog after selection
                displaying_subcat1.dispose();
            }
        });

        // Add components to the dialog box and make it visible
        displaying_subcat1.add(okButton);
        displaying_subcat1.add(subcategories_box);
        displaying_subcat1.setVisible(true);
    }

    // Method to display items in a dialog and allow adding them to the cart
    public static void printData(String category2, PreparedStatement ps2) throws Exception {
        // Execute the query
        ResultSet rs = ps2.executeQuery();

        // Hashtable to map item IDs to their corresponding checkboxes
        Hashtable<Integer, JCheckBox> items = new Hashtable<>();

        // Dialog to display the items
        JDialog dialog = new JDialog();
        dialog.setTitle("Products");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 600);

        // Panel to hold checkboxes for each item
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        // Loop through the result set to display each item
        while (rs.next()) {
            // Format item details as a string to be displayed with each checkbox
            String itemText = String.format(
                "ID: %2d  Subcategory: %-10s  Product: %-20s  Gender: %-6s  Brand: %-15s  Size: %-5s  Color: %-10s  Price: %.2f",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                rs.getString(7), rs.getDouble(8));

            // Create a checkbox for each item and add it to the panel
            JCheckBox checkBox = new JCheckBox(itemText);
            items.put(rs.getInt(1), checkBox);
            checkBoxPanel.add(checkBox);

            // Add a separator between items
            JLabel separator = new JLabel(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            checkBoxPanel.add(separator);
        }

        // Add scroll functionality for the item list
        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);

        // Button to add selected items to the cart
        JButton closeButton = new JButton("ADD TO CART");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                String storedProcedure = "";

                // Loop through the selected items and add them to the cart using stored procedures
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
                            // Call the appropriate stored procedure for each selected item
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

                // If items were successfully added to the cart, show a success message and close the dialog
                if (flag) {
                    JOptionPane.showMessageDialog(Shopping_template1.frame, "Items added to cart", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
        });

        // Add the scroll pane and "ADD TO CART" button to the dialog
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        // Make the dialog visible
        dialog.setVisible(true);
    }
}


class FileOpenerExample {

    // Main method to open the file chooser and select a file
    public static void main1() {
        // Create a JFrame (not shown in this code, but assumed to be part of the UI)

        // Create a JButton (not shown in this code, but assumed to be part of the UI)

        // Create a file chooser and set its initial directory to the user's folder
        JFileChooser fileChooser = new JFileChooser(
                "D:\\Java language\\java programs\\src\\JDBC\\" + Login_template.user_name);

        // Show the file chooser dialog and get the user's selection
        int returnValue = fileChooser.showOpenDialog(null);

        // If the user selected a file, proceed to open it
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
            openFile(selectedFile); // Call the method to open the file
        }
    }

    // Method to open the selected file
    public static void openFile(File file) {
        try {
            // Get the desktop environment for the current platform
            Desktop desktop = Desktop.getDesktop();

            // Check if the platform supports the 'open' action
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(file); // Open the file using the associated application
            } else {
                // If the open action is not supported, display a message
                System.out.println("Desktop is not supported on this platform");
            }
        } catch (Exception e) {
            // Catch and print any exceptions that occur while trying to open the file
            e.printStackTrace();
            System.out.println("Error opening the file: " + e.getMessage()); // Print the error message
        }
    }
}

class JToggleButtonExample extends JFrame implements ItemListener {
    
    // Main method to create an instance of JToggleButtonExample
    public static void main(String[] args) {
        new JToggleButtonExample();
    }

    // JToggleButton declared as a static field to be accessed within the class
    public static JToggleButton button;

    // Constructor to set up the frame, layout, and add a toggle button
    JToggleButtonExample() {
        setTitle("JToggleButton with ItemListener Example"); // Set the title of the JFrame
        setLayout(new FlowLayout()); // Set layout manager to FlowLayout
        setJToggleButton(); // Method to configure the JToggleButton
        setAction(); // Method to add action listener to the button
    }

    // Method to create and add a JToggleButton to the panel
    private void setJToggleButton() {
        button = new JToggleButton("YES"); // Create a toggle button with initial text "YES"
        button.setBackground(Color.WHITE); // Set button background color
        button.setBounds(600, 250, 90, 50); // Set button position and size
        Shopping_template1.panel.add(button); // Add button to the panel (assuming panel is part of Shopping_template1)
    }

    // Method to add item listener to the toggle button
    private void setAction() {
        button.addItemListener((ItemListener) this); // Add this class as an item listener
    }

    // Event handler for the toggle button state change
    public void itemStateChanged(ItemEvent eve) {
        // Check if the button is selected and update its text accordingly
        if (button.isSelected()) {
            button.setText("NO"); // Change text to "NO" when selected
        } else {
            button.setText("YES"); // Change text to "YES" when not selected
        }
    }
}

class BillPaymentPanel extends JFrame {

    // Static field to hold the status of the coupon won during the lucky spin
    public static String sts = null;
    
    // Static field for the total bill amount
    static int total_amt = 0;

    // Constructor to set up the Bill Payment panel
    public BillPaymentPanel(int total_amt) {
        setTitle("Bill Payment"); // Set the title of the frame
        setSize(450, 150); // Set the size of the frame

        // Create a main panel with BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a button panel with FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create a button for UPI payment and add an action listener to it
        JButton upiButton = new JButton("UPI");
        upiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show UPI image in a dialog when UPI button is clicked
                ImageIcon upi = new ImageIcon("D:\\Java language\\java programs\\src\\JDBC\\src\\Upi.jpg");
                int width = upi.getIconWidth();
                int height = upi.getIconHeight();
                JDialog upi_img_dialog = new JDialog(Shopping_template1.frame, "Jay doshi upi id");
                upi_img_dialog.setSize(width, height);
                JLabel jp = new JLabel(upi); // Add UPI image to label
                upi_img_dialog.add(jp, BorderLayout.CENTER);
                upi_img_dialog.setLocation(540, 220);
                upi_img_dialog.setVisible(true); // Show the dialog
            }
        });

        // Create a button for Cash on Delivery and add an action listener to it
        JButton cashOnDeliveryButton = new JButton("Cash on Delivery");
        cashOnDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a message when Cash on Delivery is selected
                JOptionPane.showMessageDialog(null, "Cash on Delivery selected");
            }
        });

        // Create a button for Cancel Payment and add an action listener to it
        JButton cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a message when payment is cancelled
                JOptionPane.showMessageDialog(null, "Payment Cancelled");
            }
        });

        // Create a button for a lucky spin and add an action listener to it
        JButton check_your_luck = new JButton("Take a lucky spin");
        check_your_luck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Load happy and sad emoji images for lucky spin outcome
                    BufferedImage happy_image = ImageIO.read(new File("D:\\Java language\\java programs\\src\\JDBC\\src\\Party_Face_Emoji.png"));
                    BufferedImage sad_image = ImageIO.read(new File("D:\\Java language\\java programs\\src\\JDBC\\src\\sad_emoji.png"));

                    // Create a panel to display the outcome of the lucky spin
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());

                    // Read coupon file to determine the available coupons
                    FileReader fr1 = new FileReader("D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
                    BufferedReader bf1 = new BufferedReader(fr1);
                    String line = bf1.readLine();
                    int count = 0;
                    while (line != null) {
                        count++;
                        line = bf1.readLine(); // Count the number of coupons
                    }
                    bf1.close();

                    // Generate a random coupon index
                    int min = 1;
                    int num = (int) (Math.random() * count) + min;

                    // Read the coupon that matches the random number
                    FileReader fr2 = new FileReader("D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
                    BufferedReader bf2 = new BufferedReader(fr2);
                    String line2;
                    int temp = 0;
                    while (temp != num) {
                        line2 = bf2.readLine();
                        temp++;
                        sts = line2; // Store the winning coupon in sts
                    }
                    bf2.close();

                    // Show the result of the lucky spin based on the selected coupon
                    if (sts.equals("")) {
                        // If no coupon is won, display sad emoji and message
                        JLabel imageLabel = new JLabel(new ImageIcon(sad_image));
                        panel.add(imageLabel, BorderLayout.CENTER);
                        JLabel textLabel = new JLabel("UNFORTUNATELY YOU HAVE WON NOTHING ");
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);
                        JOptionPane.showMessageDialog(null, panel, "UNLUCKY!", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        // If a coupon is won, display happy emoji and the coupon details
                        JLabel imageLabel = new JLabel(new ImageIcon(happy_image));
                        panel.add(imageLabel, BorderLayout.CENTER);
                        JLabel textLabel = new JLabel("You have won " + sts);
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);
                        JOptionPane.showMessageDialog(null, panel, "Congratulations!", JOptionPane.PLAIN_MESSAGE);

                        // Apply discount based on the coupon won
                        if (sts.equals("Brad's deal") || sts.equals("60% off official") || sts.equals("Shopper Appraise")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - (0.6 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Backpage Frebbies") || sts.equals("Mommy saves big")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - (0.4 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Flat 90% Off") || sts.equals("Luxurious Frugal")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - (0.9 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("INR 250 Off")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - 250);
                        } else if (sts.equals("Flat 70% off (Fastrack)") || sts.equals("Global15")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - (0.7 * BillPaymentPanel.total_amt));
                        } else if (sts.equals("Flat 30") || sts.equals("Fare Borrow")) {
                            BillPaymentPanel.total_amt = (int) (BillPaymentPanel.total_amt - (0.3 * BillPaymentPanel.total_amt));
                        }
                    }
                    check_your_luck.setVisible(false); // Hide the lucky spin button after use
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(upiButton);
        buttonPanel.add(cashOnDeliveryButton);
        buttonPanel.add(cancelPaymentButton);
        buttonPanel.add(check_your_luck);

        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the frame and display the window
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
