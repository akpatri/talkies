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
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Asish Kumar Patri
 */
public class model {
    String exc="",status="",info="";
    ServerSocket ss=null;
    Socket s1, s2;
    PrintStream ps1, ps2;
    Scanner sc1, sc2;
    int Conneciton_no;

    /**
     *
     */
    public model(){
       
    }

    /**
     *
     * @return
     */
    public String getDate() {
        Date date = new Date();
        return date.toString();
    }

    /**
     *
     * @param address
     * @param port
     */
    public void clintConnection(InetAddress address, int port) {
        try {
                if(s2==null){
                    s2=new Socket(address, port);
                    addStatus("Clint created sucessifully");
                    
                }
                if (s2.isConnected()) {
                    
                    addStatus("clint connected");
                } else {
                    
                    addStatus("clint attempted uncessifull");
                    s2.close();
                }
            
        } catch (IOException ex) {
            exc+="<br>"+ex+"<br>";
        }
    }

    /**
     *
     * @param msg
     */
    public void clintSend(String msg) {
        if (s2!=null && s2.isConnected() && !msg.isEmpty()) {
            try {
                if(ps2==null){
                    ps2 = new PrintStream(s2.getOutputStream());
                    addStatus("clint is ready to send...");
                    
                }
                ps2.println(msg);
                
                addStatus("clint sent sucessifully..");
                
            } catch (IOException ex) {
                exc+="<br>"+ex+"<br>";
            }

        }
    }

    /**
     *
     * @return
     */
    public String clintRead() {
        String msg = null;
        if (s2!=null && s2.isConnected()) {
            try {
                if(sc2==null){
                    sc2 = new Scanner(s2.getInputStream());
                    
                    addStatus("clint is ready to read..");
                }
                if (sc2.hasNextLine()) {
                    msg = sc2.nextLine();
                    //status+="clint read sucessifully<br>";
                    addStatus("clint read sucessifully");
                }
            } catch (IOException ex) {
                exc+="<br>"+ex+"<br>";
            }
        }
        return msg;
    }

    /**
     *
     * @param msg
     * @param panel
     */
    public void writeLeft(String msg, JPanel panel) {
        if (msg!=null && !msg.isEmpty()) {
            JLabel jl = new JLabel("<html><p style=\"width:250px\">"+msg+"</p></html>");
            jl.setAlignmentX(Component.LEFT_ALIGNMENT);
            Font f = new Font(Font.SERIF, Font.BOLD, 20);
            jl.setFont(f);
            panel.add(jl);
            panel.validate();
        }
    }

    /**
     *
     * @return
     */
    public String getStatus(){
        return status;
    }

    /**
     *
     * @return
     */
    public String getException(){
        return exc;
    }

    /**
     *
     * @param status
     */
    public void addStatus(String status){
        this.status+=status+"<br>";
    }
}

