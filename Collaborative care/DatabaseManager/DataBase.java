package DatabaseManager;

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

import Admin.admin;
import PatientData.patient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DataBase {
    private int id;
    private String name;
    private String email;
    private String response;
    private String gender;
    private String occ;
    private String contact;
    private String phone;
    private String sympton;
    private String blood;
    private String religion;
    private String dob; 
    private double weight;
    private double price;
    private String date;
    private String doctor;
    private String field;
    private String operator;
    private String value;
    private ArrayList<patient> patients = new ArrayList<patient>();
    private ArrayList<admin> admins = new ArrayList<admin>();
    
    public ArrayList<admin> getAdminList(){ return admins;};
    private ArrayList<patient> revpatients = new ArrayList<patient>();
    public ArrayList<patient> getPatientsList(){ return patients;};
    
    public ArrayList<patient> getrevPatientList(){return revpatients;};
   
    
    public DataBase(String name,String email,String response)
    {
       
        this.name = name;
        this.email = email;
        this.response = response;
        insert_query();
    }

    public DataBase(int id, String name,String oc,String email,String gender,String tele, String sym,String blood,String add,String dob,double weight,String doc,double paid,String date)
    { 

        this.id =id;
        this.name = name;
        this.occ =oc;
        this.contact = email;
        this.gender = gender;
        this.phone = tele;
        this.doctor = doc;
        this.sympton =sym;
        this.blood = blood ;   
        this.religion   = add;
        this.dob=dob;
        this.weight=weight;
        this.price = paid;
        this.date = date;
        insert_patient();
    }

    public DataBase(String field,String operator,String value,ArrayList<patient> patients)
    {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.patients = patients;
    }
  
    
    public DataBase()
    {

    }
   
    public ArrayList<patient> filter_query(){
        ArrayList<patient> newpatients = new ArrayList<patient>();
    
    
         if(field.equals("ID"))
        {
            for(patient pat : patients)
            {
                if(pat.getid()==( Integer.parseInt(value) ))
                {
                    newpatients.add( pat);
                }
            }
        }
        if(field.equals("Name"))
        {
            for(patient pat : patients)
            {
                if(pat.getname().equals(value))
                {
                    newpatients.add( pat);
                }
            }
        }
        else if(field.equals("Doctor"))
        {
            for(patient pat : patients)
            {
                if(pat.get_docseen().equals(value))
                {
                    newpatients.add( pat);
                }
            }
        }
    
        else if(field.equals("Blood Group"))
        {
            for(patient pat : patients)
            {
                if(pat.get_group().equals(value))
                {
                    newpatients.add( pat);
                }
            }
        }
        else if(field.equals("Gender"))
        {
            for(patient pat : patients)
            {
                if(pat.getgender().equals(value))
                {
                    newpatients.add( pat);
                }
            }
        }
        else
        {
             try
                {
                    Double.parseDouble(value);
                }
                catch (Exception e)
                {
                    return newpatients;
                }
            if(operator.equals("="))
            {
                for(patient pat : patients)
                {
                
                    if(pat.get_Paid() == Double.parseDouble(value))
                    {
                        newpatients.add( pat);
                    }
                }
            }
            else if(operator.equals(">"))
            {
                for(patient pat : patients)
                {
                
                    if(pat.get_Paid() > Double.parseDouble(value))
                    {
                        newpatients.add( pat);
                    }
                }
    
            }
            else
            {
                for(patient pat : patients)
                {
                
                    if(pat.get_Paid() < Double.parseDouble(value))
                    {
                        newpatients.add( pat);
                    }
                }
            }
        }
    
        return newpatients;
    }
   
    
    public void insert_query()
    {
        Statement sqlSt;
        String SQL = "insert into Prescription (name, email, Prescription)" + " values (?, ?, ?)";
     
        System.out.println(SQL);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hospital";
            Connection dbConnect =  DriverManager.getConnection(dbURL, "COMP2171","COMP2171project");
            sqlSt = dbConnect.createStatement();//allows SQL to be executed
            PreparedStatement preparedStmt = dbConnect.prepareStatement(SQL);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setString (3, response);
            preparedStmt.execute();
            sqlSt.close();
        }
        catch(ClassNotFoundException ex){
            System.out.println("DIDNT LOAD JAR");
        }
        catch (SQLException ex){
            System.out.println("SQL IS BAD" +ex.getMessage());
        }
    }



    public void insert_patient()
    {
        Statement sqlSt;
        String SQL = "insert into patients (id,name, occupation, email, gender, telephone, symptoms, blood, address, dob, weight, doctor_seen, amount_paid, app_date)" + " values (?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
     
        System.out.println(SQL);

        try{

            //opening a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hospital";
            Connection dbConnect =  DriverManager.getConnection(dbURL, "COMP2171", "COMP2171project");


            sqlSt = dbConnect.createStatement();//allows SQL to be executed
            PreparedStatement preparedStmt = dbConnect.prepareStatement(SQL);
            preparedStmt.setInt (1, id);
            preparedStmt.setString (2, name);
            preparedStmt.setString (3, occ);
            preparedStmt.setString (4, contact);
            preparedStmt.setString(5, gender);
            preparedStmt.setString    (6, phone);
            preparedStmt.setString    (7, sympton);
            preparedStmt.setString    (8, blood);
            preparedStmt.setString    (9, religion);
            preparedStmt.setString    (10, dob);
            preparedStmt.setDouble   (11, weight);
            preparedStmt.setString    (12, doctor);
            preparedStmt.setDouble(13, price);
            preparedStmt.setString    (14, date);
            preparedStmt.execute();//magic
            sqlSt.close();
        }
        catch(ClassNotFoundException ex){
            System.out.println("DIDNT LOAD JAR");
        }
        catch (SQLException ex){
            System.out.println("SQL IS BAD" +ex.getMessage());
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
        getrevPatientList().clear();
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
                 double weight=Double.parseDouble(result.getString(11));
                 String dob=result.getString(10);
                 String docseen=result.getString(12);
                 double paid= Double.parseDouble(result.getString(13));
                 String date=result.getString(14);

                
                patients.add(new patient(id, name, occupation, email, gender, tele, symp, blood, add,dob,weight,docseen, paid, date));
                revpatients.add(new patient(id, name, occupation, email, gender, tele, symp, blood, add,dob,weight,docseen, paid, date));
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
        return getrevPatientList().get(0).getid();
    } catch(Exception ex)
    {
        return -1;
    }
     
     
}

}
