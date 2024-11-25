package LoginSystem;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javafx.scene.input.KeyEvent;

public class MyFrame extends JFrame {
  
    MyFrame() {
        
    }

    public void homepageFrame () throws SQLException{
        baseFrame("Login", 800, 600);
        
        // Add login components to the frame        
        this.setVisible(true);
    }

    public void baseFrame (String title, int x, int y) throws SQLException {
        loginButton();
        signUpButton();
        this.setTitle(title);
        this.setResizable(false);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(143, 159, 168));
        this.setLayout(null);
    }

    public JFrame createNewFrame (String title, int x, int y) {
        JFrame frame = new JFrame();

        frame.setTitle(title);
        frame.setResizable(false);
        frame.setSize(x, y);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(143, 159, 168));
        frame.setLayout(null);

        return frame;
    }

    public JLabel[] basicComponents() {
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(230, 200, 100, 30);
        this.add(userLabel);

        
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(230, 240, 100, 30);
        this.add(passwordLabel);

        return new JLabel[] {userLabel, passwordLabel};
        
    }
    public JTextField user () {
        JTextField userTextField = new JTextField();
        userTextField.setBounds(300, 200, 200, 30);
        this.add(userTextField);


        return userTextField;
    }

    public JPasswordField password () {
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(300, 240, 200, 30);
        passwordTextField.setEchoChar('*');
        this.add(passwordTextField);

        return passwordTextField;
    }
    public void loginButton () {
        basicComponents();

        MyButton loginButton = new MyButton("Login");

        
        this.add(loginButton.createLogIn(user(), password()));
        
        this.setLayout(null);
    }
    public void signUpButton () throws SQLException {
        MyButton signup = new MyButton("Sign Up");
        this.add(signup.createAccount());
    }
}