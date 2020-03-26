
 // Prescribed by zubquzaini
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.*;

public class flightApp {
    
    JFrame frame = new JFrame("Fly Malaya");
    
    Client c = new Client();
    Admin a = new Admin();
    
    JLabel welcomeText = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel phoneLabel = new JLabel();
    
    JTextField phoneField= new JTextField();
    JTextField nameField= new JTextField();
    JTextField optionField = new JTextField();
    JTextField codeField = new JTextField();
    JTextField numField = new JTextField();
    JTextField promoField = new JTextField();
    JTextField paymentField = new JTextField();
    JTextField securityField = new JTextField();
    
    JButton login=new JButton("Login"); 
    
    int count=1;

    public static void main(String args[]) {
        
        flightApp a = new flightApp();
        a.welcomeSection();
    }
    
    public void welcomeSection() {
            
        JLabel top = new JLabel();
        JLabel middle = new JLabel();
        JLabel bottom = new JLabel();
        JLabel option = new JLabel();
        JLabel booking = new JLabel();
        JLabel schedule = new JLabel();
        JButton enter = new JButton("Enter");
        
        top.setText("****************************************");   
        middle.setText("*           Welcome to Fly Malaya          *");
        bottom.setText("****************************************");
        option.setText("Select option:");
        booking.setText("1-Booking");
        schedule.setText("2-Schedule");
       
        top.setBounds(180, 50, 300, 100);
        middle.setBounds(180, 70, 300, 100);
        bottom.setBounds(180, 90, 300, 100);
        option.setBounds(230, 110, 300, 100);
        booking.setBounds(230, 140, 300, 100);
        schedule.setBounds(230, 160, 300, 100);
        optionField.setBounds(230, 230, 140, 30);
        enter.setBounds(245, 270, 110, 40);
        
        enter.addActionListener(new option());
        
        frame.add(top);
        frame.add(middle);
        frame.add(bottom);
        frame.add(option);
        frame.add(booking);
        frame.add(schedule);
        frame.add(optionField);
        frame.add(enter);
        frame.setSize(600,600);    
        frame.setLayout(null);    
        frame.setVisible(true); 
    }
   
    public void loginSection(String nav) {
        frame.getContentPane().removeAll();
        frame.repaint();
        
        welcomeText.setText("Welcome to Fly Malaya");
        nameLabel.setText("Username ");
        phoneLabel.setText("Phone Number ");
        
        welcomeText.setBounds(220, 50, 200, 100);
        nameLabel.setBounds(160, 150, 100, 30);
        phoneLabel.setBounds(160, 190, 150, 30);        
        nameField.setBounds(260, 150, 180, 30);
        phoneField.setBounds(260, 190, 180, 30);
        login.setBounds(220, 250, 140, 40);
        
        if(nav.equals("1")) {
            login.addActionListener(new openBooking());
        } else {
            //login.addActionListener(new display());
        }
        
        frame.add(welcomeText);
        frame.add(nameLabel);
        frame.add(phoneLabel);
        frame.add(nameField);
        frame.add(phoneField);
        frame.add(login);
        frame.setSize(600,600);    
        frame.setLayout(null);    
        frame.setVisible(true);  
    }

    public void bookingSection() {
        frame.getContentPane().removeAll();
        frame.repaint();
        
        String name = nameField.getText();
        String phone = phoneField.getText();

        c.setData(name, phone);
        
        JLabel table = new JLabel();
        JLabel codeLabel = new JLabel();
        JLabel numLabel = new JLabel();
        JButton validate = new JButton("Validate");
        JButton returnHome = new JButton("Return");
 
        table.setText(c.toString());
        codeLabel.setText("Flight code: ");
        numLabel.setText("Number of ticket(s): ");
        
        table.setBounds(50, 20, 500, 200);
        codeLabel.setBounds(130, 240, 100, 30);
        numLabel.setBounds(130, 280, 150, 30);        
        codeField.setBounds(260, 240, 180, 30);
        numField.setBounds(260, 280, 180, 30);
        validate.setBounds(300, 340, 140, 40);
        returnHome.setBounds(150, 340, 140, 40);
        
        validate.addActionListener(new validate());
        returnHome.addActionListener(new goHome());

        frame.add(table);
        frame.add(codeLabel);
        frame.add(numLabel);
        frame.add(codeField);
        frame.add(numField);
        frame.add(validate);
        frame.add(returnHome);
    }
    
    public void promoSection() {
        
        JLabel paymentLabel = new JLabel();
        JLabel promoLabel = new JLabel();
        JLabel promoLabel2 = new JLabel();
        JButton calculate = new JButton("Calculate");
        
        paymentLabel.setText("Total payment is RM" + c.calcTotal());
        promoLabel.setText("Psst! psst! all you need is 'Superfly' to receive 5% discount");
        promoLabel2.setText("Enter promo code: ");
        
        paymentLabel.setBounds(50, 20, 350, 30);
        promoLabel.setBounds(50, 50, 450, 30);
        promoLabel2.setBounds(50, 80,  180, 30);
        promoField.setBounds(180, 80, 180, 30);
        calculate.setBounds(220, 120, 110, 40);
        
        calculate.addActionListener(new calculate());
        
        frame.add(paymentLabel);
        frame.add(promoLabel);
        frame.add(promoLabel2);
        frame.add(promoField);
        frame.add(calculate);
    }
    
    public void paymentSection() {
        JLabel paymentLabel = new JLabel();
        JLabel promoLabel2 = new JLabel();
        JButton book = new JButton("Book Now!");
        
        if(c.getPromo().equalsIgnoreCase("Superfly")) {
            
            paymentLabel.setText("Total price after disount is RM" + c.calcDiscount());
        } else {
            
            paymentLabel.setText("Total price is RM" + c.calcDiscount());
        }
        promoLabel2.setText("Enter payment: ");
        
        paymentLabel.setBounds(50, 20, 350, 30);
        promoLabel2.setBounds(50, 80,  180, 30);
        paymentField.setBounds(180, 80, 180, 30);
        book.setBounds(220, 120, 110, 40);
        
        book.addActionListener(new book());
        frame.add(paymentLabel);
        frame.add(promoLabel2);
        frame.add(paymentField);
        frame.add(book);
    }
    
    public void receiptSection() throws Exception {
        FileWriter fw=new FileWriter("receipt.txt",true);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        
        JLabel receipt = new JLabel();
        JButton returnHome = new JButton("Return");
        
        receipt.setText(c.printReceipt());
        
        receipt.setBounds(20, 20, 300, 300);
        returnHome.setBounds(220, 350,140, 40);
        
        count++;
        a.setProfit(c.calcDiscount());
        a.setCollection(count);
        returnHome.addActionListener(new goHome());
        
        pw.println(c.saveReceipt());
        pw.close();
        
        frame.add(receipt);
        frame.add(returnHome);
    }
    
    public void scheduleSection() {
    
        JLabel table = new JLabel();
        JButton returnHome = new JButton("Return");
        
        table.setText(c.toString());
        table.setBounds(50, 20, 500, 200);
        returnHome.setBounds(220, 350,140, 40);
        
        returnHome.addActionListener(new goHome());
        frame.add(table);
        frame.add(returnHome);
    }
    
    public void securitySection() {
        
        JLabel warning = new JLabel();
        JLabel warning2 = new JLabel();
        JLabel warning3 = new JLabel();
        JButton proceed = new JButton("Proceed");
        JButton returnHome = new JButton("Return");
        
        warning.setText("Warning!! This section is for authoize person only!!!");
        warning2.setText("Proceed at your own risk...");
        warning3.setText("Enter code: ");
        
        warning.setBounds(20, 20, 400, 40);
        warning2.setBounds(20, 40, 400, 40);
        warning3.setBounds(80, 80, 80, 40);
        securityField.setBounds(160, 80, 140, 30);
        proceed.setBounds(240, 140, 140, 40);
        returnHome.setBounds(80, 140, 140, 40);
        
        proceed.addActionListener(new openAdmin());
        returnHome.addActionListener(new goHome());
        
        frame.add(warning);
        frame.add(warning2);
        frame.add(warning3);
        frame.add(securityField);
        frame.add(proceed);
        frame.add(returnHome);
    }
    
    public void adminSection() throws Exception {

        JLabel receipt = new JLabel();
        JPanel panel = new JPanel();

        receipt.setText(a.displayReceipt());
        panel.add(receipt);
        JOptionPane.showMessageDialog(frame, a.toString());
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        scrollPane.setBounds(50, 50, 500, 500);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(600, 600));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private class goHome implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();    
            
            optionField.setText("");
            nameField.setText("");
            phoneField.setText("");
            codeField.setText("");
            numField.setText("");
            securityField.setText("");
            
            welcomeSection();
        }
    }
    
    private class option implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            String nav = optionField.getText();
            if(nav.equals("1")) {
                
                loginSection(nav);
            } else if(nav.equals("2")) {
                frame.getContentPane().removeAll();
                frame.repaint();  
                optionField.setText("");
                scheduleSection();
            } else if(nav.equals("3")){
                frame.getContentPane().removeAll();
                frame.repaint();  
                optionField.setText("");
                securitySection();
            } else {
            
            }
        }
    }
    
    private class openBooking implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            phoneField.setText("");
            bookingSection();
        }
    }
    
    private class openAdmin implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(a.checkPassword(securityField.getText())) {
                securityField.setText("");
                frame.getContentPane().removeAll();
                frame.repaint();
                try {
                    adminSection();
                } catch(Exception exc) {
                    
                    System.out.println("Already at the end of file!" + exc);
                }
            } else {
                welcomeSection();
            }
        }
    }
    
    private class validate implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            String code = codeField.getText();
            int num = Integer.parseInt(numField.getText());
            c.setBook(code, num);
            
            if(c.getKey(code) == 5) {
                
                JOptionPane.showMessageDialog(frame, "Invalid code!!! Please try again");
                codeField.setText("");
                numField.setText("");
                bookingSection();
            } else {
                frame.getContentPane().removeAll();
                frame.repaint();   
                promoSection();
            }
        }
    }
    
    private class calculate implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            String promo = promoField.getText();
            c.setPromo(promo);
            frame.getContentPane().removeAll();
            frame.repaint();
            promoField.setText("");
            paymentSection();
        }
    }
    
    private class book implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            double payment = Double.parseDouble(paymentField.getText());
            
            c.setPayment(payment);
            if(c.calcBalance() >= 0) {
                try {
                    JOptionPane.showMessageDialog(frame, "Success!");
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    paymentField.setText("");
                    receiptSection();                
                } catch (Exception exc) {
                    // TODO: handle exception
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient Amount!! Please try again");
                paymentField.setText("");
                paymentSection();
            }
        }
    }
}
