package PatientData;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;
import javax.swing.plaf.basic.BasicButtonUI;

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

import DatabaseManager.DataBase;
import DatabaseManager.PatientRecord;
import DatabaseManager.RecordManager;

import java.awt.font.TextAttribute;


import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.*;
import java.awt.Font;


public class clientChart {
    private JPanel recepPanel;
    static Point mouseDownCompCoords;
    private JPanel upperbutton;
    private DefaultListCellRenderer listRenderer;
    private  JTextField iDinput;
    private JTextField nameinput;
    private JTextField contactinput;
    private JComboBox genderinput;
    private JTextField phoneinput; 
    private JTextField symptomsinput;
    private JTextField occinput;
    private JComboBox groupinput;
    private JTextField relinput ;
    private JComboBox consinput;
    private JTextField priceinput;
    private JTextField dateinput;
    private JLabel titleinfo;
    private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel model1;
    private JTextField Notes;
    private JPanel listPanel;
    private boolean onmain = true;
    private JPanel chartPanel;
    private JPanel pastap;
    DataBase listing;
    PatientRecord rec;
    private int row;
    JFrame frame;
    public RecordManager controller;
    public clientChart(DataBase listing, PatientRecord rec,int row )
    {
        this.listing = listing;
        this.row = row;
        this.rec = rec;
        frame = new JFrame();
        frame.setBounds(400, 100, 950, 645);
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

        chartPanel = new JPanel();
        chartPanel.setLayout(null);
        chartPanel.setPreferredSize(new Dimension(950, 545));

        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        JPanel info = new JPanel();
        info.setLayout(null);
        info.setBounds(0, 0, 950, 300);
        info.setBackground(Color.decode("#54aeef"));
        chartPanel.add(info);
        info.setUI(new StyledPanelUI());

        pastap = new JPanel();
        pastap.setLayout(new BorderLayout());
        pastap.setBounds(0, 300, 950, 245);
        pastap.setBackground(Color.blue);
        chartPanel.add(pastap);
        //picture
        JPanel symp1 = new JPanel();
        symp1.setLayout(null);
        symp1.setBounds(100, 30, 200, 200);
        symp1.setBackground(Color.red);
        info.add(symp1);

        JLabel userIcon1 = new JLabel(new ImageIcon("images\\emptyperson.jpg"));
        userIcon1.setBounds(0, 0, 200, 200);
        symp1.add(userIcon1);

        JButton xbut = new JButton(new ImageIcon("images\\x.png"));
        xbut.setBounds(920, 0, 30, 30);
        xbut.setBackground(new Color(0,0,0,0));
        xbut.setFont(new Font("Serif", Font.BOLD, 18));
        xbut.setBorderPainted(false);
        xbut.setFocusPainted(false);
        xbut.setForeground(Color.decode("#54aeef"));
        xbut.setRolloverEnabled(false);
        info.add(xbut);
        xbut.addActionListener(new CloseListener());
        
        


        JPanel symp = new JPanel();
        symp.setLayout(new BorderLayout());
        symp.setBounds(730, 25, 200, 250);
        symp.setBackground(Color.red);

        
        
     
        
 

        JLabel nameLabel  = new JLabel(listing.getPatientsList().get(row).getname().toUpperCase());
        nameLabel.setBounds(345, 35, 1500, 50);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        info.add(nameLabel);
        
        JLabel agegenderLabel  = new JLabel(listing.getPatientsList().get(row).getgender().toUpperCase());
        agegenderLabel.setBounds(345, 75, 1500, 50);
        agegenderLabel.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(agegenderLabel);

        JLabel doc  = new JLabel("Doctor");
        doc.setBounds(345, 105, 1500, 50);
        doc.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(doc);

        JLabel doc1  = new JLabel("MD, " + listing.getPatientsList().get(row).get_docseen().toUpperCase());
        doc1.setBounds(400, 105, 1500, 50);
        doc1.setFont(new Font("Serif", Font.PLAIN, 15));
        info.add(doc1);

        JLabel date  = new JLabel("Apt date");
        date.setBounds(345, 170, 1500, 50);
        date.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(date);

        JLabel date1  = new JLabel(listing.getPatientsList().get(row).get_Date().toUpperCase());
        date1.setBounds(440, 170, 1500, 50);
        date1.setFont(new Font("Serif", Font.PLAIN, 15));
        info.add(date1);

        JLabel dob  = new JLabel("Occupation");
        dob.setBounds(345, 140, 1500, 50);
        dob.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(dob);

        JLabel dob1  = new JLabel(listing.getPatientsList().get(row).getoc().toUpperCase());
        dob1.setBounds(440, 140, 1500, 50);
        dob1.setFont(new Font("Serif", Font.PLAIN, 15));
        info.add(dob1);

        JLabel phone  = new JLabel("Phone");
        phone.setBounds(635, 140, 1500, 50);
        phone.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(phone);

        JLabel phone1  = new JLabel(listing.getPatientsList().get(row).get_tele().toUpperCase());
        phone1.setBounds(690, 140, 1500, 50);
        phone1.setFont(new Font("Serif", Font.PLAIN, 15));
        info.add(phone1);

        JLabel med  = new JLabel("Blood type");
        med.setBounds(545, 170, 1500, 50);
        med.setFont(new Font("Serif", Font.BOLD, 15));
        info.add(med);

        JLabel med1  = new JLabel(listing.getPatientsList().get(row).get_group());
        med1.setBounds(645, 170, 1500, 50);
        med1.setFont(new Font("Serif", Font.PLAIN, 15));
        info.add(med1);

       

        SwingUtilities.invokeLater(() -> {
            Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
            attributes.put(TextAttribute.TRACKING, 0.03);
    
            nameLabel.setFont(nameLabel.getFont().deriveFont(attributes));
            phone.setFont(phone.getFont().deriveFont(attributes));
            med.setFont(med.getFont().deriveFont(attributes));
            med1.setFont(med1.getFont().deriveFont(attributes));
            phone1.setFont(phone1.getFont().deriveFont(attributes));
            dob.setFont(dob.getFont().deriveFont(attributes));
            dob1.setFont(dob1.getFont().deriveFont(attributes));
            
        });
        String[] coloumnNames = {"sypmtoms"};
        

         DefaultTableModel model =new DefaultTableModel(coloumnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {       
                return false; // or a condition at your choice with row and column
            }
         };
         
        

         
           table = new JTable(model);
           table.setRowSelectionAllowed(false);
    
    
            JTableHeader header = table.getTableHeader();
            header.setBorder(null);
            header.setFont(new Font("Serif", Font.BOLD, 20));
            header.setBackground(Color.decode("#54aeef"));
            header.setForeground(Color.WHITE);
           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
           centerRenderer.setBorder(null);
           table.setFont(new Font("Serif", Font.PLAIN, 16));
         
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
            {
                table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
            }
            
    
            table.setShowVerticalLines(false);
    
   
   
       table.setRowHeight(37);
       table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 17));
       table.getTableHeader().setPreferredSize(new Dimension(100, 35));
       table.setPreferredScrollableViewportSize(new Dimension(200, 50));
       table.setFillsViewportHeight(true);
       JScrollPane scrollPane = new JScrollPane(table);
       scrollPane.setBorder(new LineBorder(Color.WHITE, 5));
       
       symp.add(scrollPane);
       
       
       pasapp();

        
        
        frame.add(chartPanel, BorderLayout.CENTER);
        ///frame.add(update,BorderLayout.SOUTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chart");
        frame.pack();
        frame.setVisible(true);
    }


    public void pasapp()
    {
        String[] coloumnNames = {"Symptoms"};
        

       model = new DefaultTableModel(coloumnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {       
                return false; // or a condition at your choice with row and column
            }
         };
         
           table = new JTable(model);
           table.setRowSelectionAllowed(false);
           
    
            JTableHeader header = table.getTableHeader();
            header.setBorder(null);
            header.setFont(new Font("Serif", Font.BOLD, 20));
            header.setBackground(Color.decode("#54aeef"));
            header.setForeground(Color.WHITE);
           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
           centerRenderer.setBorder(null);
           table.setFont(new Font("Serif", Font.PLAIN, 16));
         
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
            {
                table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
            }
            
    
            table.setShowVerticalLines(false);
    
            addToTable(listing.getPatientsList().get(row));
    
   
       table.setRowHeight(37);
       table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 17));
       table.getTableHeader().setPreferredSize(new Dimension(100, 35));
       table.setPreferredScrollableViewportSize(new Dimension(200, 50));
       table.setFillsViewportHeight(true);
       JScrollPane scrollPane = new JScrollPane(table);
       scrollPane.setBorder(new LineBorder(Color.decode("#54aeef"), 5));

       
       

       pastap.add(scrollPane);
       
       
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

    private void addToTable(patient p)
    {
        model.setRowCount(0);
        String[] item = p.get_sym();
        model.addRow(item);

    }

    private class CloseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //DO SOMETHING
            frame.setVisible(false);
            
        }
    }

   
    
    
}
