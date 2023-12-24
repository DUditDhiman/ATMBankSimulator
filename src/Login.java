import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{
    JTextField cardNo;
    JPasswordField pin;
    JButton signIn;
    JButton signUp;
    JButton clear;
    public Login(){
        this.setTitle("Automated Teller Machine");
        this.setSize(800,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocation(350,200);
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Welcome To ATM - Universal Bank");
        nameLabel.setBounds(100,20,600,50);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        nameLabel.setForeground(Color.BLUE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Raleway",Font.BOLD,34));

        JLabel cardNoLable = new JLabel();
        cardNoLable.setText("Card No.");
        cardNoLable.setBounds(150,100,150,45);
        cardNoLable.setFont(new Font("Raleway",Font.BOLD,26));

        cardNo = new JTextField();
        cardNo.setBounds(350, 100, 300, 45);
        cardNo.setFont(new Font("Raleway",Font.BOLD,26));
        cardNo.setText("504093604574");

        JLabel pinLable = new JLabel();
        pinLable.setText("PIN");
        pinLable.setBounds(150,160,150,45);
        pinLable.setFont(new Font("Raleway",Font.BOLD,26));

        pin = new JPasswordField();
        pin.setBounds(350, 160, 300, 45);
        pin.setFont(new Font("Raleway",Font.BOLD,26));

        signIn = new JButton("SIGN IN");
        signIn.setBounds(220, 220, 150, 45);
        signIn.setBackground(Color.BLACK);
        signIn.setForeground(Color.WHITE);
        signIn.setFont(new Font("Raleway",Font.BOLD,20));
        signIn.setFocusable(false);
        signIn.addActionListener(this);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 220, 150, 45);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setFont(new Font("Raleway",Font.BOLD,20));
        clear.setFocusable(false);
        clear.addActionListener(this);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(220, 280, 360, 45);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.setFont(new Font("Raleway",Font.BOLD,20));
        signUp.setFocusable(false);
        signUp.addActionListener(this);

        this.add(nameLabel);
        this.add(cardNoLable);
        this.add(pinLable);
        this.add(cardNo);
        this.add(pin);
        this.add(signIn);
        this.add(clear);
        this.add(signUp);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            cardNo.setText("504093604574");
            pin.setText("");
        }else if(e.getSource() == signIn){
            try {
                JDBCConn connection = new JDBCConn();
                String cardNumber = cardNo.getText();
                String pinNumber = pin.getText();
                String query = String.format("select * from login where cardNumber = '%s' and pinNumber = '%s'", cardNumber, pinNumber);

                ResultSet resultSet = connection.statement.executeQuery(query);
                if (resultSet.next()) {
                    this.setVisible(false);
                    new ATMUserInterface(pinNumber,cardNumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Credentials are not matched", "User Not find", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }else if(e.getSource() == signUp){
            this.setVisible(false);
            new SignUpOne().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Login();
    }

}