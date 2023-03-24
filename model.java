/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkies.server;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Asish Kumar Patri
 * @author Dhruba Nath
 * @author Priyansu Meher
 * <br>
 * This class is used to write Business Logic<br>
 * Controller class use this class's objects to update view
 * This class has no dependency with other classes
 * All the method of this class is public
 * Any one can write any algorism in this class
 * Future add on and other feature can be written in this class for testing purpose
 * 
 * 
 */
public class model {
    String exc , status ;
    ServerSocket ss = null;
    Socket s1;
    PrintStream ps1;
    Scanner sc1;
    int Conneciton_no;

    /**
     * Model class Constructor
     */
    public model() {
        exc="";
        status="";
    }

    /**
     * Used to get recent date and time
     * @return String representing Current date and time
     */
    public String getDate() {
        Date date = new Date();
        return date.toString();
    }

    /**
     * Used for Server site Connection
     * @param port Integer represent port number where Server Run
     */
    public void serverConnection(int port) {
        Conneciton_no = 0;
        try {
            if (ss == null) {
                ss = new ServerSocket(port);
                addStatus("Server connectectd to an address...");
            }
            while (true) {
                addStatus("Server is waiting for a connection....");
                s1 = ss.accept();
                Conneciton_no++;
                addStatus("Connected :" + Conneciton_no + "\n");
            }
        } catch (IOException ex) {
            exc += "<br>" + ex + "<br>";
        }
    }

    /**
     * Used to send massage
     * @param msg String represent message to send 
     */
    public void serverSend(String msg) {
        try {
            if (ss != null && s1 != null && s1.isConnected() && !msg.isEmpty()) {
                if (ps1 == null) {
                    ps1 = new PrintStream(s1.getOutputStream());
                    addStatus("Server is ready to send data");
                }
                ps1.println(msg);
                addStatus("Server send successifully");
            }
        } catch (IOException ex) {
            exc += "<br>" + ex + "<br>";
        }
    }

    /**
     * This method used by Server to read what is sent by Clints
     * @return String that Receved by Server 
     * 
     */
    public String serverRead() {
        String msg = null;
        try {
            if (ss != null && s1 != null && s1.isConnected()) {
                if (sc1 == null) {
                    sc1 = new Scanner(s1.getInputStream());
                    addStatus("Server is ready to read...");
                }
                if (sc1.hasNextLine()) {
                    msg = sc1.nextLine();
                    addStatus("Server read Succifully...");
                }
            }
        } catch (Exception ex) {
            exc += "<br>" + ex + "<br>";
        }
        return msg;
    }

    /**
     * This method is for Write Strings at leftSide of a panned
     * @param msg massage to Write
     * @param panel Where to Write
     */
    public void writeLeft(String msg, JPanel panel) {
        if (msg != null && !msg.isEmpty()) {
            JLabel jl = new JLabel("<html><p style=\"width:250px\">" + msg + "</p></html>");
            jl.setAlignmentX(Component.LEFT_ALIGNMENT);
            Font f = new Font(Font.SERIF, Font.BOLD, 20);
            jl.setFont(f);
            panel.add(jl);
            panel.validate();
        }
    }

    /**
     * This method used to add any kind of Status 
     * This is useful in debugging purpose
     * These Status are returned by getStatus() method
     * @param status Current Status of programme
     */
    public void addStatus(String status) {
        this.status += status + "<br>";
    }

    /**
     * provide any Exception while execution
     * @return String representing any Exception while programme is running
     */
    public String getException() {
        return exc;
    }

    /**
     * This method provide any kind of Status 
     * This is useful in debugging purpose
     * @return String represents Current Status of programme
     */
    public String getStatus(){
        return status;
    }
}
