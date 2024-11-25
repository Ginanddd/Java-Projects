
import java.awt.Color;
import java.sql.*;
import javax.swing.*;

public class MyButton extends JButton {
    private String username = "", password = "", title;
    int i = 0;
    MyButton (String title) {
        this.setText(title);

        this.title = title;
    }
    public MyButton createLogIn(JTextField userTextField, JPasswordField passwordTextField) {
        AccountDataBase adb = new AccountDataBase();

        MyButton button = new MyButton(getTitle());
        button.setBounds(50, 200, 70, 30);
        button.setLocation(310, 280);

        button.addActionListener(e -> {
            username = userTextField.getText();
            password = new String (passwordTextField.getPassword());
            
            if (adb.correctAccountDetail(username, password)) {
                JOptionPane.showMessageDialog(button, "Success", "Successful", JOptionPane.INFORMATION_MESSAGE);           
                i = 0;
            }
            else {
                i++;
                if (i >= 3) {
                    JOptionPane.showMessageDialog(button, "Too Many Attemps!", "Log in Failed", JOptionPane.ERROR_MESSAGE);   
                    button.setEnabled(false);
                    
                    Timer timer = new Timer(15000, l -> button.setEnabled(true));
                    timer.start();
                    
                    i = 0;
                    
                }
                else
                JOptionPane.showMessageDialog(button, "Wrong credentials", "Log in Failed", JOptionPane.ERROR_MESSAGE);   
                
                passwordTextField.setText("");
            }
            
            System.out.println(adb.correctAccountDetail(username, password));
            System.out.println(username);
            System.out.println(password);
            System.out.println(i);
            
          

        });
        return button;
    }
    public String getTitle() {
        return title;
    }
    public MyButton createAccount () throws SQLException{
        MyButton createButton = new MyButton(getTitle());
        createButton.setBounds(390,280,80,30);

        
        createButton.addActionListener(e -> {
            JFrame signUpFrame = new JFrame();
            MyFrame signFrame = new MyFrame();

            signUpFrame.setTitle("Sign Up");
            signUpFrame.setSize(400,300);
            signUpFrame.setLocationRelativeTo(null);
            signUpFrame.setResizable(false);
            signUpFrame.getContentPane().setBackground(Color.BLACK);
            signUpFrame.setLayout(null);

            JLabel userLabel = signFrame.basicComponents()[0];
            JLabel passLabel = signFrame.basicComponents()[1];

            userLabel.setForeground(Color.WHITE);
            userLabel.setLocation(90,90);
            
            signUpFrame.add(userLabel);
            
            
            passLabel.setForeground(Color.WHITE);
            passLabel.setLocation(90, 125);
            signUpFrame.add(passLabel);

            JTextField userField = signFrame.user();
            userField.setBounds(155, 98, 100, 17);


            signUpFrame.add(userField);

            JPasswordField passwordField = signFrame.password();
            passwordField.setBounds(155, 133, 100, 17);

            signUpFrame.add(passwordField);

            JButton signUpButton = new JButton("Sign up");
            signUpButton.setBounds(155,160, 95, 25);

            try {
                signUpFunction(signUpButton, userField, passwordField);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            signUpFrame.add(signUpButton);


            signUpFrame.setVisible(true);

        });

        return createButton;
    }
    public void signUpFunction (JButton signUpButton, JTextField username, JPasswordField password) throws SQLException{
        signUpButton.addActionListener(e -> {
            AccountDataBase adb = new AccountDataBase();

            String userData = username.getText();
            String passwordData = new String (password.getPassword());

            if (adb.addAccount(signUpButton, userData, passwordData))
                System.out.println("Successfully Created");
            else
                System.out.println("Account Already Existed");

        });
    }
    
}
