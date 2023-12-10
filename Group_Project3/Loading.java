package JDBC.src.Group_Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class Loading extends JFrame {
    private JLabel loadingLabel;
    private Timer timer;

    public Loading() {
        setTitle("Loading Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);

        loadingLabel = new JLabel("Loading...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loadingLabel, BorderLayout.CENTER);

        timer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.setRepeats(false); 

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Loading Loading = new Loading();
                Loading.setVisible(true);
            }
        });
    }
}

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

//  public class Loading extends JFrame {
//     private JLabel loadingLabel;
//     private JButton closeButton; // Add a close button

//     public Loading() {
//         setTitle("Loading Screen");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(300, 100);
//         setLocationRelativeTo(null);

//         loadingLabel = new JLabel("Loading...");
//         loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         add(loadingLabel, BorderLayout.CENTER);

//         closeButton = new JButton("Close"); // Create a close button
//         add(closeButton, BorderLayout.SOUTH);

//         closeButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Close the JFrame when the button is clicked
//                 dispose();
//             }
//         });
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 Loading Loading = new Loading();
//                 Loading.setVisible(true);
//             }
//         });
//     }
// }
