/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fill.my_etc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import my.fill.strct.C_node_img;
import my.fill.strct.C_node_queue;

/**
 *
 * @author SSI
 */
public class C_myfunc{
    /**
     * gif png jpg
     * @param filename
     * @return 
     */
    public static Image getImage1(String filename) throws IOException{
        //Image img=Toolkit.getDefaultToolkit().getImage(filename);
        Image img=ImageIO.read(new File(filename));
        return img;        
    }
    public static Image getImage1(URL filename) throws IOException{
        //Image img=Toolkit.getDefaultToolkit().getImage(filename);
        Image img=ImageIO.read(filename);
        return img;        
    }
    public static C_node_img getCenter(Image img){
        C_node_img highwidth=getHighWidth(img);
        return new C_node_img(highwidth.getX()/2, highwidth.getY()/2);
    }
    public static C_node_img getHighWidth(Image img){
        return new C_node_img(img.getWidth(null), img.getHeight(null));
    }
    /**
     * รับเข้าไฟล์ส่งออก BufferImage
     * @param file
     * @return 
     */
    public BufferedImage my_fillMaps(String file,boolean crop){
        try {
            Image orgimg=getImage1(file);
            return my_fillCenter(orgimg,0x0000ff00,crop);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
        
    /**
     * รับเข้าและส่งออกเป็นไฟล์ png
     * @param file
     * @param output 
     */
    public void my_fillMaps(String file,String output,boolean crop){
        try {
            Image orgimg=getImage1(file);
            BufferedImage img = my_fillCenter(orgimg,0x0000ff00,crop);
            writeImg(output, img);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void my_fillMaps(URL url,String output,boolean crop){
        try {
            Image orgimg=getImage1(url);
            BufferedImage img = my_fillCenter(orgimg,0x0000ff00,crop);
            writeImg(output, img);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public BufferedImage my_fillMaps(URL url,boolean crop){
        try {
            Image orgimg=getImage1(url);
            BufferedImage img = my_fillCenter(orgimg,0x0000ff00,crop);
            return img;
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    public BufferedImage my_fillMaps(BufferedImage orgimg,boolean crop){
        return my_fillCenter(orgimg,0x0000ff00,crop);
    }
    
    
    public BufferedImage my_fillCenter(Image img,int color,boolean crop){
        C_node_img highwidth=getHighWidth(img);
        C_node_img center =getCenter(img);
        C_node_queue quele=new C_node_queue();
        
        BufferedImage imgorg=toBufferedImage(img);
        BufferedImage imgpaint=new BufferedImage(highwidth.getX(), highwidth.getY(), BufferedImage.TYPE_INT_ARGB);
        //BufferedImage imgpaint=toBufferedImage(img);
        
        quele.my_setEmp();
        quele.my_add(center);
        C_node_img n=quele.my_next();
        int xmin,xmax,ymin,ymax;
        xmin=xmax=n.getX();
        ymin=ymax=n.getY();
        //int rgb1=imgpaint.getRGB(n.getX(), n.getY());
        //int rgb2=imgorg.getRGB(n.getX(), n.getY());
        long i =0;
        color|=0xff000000;
        int x,y;
        while(n!=null){
            x=n.getX();
            y=n.getY();
            if(checkColor(imgorg, imgpaint , 0, x,y )){
            i++;
                imgpaint.setRGB(x, y, color);
                //เพิ่มตำแหน่งสุดท้ายคิว
                xmin=(x<xmin)?x:xmin;
                xmax=(x>xmax)?x:xmax;
                ymin=(y<ymin)?y:ymin;
                ymax=(y>ymax)?y:ymax;
                try{
                if(checkColor(imgorg, imgpaint , 0, x, y-1))quele.my_add(new C_node_img( x, y-1));
                }catch(Exception ex){
                    
                }
                try{
                if(checkColor(imgorg, imgpaint , 0, x+1, y))quele.my_add(new C_node_img( x+1, y));
                }catch(Exception ex){
                    
                }
                try{
                if(checkColor(imgorg, imgpaint , 0, x, y+1))quele.my_add(new C_node_img( x, y+1));
                }catch(Exception ex){
                    
                }
                try{
                if(checkColor(imgorg, imgpaint , 0, x-1, y))quele.my_add(new C_node_img( x-1, y));
                }catch(Exception ex){
                    
                }
                
            }
            n = quele.my_next();

        }
        if (crop) {
            BufferedImage copout = imgpaint.getSubimage(xmin, ymin, xmax - xmin, ymax - ymin);
            return copout;
        }else return imgpaint;
    }
    
    private boolean checkColor(BufferedImage img1,BufferedImage img2,int target,int x,int y){
        if(img1.getRGB(x, y)==target)
            if((img2.getRGB(x, y)&0xffffff)==target)
                return true;
        return false;
        //return (img1.getRGB(x, y)==target && img2.getRGB(x, y)==target);
    }

    /**
     * แปลง Image เป็น Buffer Image
     * @param img
     * @return 
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    public static void writeImg(String parth, BufferedImage imgdata) {
        File outputfile = new File(parth);
        try {
            ImageIO.write(imgdata, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
