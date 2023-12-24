import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ATMUserInterface extends JFrame implements ActionListener{
    JButton deposit, withdrawl,pinChange,balanceCheck,miniStatement,fastCash,exitBtn;
    String pinNumber;
    String cardNumber;
    public ATMUserInterface(String pin, String cardNo){
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

        JLabel label = new JLabel("Please select your Transaction");
        label.setFont(new Font("Raleway",Font.BOLD,16));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        deposit = new JButton("Deposit");
        deposit.setFont(new Font("Raleway",Font.BOLD,16));
        deposit.setBackground(Color.WHITE);
        deposit.setForeground(Color.BLACK);
        deposit.setBounds(120, 260, 130, 40);
        deposit.setFocusable(false);
        deposit.addActionListener(this);

        withdrawl = new JButton("Withdraw");
        withdrawl.setFont(new Font("Raleway",Font.BOLD,16));
        withdrawl.setBackground(Color.WHITE);
        withdrawl.setForeground(Color.BLACK);
        withdrawl.setBounds(266, 260, 130, 40);
        withdrawl.setFocusable(false);
        withdrawl.addActionListener(this);

        fastCash = new JButton("Fast Cash");
        fastCash.setFont(new Font("Raleway",Font.BOLD,16));
        fastCash.setBackground(Color.WHITE);
        fastCash.setForeground(Color.BLACK);
        fastCash.setBounds(120, 320, 130, 40);
        fastCash.setFocusable(false);
        fastCash.addActionListener(this);

        miniStatement = new JButton("E-Statement");
        miniStatement.setFont(new Font("Raleway",Font.BOLD,16));
        miniStatement.setBackground(Color.WHITE);
        miniStatement.setForeground(Color.BLACK);
        miniStatement.setBounds(266, 320, 130, 40);
        miniStatement.setFocusable(false);
        miniStatement.addActionListener(this);

        pinChange = new JButton("Pin Change");
        pinChange.setFont(new Font("Raleway",Font.BOLD,16));
        pinChange.setBackground(Color.WHITE);
        pinChange.setForeground(Color.BLACK);
        pinChange.setBounds(120, 380, 130, 40);
        pinChange.setFocusable(false);
        pinChange.addActionListener(this);

        balanceCheck = new JButton("Balance");
        balanceCheck.setFont(new Font("Raleway",Font.BOLD,16));
        balanceCheck.setBackground(Color.WHITE);
        balanceCheck.setForeground(Color.BLACK);
        balanceCheck.setBounds(266, 380, 130, 40);
        balanceCheck.setFocusable(false);
        balanceCheck.addActionListener(this);

        exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Raleway",Font.BOLD,16));
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setForeground(Color.BLACK);
        exitBtn.setBounds(185, 440, 130, 40);
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(deposit);
        backgroundLabel.add(withdrawl);
        backgroundLabel.add(fastCash);
        backgroundLabel.add(miniStatement);
        backgroundLabel.add(pinChange);
        backgroundLabel.add(balanceCheck);
        backgroundLabel.add(exitBtn);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitBtn) {
            System.exit(0);
        }else if (e.getSource()==deposit) {
            this.setVisible(false);
            new Deposit(pinNumber,cardNumber).setVisible(true);
        }else if (e.getSource()==withdrawl ) {
            this.setVisible(false);
            new Withdrawl(pinNumber,cardNumber).setVisible(true);
        }else if (e.getSource()==fastCash) {
            this.setVisible(false);
            new FastCash(pinNumber,cardNumber).setVisible(true);
        }else if (e.getSource()==pinChange) {
            this.setVisible(false);
            new PinChange(pinNumber,cardNumber).setVisible(true);
        }else if (e.getSource()==balanceCheck) {
            this.setVisible(false);
            new BalanceCheck(pinNumber,cardNumber).setVisible(true);
        }else if (e.getSource()==miniStatement) {
            new MiniStatement(pinNumber,cardNumber).setVisible(true);
        }
    }
}
