
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class AccountDataBase{
    public Connection createConnection () throws SQLException {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_and_pass", "root", "roginand");
                
                System.out.println("Connected to Database Successfully\n");
                    
                return con;
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return null;
        }
    
    public boolean accountExist (String username) {
        try {
            if (username.trim().isEmpty() || username == null)
                return false;


            Connection con = createConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT username FROM user_and_pass");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                if (rs.getString("username").trim().equalsIgnoreCase(username)) {
                    stmt.close();
                    return true;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addAccount (JButton signup, String username, String password) {
        if (accountExist(username)) {
            JOptionPane.showMessageDialog(signup, "Account already Existed", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (password.trim().isEmpty() && username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(signup,"Username and Password Cannot be Empty!", "Invalid", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(signup,"Password Cannot be Empty!", "Invalid", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(signup,"Username Cannot be Empty!", "Invalid", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Connection con = createConnection();

            PreparedStatement stmt = con.prepareStatement("INSERT INTO user_and_pass (username, password) values (?, ?)");


            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeUpdate();
            
            stmt.close();

            JOptionPane.showMessageDialog(signup, "Account Successfully Created!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean correctAccountDetail (String username, String password) {
        try {
            Connection con = createConnection();

            if (accountExist(username)) {
                PreparedStatement stmt = con.prepareStatement("SELECT password FROM user_and_pass WHERE username = ?");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getString("password").equals(password)) {
                        stmt.close();
                        return true;
                    }

                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
