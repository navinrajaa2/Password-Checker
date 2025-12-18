import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.event.*;

class logic {

    public static String logic(String password) {
        int score = 0;
        boolean isup = false;
        boolean islow = false;
        boolean isnum = false;
        boolean isspec = false;

        if (password.length() >= 8) {
            score++;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                isup = true;
            } else if (Character.isLowerCase(ch)) {
                islow = true;
            } else if (Character.isDigit(ch)) {
                isnum = true;
            } else {
                isspec = true;
            }
        }

        if (isup)
            score++;
        if (islow)
            score++;
        if (isnum)
            score++;
        if (isspec)
            score++;

        switch (score) {
            case 5:
                return "Very Strong";
            case 4:
                return "Strong";
            case 3:
                return "Medium";
            case 2:
                return "Weak";
            default:
                return "Very Weak";
        }
    }
}

public class passwordcheck {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Password Strength Checker");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Enter Password:", SwingConstants.CENTER);
        JPasswordField passwordField = new JPasswordField();
        JLabel resultLabel = new JLabel("Strength: ", SwingConstants.CENTER);

        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            private void update() {
                String password = new String(passwordField.getPassword());
                String strength = logic.logic(password);
                resultLabel.setText("Strength: " + strength);
                switch (strength) {
                    case "Very Weak":
                        resultLabel.setForeground(Color.RED);
                        break;
                    case "Weak":
                        resultLabel.setForeground(Color.ORANGE);
                        break;
                    case "Medium":
                        resultLabel.setForeground(Color.YELLOW.darker());
                        break;
                    case "Strong":
                        resultLabel.setForeground(Color.GREEN.darker());
                        break;
                    case "Very Strong":
                        resultLabel.setForeground(Color.BLUE);
                        break;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        frame.add(titleLabel);
        frame.add(passwordField);
        frame.add(resultLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
