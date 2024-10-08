package JDBC.src.Group_Project3;

// minor changes in branch1 
import java.awt.*;
import javax.swing.*;

// import org.postgresql.util.PSQLException;

import java.sql.*;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_template extends JFrame {
    // Create a static JFrame to hold the main window
    static JFrame frame = new JFrame("Online Shopping system");

    // Create a CardLayout to switch between Login and Signup panels
    static CardLayout cardLayout = new CardLayout();
    static JPanel cardPanel = new JPanel(cardLayout); // JPanel to hold both Login and Signup panels

    // Database connection object
    public static Connection con = null;
    // Count the number of failed login attempts
    static int falied_count = 0;

    // Constructor to establish the database connection
    Login_template() throws Exception {
        // Load PostgreSQL driver
        Class.forName("org.postgresql.Driver");
        // Connect to the database
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");
    }

    // User-related static fields to store the data of logged-in or registering
    // users
    public static String user_name;
    public static char[] password;
    public static String email;
    public static String city;
    public static String State;
    public static String pincode;
    public static Long phone_no;
    public static java.sql.Date Dob;

    // Main method to start the application
    public static void main(String[] args) throws Exception {
        // Set the frame size
        frame.setSize(1500, 1500);

        // Create the Login and Signup panels
        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignupPanel();

        // Add the panels to the card panel (to switch between them)
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(signupPanel, "Signup");

        // Add the card panel to the frame and set it visible
        frame.getContentPane().add(cardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to create the Login panel
    public static JPanel createLoginPanel() throws Exception {
        // Create a new JPanel with null layout for custom positioning
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.LIGHT_GRAY); // Set background color

        // Create and style the labels
        JLabel label = new JLabel("Online Clothing System!");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(450, 5, 500, 100);

        JLabel label2 = new JLabel("LOGIN PORTAL");
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setBounds(520, 60, 500, 100);

        JLabel Text = new JLabel("Enter your username:");
        Text.setBounds(150, 150, 200, 40);
        Text.setFont(new Font("Arial", Font.BOLD, 15));

        // Create the text field for username input
        JTextField textArea = new JTextField();
        textArea.setBounds(350, 160, 100, 25);

        // Create password field for password input
        JPasswordField passwordArea = new JPasswordField();
        JLabel passwordtext = new JLabel("Enter your Password:");
        passwordtext.setFont(new Font("Arial", Font.BOLD, 15));
        passwordtext.setBounds(150, 250, 200, 40);
        passwordArea.setBounds(350, 260, 100, 25);

        // Add an image to the login panel
        ImageIcon imageIcon1 = new ImageIcon("group_icon.jpg");
        JLabel imageLabel = new JLabel(imageIcon1);
        JPanel photo = new JPanel();
        photo.setBounds(670, 300, 330, 380); // Set image position
        photo.add(imageLabel);

        // Create Login and Signup buttons
        JButton Login_Button = new JButton("Login");
        Login_Button.setBounds(400, 400, 110, 45);

        JButton SignUp_Button = new JButton("Signup");
        SignUp_Button.setBounds(200, 400, 110, 45);

        // Note for password rules
        String Note = "<html>NOTE: Password must be of length 8 to 15 .<br> 1) It must contain atleast one uppercase letter.<br>"
                + " 2) It must contain atleast one digit.<br>" + "3) It must contain atleast one special character.<br>"
                + " 4) It must not contain any space.</html>";

        JLabel redNote = new JLabel(Note);
        redNote.setFont(new Font("Arial", Font.BOLD, 20));
        redNote.setForeground(Color.RED);
        redNote.setBounds(650, 150, 600, 160); // Set bounds for the note

        // Action listener for Signup button to switch to Signup panel
        SignUp_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Signup"); // Show Signup panel
            }
        });

        // Action listener for Login button to validate login credentials
        Login_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user_name = textArea.getText(); // Get username input
                password = passwordArea.getPassword(); // Get password input
                String password1 = new String(password); // Convert password to string

                try {
                    // SQL query to validate username and password
                    String sql = "select * from account_details where user_name = ? and password = ?";
                    PreparedStatement preparedStatement1 = con.prepareStatement(sql);
                    preparedStatement1.setString(1, user_name);
                    preparedStatement1.setString(2, password1);
                    ResultSet rs = preparedStatement1.executeQuery();
                    boolean flag = false;

                    // Check if the result set contains any user
                    if (rs.next()) {
                        flag = true;
                    }

                    if (flag) {
                        // If login is successful, display a success message
                        JOptionPane.showMessageDialog(frame, "Login successful", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        Shopping_template1.main(null); // Redirect to shopping template
                    } else {
                        // If login fails, display an error message
                        JOptionPane.showMessageDialog(frame, "Login Failed", "Error",
                                JOptionPane.WARNING_MESSAGE);
                        falied_count++; // Increment failed login attempts count
                    }

                    // If failed login attempts exceed 2, prompt the user to change the password
                    if (falied_count > 2 && Login_Button.isSelected() == false) {
                        int result1 = JOptionPane.showConfirmDialog(frame, "Do you want to change the password ");
                        switch (result1) {
                            case JOptionPane.YES_OPTION:
                                // Security questions for password reset
                                JTextField answer1 = new JTextField();
                                JTextField answer2 = new JTextField();
                                JTextField answer3 = new JTextField();

                                int result;
                                String question1 = "Enter your favorite color";
                                String question2 = "Enter your favorite city";
                                String question3 = "Enter your Nephew's name (NA if none)";
                                result = JOptionPane.showOptionDialog(frame, new Object[] { question1,
                                        answer1, question2, answer2, question3, answer3 },
                                        "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                        null, null);

                                if (result == JOptionPane.OK_OPTION) {
                                    // Check if security answers match with the database
                                    String check_ans = "Select question1, question2, question3 from account_details where user_name = ? group by question1,question2,question3;";
                                    PreparedStatement ps = con.prepareStatement(check_ans);
                                    ps.setString(1, Login_template.user_name);
                                    ResultSet rs1 = ps.executeQuery();
                                    while (rs1.next()) {
                                        if (answer1.getText().equalsIgnoreCase(rs1.getString(1))
                                                && answer2.getText().equalsIgnoreCase(rs1.getString(2))
                                                && answer3.getText().equalsIgnoreCase(rs1.getString(3))) {
                                            // Prompt to set a new password
                                            JTextField new_password = new JTextField();
                                            String input = JOptionPane.showInputDialog("ENTER THE NEW PASSWORD",
                                                    new_password);
                                            boolean flag2 = false;
                                            String regex1 = "^(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{8,15}$";
                                            Pattern pattern1 = Pattern.compile(regex1);
                                            Matcher matcher1 = pattern1.matcher(input);

                                            while (!flag2) {
                                                if (matcher1.matches()) {
                                                    // Update the password if valid
                                                    flag2 = true;
                                                    String update_password = "UPDATE account_details SET password = ? where user_name =?";
                                                    PreparedStatement pst = con.prepareStatement(update_password);
                                                    pst.setString(1, input);
                                                    pst.setString(2, user_name);
                                                    pst.executeUpdate();
                                                    JOptionPane.showMessageDialog(frame, "PASSWORD IS UPDATED");
                                                } else {
                                                    // If the new password is invalid, show a warning message
                                                    JOptionPane.showMessageDialog(frame, "Enter a valid password",
                                                            "Warning", JOptionPane.WARNING_MESSAGE);
                                                }
                                            }
                                        } else {
                                            // Show a warning if security answers are invalid
                                            JOptionPane.showMessageDialog(frame, "INVALID INFO", "Warning",
                                                    JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case JOptionPane.NO_OPTION:
                                break;
                            case JOptionPane.CANCEL_OPTION:
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e1) {
                    // If any error occurs, display the error message
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                }
            }
        });

        // Add components to the panel
        panel.add(label);
        panel.add(label2);
        panel.add(Text);
        panel.add(textArea);
        panel.add(passwordArea);
        panel.add(passwordtext);
        panel.add(Login_Button);
        panel.add(SignUp_Button);
        panel.add(redNote);
        panel.add(photo);

        return panel; // Return the login panel
    }

    // SIGNUP PANEL
   // SIGNUP PANEL
public static JPanel createSignupPanel() throws Exception {
    // Create a JPanel with a null layout to hold all components
    JPanel panel = new JPanel(null);
    try {
        new Login_template();  // Initialize Login_template (assumed to be required for the layout)
        
        // Set background color and create a label for the signup portal heading
        panel.setBackground(Color.LIGHT_GRAY);
        JLabel signupLabel = new JLabel("SIGNUP PORTAL ");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 30));  // Set font for the signup portal label
        signupLabel.setBounds(520, 10, 500, 100);  // Position the signup portal label

        // Back button to return to login panel
        JButton Back_Button = new JButton("Back to Login");
        Back_Button.setBounds(200, 550, 150, 50);  // Set button size and position

        // Add action listener to switch back to the login card panel
        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        // Display password rules and note to the user
        String Note = "<html>NOTE: Password must be of length 8 to 15 .<br> 1) It must contain atleast one uppercase letter.<br>"
                + " 2) It must contain atleast one digit.<br>"
                + "3) It must contain atleast one special character.<br>"
                + " 4) It must not contain any space.<br>"
                + "AND PLEASE DO NOT KEEP ANY FIELD EMPTY </html>";

        JLabel redNote = new JLabel(Note);
        redNote.setFont(new Font("Arial", Font.BOLD, 20));  // Set font for the note
        redNote.setForeground(Color.RED);  // Highlight note in red color
        redNote.setBounds(650, 130, 600, 160);  // Set note position

        // Button to create an account
        JButton create_acct_Button = new JButton("Create account");
        create_acct_Button.setBounds(400, 550, 150, 50);  // Set position and size of the create account button

        // Input fields for phone number
        JLabel phone_no_label = new JLabel("Enter the phone number");
        JTextField phone_input_area = new JTextField();
        phone_no_label.setBounds(130, 150, 200, 20);  // Set position for phone number label
        phone_no_label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for the label
        phone_input_area.setBounds(350, 150, 110, 20);  // Set position for phone input area

        // Input fields for email
        JLabel email_label = new JLabel("Enter your email");
        JTextField email_input_area = new JTextField();
        email_label.setBounds(130, 200, 200, 20);  // Set position for email label
        email_label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for the label
        email_input_area.setBounds(350, 200, 110, 20);  // Set position for email input area

        // Input fields for city and state
        JLabel city_label = new JLabel("Enter your City:");
        city_label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for the city label
        city_label.setBounds(130, 250, 200, 20);  // Set position for city label
        JTextField city_input_area = new JTextField();
        city_input_area.setBounds(350, 250, 110, 20);  // Set position for city input area

        JLabel state_label = new JLabel("Enter your state");
        state_label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for the state label
        state_label.setBounds(130, 300, 200, 20);  // Set position for state label
        JComboBox<String> stateComboBox;  // Dropdown to select Indian states
        String[] indianStates = {
            "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand",
            "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
            "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab",
            "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
            "Uttar Pradesh", "Uttarakhand", "West Bengal"
        };
        stateComboBox = new JComboBox<>(indianStates);  // Populate ComboBox with Indian states
        stateComboBox.setBounds(350, 300, 150, 20);  // Set position for the ComboBox

        // Input field for pincode
        JLabel pincode_Label = new JLabel("Enter the pincode:");
        pincode_Label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for pincode label
        pincode_Label.setBounds(130, 340, 200, 20);  // Set position for pincode label
        JTextField pincode_input_area = new JTextField();
        pincode_input_area.setBounds(350, 340, 110, 20);  // Set position for pincode input area

        // Button to select the date of birth (opens a calendar)
        JLabel choose_date_label = new JLabel("Enter your Date of birth");
        choose_date_label.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for date label
        choose_date_label.setBounds(130, 380, 200, 40);  // Set position for date label
        JButton select_date_button = new JButton("Enter the date");
        select_date_button.setBounds(350, 380, 160, 25);  // Set position for date button

        // Username input field
        JLabel Text = new JLabel("Enter your username:");
        Text.setBounds(130, 420, 200, 40);  // Set position for username label
        Text.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for username label
        JTextField textArea = new JTextField();
        textArea.setBounds(350, 430, 100, 25);  // Set position for username input field

        // Password input field
        JPasswordField passwordArea = new JPasswordField();
        JLabel passwordtext = new JLabel("Enter your Password:");
        passwordtext.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for password label
        passwordtext.setBounds(130, 470, 200, 40);  // Set position for password label
        passwordArea.setBounds(350, 470, 100, 25);  // Set position for password input field

        // Security questions and answers for privacy purposes
        JLabel questions_text = new JLabel("Give answer of the question for privacy purpose");
        questions_text.setFont(new Font("Arial", Font.BOLD, 20));  // Set font for questions
        questions_text.setBounds(650, 270, 600, 160);  // Set position for questions label
        JTextField answer1 = new JTextField();  // Answer for question 1
        JTextField answer2 = new JTextField();  // Answer for question 2
        JTextField answer3 = new JTextField();  // Answer for question 3

        // Security questions
        String question1 = "Enter the favourite color";
        String question2 = "Enter the favourite city";
        String question3 = "Enter your Nephew (NA if you don't have one)";

        // Display labels for the security questions
        JLabel label1 = new JLabel(question1);
        JLabel label2 = new JLabel(question2);
        JLabel label3 = new JLabel(question3);
        label1.setFont(new Font("Arial", Font.BOLD, 15));  // Set font for the question labels
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label1.setBounds(650, 310, 600, 160);  // Set position for question 1
        label2.setBounds(650, 370, 600, 160);  // Set position for question 2
        label3.setBounds(650, 430, 600, 160);  // Set position for question 3
        answer1.setBounds(850, 380, 110, 20);  // Set position for answer 1
        answer2.setBounds(850, 450, 110, 20);  // Set position for answer 2
        answer3.setBounds(750, 540, 110, 20);  // Set position for answer 3

        // Action listener for the create account button
        create_acct_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                // Get user input values
                user_name = textArea.getText();
                String phone = phone_input_area.getText();
                phone_no = Long.parseLong(phone_input_area.getText());
                password = passwordArea.getPassword();
                // Validate phone number length
                if (phone.length() != 10) {
                    flag = true;
                    JOptionPane.showMessageDialog(panel, "Please enter a valid phone number");
                }
                // Validate username length
                if (user_name.length() < 3) {
                    flag = true;
                    JOptionPane.showMessageDialog(panel, "Username must be of length greater than or equal to 3");
                }
                // Validate password length
                if (password.length < 8 || password.length > 15) {
                    flag = true;
                    JOptionPane.showMessageDialog(panel, "Password must be of length between 8 to 15");
                }
                // Show confirmation if all validations are passed
                if (!flag) {
                    JOptionPane.showMessageDialog(panel, "Account created successfully");
                }
            }
        });

        // Add all components to the signup panel
        panel.add(create_acct_Button);
        panel.add(Back_Button);
        panel.add(signupLabel);
        panel.add(passwordArea);
        panel.add(phone_no_label);
        panel.add(phone_input_area);
        panel.add(passwordtext);
        panel.add(textArea);
        panel.add(Text);
        panel.add(redNote);
        panel.add(answer1);
        panel.add(answer2);
        panel.add(answer3);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(pincode_Label);
        panel.add(pincode_input_area);
        panel.add(select_date_button);
        panel.add(choose_date_label);
        panel.add(stateComboBox);
        panel.add(state_label);
        panel.add(email_input_area);
        panel.add(email_label);
        panel.add(city_input_area);
        panel.add(city_label);
        panel.add(questions_text);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return panel;
}

// Calendar_panel class extends JFrame to create a custom window for date selection using a JCalendar component
class Calendar_panel extends JFrame {
    private JCalendar calendar;  // JCalendar component to display the calendar for date selection

    // Constructor to initialize the calendar window
    public Calendar_panel() {
        setTitle("Date Picker");  // Set the title of the window
        setSize(400, 300);  // Set the size of the window
        setLocationRelativeTo(null);  // Center the window on the screen

        // Initialize the JCalendar component for date selection
        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(250, 200));  // Set preferred size for the calendar

        // Create a button to confirm the selected date
        JButton selecButton = new JButton("Select Date");
        JPanel panel = new JPanel();  // Create a panel to hold the calendar and button
        
        // Add action listener to the "Select Date" button
        selecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected date from the calendar
                java.util.Date D = calendar.getDate();
                
                // Define the date format as dd-MM-yyyy
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    // Format the selected date and convert it to java.sql.Date format
                    java.util.Date D1 = dateFormat.parse(dateFormat.format(D));
                    Login_template.Dob = new java.sql.Date(D1.getTime());  // Store the selected date in the Login_template class

                    // Hide and dispose the current window after the date is selected
                    setVisible(false);
                    dispose();
                } catch (ParseException e1) {
                    // Catch and print any parse exceptions that occur while formatting the date
                    e1.printStackTrace();
                }
            }
        });

        // Add the calendar and button to the panel
        panel.add(calendar);
        panel.add(selecButton);

        // Add the panel to the frame's content pane
        getContentPane().add(panel);

        // Pack the frame to adjust to preferred sizes of all components
        pack();
    }

    // Method to invoke the calendar window when the user needs to select a date
    public static void date_selector() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the calendar window
                Calendar_panel app = new Calendar_panel();
                app.setVisible(true);
            }
        });
    }
}
