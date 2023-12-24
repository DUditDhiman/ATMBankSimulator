import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

public class SignUpTwo extends JFrame implements ActionListener{
    JComboBox incomeField;
    JComboBox occupationField;
    JTextField panField;
    JTextField aadharField;
    JRadioButton yesSenior;
    JRadioButton noSenior;
    JRadioButton yesExist;
    JRadioButton noExist;
    JButton nextBtn;
    String uniqueFormNo;
    public SignUpTwo(String formNo){
        this.uniqueFormNo = formNo;

        this.setTitle("SignUp - For Account");
        this.setSize(600,520);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350,100);
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        JLabel formLabel = new JLabel("User Sign Up Form (Page - 2)"); 
        formLabel.setFont(new Font("Raleway",Font.BOLD,24));
        formLabel.setBounds(100, 10, 400, 40);
        formLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel pageLabel = new JLabel("Additional Details");
        pageLabel.setFont(new Font("Raleway",Font.BOLD,20));
        pageLabel.setBounds(200, 60, 200, 20);
        pageLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel incomeLabel = new JLabel("Income");
        incomeLabel.setFont(new Font("Raleway",Font.BOLD,18));
        incomeLabel.setBounds(50,100,200,25);

        String [] incomeValues = {"Null(Dependent)","0 - 2L","2L - 5L","5L - 7L","Up to 10L"};
        incomeField = new JComboBox(incomeValues);
        incomeField.setFont(new Font("Raleway",Font.PLAIN,18));
        incomeField.setBounds(300,100,200,25);
        incomeField.setBackground(Color.WHITE);

        JLabel occupationLabel = new JLabel("Occupation");
        occupationLabel.setFont(new Font("Raleway",Font.BOLD,18));
        occupationLabel.setBounds(50,140,200,25);

        String [] occupationValues = {"Salaried","Self Employed","Student","Retired","Other"};
        occupationField = new JComboBox(occupationValues);
        occupationField.setFont(new Font("Raleway",Font.PLAIN,18));
        occupationField.setBounds(300,140,200,25);
        occupationField.setBackground(Color.WHITE);

        JLabel panLabel = new JLabel("PAN Number");
        panLabel.setFont(new Font("Raleway",Font.BOLD,18));
        panLabel.setBounds(50,180,200,25);

        panField = new JTextField();
        panField.setFont(new Font("Raleway",Font.PLAIN,18));
        panField.setBounds(300,180,200,25);

        JLabel aadharLabel = new JLabel("Aadhar Number");
        aadharLabel.setFont(new Font("Raleway",Font.BOLD,18));
        aadharLabel.setBounds(50,220,200,25);

        aadharField = new JTextField();
        aadharField.setFont(new Font("Raleway",Font.PLAIN,18));
        aadharField.setBounds(300,220,200,25);

        JLabel seniorLabel = new JLabel("Senior Citizen");
        seniorLabel.setFont(new Font("Raleway",Font.BOLD,18));
        seniorLabel.setBounds(50,260,200,25);

        yesSenior = new JRadioButton("Yes");
        yesSenior.setFont(new Font("Raleway",Font.PLAIN,16));
        yesSenior.setBounds(300,260,80,25);

        noSenior = new JRadioButton("No");
        noSenior.setFont(new Font("Raleway",Font.PLAIN,16));
        noSenior.setBounds(400,260,80,25);

        ButtonGroup seniorField = new ButtonGroup();
        seniorField.add(yesSenior);
        seniorField.add(noSenior);

        JLabel existingLabel = new JLabel("Existing Account");
        existingLabel.setFont(new Font("Raleway",Font.BOLD,18));
        existingLabel.setBounds(50,300,200,25);

        yesExist = new JRadioButton("Yes");
        yesExist.setFont(new Font("Raleway",Font.PLAIN,16));
        yesExist.setBounds(300,300,80,25);

        noExist = new JRadioButton("No");
        noExist.setFont(new Font("Raleway",Font.PLAIN,16));
        noExist.setBounds(400,300,80,25);

        ButtonGroup existingField = new ButtonGroup();
        existingField.add(yesExist);
        existingField.add(noExist);

        nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("Raleway",Font.BOLD,20));
        nextBtn.setBounds(250,360,100,45);
        nextBtn.setFocusable(false);
        nextBtn.addActionListener(this);

        this.add(formLabel);
        this.add(pageLabel);
        this.add(incomeLabel);
        this.add(occupationLabel);
        this.add(panLabel);
        this.add(aadharLabel);
        this.add(seniorLabel);
        this.add(existingLabel);
        this.add(incomeField);
        this.add(occupationField);
        this.add(panField);
        this.add(aadharField);
        this.add(yesSenior);
        this.add(noSenior);
        this.add(yesExist);
        this.add(noExist);
        this.add(nextBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String income = incomeField.getSelectedItem().toString();
        String occupation = occupationField.getSelectedItem().toString();
        String panNumber = panField.getText();
        String aadharNumber = aadharField.getText();
        String seniorCitizen = null;
        if (yesSenior.isSelected()) {
            seniorCitizen = "Yes";
        }else if (noSenior.isSelected()) {
            seniorCitizen = "No";
        }
        String accountExist = null;
        if (yesExist.isSelected()) {
            accountExist = "Yes";
        }else if(noExist.isSelected()){
            accountExist = "No";
        }

        try {
            if (income.equals("")) {
                displayError("Income");
            }else if (occupation.equals("")) {
                displayError("Occupation");
            }else if (panNumber.equals("")) {
                displayError("PAN Number");
            }else if (aadharNumber.length()<14) {
                displayError("Aadhar Number");
            }else if (seniorCitizen == null) {
                displayError("Senior Citizen");
            }else if (accountExist == null) {
                displayError("Account Exist");
            }else{
                JDBCConn connection = new JDBCConn();
                String query = String.format("insert into signuptwo values('%s','%s','%s','%s','%s','%s','%s')", uniqueFormNo,income,occupation,panNumber,aadharNumber,seniorCitizen,accountExist);
                int row_affected = connection.statement.executeUpdate(query);

                if (row_affected>0) {
                    JOptionPane.showMessageDialog(null, "Details Added Succesfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new SignUpThree(uniqueFormNo).setVisible(true);
                }else{
                   JOptionPane.showMessageDialog(null, "DataBase Error", "Not Saved", JOptionPane.ERROR_MESSAGE); 
                }
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    public void displayError(String field){
        String message =  String.format("Enter valid value for '%s'",field);
        JOptionPane.showMessageDialog(null, message, "Missing Detail", JOptionPane.ERROR_MESSAGE);
    }
}
