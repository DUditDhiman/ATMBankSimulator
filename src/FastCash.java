import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Color;
public class FastCash extends JFrame implements ActionListener{
    JButton hundredBtn, fiveHundredBtn, oneKBtn, twoKFiveHundredBtn, fiveKBtn,tenKBtn,backBtn;
    String pinNumber;
    String cardNumber;
    public FastCash(String pin, String cardNo){
        this.pinNumber = pin;
        this.cardNumber = cardNo;
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("ATMUi.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon atmScaledImage = new ImageIcon(scaledImage);

        this.setSize(700,700);
        this.setLocation(300,0);
        this.setLayout(null);

        JLabel backgroundLabel = new JLabel(atmScaledImage);
        backgroundLabel.setBounds(0,0,700,700);

        JLabel label = new JLabel("Select Withdrawl Amount");
        label.setFont(new Font("Raleway",Font.BOLD,16));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        hundredBtn = new JButton("100 Rs.");
        hundredBtn.setFont(new Font("Raleway",Font.BOLD,16));
        hundredBtn.setBackground(Color.WHITE);
        hundredBtn.setForeground(Color.BLACK);
        hundredBtn.setBounds(120, 260, 130, 40);
        hundredBtn.setFocusable(false);
        hundredBtn.addActionListener(this);

        fiveHundredBtn = new JButton("500 Rs.");
        fiveHundredBtn.setFont(new Font("Raleway",Font.BOLD,16));
        fiveHundredBtn.setBackground(Color.WHITE);
        fiveHundredBtn.setForeground(Color.BLACK);
        fiveHundredBtn.setBounds(266, 260, 130, 40);
        fiveHundredBtn.setFocusable(false);
        fiveHundredBtn.addActionListener(this);

        oneKBtn = new JButton("1000 Rs.");
        oneKBtn.setFont(new Font("Raleway",Font.BOLD,16));
        oneKBtn.setBackground(Color.WHITE);
        oneKBtn.setForeground(Color.BLACK);
        oneKBtn.setBounds(120, 320, 130, 40);
        oneKBtn.setFocusable(false);
        oneKBtn.addActionListener(this);

        twoKFiveHundredBtn = new JButton("2500 Rs.");
        twoKFiveHundredBtn.setFont(new Font("Raleway",Font.BOLD,16));
        twoKFiveHundredBtn.setBackground(Color.WHITE);
        twoKFiveHundredBtn.setForeground(Color.BLACK);
        twoKFiveHundredBtn.setBounds(266, 320, 130, 40);
        twoKFiveHundredBtn.setFocusable(false);
        twoKFiveHundredBtn.addActionListener(this);

        fiveKBtn = new JButton("5000 Rs.");
        fiveKBtn.setFont(new Font("Raleway",Font.BOLD,16));
        fiveKBtn.setBackground(Color.WHITE);
        fiveKBtn.setForeground(Color.BLACK);
        fiveKBtn.setBounds(120, 380, 130, 40);
        fiveKBtn.setFocusable(false);
        fiveKBtn.addActionListener(this);

        tenKBtn = new JButton("10000 Rs.");
        tenKBtn.setFont(new Font("Raleway",Font.BOLD,16));
        tenKBtn.setBackground(Color.WHITE);
        tenKBtn.setForeground(Color.BLACK);
        tenKBtn.setBounds(266, 380, 130, 40);
        tenKBtn.setFocusable(false);
        tenKBtn.addActionListener(this);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway",Font.BOLD,16));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(185, 440, 130, 40);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(hundredBtn);
        backgroundLabel.add(fiveHundredBtn);
        backgroundLabel.add(oneKBtn);
        backgroundLabel.add(twoKFiveHundredBtn);
        backgroundLabel.add(fiveKBtn);
        backgroundLabel.add(tenKBtn);
        backgroundLabel.add(backBtn);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String withdrawAmount = null;
        Date date = new Date();
        if (e.getSource() == hundredBtn) {
            withdrawAmount = "100";
        }else if (e.getSource() == fiveHundredBtn) {
            withdrawAmount = "500";
        }else if (e.getSource() == oneKBtn) {
            withdrawAmount = "1000";
        }else if (e.getSource() == twoKFiveHundredBtn) {
            withdrawAmount = "2500";
        }else if (e.getSource() == fiveKBtn) {
            withdrawAmount = "5000";
        }else if (e.getSource() == tenKBtn) {
            withdrawAmount = "10000";
        }else if (e.getSource() == backBtn) {
            this.setVisible(false);
            new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
        }
        try {
            JDBCConn connection = new JDBCConn();

            String query1 = String.format("select * from bank where cardNumber = '%s'",cardNumber);
            ResultSet resultSet = connection.statement.executeQuery(query1);

            int balance = 0;

            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));//update balance for each deposite
                }else{
                    balance -= Integer.parseInt(resultSet.getString("amount"));//update balance for each withdraw
                }
            }

            if (balance < Integer.parseInt(withdrawAmount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance", "Not Possible", JOptionPane.ERROR_MESSAGE);
                this.setVisible(false);
                new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
            }else{
                String query2 = String.format("insert into bank values('%s','%s','%s','%s','%s')",date,"FashCash",withdrawAmount,cardNumber,pinNumber);

                int row_affected = connection.statement.executeUpdate(query2);

                if (row_affected > 0) {
                    String message = String.format("Rs %s Debited Successfully", withdrawAmount);
                    JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "DataBase Error", "Not Saved", JOptionPane.ERROR_MESSAGE); 
                }
            }   
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
