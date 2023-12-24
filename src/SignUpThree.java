import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;

public class SignUpThree extends JFrame implements ActionListener{
    JRadioButton saving, current,fixed, recurring;
    JCheckBox atm, netBank, mobBank, alert, checkbook, estatement, declare;
    JButton submit, cancel;
    String uniqueFormNo;
    public SignUpThree(String formNo){
        this.uniqueFormNo = formNo;

        this.setTitle("SignUp - For Account");
        this.setSize(600,620);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350,50);
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        JLabel formLabel = new JLabel("User Sign Up Form (Page - 3)"); 
        formLabel.setFont(new Font("Raleway",Font.BOLD,24));
        formLabel.setBounds(100, 10, 400, 45);
        formLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel pageLabel = new JLabel("Account Details");
        pageLabel.setFont(new Font("Raleway",Font.BOLD,20));
        pageLabel.setBounds(200, 60, 200, 25);
        pageLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel typeLabel = new JLabel("Account Type");
        typeLabel.setFont(new Font("Raleway",Font.BOLD,20));
        typeLabel.setBounds(50,100,200,25);

        saving = new JRadioButton("Saving Account");
        saving.setFont(new Font("Raleway",Font.BOLD,16));
        saving.setBounds(50,140,250,25);

        fixed = new JRadioButton("Fixed Deposite Account");
        fixed.setFont(new Font("Raleway",Font.BOLD,16));
        fixed.setBounds(320,140,250,25);

        current = new JRadioButton("Current Account");
        current.setFont(new Font("Raleway",Font.BOLD,16));
        current.setBounds(50,180,250,25);

        recurring = new JRadioButton("Recurring Account");
        recurring.setFont(new Font("Raleway",Font.BOLD,16));
        recurring.setBounds(320,180,250,25);

        ButtonGroup type = new ButtonGroup();
        type.add(saving);
        type.add(fixed);
        type.add(current);
        type.add(recurring);

        JLabel cardNoLabel = new JLabel("Card Number");
        cardNoLabel.setFont(new Font("Raleway",Font.BOLD,20));
        cardNoLabel.setBounds(50,220,200,25);

        JLabel cardNumber = new JLabel("XXXX-XXXX-XXXX-4152");
        cardNumber.setFont(new Font("Raleway",Font.BOLD,20));
        cardNumber.setBounds(270,220,250,25);

        JLabel cardDemo = new JLabel("(Dummy 16 Digit Card No)");
        cardDemo.setFont(new Font("Raleway",Font.BOLD,8));
        cardDemo.setBounds(270,240,250,15);


        JLabel pinLabel = new JLabel("Pin:");
        pinLabel.setFont(new Font("Raleway",Font.BOLD,20));
        pinLabel.setBounds(50,260,200,25);

        JLabel pin = new JLabel("****");
        pin.setFont(new Font("Raleway",Font.BOLD,20));
        pin.setBounds(270,260,100,25);

        JLabel pinDemo = new JLabel("(Dummy 4 Digit Pin)");
        pinDemo.setFont(new Font("Raleway",Font.BOLD,8));
        pinDemo.setBounds(270,280,250,15);

        JLabel servicLabel = new JLabel("Services Required:");
        servicLabel.setFont(new Font("Raleway",Font.BOLD,20));
        servicLabel.setBounds(50,310,100,25);

        atm = new JCheckBox("ATM Card");
        atm.setFont(new Font("Raleway",Font.BOLD,16));
        atm.setBounds(50, 350, 200, 25);

        netBank = new JCheckBox("Internet Banking");
        netBank.setFont(new Font("Raleway",Font.BOLD,16));
        netBank.setBounds(300, 350, 200, 25);

        mobBank = new JCheckBox("Mobile Banking");
        mobBank.setFont(new Font("Raleway",Font.BOLD,16));
        mobBank.setBounds(50, 390, 200, 25);

        alert = new JCheckBox("Email & SMS Alerts");
        alert.setFont(new Font("Raleway",Font.BOLD,16));
        alert.setBounds(300, 390, 200, 25);

        checkbook = new JCheckBox("CheckBook");
        checkbook.setFont(new Font("Raleway",Font.BOLD,16));
        checkbook.setBounds(50, 430, 200, 25);

        estatement = new JCheckBox("E-Statement");
        estatement.setFont(new Font("Raleway",Font.BOLD,16));
        estatement.setBounds(300, 430, 200, 25);

        declare = new JCheckBox("I hereby declare that the above entered details are correct to best of my knowledge.");
        declare.setFont(new Font("Raleway",Font.BOLD,12));
        declare.setBounds(50, 470, 500, 25);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Raleway",Font.BOLD,20));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(100,520,180,45);
        cancel.setFocusable(false);
        cancel.addActionListener(this);

        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway",Font.BOLD,20));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(320,520,180,45);
        submit.setFocusable(false);
        submit.addActionListener(this);
        
        this.add(formLabel);
        this.add(pageLabel);
        this.add(typeLabel);
        this.add(saving);
        this.add(fixed);
        this.add(current);
        this.add(recurring);
        this.add(cardNoLabel);
        this.add(cardNumber);
        this.add(cardDemo);
        this.add(pinLabel);
        this.add(pin);
        this.add(pinDemo);
        this.add(servicLabel);
        this.add(atm);
        this.add(netBank);
        this.add(mobBank);
        this.add(alert);
        this.add(checkbook);
        this.add(estatement);
        this.add(declare);
        this.add(cancel);
        this.add(submit);
        this.setUndecorated(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String accountType = null;
            if(saving.isSelected()){
                accountType = "Saving";
            }else if (current.isSelected()) {
                accountType = "Current";
            }else if (recurring.isSelected()) {
                accountType = "Recurring Deposite";
            }else if (fixed.isSelected()) {
                accountType = "Fixed Deposite";
            }

            Random random = new Random();
            String cardNumber = "" + Math.abs((random.nextLong() % 9000L) + 5040936045740000L);//16 Digit card number with starting digit 504093604574 and contcat with empty string to make it string

            String pinNumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            StringBuilder facility = new StringBuilder("");

            if (atm.isSelected()) {
                facility.append("ATM Card");
            }
            if (netBank.isSelected()) {
                facility.append(" Net Banking");
            }
            if (mobBank.isSelected()) {
                facility.append(" Mobile Banking");
            }
            if (alert.isSelected()) {
                facility.append(" SMS Alert");
            }
            if (checkbook.isSelected()) {
                facility.append(" Check Book");
            }
            if (estatement.isSelected()) {
                facility.append(" E-Statement");
            }

            try {
                if (accountType == null) {
                    JOptionPane.showMessageDialog(null, "Enter valid value for Account Type", "Missing Detail", JOptionPane.ERROR_MESSAGE);
                }else if (!declare.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You need to check declaration box to continue", "Important", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JDBCConn connection = new JDBCConn();
                    String query1 = String.format("insert into signupthree values('%s','%s','%s','%s','%s')", uniqueFormNo,accountType,cardNumber,pinNumber,facility);

                    String query2 = String.format("insert into login values('%s','%s','%s')", uniqueFormNo,cardNumber,pinNumber);
                    int row_affected_query1 = connection.statement.executeUpdate(query1);
                    int row_affected_query2 = connection.statement.executeUpdate(query2);

                    if (row_affected_query1 > 0 && row_affected_query2 > 0) {
                        JOptionPane.showMessageDialog(null, "Account Created Succesfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
                        String message = String.format("Card Number : %s \n Pin : %s",cardNumber,pinNumber);
                        JOptionPane.showMessageDialog(null, message, "Account Details", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        new Deposit(pinNumber,cardNumber).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "DataBase Error", "Not Saved", JOptionPane.ERROR_MESSAGE); 
                    }
                    }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }else if (e.getSource() == cancel) {
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new SignUpThree("");
    }
}
