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
    static JFrame frame = new JFrame("Online Shopping system");
    static CardLayout cardLayout = new CardLayout();
    static JPanel cardPanel = new JPanel(cardLayout);
    public static Connection con = null;
    static int falied_count = 0;

    Login_template() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");
    }

    public static String user_name;
    public static char[] password;
    public static String email;
    public static String city;
    public static String State;
    public static String pincode;
    public static Long phone_no;
    public static java.sql.Date Dob;

    public static void main(String[] args) throws Exception {
        frame.setSize(1500, 1500);

        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignupPanel();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(signupPanel, "Signup");

        frame.getContentPane().add(cardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // LOGIN PANEL
    public static JPanel createLoginPanel() throws Exception {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Online Clothing System!");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(450, 5, 500, 100);

        JLabel label2 = new JLabel("LOGIN PORTAL");
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setBounds(520, 60, 500, 100);

        JLabel Text = new JLabel("Enter your username:");
        Text.setBounds(150, 150, 200, 40);
        Text.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField textArea = new JTextField();
        textArea.setBounds(350, 160, 100, 25);

        JPasswordField passwordArea = new JPasswordField();
        JLabel passwordtext = new JLabel("Enter your Password:");
        passwordtext.setFont(new Font("Arial", Font.BOLD, 15));
        passwordtext.setBounds(150, 250, 200, 40);
        passwordArea.setBounds(350, 260, 100, 25);

        ImageIcon imageIcon1 = new ImageIcon("group_icon.jpg");
        JLabel imageLabel = new JLabel(imageIcon1);
        JPanel photo = new JPanel();
        photo.setBounds(670, 300, 330, 380);
        photo.add(imageLabel);

        JButton Login_Button = new JButton("Login");
        Login_Button.setBounds(400, 400, 110, 45);

        JButton SignUp_Button = new JButton("Signup");
        SignUp_Button.setBounds(200, 400, 110, 45);

        String Note = "<html>NOTE: Password must be of length 8 to 15 .<br> 1) It must contain atleast one uppercase letter.<br>"
                +
                " 2) It must contain atleast one digit.<br>" +
                "3) It must contain atleast one special character.<br>" +
                " 4) It must not contain any space.</html>";

        JLabel redNote = new JLabel(Note);
        redNote.setFont(new Font("Arial", Font.BOLD, 20));
        redNote.setForeground(Color.RED);
        redNote.setBounds(650, 150, 600, 160);

        SignUp_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Signup");
            }
        });

        Login_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user_name = textArea.getText();
                password = passwordArea.getPassword();
                String password1 = "";
                for (int i = 0; i < password.length; i++) {
                    password1 += password[i];
                }
                try {
                    String sql = "select * from account_details where user_name = ? and password = ?";
                    PreparedStatement preparedStatement1 = con.prepareStatement(sql);
                    preparedStatement1.setString(1, user_name);
                    preparedStatement1.setString(2, password1);
                    ResultSet rs = preparedStatement1.executeQuery();
                    boolean flag = false;
                    if (rs.next()) {
                        flag = true;
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(frame, "Login succesfull", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        Shopping_template1.main(null);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login Failed", "Error",
                                JOptionPane.WARNING_MESSAGE);
                        falied_count++;
                    }

                    if (falied_count > 2 && Login_Button.isSelected() == false) {
                        int result1 = JOptionPane.showConfirmDialog(frame, "Do you want to change the password ");
                        switch (result1) {
                            case JOptionPane.YES_OPTION:
                                JTextField answer1 = new JTextField();
                                JTextField answer2 = new JTextField();
                                JTextField answer3 = new JTextField();

                                int result;
                                String question1 = "Enter the favourite color";
                                String question2 = "Enter the favourite city";
                                String question3 = "Enter your Nephew (NA if you don't have one)";
                                result = JOptionPane.showOptionDialog(frame, new Object[] { question1,
                                        answer1, question2, answer2, question3, answer3 },
                                        "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                        null, null);
                                if (result == JOptionPane.OK_OPTION) {
                                    String check_ans = "Select question1, question2, question3 from account_details  where user_name = ? group by question1,question2,question3;";
                                    PreparedStatement ps = con.prepareStatement(check_ans);
                                    ps.setString(1, Login_template.user_name);
                                    ResultSet rs1 = ps.executeQuery();
                                    while (rs1.next()) {
                                        if (answer1.getText().equalsIgnoreCase(rs1.getString(1))
                                                && answer2.getText().equalsIgnoreCase(rs1.getString(2))
                                                && answer3.getText().equalsIgnoreCase(rs1.getString(3))) {
                                            JTextField new_password = new JTextField();
                                            String input = JOptionPane.showInputDialog("ENTER THE NEW PASSWORD",
                                                    new_password);
                                            boolean flag2 = false;
                                            String regex1 = "^(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{8,15}$";
                                            Pattern pattern1 = Pattern.compile(regex1);
                                            Matcher matcher1 = pattern1.matcher(input);
                                            while (flag2 == false) {
                                                if (matcher1.matches()) {
                                                    flag2 = true;
                                                    String update_password = "UPDATE account_details SET password = ? where user_name =?";
                                                    PreparedStatement pst = con.prepareStatement(update_password);
                                                    pst.setString(1, input);
                                                    pst.setString(2, user_name);
                                                    pst.executeUpdate();
                                                    JOptionPane.showMessageDialog(frame, "PASSWORD IS UPDATED");
                                                } else {
                                                    JOptionPane.showMessageDialog(
                                                            frame,
                                                            "Enter a valid password",
                                                            "Warning",
                                                            JOptionPane.WARNING_MESSAGE);
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    frame,
                                                    "INVALID INFO",
                                                    "Warning",
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
                    // e1.printStackTrace();;
                    JOptionPane.showMessageDialog(frame, "Don't keep field empty", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    falied_count++;
                }
            }
        });

        panel.add(photo);
        panel.add(redNote);
        panel.add(passwordArea);
        panel.add(passwordtext);
        panel.add(textArea);
        panel.add(Text);
        panel.add(Login_Button);
        panel.add(label);
        panel.add(label2);
        panel.add(SignUp_Button);

        return panel;
    }

    // SIGNUP PANEL
    public static JPanel createSignupPanel() throws Exception {
        JPanel panel = new JPanel(null);
        try {
            new Login_template();
            panel.setBackground(Color.LIGHT_GRAY);
            JLabel signupLabel = new JLabel("SIGNUP PORTAL ");
            signupLabel.setFont(new Font("Arial", Font.BOLD, 30));
            signupLabel.setBounds(520, 10, 500, 100);

            JButton Back_Button = new JButton("Back to Login");
            Back_Button.setBounds(200, 550, 150, 50);

            Back_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Login");
                }
            });

            String Note = "<html>NOTE: Password must be of length 8 to 15 .<br> 1) It must contain atleast one uppercase letter.<br>"
                    +
                    " 2) It must contain atleast one digit.<br>" +
                    "3) It must contain atleast one special character.<br>" +
                    " 4) It must not contain any space.<br>" +
                    "AND PLEASE DO NOT KEEP ANY FIELD EMPTY </html>";

            JLabel redNote = new JLabel(Note);
            redNote.setFont(new Font("Arial", Font.BOLD, 20));
            redNote.setForeground(Color.RED);
            redNote.setBounds(650, 130, 600, 160);

            JButton create_acct_Button = new JButton("Create account");
            create_acct_Button.setBounds(400, 550, 150, 50);

            JLabel phone_no_label = new JLabel("Enter the phone number");
            JTextField phone_input_area = new JTextField();
            phone_no_label.setBounds(130, 150, 200, 20);
            phone_no_label.setFont(new Font("Arial", Font.BOLD, 15));
            phone_input_area.setBounds(350, 150, 110, 20);

            JLabel email_label = new JLabel("Enter your email");
            JTextField email_input_area = new JTextField();
            email_label.setBounds(130, 200, 200, 20);
            email_label.setFont(new Font("Arial", Font.BOLD, 15));
            email_input_area.setBounds(350, 200, 110, 20);

            JLabel city_label = new JLabel("Enter your City:");
            city_label.setFont(new Font("Arial", Font.BOLD, 15));
            city_label.setBounds(130, 250, 200, 20);
            JTextField city_input_area = new JTextField();
            city_input_area.setBounds(350, 250, 110, 20);

            JLabel state_label = new JLabel("Enter your state");
            state_label.setFont(new Font("Arial", Font.BOLD, 15));
            state_label.setBounds(130, 300, 200, 20);
            JComboBox<String> stateComboBox;
            String[] indianStates = {
                    "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
                    "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand",
                    "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
                    "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab",
                    "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
                    "Uttar Pradesh", "Uttarakhand", "West Bengal"
            };
            stateComboBox = new JComboBox<>(indianStates);
            stateComboBox.setBounds(350, 300, 150, 20);

            JLabel pincode_Label = new JLabel("Enter the pincode:");
            pincode_Label.setFont(new Font("Arial", Font.BOLD, 15));
            pincode_Label.setBounds(130, 340, 200, 20);
            JTextField pincode_input_area = new JTextField();
            pincode_input_area.setBounds(350, 340, 110, 20);

            JLabel choose_date_label = new JLabel("Enter your Date of birth");
            choose_date_label.setFont(new Font("Arial", Font.BOLD, 15));
            choose_date_label.setBounds(130, 380, 200, 40);
            JButton select_date_button = new JButton("Enter the date");
            select_date_button.setBounds(350, 380, 160, 25);
            select_date_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Calendar_panel.date_selector();
                }
            });

            JLabel Text = new JLabel("Enter your username:");
            Text.setBounds(130, 420, 200, 40);
            Text.setFont(new Font("Arial", Font.BOLD, 15));
            JTextField textArea = new JTextField();
            textArea.setBounds(350, 430, 100, 25);

            JPasswordField passwordArea = new JPasswordField();
            JLabel passwordtext = new JLabel("Enter your Password:");
            passwordtext.setFont(new Font("Arial", Font.BOLD, 15));
            passwordtext.setBounds(130, 470, 200, 40);
            passwordArea.setBounds(350, 470, 100, 25);
            JLabel questions_text = new JLabel("Give answer of the question for privacy purpose");
            questions_text.setFont(new Font("Arial", Font.BOLD, 20));
            questions_text.setBounds(650, 270, 600, 160);
            JTextField answer1 = new JTextField();
            JTextField answer2 = new JTextField();
            JTextField answer3 = new JTextField();

            String question1 = "Enter the favourite color";
            String question2 = "Enter the favourite city";
            String question3 = "Enter your Nephew (NA if you don't have one)";

            JLabel label1 = new JLabel(question1);
            JLabel label2 = new JLabel(question2);
            JLabel label3 = new JLabel(question3);
            label1.setFont(new Font("Arial", Font.BOLD, 15));
            label2.setFont(new Font("Arial", Font.BOLD, 15));
            label3.setFont(new Font("Arial", Font.BOLD, 15));
            label1.setBounds(650, 310, 600, 160);
            label2.setBounds(650, 370, 600, 160);
            label3.setBounds(650, 430, 600, 160);
            answer1.setBounds(850, 380, 110, 20);
            answer2.setBounds(850, 450, 110, 20);
            answer3.setBounds(750, 540, 110, 20);

            create_acct_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    user_name = textArea.getText();
                    String phone = phone_input_area.getText();
                    phone_no = Long.parseLong(phone_input_area.getText());
                    password = passwordArea.getPassword();
                    email = email_input_area.getText();
                    State = (String) stateComboBox.getSelectedItem();
                    city = city_input_area.getText();
                    Long pinc = Long.parseLong(pincode_input_area.getText());
                    String password1 = "";
                    for (int i = 0; i < password.length; i++) {
                        password1 += password[i];
                    }
                    if (phone.length() == 10) {
                        if (email.contains("@") && email.contains(".com")) {
                            String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{8,15}$";
                            Pattern pattern = Pattern.compile(regex);
                            Matcher matcher = pattern.matcher(password1);
                            if (matcher.matches()) {
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Enter a valid password",
                                        "Warning",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Enter a valid email",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Enter a valid phoneno",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    if (flag) {
                        String insert_values = "INSERT INTO account_details (user_name, password, email, phone_no, city, state, pincode,dob,question1,question2,question3) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                        try {
                            PreparedStatement pst = con.prepareStatement(insert_values);
                            pst.setString(1, user_name);
                            pst.setString(2, password1);
                            pst.setString(3, email);
                            pst.setLong(4, phone_no);
                            pst.setString(5, city);
                            pst.setString(6, State);
                            pst.setLong(7, pinc);
                            pst.setDate(8, Dob);
                            pst.setString(9, answer1.getText());
                            pst.setString(10, answer2.getText());
                            pst.setString(11, answer3.getText());
                            if (pst.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(frame, "Your account is created", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                String modifiedUsername = user_name.replaceAll("[^a-zA-Z0-9_]", "_");

                                String createUsertable = "CREATE TABLE " + modifiedUsername
                                        + " (cart_id SERIAL PRIMARY KEY, product_id INT, subcategory VARCHAR(255), product  VARCHAR(255), gender VARCHAR(10), brand VARCHAR(255), size VARCHAR(10), color  VARCHAR(255), price DECIMAL(10, 2));";

                                PreparedStatement ps2 = con.prepareStatement(createUsertable);
                                ps2.execute();
                            }
                        } catch (PSQLException p) {
                            JOptionPane.showMessageDialog(

                                    frame,
                                    "This user_name is already exsisted",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);

                        } catch (Exception e1) {

                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Enter a valid input and don't keep fields empty",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            });

            panel.add(answer1);
            panel.add(answer2);
            panel.add(answer3);
            panel.add(questions_text);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(pincode_Label);
            panel.add(pincode_input_area);
            panel.add(select_date_button);
            panel.add(choose_date_label);
            panel.add(state_label);
            panel.add(stateComboBox);
            panel.add(city_input_area);
            panel.add(city_label);
            panel.add(email_label);
            panel.add(email_input_area);
            panel.add(phone_no_label);
            panel.add(phone_input_area);
            panel.add(Text);
            panel.add(textArea);
            panel.add(passwordtext);
            panel.add(passwordArea);
            panel.add(create_acct_Button);
            panel.add(redNote);
            panel.add(signupLabel);
            panel.add(Back_Button);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Don't keep field empty", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        return panel;
    }
}

class Calendar_panel extends JFrame {
    private JCalendar calendar;

    public Calendar_panel() {
        setTitle("Date Picker");
        setSize(400, 300);
        setLocationRelativeTo(null);

        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(250, 200));

        JButton selecButton = new JButton("Select Date");
        JPanel panel = new JPanel();
        selecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                java.util.Date D = calendar.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    java.util.Date D1 = dateFormat.parse(dateFormat.format(D));
                    Login_template.Dob = new java.sql.Date(D1.getTime());
                    setVisible(false);
                    dispose();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(calendar);
        panel.add(selecButton);

        getContentPane().add(panel);

        pack();
    }

    public static void date_selector() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calendar_panel app = new Calendar_panel();
                app.setVisible(true);
            }
        });
    }
}
