import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_UI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public Login_UI() {
        setTitle("Advanced Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Status label for displaying messages
        statusLabel = new JLabel();
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(statusLabel);

        // Login button
        JButton loginButton = new JButton("Login");
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        // ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Replace this with your authentication logic
                boolean authenticated = authenticate(username, password);

                if (authenticated) {
                    statusLabel.setText("Login successful");
                    // You can navigate to the main application here
                } else {
                    statusLabel.setText("Login failed. Please try again.");
                    passwordField.setText(""); // Clear the password field on failure
                }
            }
        });

        // Add the panel to the JFrame
        add(panel);

        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        // Replace this with your actual authentication logic
        // For demonstration, we assume a hardcoded username and password
        String validUsername = "jay";
        String validPassword = "doshi";

        return username.equals(validUsername) && password.equals(validPassword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login_UI();
            }
        });
    }
}
