/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkies.clint;

/**
 *
 * @author Asish Kumar Patri
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Asish Kumar Patri
 */
public class view {
    controller c;
    JFrame jfr;
    JPanel jf;
    Font f;
    JMenu menu1,menu2,menu3;
    CardLayout cl;
    JPanel jpc1,jpc2, jpc3;
    JButton jbtnr1,jbtnr2,jpc1bt1,jpc3bt1;
    JLabel jlbr1,jlbr2,jlbr3,jpc1lb1,jpc1lb2,jpc1lb3,log;
    JTextField jtfr1,jtfr2,jtfr3,jpc3tf1;
    Color darkGreen=new Color(60,179,113);
    GridBagConstraints gbc;
    BoxLayout boxl;

    /**
     *
     */
    public view(){
        c=new controller(this);
        mainForm();
    }

    /**
     *
     */
    public void form1(){
        JPanel jpr=new JPanel();
        jpr.setBackground(null);
        jpr.setLayout(new GridBagLayout());
        
        
        
        gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.VERTICAL;
        jbtnr1=new JButton(new ImageIcon(getClass().getResource("assets/person.png")));
        jbtnr1.setBackground(Color.white);
        jbtnr1.setName("photo");
        jbtnr1.addMouseListener(c);
        gbc.insets=new Insets(100, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.weightx=0;
        gbc.ipady=0;
        gbc.ipadx=0;
        jpr.add(jbtnr1,gbc);
        
        
        jlbr1=new JLabel("Name : ");
        f=new Font(Font.MONOSPACED,Font.BOLD,20);
        jlbr1.setFont(f);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(20, 50, 0, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.weightx=0;
        gbc.weighty=1;
        jpr.add(jlbr1,gbc);
        
        jtfr1=new JTextField();
        jtfr1.setFont(f);
        jtfr1.setName("name");
        jtfr1.setToolTipText("Enter Your Name");
        gbc.insets=new Insets(20, 0, 0, 50);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.weightx=0.9;
        gbc.weighty=1;
        jpr.add(jtfr1,gbc);
        jtfr1.addKeyListener(c);
        
        jlbr2=new JLabel("Server IP :");
        jlbr2.setFont(f);
        gbc.insets=new Insets(20, 50, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.weightx=0;
        gbc.weighty=1;
        jpr.add(jlbr2,gbc);
        
        jtfr2=new JTextField();
        jtfr2.setFont(f);
        jtfr2.setName("host");
        jtfr2.setToolTipText("People with same Port number can communicate with each other");
        gbc.insets=new Insets(20, 0, 0, 50);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.weightx=1;
        gbc.weighty=1;
        jpr.add(jtfr2,gbc);
        
        jlbr3=new JLabel("Port :");
        jlbr3.setFont(f);
        gbc.insets=new Insets(20, 50, 0, 0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.weightx=0;
        gbc.weighty=1;
        jpr.add(jlbr3,gbc);
        
        jtfr3=new JTextField();
        jtfr3.setFont(f);
        jtfr3.setName("host");
        jtfr3.setToolTipText("People with same Port number can communicate with each other");
        gbc.insets=new Insets(20, 0, 0, 50);
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.weightx=1;
        gbc.weighty=1;
        jpr.add(jtfr3,gbc);
        jtfr3.addKeyListener(c);
        
        
        jbtnr2=new JButton("Next");
        jbtnr2.setName("next");
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.insets=new Insets(20, 50, 100, 50);
        jpr.add(jbtnr2,gbc);
        jbtnr2.addMouseListener(c);
        jbtnr2.addKeyListener(c);
       
       jf.add(jpr); 
    }

    /**
     *
     */
    public void form2(){
        JPanel jpc=new JPanel();
        gbc=new GridBagConstraints();
        jpc.setBackground(Color.ORANGE);
        jpc.setLayout(new GridBagLayout());
       
        
        jpc1=new JPanel();
        jpc1.setBackground(darkGreen);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weighty=0.015;
        gbc.weightx=0.15;
        gbc.fill=GridBagConstraints.BOTH;
        jpc.add(jpc1,gbc);
        
        jpc1.setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.BOTH;
        jpc1bt1=new JButton(new ImageIcon(getClass().getResource("assets/back.png")));
        jpc1bt1.setName("prev");
        jpc1bt1.addMouseListener(c);
        jpc1bt1.addKeyListener(c);
       
        jpc1bt1.setBackground(null);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridheight=2;
        gbc.ipadx=0;
        gbc.ipady=0;
        jpc1.add(jpc1bt1,gbc);
        
        jpc1lb1=new JLabel(new ImageIcon(getClass().getResource("assets/persion.png")));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.insets=new Insets(0, 5, 0, 15);
        jpc1.add(jpc1lb1,gbc);
        gbc.insets=new Insets(0, 0, 0,0 );
        
        jpc1lb2=new JLabel("Asish kumar patri");
        jpc1lb2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        Font f=new Font(Font.SANS_SERIF,Font.BOLD,18);
        jpc1lb2.setFont(f);
        gbc.insets=new Insets(10, 0, 0,0 );
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0.5;

        jpc1.add(jpc1lb2,gbc);
        
        jpc1lb3=new JLabel("23 jan 2022");
        jpc1lb3.setAlignmentY(Component.TOP_ALIGNMENT);
        f=new Font(Font.SANS_SERIF,Font.BOLD,15);
        jpc1lb3.setFont(f);
        gbc.insets=new Insets(0, 0, 10,0 );
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.weightx=1;
        gbc.weighty=0.5;
        jpc1.add(jpc1lb3,gbc);
        gbc.insets=new Insets(0, 0, 0,0 );

        jpc2=new JPanel();
        jpc2.setBackground(Color.white);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weightx=1;
        gbc.weighty=0.8;
        gbc.fill=GridBagConstraints.BOTH;
        
        JScrollPane jsp=new JScrollPane(jpc2);
        jpc.add(jsp,gbc);
        boxl=new BoxLayout(jpc2, BoxLayout.PAGE_AXIS);
        jpc2.setLayout(boxl);
        jpc2.setBorder(new EmptyBorder(10,20,10,20));

        
        jpc3=new JPanel();
        jpc3.setBackground(darkGreen);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.weighty=0.025;
        gbc.weightx=0.5;
        gbc.fill=GridBagConstraints.BOTH;
        jpc.add(jpc3,gbc);
        jpc3.setLayout(new GridBagLayout());
        
        jpc3tf1=new JTextField();
        jpc3tf1.setMargin(new Insets(0, 10, 0, 0));
        f=new Font(Font.MONOSPACED,Font.BOLD,20);
        jpc3tf1.setFont(f);
        jpc3tf1.setName("text");
        jpc3tf1.setToolTipText("Enter msg here");
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=0.8;
        gbc.insets=new Insets(10, 10, 10, 10);
        jpc3.add(jpc3tf1,gbc);
        jpc3tf1.addKeyListener(c);
        
        
        jpc3bt1=new JButton(new ImageIcon(getClass().getResource("assets/send.png")));
        jpc3bt1.setName("send");
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx=0;
        gbc.insets=new Insets(10, 10, 10, 10);
        jpc3.add(jpc3bt1,gbc);
        jpc3bt1.addMouseListener(c);
       
        jf.add(jpc);
        jpc3bt1.setBackground(null);

    }

    /**
     *
     * @param items
     * @param name
     * @return
     */
    public JMenu createMenu(JMenuItem[] items,String name){
        JMenu menu=new JMenu(name);
        for(int i=0;i<items.length;i++){
            menu.add(items[i]);
            items[i].setName(items[i].getText());
            items[i].addActionListener(c);
        }
        return menu;
    }

    /**
     *
     */
    public void mainForm(){
        jfr=new JFrame("Clint");
        jfr.setName("mainForm");
        jfr.addWindowListener(c);
        jfr.setLayout(new BorderLayout());
        menu1=createMenu(new JMenuItem[]{new JMenuItem("Setting"),new JMenuItem("About")}, "File");
        menu2=createMenu(new JMenuItem[]{new JMenuItem("Information"),new JMenuItem("logInfo"),new JMenuItem("Exception")},"LogCat");
        menu3=createMenu(new JMenuItem[]{new JMenuItem("clintHelp"),new JMenuItem("serverHelp"),new JMenuItem("Configuration")},"Help");
        JMenuBar mb=new JMenuBar();
        mb.add(menu1);
        mb.add(menu2);
        mb.add(menu3);
        jfr.getContentPane().add(mb,BorderLayout.PAGE_START);
        jf=new JPanel();
        jfr.setBounds(200, 200, 450, 700);
        jfr.setResizable(false);
        jfr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cl=new CardLayout();
        jf.setLayout(cl);
        jfr.add(jf);
        form1();
        form2();
        jfr.setVisible(true);
        
    }
    
}
