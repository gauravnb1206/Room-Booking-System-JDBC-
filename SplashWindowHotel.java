import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashWindowHotel extends JFrame implements ActionListener {
    JLabel lblshow;
    JButton btnnext;

    public SplashWindowHotel() {
        // Create label to display image
        lblshow = new JLabel();
        lblshow.setHorizontalAlignment(SwingConstants.CENTER);

        // Load the image and scale it to fit the label's size
        ImageIcon imageIcon = new ImageIcon("D:\\Java Practice\\HotelManagementSystem\\room.jpg");
        Image image = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH); // Scale image to fit
        lblshow.setIcon(new ImageIcon(image)); // Set the scaled image

        // Create the "Next" button
        btnnext = new JButton("Welcome to our Room Booking System");
        btnnext.setForeground(new Color(0, 0, 0));
        btnnext.setBackground(new Color(222, 222, 239));
        btnnext.setFont(new Font("Sanskrit Text", Font.BOLD | Font.ITALIC, 20));
        
        // Set layout for the frame
        getContentPane().setLayout(null);

        // Add components to the frame
        lblshow.setBounds(50, 24, 450, 285); // Set bounds for the label (adjust accordingly)
        btnnext.setBounds(22, 344, 491, 59); // Set bounds for the button (adjust accordingly)
        
        getContentPane().add(lblshow);
        getContentPane().add(btnnext);
        
        // Action listener for button click
        btnnext.addActionListener(this);

        // Frame specifications
        setSize(550, 450);  // Adjust frame size as needed
        setVisible(true);
        setTitle("Hotel Management System");
        setLocationRelativeTo(null);  // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the app when the window is closed
    }

    public static void main(String[] args) {
        // Create and show the splash window
        new SplashWindowHotel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Close the splash window and open the login page
        this.dispose();
        new LoginPage();  // Make sure the LoginPage class is correctly implemented
    }
}
