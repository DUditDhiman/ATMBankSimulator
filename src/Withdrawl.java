import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener{
    JTextField withdraw;
    JButton withdrawBtn, backBtn;
    String pinNumber;
    String cardNumber;
    public Withdrawl(String pin, String cardNo){
        this.pinNumber = pin;
        this.cardNumber = cardNo;
        this.setSize(700,700);
        this.setLocation(300,0);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("ATMUi.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon atmScaledImage = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(atmScaledImage);
        backgroundLabel.setBounds(0,0,700,700);

        JLabel label = new JLabel("Withdrawl Money");
        label.setFont(new Font("Raleway",Font.BOLD,18));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,20);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JLabel withdrawlLabel = new JLabel("Enter Amount To Withdraw");
        withdrawlLabel.setFont(new Font("Raleway",Font.BOLD,16));
        withdrawlLabel.setForeground(Color.WHITE);
        withdrawlLabel.setBounds(114,240,288,20);
        withdrawlLabel.setHorizontalAlignment(JLabel.CENTER);

        withdraw = new JTextField();
        withdraw.setFont(new Font("Raleway",Font.BOLD,36));
        withdraw.setForeground(Color.RED);
        withdraw.setBackground(Color.BLACK);
        withdraw.setCaretColor(Color.WHITE);
        withdraw.setHorizontalAlignment(JTextField.RIGHT);
        withdraw.setBounds(120,300,276,60);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway",Font.BOLD,16));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(120,420,120,40);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFont(new Font("Raleway",Font.BOLD,16));
        withdrawBtn.setBackground(Color.WHITE);
        withdrawBtn.setForeground(Color.BLACK);
        withdrawBtn.setBounds(278,420,120,40);
        withdrawBtn.setFocusable(false);
        withdrawBtn.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(withdrawlLabel);
        backgroundLabel.add(withdraw);
        backgroundLabel.add(withdrawBtn);
        backgroundLabel.add(backBtn);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawBtn) { 
                String withdrawAmount = withdraw.getText();
                Date date = new Date();
                if (withdrawAmount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter valid value for Withdraw Amount", "Missing", JOptionPane.INFORMATION_MESSAGE); 
                }else{
                    try {
                        JDBCConn connection = new JDBCConn();//Need to work on total balance

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
                        }else{
                            String query2 = String.format("insert into bank values('%s','%s','%s','%s','%s')",date,"Withdraw",withdrawAmount,cardNumber,pinNumber);

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
                    }catch (SQLException se) {
                    System.out.println(se.getMessage());   
                    }
                }    
            }else if(e.getSource() == backBtn){
                this.setVisible(false);
                new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
            }
    }
}

