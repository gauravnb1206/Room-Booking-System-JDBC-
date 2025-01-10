import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterWindow extends JFrame implements ActionListener {
    JLabel lblTitle, lblUserId, lblName, lblAddress, lblMobile, lblEmail, lblUsername, lblPassword;
    JTextField txtUserId, txtName, txtAddress, txtMobile, txtEmail, txtUsername;
    JPasswordField txtPassword;
    JButton btnRegister, btnExit;

    public RegisterWindow() {
        // Set overall background and layout
        getContentPane().setBackground(new Color(255, 255, 255)); // White background
        getContentPane().setLayout(null);

        // Title Label
        lblTitle = new JLabel("Register Here");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 122, 204)); // Blue color
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(150, 20, 300, 40);
        getContentPane().add(lblTitle);

        // Labels
        lblUserId = createLabel("User ID:", 50, 80);
        lblName = createLabel("Name:", 50, 130);
        lblAddress = createLabel("Address:", 50, 180);
        lblMobile = createLabel("Mobile:", 50, 230);
        lblEmail = createLabel("Email:", 50, 280);
        lblUsername = createLabel("Username:", 50, 330);
        lblPassword = createLabel("Password:", 50, 380);

        // Text Fields
        txtUserId = createTextField(200, 80);
        txtName = createTextField(200, 130);
        txtAddress = createTextField(200, 180);
        txtMobile = createTextField(200, 230);
        txtEmail = createTextField(200, 280);
        txtUsername = createTextField(200, 330);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 380, 300, 30);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        getContentPane().add(txtPassword);

        // Buttons
        btnRegister = createButton("Register", 150, 450, new Color(0, 153, 76)); // Green
        btnExit = createButton("Exit", 320, 450, new Color(204, 0, 0)); // Red

        // Add event listeners
        btnRegister.addActionListener(this);
        btnExit.addActionListener(this);

        // Frame properties
        setTitle("Registration Window");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 122, 204)); // Blue color
        label.setBounds(x, y, 150, 30);
        getContentPane().add(label);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 300, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        getContentPane().add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBounds(x, y, 120, 40);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(color.darker()));
        getContentPane().add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            try {
                // Get input from fields
                int userId = Integer.parseInt(txtUserId.getText().trim());
                String name = txtName.getText().trim();
                String address = txtAddress.getText().trim();
                String mobile = txtMobile.getText().trim();
                String email = txtEmail.getText().trim();
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                // Establish connection to MySQL
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

                // Prepare SQL query
                String sql = "INSERT INTO user (userId, Name, Address, Mobile, Email, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);

                // Set the values
                pstmt.setInt(1, userId);
                pstmt.setString(2, name);
                pstmt.setString(3, address);
                pstmt.setString(4, mobile);
                pstmt.setString(5, email);
                pstmt.setString(6, username);
                pstmt.setString(7, password);

                // Execute the query
                pstmt.executeUpdate();

                // Success message
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                con.close();
                this.dispose();
                new LoginPage();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnExit) {
            this.dispose(); // Close the registration window
        }
    }

    public static void main(String[] args) {
        new RegisterWindow();
    }
}
