import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MiniStatement extends JFrame{
    String pinNumber;
    String cardNumber;
    public MiniStatement(String pin, String cardNumber){
        this.pinNumber = pin;
        this.cardNumber = cardNumber;
        this.setSize(400,620);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocation(20,50);
        this.getContentPane().setBackground(Color.WHITE);

        JLabel bankLabel = new JLabel("Universal Bank");
        bankLabel.setFont(new Font("Raleway",Font.BOLD,32));
        bankLabel.setForeground(Color.BLUE);
        bankLabel.setHorizontalAlignment(JLabel.CENTER);
        bankLabel.setVerticalAlignment(JLabel.CENTER);
        bankLabel.setBounds(20, 20, 360, 40);

        JLabel label = new JLabel("Mini Statement(Last 10 Transactions)");
        label.setFont(new Font("Raleway",Font.BOLD,18));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(20, 60, 360, 20);

        String cardNo = "0000-XXXX-XXXX-0000";

        JLabel cardLabel = new JLabel("Card No:");
        cardLabel.setFont(new Font("Raleway",Font.BOLD,16));
        cardLabel.setBounds(20, 100, 160, 20);

        JTextArea miniStatements = new JTextArea("");
        miniStatements.setFont(new Font("Raleway",Font.BOLD,16));
        miniStatements.setBounds(0, 140, 400, 400);
        // miniStatements.setBackground(Color.GREEN);
        miniStatements.setEditable(false);

        int balance = 0;

        JLabel balanceLabel = new JLabel("Clear Balance :");
        balanceLabel.setFont(new Font("Raleway",Font.BOLD,16));
        balanceLabel.setBounds(20, 540, 160, 30);
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            JDBCConn connection = new JDBCConn();
            String query1 = String.format("select cardNumber from login where pinNumber = '%s'", pinNumber);
            String query2 = String.format("select * from bank where pinNumber = '%s' order by date desc limit 10 ", pinNumber);
            String query3 = String.format("select * from bank where pinNumber = '%s'",pinNumber);


            ResultSet rsQ1 = connection.statement.executeQuery(query1);
            if (rsQ1.next()) {
                cardNo = rsQ1.getString("cardNumber");
            }
            ResultSet rsQ2 = connection.statement.executeQuery(query2);

            while (rsQ2.next()) {
                miniStatements.setText(miniStatements.getText() + rsQ2.getString("date")+ " | " + rsQ2.getString("type")+ " | "  + rsQ2.getString("amount") + " |\n\n");
            }
            ResultSet rsQ3 = connection.statement.executeQuery(query3);
            while (rsQ3.next()) {
                if (rsQ3.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rsQ3.getString("amount"));//update balance for each deposite
                }else{
                    balance -= Integer.parseInt(rsQ3.getString("amount"));//update balance for each withdraw
                }
            }
            
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        JLabel accountCardNumber = new JLabel(String.format("%s-XXXX-XXXX-%s",cardNo.substring(0,4),cardNo.substring(12)));
        accountCardNumber.setFont(new Font("Raleway",Font.BOLD,16));
        accountCardNumber.setBounds(180, 100, 200, 20);

        JLabel finalBalance = new JLabel("" + balance + ".00");
        finalBalance.setFont(new Font("Raleway",Font.BOLD,24));
        finalBalance.setForeground(Color.GREEN);
        finalBalance.setBounds(180, 540, 160, 30);
          
        this.add(bankLabel);
        this.add(label);
        this.add(cardLabel);
        this.add(accountCardNumber);
        this.add(miniStatements);
        this.add(balanceLabel);
        this.add(finalBalance);
    }
}
