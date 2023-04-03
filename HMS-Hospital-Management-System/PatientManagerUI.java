
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.lang.ModuleLayer.Controller;

public class PatientManagerUI{
    
    
    private JPanel innerloginPanel;
    private JPanel innerloginPaneltitle;
    private JPanel loginPanel;
    private JTextField user;
    private JTextField pass;
    private JFrame frame;
    private PatientManagerUI login;
    static Point mouseDownCompCoords;
    public ArrayList<admin> admins = new ArrayList<admin>();
    public ArrayList<patient> patients = new ArrayList<patient>();
    public ArrayList<patient> revpatients = new ArrayList<patient>();
    public PatientManagerUI()
    {
        getadmins();
        getPatients();
        login = this;
        final int dimx = 650;
        frame = new JFrame();
        frame.addMouseListener(new MouseListener(){
            public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
            }
            public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });
        
        frame.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }
                
            public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
        mouseDownCompCoords = null;

        loginPanel = new JPanel();


        loginPanel.setLayout(null);
        loginPanel.setPreferredSize(new Dimension(1200, 600));
        loginPanel.setBackground(Color.WHITE);

        
       JLabel docim = new JLabel(new ImageIcon("images\\doc_500x400.png"));
       docim.setBounds(110, 100, 500, 400);
       loginPanel.add(docim);

        innerloginPanel = new JPanel();
        innerloginPanel.setLayout(null);
        innerloginPanel.setBounds(dimx, 220, 500, 300);
        innerloginPanel.setBackground(Color.WHITE);
        
        innerloginPaneltitle = new JPanel();
        innerloginPaneltitle.setLayout(null);
        innerloginPaneltitle.setBounds(dimx, 130, 500, 120);
        innerloginPaneltitle.setBackground(Color.WHITE);

        

        JPanel k = new JPanel();
        k.setLayout(null);
        k.setBounds(740, 220, 350, 50);
        k.setBackground(Color.WHITE);
        frame.add(k);

        JLabel kt  = new JLabel("Lets get you Logged in", SwingConstants.CENTER);
        kt.setBounds(0, 0, 350, 50);
        kt.setForeground(Color.BLACK);
        kt.setFont(new Font("Serif", Font.PLAIN, 20));
        k.add(kt);

        JPanel k1 = new JPanel();
        k1.setLayout(null);
        k1.setBounds(733, 110, 800, 120);
        k1.setBackground(Color.WHITE);
        frame.add(k1);

        JLabel kt1  = new JLabel("Collaborative Care", SwingConstants.CENTER);
        kt1.setBounds(45, 0, 350, 50);
        kt1.setForeground(Color.decode("#54aeef"));
        kt1.setFont(new Font("Serif", Font.BOLD, 35));
        k1.add(kt1);
        
        JLabel kt1img  = new JLabel(new ImageIcon("images\\plus.png"));
        kt1img.setBounds(0, 0, 50, 50);
        k1.add(kt1img);

        JLabel title  = new JLabel("Welcome Back!", SwingConstants.CENTER);
        title.setBounds(-65, 19, 500, 120);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        k1.add(title);

        
        user  = new JTextField("Username") {
            @Override protected void paintComponent(Graphics g) {
              int w = getWidth();
              int h = getHeight();
              Graphics2D g2 = (Graphics2D)g.create();
              g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
              g.setColor(getBackground());
              g.fillRoundRect(0, 0, w-1, h-1, h, h);
              g2.setColor(Color.GRAY);
              g2.drawRoundRect(0, 0, w-1, h-1, h, h);
              super.paintComponent(g);
            }
          };

          user.setFont(new Font("Serif", Font.PLAIN, 16));
    
          user.setOpaque(false);
          user.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        user.setBounds(130, 90, 300, 38);
        innerloginPanel.add(user);

        //displays username and password initially,once textbox is clicked the text disappears
        user.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if(user.getText().length() > 0)
                {
                    user.setText("");
                }
              
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(user.getText().length() == 0)
                {
                    user.setText("Username");
                }
                
            }
            
        });

        

        

       JPanel userIcon = new JPanel();
       userIcon.setBounds(90, 90, 38, 38);
       innerloginPanel.add(userIcon);
       JLabel userIcon1 = new JLabel(new ImageIcon("images\\user.png"));
       userIcon1.setBounds(0, 0, 55, 55);
       userIcon.add(userIcon1);
       
       pass   = new JTextField("Password") {
            @Override protected void paintComponent(Graphics g) {
              int w = getWidth();
              int h = getHeight();
              Graphics2D g2 = (Graphics2D)g.create();
              g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
              g.setColor(getBackground());
              g.fillRoundRect(0, 0, w-1, h-1, h, h);
              g2.setColor(Color.GRAY);
              g2.drawRoundRect(0, 0, w-1, h-1, h, h);
              super.paintComponent(g);
            }
          };
        pass.setOpaque(false);
        pass.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        pass.setFont(new Font("Serif", Font.PLAIN, 16));
        pass.setBounds(130, 140, 300, 38);
        innerloginPanel.add(pass);
        pass.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if(pass.getText().length() > 0)
                {
                    pass.setText("");
                }
              
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(pass.getText().length() == 0)
                {
                    pass.setText("Password");
                }
                
            }
            
        });

        JPanel passIcon = new JPanel();
        passIcon.setBounds(90, 140, 38, 38);
        innerloginPanel.add(passIcon);
        JLabel passIcon1 = new JLabel(new ImageIcon("images\\pass.png"));
        passIcon1.setBounds(0, 0, 55, 55);
        passIcon.add(passIcon1);

        //DESIGN BORDER
        JPanel top = new JPanel();
        top.setBounds(0, 0, 1200, 38);
        top.setBackground(Color.decode("#54aeef"));
        frame.add(top);

        JPanel bottom = new JPanel();
        bottom.setBounds(0, 562, 1200, 38);
        bottom.setBackground(Color.decode("#54aeef"));
        frame.add(bottom);

        JButton login = new JButton("Login") {
            @Override protected void paintComponent(Graphics g) {
              int w = getWidth();
              int h = getHeight();
              Graphics2D g2 = (Graphics2D)g.create();
              g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
              g.setColor(getBackground());
              g.fillRoundRect(0, 0, w-1, h-1, h, h);
              g2.setColor(Color.GRAY);
              g2.drawRoundRect(0, 0, w-1, h-1, h, h);
              super.paintComponent(g);
            }
          };
          login.setOpaque(false);
          login.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
          login.setBackground(Color.decode("#54aeef"));
        login.setBounds(130, 220, 300, 38);
        login.setFont(new Font("Serif", Font.PLAIN, 20));
        login.setForeground(Color.white);
        ButtonListener listener = new ButtonListener();
        login.addActionListener(listener);
        innerloginPanel.add(login);
        login.grabFocus();
        
        loginPanel.add(innerloginPaneltitle);
        loginPanel.add((innerloginPanel));
        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        
        new PatientManagerUI();

        
    }

    private class ButtonListener implements ActionListener
    {
       
        public void actionPerformed(ActionEvent e)
        {
            boolean coruse = false;
            boolean corpass = false;
            userLogin userl = new userLogin( user.getText(), pass.getText());
            boolean[] result=userl.authenticate(admins);
           coruse = result[0];
           corpass = result[1];

           if(coruse && corpass)
           {
               login.frame.setVisible(false);
               new PatientAdmissionUI(login);
           }
           else

            if(!coruse)
            {
                user.setText("INCORRECT USERNAME");
                coruse = false;
            }
            else
            {
                pass.setText("INCORRECT PASSWORD");
                corpass = false;
            }

        }

    }

    public void getadmins()
    {
        Statement sqlSt;
        String output = "";
        ResultSet result;
        String SQL = "SELECT * from admins";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hospital";
            Connection dbConnect =  DriverManager.getConnection(dbURL, "COMP2171", "COMP2171project");
            sqlSt = dbConnect.createStatement();//allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
           
            while(result.next() != false)
            {
                int id = Integer.parseInt(result.getString(1));
                String fname = result.getString(2);
                String lname= result.getString(3);
                String user= result.getString(4);
                String pass= result.getString(5);
                String email= result.getString(6);
                String pos= result.getString(7);
                String tele= result.getString(8);
                admins.add(new admin(id, fname, lname, user, pass, email, pos, tele));   
            
            }

            sqlSt.close();

            System.out.println(output);


        }
        catch(ClassNotFoundException ex){
            System.out.println("DIDNT LOAD JAR");
        }
        catch (SQLException ex){
            System.out.println("SQL IS BAD" +ex.getMessage());
        }
    }


    public void getPatients()
    {
        patients = new ArrayList<patient>();
        revpatients.clear();
        Statement sqlSt;
        String output = "";
        ResultSet result;
        String SQL = "SELECT * from patients";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hospital";
            Connection dbConnect =  DriverManager.getConnection(dbURL, "COMP2171", "COMP2171project");
            sqlSt = dbConnect.createStatement();//allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
            while(result.next() != false)
            {
                 int id = Integer.parseInt(result.getString(1));
                 String name = result.getString(2);
                 String occupation=result.getString(3);
                 String email=result.getString(4);
                 String gender=result.getString(5);
                 String tele=result.getString(6);
                 String symp=result.getString(7);
                 String blood=result.getString(8);
                 String add=result.getString(9);
                 String docseen=result.getString(10);
                 double paid=  Double.parseDouble(result.getString(11));
                 String date=result.getString(12);

                
                patients.add(new patient(id, name, occupation, email, gender, tele, symp, blood, add, docseen, paid, date));
                revpatients.add(new patient(id, name, occupation, email, gender, tele, symp, blood, add, docseen, paid, date));
            }

            Collections.reverse(revpatients);

            sqlSt.close();
        }
        catch(ClassNotFoundException ex){
            System.out.println("DIDNT LOAD JAR");
        }
        catch (SQLException ex){
            System.out.println("SQL IS BAD" +ex.getMessage());
        }

        System.out.println("succesful");
    }

    public int getlastID()
    {
        try{
            return revpatients.get(0).getid();
        } catch(Exception ex)
        {
            return -1;
        }
         
         
    }



}