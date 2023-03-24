/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkies.server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * <b>This class is used to handle the View</b>
 * @author Asish Kumar Patri
 * @author DhruvNath
 * @author Priyansu Meher
 * 
 * <br>
 * Controller class is used to Handle any kind of Event from view
 * It uses Model class to fetch the bussiness logic and update the view object
 *
 */
public class controller implements MouseListener, KeyListener, ActionListener, WindowListener {

    view v;
    model m;
    SwingWorker<Object, Object> serverCon, serverRead, logTh, exptnTh, infoTh;
    JFrame log, exc, info, about;

    /**
     * Constructor of controller
     * @param v View class Object for accessing View class fields and update the frontend
     */
    public controller(view v) {
        this.v = v;
        m = new model();
    }

    /**
     * This method create two thread
     * <br>
     * One for open a seamless connection between many Clint
     * <br>
     * One for read regularly and update the chat Area
     * 
     */
    private void server() {
        if (serverCon == null) {
            serverCon = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    m.serverConnection(Integer.parseInt(v.jtfr2.getText()));
                    return null;
                }
            };
            serverCon.execute();
        }
        if (serverRead == null) {
            serverRead = new SwingWorker<Object, Object>() {
                @Override
                protected Object doInBackground() throws Exception {
                    while (true) {
                        v.jpc1lb3.setText(m.getDate());
                        String msg = m.serverRead();
                        m.writeLeft(msg, v.jpc2);
                    }
                }
            };
            serverRead.execute();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String name = e.getComponent().getName();
        switch (name) {
            case "photo": {
            }
            case "next": {
                if (!v.jtfr1.getText().isEmpty() && !v.jtfr2.getText().isEmpty()) {
                    v.jpc1lb2.setText(v.jtfr1.getText());
                    server();
                    v.jtfr1.setEditable(false);
                    v.jtfr2.setEditable(false);
                    v.cl.next(v.jf);
                }
                break;
            }
            case "send": {
                String msg = v.jpc3tf1.getText();
                m.writeLeft("You :" + msg, v.jpc2);
                v.jpc3tf1.setText(null);
                m.serverSend(v.jtfr1.getText().substring(0, 3) + " :" + msg);
                break;
            }
            case "prev": {
                v.cl.previous(v.jf);
                break;
            }
            default:
                System.out.println(e.getComponent().getName() + ":" + name);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getComponent().getName()) {

            case "name": {
                if (!v.jtfr1.getText().isEmpty() && !v.jtfr2.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    v.jpc1lb2.setText(v.jtfr1.getText());
                    server();
                    v.jtfr1.setEditable(false);
                    v.jtfr2.setEditable(false);
                    v.cl.next(v.jf);
                }
                break;
            }
            case "host": {
                if (!v.jtfr1.getText().isEmpty() && !v.jtfr2.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    v.jpc1lb2.setText(v.jtfr1.getText());
                    server();
                    v.jtfr1.setEditable(false);
                    v.jtfr2.setEditable(false);
                    v.cl.next(v.jf);
                }
                break;
            }

            case "text": {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String msg = v.jpc3tf1.getText();
                    m.writeLeft("You :" + msg, v.jpc2);
                    v.jpc3tf1.setText(null);
                    m.serverSend(v.jtfr1.getText().substring(0, 3) + " :" + msg);
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Information": {
                try {
                    if (info == null) {
                        info = new JFrame("Information Window");
                        info.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        info.setName(e.getActionCommand());
                        info.setLayout(new BoxLayout(info.getContentPane(), BoxLayout.Y_AXIS));
                        info.setBounds(400, 400, 300, 150);
                        info.addWindowListener(this);
                        String ip = InetAddress.getLocalHost().toString();
                        JLabel l = new JLabel();
                        JScrollPane pane = new JScrollPane(l);
                        info.add(pane);
                        info.setVisible(true);
                        infoTh = new SwingWorker<Object, Object>() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                while (true) {
                                    String port = v.jtfr2.getText();
                                    String name = v.jtfr1.getText();
                                    String time = m.getDate();
                                    String infor = String.format("<html>InetAddress :%s"
                                            + "<br>Port No :%s"
                                            + "<br>Name :%s"
                                            + "<br>Time :%s"
                                            + "</html>", ip, port, name, time);
                                    l.setText(infor);
                                    pane.validate();
                                }

                            }
                        };
                        infoTh.execute();
                    } else {
                        info.setVisible(true);

                    }

                } catch (UnknownHostException ex) {
                    info.dispose();
                }
                break;
            }
            case "logInfo": {
                if (log == null) {
                    log = new JFrame("Status window");
                    log.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    log.setName(e.getActionCommand());
                    log.setBounds(400, 400, 400, 200);
                    log.addWindowListener(this);
                    JPanel p = new JPanel();
                    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
                    JScrollPane sp = new JScrollPane(p);
                    JLabel l = new JLabel();
                    p.add(l);
                    log.add(sp);
                    log.setVisible(true);
                    logTh = new SwingWorker<Object, Object>() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            while (true) {
                                l.setText("<html>" + m.getStatus() + "</html>");
                                p.validate();
                            }
                        }
                    };

                    logTh.execute();

                } else {
                    log.setVisible(true);
                    log.validate();

                }
                break;

            }

            case "Exception": {
                if (exc == null) {
                    exc = new JFrame("Exception window");
                    exc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    exc.setName(e.getActionCommand());
                    exc.setBounds(400, 400, 400, 200);
                    exc.addWindowListener(this);
                    exc.setLayout(new BoxLayout(exc.getContentPane(), BoxLayout.Y_AXIS));
                    JLabel l1 = new JLabel();
                    exc.add(l1);
                    exc.setVisible(true);
                    exptnTh = new SwingWorker<Object, Object>() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            while (true) {
                                l1.setText("<html>" + m.getException() + "</html>");
                                exc.validate();
                            }
                        }
                    };

                    exptnTh.execute();

                } else {
                    exc.setVisible(true);
                    exc.validate();
                }
                break;
            }
            case "Setting": {
                break;
            }
            case "About": {
                if (about == null) {
                    about = new JFrame("About Window");
                    about.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    about.addWindowListener(this);
                    about.setName(e.getActionCommand());
                    about.setBounds(200, 200, 350, 150);
                    JLabel l = new JLabel();
                    l.setAlignmentX(Component.CENTER_ALIGNMENT);
                    l.setAlignmentY(Component.CENTER_ALIGNMENT);
                    String notice = "This Programme is CopyRight to"
                            + "<br>Asish kumar Patri"
                            + "<br>DhruvNath<"
                            + "<br>Priyansu Meher"
                            + "<br>Developed by last of December under GNU License ";
                    String copyRight = String.format("<html><p style=\"width:250px\">%s</p></html>", notice);
                    l.setText(copyRight);
                    about.add(l, BorderLayout.CENTER);
                    about.setVisible(true);
                } else {
                    about.setVisible(true);
                }
                break;
            }
            case "clintHelp": {
                break;
            }
            case "serverHelp": {
                break;
            }
            case "Configuration": {
                break;
            }
            default:
                System.out.println(e.paramString());

        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
        switch (e.getComponent().getName()) {
            case "mainForm": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "Information": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "logInfo": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "Exception": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "Setting": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "About": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "clintHelp": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "serverHelp": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }
            case "Configuration": {
                m.addStatus(String.format("%s form Opened", e.getComponent().getName()));
                break;
            }

        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        Component comp = e.getComponent();

        switch (comp.getName()) {
            case "mainForm": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                break;
            }
            case "Information": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                infoTh.cancel(true);
                infoTh = null;
                info = null;
                break;
            }
            case "logInfo": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                logTh.cancel(true);
                logTh = null;
                log = null;
                break;
            }
            case "Exception": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                exptnTh.cancel(true);
                exptnTh = null;
                exc = null;
                break;
            }
            case "Setting": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                break;
            }
            case "About": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                about = null;
                break;
            }
            case "clintHelp": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                break;
            }
            case "serverHelp": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                break;
            }

            case "Configuration": {
                m.addStatus(String.format("%s is Closed", comp.getName()));
                break;
            }

        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
