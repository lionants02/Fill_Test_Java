/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fill.my_etc;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSI
 */
public class C_myfuncAutoZoom extends C_myfunc{
    private int limit_width = 900;
    private int limit_height = 900;

    public BufferedImage my_fillMapsAuto(double lat, double longg) {
        C_myfunc_makeURL funcurl=new C_myfunc_makeURL();
        URL url=null;
        try {
            url=new URL(funcurl.getUrlMaps(lat, longg));
        } catch (MalformedURLException ex) {
            Logger.getLogger(C_myfuncAutoZoom.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage orgimg = my_fillMaps(url);
        double multiplexvalue=1;
        int height = orgimg.getHeight();
        int width = orgimg.getWidth();
        if (width > height) {//x มากกว่า y ใช่ไหม
            multiplexvalue=((double)limit_width/(double)width);
        } else {
            multiplexvalue=((double)limit_height/(double)height);
        }
        funcurl.setZoom(multiplexvalue);
        try {
            url=new URL(funcurl.getUrlMaps(lat, longg));
        } catch (MalformedURLException ex) {
            Logger.getLogger(C_myfuncAutoZoom.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return my_fillMaps(url);
    }
     public void my_fillMapsAuto(double lat, double longg,String output) {
        BufferedImage orgimg =   my_fillMapsAuto(lat, longg);
         writeImg(output, orgimg);
     }
}
