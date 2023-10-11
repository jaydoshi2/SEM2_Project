package Group_Project3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

class JToggleButtonExample extends JFrame implements ItemListener {
    public static void main(String[] args) {
        new JToggleButtonExample();
    }

    public static JToggleButton button;

    JToggleButtonExample() {
        setTitle("JToggleButton with ItemListener Example");
        setLayout(new FlowLayout());
        setJToggleButton();
        setAction();
        
    }

    private void setJToggleButton() {
        button = new JToggleButton("YES");
        button.setBackground(Color.WHITE);
        button.setBounds(600, 250, 90, 50);
        Shopping_template1.panel.add(button);
    }

    private void setAction() {
        button.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent eve) {
        if (button.isSelected()){
            button.setText("NO");

        }
        else
            button.setText("YES");
    }
}