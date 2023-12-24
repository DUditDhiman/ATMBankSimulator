import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Color;
public class PinChange extends JFrame implements ActionListener {
    JPasswordField newPin, confirmNewPin;
    JButton save, back;
    String oldPinNumber;
    String cardNumber;
    public PinChange(String pin, String cardNo){
        this.oldPinNumber = pin;
        this.cardNumber = cardNo;
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("ATMUi.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon atmScaledImage = new ImageIcon(scaledImage);

        this.setSize(700,700);
        this.setLocation(300,0);
        this.setLayout(null);

        JLabel backgroundLabel = new JLabel(atmScaledImage);
        backgroundLabel.setBounds(0,0,700,700);

        JLabel label = new JLabel("Change Your Pin");
        label.setFont(new Font("Raleway",Font.BOLD,16));
        label.setForeground(Color.WHITE);
        label.setBounds(114,200,288,40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JLabel newPinLabel = new JLabel("Enter New Pin:");
        newPinLabel.setFont(new Font("Raleway",Font.BOLD,16));
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setBounds(120, 260, 276, 20);

        newPin = new JPasswordField();
        newPin.setFont(new Font("Raleway",Font.BOLD,24));
        newPin.setForeground(Color.BLACK);
        newPin.setBounds(120,300,276,30);

        JLabel confirmPinLabel = new JLabel("Re-Enter New Pin:");
        confirmPinLabel.setFont(new Font("Raleway",Font.BOLD,16));
        confirmPinLabel.setForeground(Color.WHITE);
        confirmPinLabel.setBounds(120,340, 276, 20);

        confirmNewPin = new JPasswordField();
        confirmNewPin.setFont(new Font("Raleway",Font.BOLD,24));
        confirmNewPin.setForeground(Color.BLACK);
        confirmNewPin.setBounds(120,380,276,30);

        save = new JButton("Save");
        save.setFont(new Font("Raleway",Font.BOLD,16));
        save.setBackground(Color.WHITE);
        save.setForeground(Color.BLACK);
        save.setBounds(266, 430, 130, 40);
        save.setFocusable(false);
        save.addActionListener(this);

        back = new JButton("Back");
        back.setFont(new Font("Raleway",Font.BOLD,16));
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setBounds(120, 430, 130, 40);
        back.setFocusable(false);
        back.addActionListener(this);

        this.add(backgroundLabel);
        backgroundLabel.add(label);
        backgroundLabel.add(newPinLabel);
        backgroundLabel.add(newPin);
        backgroundLabel.add(confirmPinLabel);
        backgroundLabel.add(confirmNewPin);
        backgroundLabel.add(save);
        backgroundLabel.add(back);
        this.setUndecorated(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
            new ATMUserInterface(oldPinNumber,cardNumber).setVisible(true);
        }else if (e.getSource() == save) {
            String newPinNumber = newPin.getText();
            String confirmNewPinNumber = confirmNewPin.getText();

            if (!newPin.getText().equals("") && newPinNumber.equals(confirmNewPinNumber)) {
                try {
                    JDBCConn connection = new JDBCConn();
                    String query1 = String.format("update bank set pinNumber = '%s' where cardNumber = '%s'",newPinNumber,cardNumber);
                    String query2 = String.format("update login set pinNumber = '%s' where cardNumber = '%s'",newPinNumber,cardNumber);
                    String query3 = String.format("update signupthree set pinNumber = '%s' where cardNumber = '%s'",newPinNumber,cardNumber);

                    int row_affected_query1 = connection.statement.executeUpdate(query1);
                    int row_affected_query2 = connection.statement.executeUpdate(query2);
                    int row_affected_query3 = connection.statement.executeUpdate(query3);

                    if (row_affected_query1 > 0 && row_affected_query2 > 0 && row_affected_query3 > 0) {
                        JOptionPane.showMessageDialog(null, "PIN Changed Successfully", "Changes Saved", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        new ATMUserInterface(newPinNumber,cardNumber).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "DataBase Error", "Not Saved", JOptionPane.ERROR_MESSAGE); 
                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null, "PIN Not Matched/Provide Valid value for PIN ", "Not Matched", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
}
