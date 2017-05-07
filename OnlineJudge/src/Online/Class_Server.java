package Online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class Class_Server {

    static DataOutputStream dout;   //It is made static so that no other Output stream is created while another object of it is created.
    static DataInputStream din;
    static ServerSocket ss;
    static Socket s;
    int Port = 6666;//Input stream of the socket created
    static int flag = 0;
    static String query = "";
    static int i = 1;
    static int j = 1;
    static int k = 1;
    static String link = "";

    public void start() {
        try {
            ss = new ServerSocket(Port);   //Creates a server socket on the server side and on the port 6666.
            s = new Socket(); //New Object of the socket
            s = ss.accept();    //Waits till a client receives the requesst
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("accepted");
        } catch (IOException ex) {
            Logger.getLogger(Class_Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stop() {

        try {
            s.close();
            ss.close();
            din.close();
            dout.close();

        } catch (IOException ex) {
            Logger.getLogger(Class_Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listen() {
        while (true) {
            String str = "abcdd";
            try {
                str = din.readUTF();
                //System.out.println(str);
            } catch (Exception e) {
                str = "xyz";
            }
            try {
                if (str.equals("stop")) {
                    din.close();
                    dout.close();
                    s.close();
                    ss.close();
                    start();
                    System.out.println("fuck pratiush");
                }

                if (str.startsWith("Problem")) {
                    limks lim = new limks();
                    lim.fetch(str);
                }

                if (str.startsWith("Insert")) {
                    flag = 1;
                    query = str;
                }

                if (str.startsWith("Questions")) {

                    String fil = "ques" + i++ + ".txt";
                    link += ("C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Problems\\" + fil);
                    link += "\n";
                    PrintWriter writer = null;
                    //System.out.println(str);
                    System.out.println(link);
                    writer = new PrintWriter("C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Problems\\" + fil);
                    writer.println(str);
                    writer.close();
                    //database db = new database();
                }

                if (str.startsWith("Name")) {
                    String x = str.substring(4);
                    System.out.println(x);
                    System.out.println(link);
                    database db = new database();
                    String query = "Update contest SET links = '" + link + "' Where name = '" + x + "'";
                    db.opendb(query, 0);
                    link = "";
                    System.out.println("successful");
                }
                String path="C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Problems\\";
                if (str.startsWith("sendproblems")) {
                    String name = str.substring(12);
                    String query1 = "Select links from contest where name='"+name+"'";
                    database db = new database();
                    query1 = db.query2(query1);
                    String linklist[] = query1.split("\n");
                    int l = linklist.length;
                    
                    Readfiles rf = new Readfiles();
                    new Thread(new Runnable() {
                        public void run() {
                            String problem="";
                            for (int k = 0; k < l; k++) {
                                String path2 = linklist[k].substring(55);
                                System.out.println(path2);
                                
                                try {
                                    problem+= rf.read(path+path2);
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(Class_Server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                

                            }
                            try {
                                dout.flush();
                                dout.writeUTF(problem);
                            } catch (IOException ex) {
                                Logger.getLogger(Class_Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                        }
                    }).start();

                }
                
                if(str.startsWith("compilationstatus"))
                {
                    
                }
                
                if(str.startsWith("input"))
                {
                    String inp = "inp" + j++ + ".txt";
                    
                    PrintWriter writer = null;
                    writer = new PrintWriter("C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Input\\" + inp);
                    writer.println(str);
                    writer.close();
                }
                
                if(str.startsWith("output"))
                {
                    String out = "out" + k++ + ".txt";
                    
                    PrintWriter writer = null;
                    writer = new PrintWriter("C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Output\\" + out);
                    writer.println(str);
                    writer.close();
                }

            } catch (Exception e) {
                System.out.println("exception in class_server");
            }
        }
    }

}
