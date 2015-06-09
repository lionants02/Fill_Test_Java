/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill_tool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.fill.my_etc.C_myfunc;
import my.fill.my_etc.C_myfuncAutoZoom;
import my.fill.my_etc.C_myfunc_makeURL;

/**
 *
 * @author SSI
 */
public class Fill_Tool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        C_myfuncAutoZoom myfunczoom=new C_myfuncAutoZoom();
        myfunczoom.my_fillMapsAuto(102.9288784, 16.4877914, "C:\\Users\\SSI\\Desktop\\max\\กรมที่ดิน\\ภาพแปล1.png");
        
        /*C_myfunc func=new C_myfunc();
        C_myfunc_makeURL funcurl=new C_myfunc_makeURL();
        //func.my_fillMaps("C:\\Users\\SSI\\Desktop\\max\\กรมที่ดิน\\ภาพแปลง.png","C:\\Users\\SSI\\Desktop\\max\\กรมที่ดิน\\ภาพแปล1.png");
        funcurl.setZoom(5);
        System.out.println(funcurl.getUrlMaps(102.9288784,16.4877914));
        try {
            func.my_fillMaps(new URL(funcurl.getUrlMaps(102.9288784,16.4877914)), "C:\\Users\\SSI\\Desktop\\max\\กรมที่ดิน\\ภาพแปล1.png");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Fill_Tool.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
}
