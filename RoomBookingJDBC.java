import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class RoomBookingJDBC extends JFrame implements ActionListener {
    JLabel lblBookingID, lblCustomerName, lblRoomType, lblCheckInDate, lblCheckOutDate, lblTotalAmount, lblMessage;
    JTextField txtBookingID, txtCustomerName, txtRoomType, txtTotalAmount;
    JDateChooser dateChooserCheckIn, dateChooserCheckOut;
    JButton btnInsert, btnUpdate, btnDelete, btnShow, btnExit;
    JPanel panelForm, panelButtons, panelMessage;
    private JLabel lblNewLabel;

    public RoomBookingJDBC() {
    	getContentPane().setBackground(new Color(255, 0, 0));
        // Labels and Text Fields
        lblBookingID = new JLabel("Booking ID:");
        lblBookingID.setForeground(new Color(255, 255, 255));
        lblBookingID.setHorizontalAlignment(SwingConstants.CENTER);
        lblCustomerName = new JLabel("Customer Name:");
        lblCustomerName.setForeground(new Color(255, 255, 255));
        lblCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
        lblRoomType = new JLabel("Room Type:");
        lblRoomType.setForeground(new Color(255, 255, 255));
        lblRoomType.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheckInDate = new JLabel("Check-In Date:");
        lblCheckInDate.setForeground(new Color(255, 255, 255));
        lblCheckInDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheckOutDate = new JLabel("Check-Out Date:");
        lblCheckOutDate.setForeground(new Color(255, 255, 255));
        lblCheckOutDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalAmount = new JLabel("Total Amount:");
        lblTotalAmount.setForeground(new Color(255, 255, 255));
        lblTotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage = new JLabel(" ");
        lblMessage.setForeground(new Color(255, 255, 255));
        lblMessage.setBackground(new Color(0, 64, 128));

        txtBookingID = new JTextField(20);
        txtCustomerName = new JTextField(20);
        txtRoomType = new JTextField(20);
        txtTotalAmount = new JTextField(20);

        // JDateChooser for Date Selection
        dateChooserCheckIn = new JDateChooser();
        dateChooserCheckIn.setBackground(new Color(255, 255, 255));
        dateChooserCheckIn.setForeground(new Color(0, 0, 0));
        dateChooserCheckIn.setDateFormatString("yyyy-MM-dd");
        dateChooserCheckOut = new JDateChooser();
        dateChooserCheckOut.setDateFormatString("yyyy-MM-dd");

        // Buttons
        btnInsert = new JButton("Insert");
        btnInsert.setForeground(new Color(255, 255, 255));
        btnInsert.setBackground(new Color(102, 179, 255));
        btnUpdate = new JButton("Update");
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setBackground(new Color(85, 170, 255));
        btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(85, 170, 255));
        btnShow = new JButton("Show");
        btnShow.setForeground(new Color(255, 255, 255));
        btnShow.setBackground(new Color(85, 170, 255));
        btnExit = new JButton("Exit");
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(85, 170, 255));

        // Add action listeners
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnShow.addActionListener(this);
        btnExit.addActionListener(this);

        // Panels
        panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setForeground(new Color(255, 255, 255));
        panelForm.setBackground(new Color(0, 64, 128));
        panelButtons = new JPanel(new FlowLayout());
        panelButtons.setBackground(new Color(255, 255, 255));
        panelMessage = new JPanel();
        panelMessage.setForeground(new Color(255, 255, 255));
        panelMessage.setBackground(new Color(0, 64, 128));

        // Add components to panels
        panelForm.add(lblBookingID);
        panelForm.add(txtBookingID);
        panelForm.add(lblCustomerName);
        panelForm.add(txtCustomerName);
        panelForm.add(lblRoomType);
        panelForm.add(txtRoomType);
        panelForm.add(lblCheckInDate);
        panelForm.add(dateChooserCheckIn);
        panelForm.add(lblCheckOutDate);
        panelForm.add(dateChooserCheckOut);
        panelForm.add(lblTotalAmount);
        panelForm.add(txtTotalAmount);

        panelButtons.add(btnInsert);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnShow);
        panelButtons.add(btnExit);
        
        lblNewLabel = new JLabel("Room Booking System");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelMessage.add(lblNewLabel);

        panelMessage.add(lblMessage);

        // Frame setup
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelForm, BorderLayout.CENTER);
        getContentPane().add(panelButtons, BorderLayout.SOUTH);
        getContentPane().add(panelMessage, BorderLayout.NORTH);

        setTitle("Room Booking Management");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

            // Date format for JDateChooser
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (e.getSource() == btnInsert) {
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO room_booking (customer_name, room_type, check_in_date, check_out_date, total_amount) VALUES (?, ?, ?, ?, ?)");
                pstmt.setString(1, txtCustomerName.getText());
                pstmt.setString(2, txtRoomType.getText());
                pstmt.setString(3, sdf.format(dateChooserCheckIn.getDate()));
                pstmt.setString(4, sdf.format(dateChooserCheckOut.getDate()));
                pstmt.setDouble(5, Double.parseDouble(txtTotalAmount.getText()));
                pstmt.executeUpdate();
                lblMessage.setText("Record Inserted Successfully!");
            } else if (e.getSource() == btnUpdate) {
                PreparedStatement pstmt = con.prepareStatement(
                        "UPDATE room_booking SET customer_name = ?, room_type = ?, check_in_date = ?, check_out_date = ?, total_amount = ? WHERE booking_id = ?");
                pstmt.setString(1, txtCustomerName.getText());
                pstmt.setString(2, txtRoomType.getText());
                pstmt.setString(3, sdf.format(dateChooserCheckIn.getDate()));
                pstmt.setString(4, sdf.format(dateChooserCheckOut.getDate()));
                pstmt.setDouble(5, Double.parseDouble(txtTotalAmount.getText()));
                pstmt.setInt(6, Integer.parseInt(txtBookingID.getText()));
                pstmt.executeUpdate();
                lblMessage.setText("Record Updated Successfully!");
            } else if (e.getSource() == btnDelete) {
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM room_booking WHERE booking_id = ?");
                pstmt.setInt(1, Integer.parseInt(txtBookingID.getText()));
                int rowsAffected = pstmt.executeUpdate();
                lblMessage.setText(rowsAffected > 0 ? "Record Deleted Successfully!" : "Record Not Found!");
            } else if (e.getSource() == btnShow) {
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM room_booking WHERE booking_id = ?");
                pstmt.setInt(1, Integer.parseInt(txtBookingID.getText()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtCustomerName.setText(rs.getString("customer_name"));
                    txtRoomType.setText(rs.getString("room_type"));
                    dateChooserCheckIn.setDate(rs.getDate("check_in_date"));
                    dateChooserCheckOut.setDate(rs.getDate("check_out_date"));
                    txtTotalAmount.setText(String.valueOf(rs.getDouble("total_amount")));
                    lblMessage.setText("Record Found!");
                } else {
                    lblMessage.setText("Record Not Found!");
                }
            } else if (e.getSource() == btnExit) {
                System.exit(0);
            }
            con.close();
        } catch (Exception ex) {
            lblMessage.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomBookingJDBC());
    }
}
