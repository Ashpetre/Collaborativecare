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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.lang.ModuleLayer.Controller;


public class RecordManager {
    private PatientRecord hi;
    
    

    public DataBase Data =new DataBase();
    public RecordManager(PatientRecord hii)
    {
         this.hi = hii;
         Data.getPatients();
       
    }
    public RecordManager()
    {
       
    }
        
    

    public void clearInput()
        {    
            hi.iDinput.setText(Integer.toString(hi.Data.getlastID()+1));
            hi.priceinput.setText("");
            hi.nameinput.setText("");
            hi.contactinput.setText("");
            hi.genderinput.setSelectedItem(" ");
            hi.phoneinput.setText("");
            hi.symptomsinput.setText("");
            hi.occinput.setText("");
            hi.groupinput.setSelectedItem(" ");
            hi.relinput.setText("");
            hi.priceinput.setText("");
            hi.consinput.setSelectedItem(" ");
            hi.dobinput.setText("");
            hi.weightinput.setText("");
            hi.datePicker.getModel().getValue( );

        }
    

    public void validateDetails()
    {
        JFrame f = new JFrame();
            boolean isint = true;
            try{
                Double.parseDouble(hi.priceinput.getText());

            } catch(Exception d)
            {
                isint = false;
            }
            if((hi.nameinput.getText().isEmpty()) || (hi.nameinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Name cannot be empty");
            }
            else if((hi.contactinput.getText().isEmpty()) || (hi.contactinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Email cannot be empty");
            }
            else if(String.valueOf(hi.genderinput.getSelectedItem()).isEmpty())
            {
                JOptionPane.showMessageDialog(f, "Please select a Gender");
            }
            else if((hi.phoneinput.getText().isEmpty()) || (hi.phoneinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Please Enter a valid Phone Number");
            }

            else if((hi.symptomsinput.getText().isEmpty()) || (hi.symptomsinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Patient must be experiencing something");
            }

            else if((hi.occinput.getText().isEmpty()) || (hi.occinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Enter an occupation");
            }

            else if(String.valueOf(hi.groupinput.getSelectedItem()).isEmpty())
            {
                JOptionPane.showMessageDialog(f, "Please select a valid blood group");
            }

            else if((hi.relinput.getText().isEmpty()) || (hi.relinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Enter an Address");
            }
            else if((hi.priceinput.getText().isEmpty()) || (hi.priceinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Please enter a price");
            }

            else if(String.valueOf(hi.consinput.getSelectedItem()).isEmpty())
            {
                JOptionPane.showMessageDialog(f, "Choose a doctor");
            }
            else if(!isint)
            {
                JOptionPane.showMessageDialog(f, "Price must be Integer"); 
            }
            else if((hi.dobinput.getText().isEmpty()) || (hi.dobinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Please enter a date of birth");
            }
            else if((hi.weightinput.getText().isEmpty()) || (hi.weightinput.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Please enter a weight");
            }
            else
            {
                if(hi.update)
                {
                    hi.updatePatient(hi.please);
                    hi.update = false;
                    JOptionPane.showMessageDialog(f, "Update successful");
                }
                else
                {
                    savePatient();
                    hi.iDinput.setText(Integer.toString(hi.Data.getlastID()+1));
                    JOptionPane.showMessageDialog(f, "Save successful");
                }
            }

        }

    public void updatePatient(int please, PatientRecord hi)
    {
        Statement sqlSt;
        String output = "";
        int result;
        String name =hi.nameinput.getText();
        String contact = hi.contactinput.getText();
        String gender = String.valueOf(hi.genderinput.getSelectedItem());
        String phone = hi.phoneinput.getText();
        String sympton = hi.symptomsinput.getText();
        String occ = hi.occinput.getText();
        String blood = String.valueOf(hi.groupinput.getSelectedItem());
        String religion = hi.relinput.getText();
        String doctor = String.valueOf(hi.consinput.getSelectedItem());
        double price =Double.parseDouble(hi.priceinput.getText());
        String dob =String.valueOf(hi.dobinput.getText());
        double weight =Double.parseDouble(hi.weightinput.getText());
        String date = hi.datePicker.getModel().getMonth()+1+"/"+ hi.datePicker.getModel().getDay()+"/"+ hi.datePicker.getModel().getYear();
 

        
        String SQL = "UPDATE patients SET name = ?, occupation = ?, email = ?, gender = ?, telephone = ?, symptoms = ?, blood = ?, address = ?, dob = ?, weight = ?, doctor_seen = ?, amount_paid = ?, app_date = ? WHERE patients.id = ?;";
        System.out.println(SQL);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hospital";
            Connection dbConnect =  DriverManager.getConnection(dbURL, "COMP2171", "COMP2171project");
            sqlSt = dbConnect.createStatement();//allows SQL to be executed
            PreparedStatement preparedStmt = dbConnect.prepareStatement(SQL);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, occ);
            preparedStmt.setString (3, contact);
            preparedStmt.setString(4, gender);
            preparedStmt.setString    (5, phone);
            preparedStmt.setString    (6, sympton);
            preparedStmt.setString    (7, blood);
            preparedStmt.setString    (8, religion);
            preparedStmt.setString    (9, dob);
            preparedStmt.setDouble   (10, weight);
            preparedStmt.setString    (11, doctor);
            preparedStmt.setDouble(12, price);
            preparedStmt.setString    (13, date);
            preparedStmt.setString    (14, Integer.toString(please));
            preparedStmt.execute();
            sqlSt.close();
            hi.Data.getPatients();;
            updateTable(hi.table);
        }
        catch(ClassNotFoundException ex){
            System.out.println("DIDNT LOAD JAR");
        }
        catch (SQLException ex){
            System.out.println("SQL IS BAD" +ex.getMessage());
        }
    }

    public void savePatient()
    {
       
        Statement sqlSt;
        String output = "";
        int result;
        int id =Integer.parseInt(hi.iDinput.getText());
        String name = hi.nameinput.getText();
        String contact = hi.contactinput.getText();
        String gender = String.valueOf(hi.genderinput.getSelectedItem());
        String phone = hi.phoneinput.getText();
        String sympton = hi.symptomsinput.getText();
        String occ = hi.occinput.getText();
        String blood = String.valueOf(hi.groupinput.getSelectedItem());
        String dob =String.valueOf(hi.dobinput.getText());
        double weight =Double.parseDouble(hi.weightinput.getText());
        String religion = hi.relinput.getText();
        String doctor = String.valueOf(hi.consinput.getSelectedItem());
        double price =Double.parseDouble(hi.priceinput.getText());
        String date = hi.datePicker.getModel().getMonth()+1+"/"+ hi.datePicker.getModel().getDay()+"/"+ hi.datePicker.getModel().getYear();

        //function here
        DataBase db = new DataBase(id,name, occ, contact, gender, phone,  sympton, blood, religion,dob,weight, doctor, price, date);
        
        hi.Data.getPatients();;
        updateTable(hi.table);
    
    }
 

    
    
    
    public void  updateTable(JTable table)
    {

        hi.model.setRowCount(0);
            for(int i = 0 ; i <hi.Data.getrevPatientList().size();i++)
            {
                if (hi.Data.getPatientsList().size()>0)
                    hi.addToTable(hi.Data.getrevPatientList().get(i));
                  
                
                    if(i == 9)
                    {
                        break;
                    }
    
            }
    }

    public void previewInformation()
    {
        
        String name = hi.nameinput.getText();
        String contact = hi.contactinput.getText();
        String gender = String.valueOf(hi.genderinput.getSelectedItem());
        String phone = hi.phoneinput.getText();
        String sympton = hi.symptomsinput.getText();
        String occ = hi.occinput.getText();
        String blood = String.valueOf(hi.groupinput.getSelectedItem());
        String religion = hi.relinput.getText();
        String doctor = String.valueOf(hi.consinput.getSelectedItem());
        String dob =String.valueOf(hi.dobinput.getText());
        String weight =hi.weightinput.getText();
        String price = hi.priceinput.getText();
        String date = hi.datePicker.getModel().getMonth()+1+"/"+ hi.datePicker.getModel().getDay()+"/"+ hi.datePicker.getModel().getYear();

        String str = "<html><br/><html><html>Collaborative Care Patient System<br/><html><html><br/><html><html>Patient Name: " + name +"<br/><html>Date of Birth: " + dob +"<br/>Weight:<html> " + weight + "<html><br/><br/>Gender::<html> " + gender;
        str+= "<html><br/>Contact Information:<html> " + contact+','+phone + "<html><br/>Occupation:<html> " + occ + "<html><br/><br/>Address:<html> " +religion  + "<html><br/>Blood Group:<html> " + blood +"<html><br/>Doctor Seen:<html> " + doctor;
        str+="<html><br/>Amount Paid:<html> " + price + "<html><br/>Date of Appointment:<html> " + date +"<html><br/>Symptoms experiencing:<html> "+sympton;
        
        hi.titleinfo.setText(str);


        
        System.out.println(hi.iDinput.getText());

    }
}
