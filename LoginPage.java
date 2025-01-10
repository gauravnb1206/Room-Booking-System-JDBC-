import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    JLabel lblUsername, lblPassword, lblMessage, lblTitle;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin, btnLogout, btnRegister;

    public LoginPage() {
        // Set the frame's layout and background
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background
        setLayout(null);

        // Title Label
        lblTitle = new JLabel("Welcome to the Login Page");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204)); // Deep blue color
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 20, 400, 40);
        add(lblTitle);

        // Labels
        lblUsername = createLabel("Username:", 100, 100);
        lblPassword = createLabel("Password:", 100, 160);
        lblMessage = new JLabel(" ");
        lblMessage.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMessage.setForeground(new Color(255, 0, 0)); // Red color
        lblMessage.setBounds(100, 240, 300, 20);
        add(lblMessage);

        // Text Fields
        txtUsername = createTextField(200, 100);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 160, 200, 30);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtPassword);

        // Buttons
        btnLogin = createButton("Login", 120, 300, new Color(34, 139, 34)); // Green
        btnLogout = createButton("Logout", 260, 300, new Color(255, 69, 0)); // Red
        btnRegister = createButton("Register", 120, 350, new Color(0, 122, 204)); // Blue

        // Add action listeners
        btnLogin.addActionListener(this);
        btnLogout.addActionListener(this);
        btnRegister.addActionListener(this);

        // Frame properties
        setTitle("Login Window");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 102, 204)); // Deep blue color
        label.setBounds(x, y, 100, 30);
        add(label);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBounds(x, y, 150, 40);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(color.darker()));
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String un = txtUsername.getText();
            String pwd = new String(txtPassword.getPassword());
            try {
                // Database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
                pstmt.setString(1, un);
                pstmt.setString(2, pwd);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    this.dispose();
                    new RoomBookingJDBC(); // Ensure RoomBookingJDBC class exists.
                } else {
                    lblMessage.setText("Invalid username or password");
                }
                con.close();
            } catch (Exception ex) {
                lblMessage.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnRegister) {
            this.dispose();
            new RegisterWindow(); // Ensure RegisterWindow class exists.
        } else if (e.getSource() == btnLogout) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
