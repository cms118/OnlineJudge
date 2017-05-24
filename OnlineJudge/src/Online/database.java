package Online;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class database {
    public int cnt,c2;
    ResultSet rs;
    boolean f;
    Class_Server serv = new Class_Server();
    public void opendb(String query,int flag) throws IOException
    {
        try {
    
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/judge","root","root");
            Statement smt = conn.createStatement();
            if(flag==0){
                try
                {
            smt.executeUpdate(query);
                }
                catch(Exception e)
                {
                   serv.dout.flush();
                   serv.dout.writeUTF("AlreadyExists");
                }
                
            }
            else if(flag==1){
            smt.executeQuery(query);
            rs =smt.executeQuery(query);     
            boolean f=rs.next();
            if(!f){
                cnt=0;
                JOptionPane.showMessageDialog(null,"user doesnt exist");
            }
            else{
                cnt=1;
            }
            }
            else if(flag==2){
                //Statement sm = conn.createStatement();
            smt.executeQuery(query);
            rs =smt.executeQuery(query);     
            f=rs.next();
            if(!f){
                c2=0;
                JOptionPane.showMessageDialog(null,"There are presently no live contests");
            }
            else{
                c2=1;
            }    
            }
            if(flag!=2)
            conn.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error 1");
            
        } catch (SQLException ex) {
            serv.dout.flush();
            serv.dout.writeUTF("AlreadyExists");
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int query(String sql)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/judge","root","root");
                Statement smt = conn.createStatement();
                rs =smt.executeQuery(sql);
                rs.last();
            } catch (SQLException ex) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            return rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;       
    }
    
    public String query2(String sql)
    {
        String str="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/judge","root","root");
                Statement smt = conn.createStatement();
                //smt.executeQuery(sql);
                ResultSet rs=null;
                rs =smt.executeQuery(sql); 
                rs.first();
                str = rs.getString("links");
                System.out.println(str);
            } catch (SQLException ex) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         return str;   
    }
}
