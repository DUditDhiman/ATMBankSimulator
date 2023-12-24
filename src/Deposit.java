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
import java.sql.SQLException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener{
    JTextField deposit;
    JButton depositBtn, backBtn;
    String pinNumber;
    String cardNumber;
    public Deposit(String pin, String cardNo){
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

        JLabel label = new JLabel("Deposite Money");
        label.setFont(new Font("Raleway",Font.BOLD,18));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,20);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JLabel depositLabel = new JLabel("Enter Amount To Deposit");
        depositLabel.setFont(new Font("Raleway",Font.BOLD,16));
        depositLabel.setForeground(Color.WHITE);
        depositLabel.setBounds(114,240,288,20);
        depositLabel.setHorizontalAlignment(JLabel.CENTER);

        deposit = new JTextField();
        deposit.setFont(new Font("Raleway",Font.BOLD,36));
        deposit.setForeground(Color.GREEN);
        deposit.setBackground(Color.BLACK);
        deposit.setCaretColor(Color.WHITE);
        deposit.setHorizontalAlignment(JTextField.RIGHT);
        deposit.setBounds(120,300,276,60);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway",Font.BOLD,16));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(120,420,120,40);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);

        depositBtn = new JButton("Deposit");
        depositBtn.setFont(new Font("Raleway",Font.BOLD,16));
        depositBtn.setBackground(Color.WHITE);
        depositBtn.setForeground(Color.BLACK);
        depositBtn.setBounds(278,420,120,40);
        depositBtn.setFocusable(false);
        depositBtn.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(depositLabel);
        backgroundLabel.add(deposit);
        backgroundLabel.add(depositBtn);
        backgroundLabel.add(backBtn);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositBtn) {
            String depositAmount = deposit.getText();
            Date date = new Date();
            if (depositAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter valid value for Deposit Amount", "Missing", JOptionPane.INFORMATION_MESSAGE); 
            }else{
                try {
                    JDBCConn connection = new JDBCConn();//Need to work on total balance
                    String query = String.format("insert into bank values('%s','%s','%s','%s','%s')",date,"Deposit",depositAmount,cardNumber,pinNumber);

                    int row_affected = connection.statement.executeUpdate(query);

                    if (row_affected > 0) {
                        String message = String.format("Rs %s Credited Successfully", depositAmount);
                        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "DataBase Error", "Not Saved", JOptionPane.ERROR_MESSAGE); 
                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());   
                }

            }

        }else if(e.getSource() == backBtn){
            this.setVisible(false);
            new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
        }
    }
}
