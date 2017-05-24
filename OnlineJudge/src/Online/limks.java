/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Online;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class limks {
    
    public void fetch(String str)
    {
        Class_Server cls = new Class_Server();
        //database db= new database();
        try {
            System.out.println(1);
            System.out.println(2);
            String path = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\OnlineJudge\\Questions\\";
            System.out.println(3);
            Readfiles rf = new Readfiles();
            System.out.println(path+str);
            String str1 = rf.read(path+str+".txt");
            System.out.println(5);
            System.out.println(str1);
            cls.dout.writeUTF(str1);
        } catch (IOException ex) {
            Logger.getLogger(limks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void upload()
    {
        
    }
    
}
