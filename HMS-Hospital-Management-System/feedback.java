import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.*;
import java.awt.Font;

public class feedback extends JFrame implements ActionListener{
    
    JButton submit;     //global variables
    JButton reset;
    JTextField tname;
    JTextField tmail;
    JTextArea tfeedback;
    Container cp;
    JCheckBox check;
    JComboBox<String> agegrp;
    JFrame f;
    JRadioButton one;
    JRadioButton two;
    JRadioButton three;
    JRadioButton four;
    JRadioButton five;
    ButtonGroup rating;
    private  feedback feed;

        
    public feedback(){
        feed =  this;
        this.setTitle("Client Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 440);
        cp= getContentPane();
        this.setLayout(null);
        this.cp.setBackground(Color.decode("#54aeef"));

        ImageIcon icon= new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        JLabel heading= new JLabel();
        heading.setText("FEEDBACK SURVEY");
        heading.setFont(new Font("Arial", Font.BOLD, 36));
        heading.setBounds(60,0,440,45);
        heading.setForeground(Color.black);
        this.cp.add(heading);


        JLabel lname= new JLabel();
        lname.setText("Name: ");
        lname.setFont(new Font("Calibri", Font.PLAIN, 18));
        lname.setBounds(40,60,80,28);
        this.cp.add(lname);

        tname = new JTextField();
        tname.setFont(new Font("Calibri", Font.PLAIN, 16));
        tname.setBounds(205, 60, 240, 28);
        this.cp.add(tname);

        JLabel lmail= new JLabel();
        lmail.setText("Email Address: ");
        lmail.setFont(new Font("Calibri", Font.PLAIN, 18));
        lmail.setBounds(40,110, 150, 28);
        this.cp.add(lmail);

        tmail = new JTextField();
        tmail.setFont(new Font("Calibri", Font.PLAIN, 16));
        tmail.setBounds(205, 105, 240, 28);
        this.cp.add(tmail);

  

       

        JLabel lfeedback= new JLabel();
        lfeedback.setText("Comment");
        lfeedback.setFont(new Font("Calibri", Font.PLAIN, 18));
        lfeedback.setBounds(40,170,170,28);
        this.cp.add(lfeedback);

        tfeedback = new JTextArea();
        tfeedback.setFont(new Font("Calibri", Font.PLAIN, 16));
        tfeedback.setBounds(22, 210, 440, 164);
        tfeedback.setAlignmentY(0);
    
        this.cp.add(tfeedback);

        submit= new JButton("Submit");
        submit.setForeground(Color.black);
        submit.setFont(new Font("Serif", Font.BOLD, 17));
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.setBackground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(125, 375, 100, 28);
        this.cp.add(submit);

        reset= new JButton("Reset");
        reset.setForeground(Color.black);
        reset.setFont(new Font("Serif", Font.BOLD, 17));
        reset.setBorderPainted(false);
        reset.setFocusPainted(false);
        reset.setBackground(Color.WHITE);
        reset.setBounds(255, 375, 100, 28);
        reset.addActionListener(this);
        this.cp.add(reset);

        this.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
            attributes.put(TextAttribute.TRACKING, 0.03);
    
            lname.setFont(lname.getFont().deriveFont(attributes));
            lmail.setFont(lmail.getFont().deriveFont(attributes));
            lfeedback.setFont(lfeedback.getFont().deriveFont(attributes));
            heading.setFont(heading.getFont().deriveFont(attributes));
            submit.setFont(submit.getFont().deriveFont(attributes));
            reset.setFont(reset.getFont().deriveFont(attributes));
            
        
            
        });
    }

    //@Override
    public void actionPerformed(ActionEvent e){
        boolean flag=false;
        String emailvalidation = "^[a-zA-Z0-9+_.-]+@(.+)$";  
        Pattern p= Pattern.compile(emailvalidation);
        Matcher mat= p.matcher(tmail.getText());
        f= new JFrame();

        if(e.getSource()==submit)
        {
            if((tname.getText().isEmpty()) || (tname.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Name cannot be empty");
            }
            else if((tmail.getText().isEmpty()) || (tmail.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Email cannot be empty");
            }
            else if(!mat.matches())
            {
                JOptionPane.showMessageDialog(f, "Please Enter a valid Email");
            }
            else
                flag=true; 
                
            if(flag)
            {
                String s1= "Thank you for your valuable Feeedback!\n\nYour Responses:-\n";
                String s2= "Name: "+tname.getText()+"\nEmail: "+tmail.getText()+"\n"+"\nFeedback: "+tfeedback.getText();
                String disp=s1+s2;
                JOptionPane.showMessageDialog(f, disp);
                DatabaseContrroller db = new DatabaseContrroller(tname.getText(),tmail.getText(), tfeedback.getText());//uploading to database
                feed.setVisible(false);
            }
            
        }

        else
        {
            tname.setText("");
            tmail.setText("");
            tfeedback.setText("");


        }


    }


    class StyledButtonUI extends BasicButtonUI  {

        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }
    
        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }
    
        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 1);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 2, 10, 1);
        }
    }

    public class StyledPanelUI extends BasicPanelUI  {

        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            JPanel button = (JPanel) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }
    
        @Override
        public void paint (Graphics g, JComponent c) {
            JPanel b = (JPanel) c;
            paintBackground(g, b,0);
            super.paint(g, c);
        }
    
        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 1);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 1, 10, 1);
        }
    }
    
}
