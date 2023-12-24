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
public class BalanceCheck extends JFrame implements ActionListener{
    String pinNumber;
    String cardNumber;
    JButton backBtn;
    public BalanceCheck(String pin, String cardNo){
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

        JLabel label = new JLabel("Check Your Account Balance");
        label.setFont(new Font("Raleway",Font.BOLD,16));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        
        int balance = 0;
         try {
             JDBCConn connection = new JDBCConn();

             String query = String.format("select * from bank where cardNumber = '%s'",cardNumber);
             ResultSet resultSet = connection.statement.executeQuery(query);

             while (resultSet.next()) {
                 if (resultSet.getString("type").equals("Deposit")) {
                     balance += Integer.parseInt(resultSet.getString("amount"));//update balance for each deposite
                 }else{
                     balance -= Integer.parseInt(resultSet.getString("amount"));//update balance for each withdraw
                 }
             }
         } catch (SQLException se) {
             System.out.println(se.getMessage());
         }

        JLabel balanceLabel = new JLabel("Clear Balance");
        balanceLabel.setFont(new Font("Raleway",Font.BOLD,24));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(120,280,276,20);

        JLabel finalBalance = new JLabel(""+ balance + ".00");
        finalBalance.setFont(new Font("Raleway",Font.BOLD,36));
        finalBalance.setForeground(Color.GREEN);
        finalBalance.setBounds(120,360,276,40);
        finalBalance.setHorizontalAlignment(JLabel.CENTER);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway",Font.BOLD,16));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(185, 440, 130, 40);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(backBtn);
        backgroundLabel.add(balanceLabel);
        backgroundLabel.add(finalBalance);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
    }
}
