import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SignUpOne extends JFrame implements ActionListener{
    JLabel formNo;
    Long random;
    JTextField nameField;
    JTextField fnameField;
    JDateChooser dobField;
    JRadioButton male;
    JRadioButton female;
    JRadioButton other;
    JRadioButton single;
    JRadioButton married;
    JTextField emailField;
    JTextField addressField;
    JTextField cityField;
    JTextField stateField;
    JTextField phoneField;
    ButtonGroup genderField;
    ButtonGroup maritalStatusField;
    JButton nextBtn;
    public SignUpOne(){
        this.setTitle("SignUp - For Account");
        this.setSize(600,600);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350,50);
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formLabel = new JLabel("User Sign Up Form No. " + random); 
        formLabel.setFont(new Font("Raleway",Font.BOLD,24));
        formLabel.setBounds(100, 10, 400, 40);
        formLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel pageLabel = new JLabel("Personal Details");
        pageLabel.setFont(new Font("Raleway",Font.BOLD,20));
        pageLabel.setBounds(200, 60, 200, 25);
        pageLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel nameLabel = new JLabel("Customer Name");
        nameLabel.setFont(new Font("Raleway",Font.BOLD,18));
        nameLabel.setBounds(50,100,200,25);

        nameField = new JTextField();
        nameField.setFont(new Font("Raleway",Font.PLAIN,18));
        nameField.setBounds(300,100,200,25);

        JLabel fnameLabel = new JLabel("Father's Name");
        fnameLabel.setFont(new Font("Raleway",Font.BOLD,18));
        fnameLabel.setBounds(50,140,200,25);

        fnameField = new JTextField();
        fnameField.setFont(new Font("Raleway",Font.PLAIN,18));
        fnameField.setBounds(300,140,200,25);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(new Font("Raleway",Font.BOLD,18));
        dobLabel.setBounds(50,180,200,25);

        dobField = new JDateChooser();
        dobField.setFont(new Font("Raleway",Font.PLAIN,18));
        dobField.setBounds(300,180,200,25);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Raleway",Font.BOLD,18));
        genderLabel.setBounds(50,220,200,25);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway",Font.PLAIN,16));
        male.setBounds(300,220,80,25);
        
        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway",Font.PLAIN,16));
        female.setBounds(400,220,80,25);

        other = new JRadioButton("Other");
        other.setFont(new Font("Raleway",Font.PLAIN,16));
        other.setBounds(500,220,80,25);

        genderField = new ButtonGroup();
        genderField.add(male);
        genderField.add(female);
        genderField.add(other);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Raleway",Font.BOLD,18));
        emailLabel.setBounds(50,260,200,25);

        emailField = new JTextField();
        emailField.setFont(new Font("Raleway",Font.PLAIN,18));
        emailField.setBounds(300,260,200,25);

        JLabel maritalStatusLabel = new JLabel("Marital Status");
        maritalStatusLabel.setFont(new Font("Raleway",Font.BOLD,18));
        maritalStatusLabel.setBounds(50,300,200,25);

        single = new JRadioButton("Single");
        single.setFont(new Font("Raleway",Font.PLAIN,16));
        single.setBounds(300,300,100,25);

        married = new JRadioButton("Married");
        married.setFont(new Font("Raleway",Font.PLAIN,16));
        married.setBounds(400,300,100,25);

        maritalStatusField = new ButtonGroup();
        maritalStatusField.add(single);
        maritalStatusField.add(married);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Raleway",Font.BOLD,18));
        addressLabel.setBounds(50,340,200,25);

        addressField = new JTextField();
        addressField.setFont(new Font("Raleway",Font.PLAIN,18));
        addressField.setBounds(300,340,200,25);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setFont(new Font("Raleway",Font.BOLD,18));
        cityLabel.setBounds(50,380,200,25);

        cityField = new JTextField();
        cityField.setFont(new Font("Raleway",Font.PLAIN,18));
        cityField.setBounds(300,380,200,25);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setFont(new Font("Raleway",Font.BOLD,18));
        stateLabel.setBounds(50,420,200,25);

        stateField = new JTextField();
        stateField.setFont(new Font("Raleway",Font.PLAIN,18));
        stateField.setBounds(300,420,200,25);

        JLabel phNoLabel = new JLabel("Phone No.");
        phNoLabel.setFont(new Font("Raleway",Font.BOLD,18));
        phNoLabel.setBounds(50,460,200,25);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Raleway",Font.PLAIN,18));
        phoneField.setBounds(300,460,200,25);

        nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("Raleway",Font.BOLD,20));
        nextBtn.setBounds(250,520,100,40);
        nextBtn.setFocusable(false);
        nextBtn.addActionListener(this);

        this.add(formLabel);
        this.add(pageLabel);
        this.add(nameLabel);
        this.add(fnameLabel);
        this.add(dobLabel);
        this.add(genderLabel);
        this.add(emailLabel);
        this.add(maritalStatusLabel);
        this.add(addressLabel);
        this.add(cityLabel);
        this.add(stateLabel);
        this.add(phNoLabel);
        this.add(nameField);
        this.add(fnameField);
        this.add(dobField);
        this.add(male);
        this.add(female);
        this.add(other);
        this.add(emailField);
        this.add(single);
        this.add(married);
        this.add(addressField);
        this.add(cityField);
        this.add(stateField);
        this.add(phoneField);
        this.add(nextBtn);
    }
     @Override
    public void actionPerformed(ActionEvent e) {
        String uniqueFormNo = random.toString(); 
        String name = nameField.getText();
        String fname = fnameField.getText();
        String gender = null;
        if(male.isSelected()){
            gender = "Male";
        }else if(female.isSelected()){
            gender = "Female";
        }else if(other.isSelected()){
            gender = "Other";
        }
        String email = emailField.getText();
        String dob = dobField.getDate().toString();
        String maritalStatus = null;
        if(single.isSelected()){
            maritalStatus = "Single";
        }else if(married.isSelected()){
            maritalStatus = "Married";
        }
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String phone  = phoneField.getText();

        try {
            if(name.equals("" )){
                displayError("Name");
            }else if (fname.equals("")) {
                displayError("Father's Name");
            }else if (gender == null) {
                displayError("Gender");
            }else if (email.equals("")) {
                displayError("Email");
            }else if (maritalStatus == null) {
                displayError("Marital Status");
            }else if (address.equals("")) {
                displayError("Address");
            }else if (city.equals("")) {
                displayError("City");
            }else if (state.equals("")) {
                displayError("State");  
            }else if (phone.length()<10) {
                displayError("Phone");
            } else if (dob.equals("")) {
                displayError("DOB");
            } else{
                JDBCConn connection = new JDBCConn();
                String query = String.format("insert into signupone values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", uniqueFormNo,name,fname,gender,email,maritalStatus,address,city,state,phone,dob);
                int row_affected = connection.statement.executeUpdate(query);
                if (row_affected>0) {
                    JOptionPane.showMessageDialog(null, "Details Added Successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new SignUpTwo(uniqueFormNo).setVisible(true);//To find user formNo in signUpTwo class
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