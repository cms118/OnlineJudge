/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Online;

import javax.swing.JLabel;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Asus
 */
public class NewClass {
    private int i=10;
    public int getint(int a)
    {
        i++;
        return 0;
    }
    public class myclass{
        public int getd(int b)
        {
            i++;
            return 0;
        }
    }
    myclass obj=new myclass();
    int c=obj.getd(2);
}
