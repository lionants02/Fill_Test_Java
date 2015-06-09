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
    public static C_node_img getCenter(Image img){
        C_node_img highwidth=getHighWidth(img);
        return new C_node_img(highwidth.getX()/2, highwidth.getY()/2);
    }
    public static C_node_img getHighWidth(Image img){
        return new C_node_img(img.getWidth(null), img.getHeight(null));
    }
    
    public BufferedImage my_fillMaps(String file){
        try {
            Image orgimg=getImage1(file);
            return my_fillCenter(orgimg,0x0000ff00);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    public void my_fillMaps(String file,String output){
        try {
            Image orgimg=getImage1(file);
            BufferedImage img = my_fillCenter(orgimg,0x0000ff00);
            writeImg(output, img);
        } catch (IOException ex) {
            Logger.getLogger(C_myfunc.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public BufferedImage my_fillCenter(Image img,int color){
        C_node_img highwidth=getHighWidth(img);
        C_node_img center =getCenter(img);
        C_node_queue quele=new C_node_queue();
        
        BufferedImage imgorg=toBufferedImage(img);
        BufferedImage imgpaint=new BufferedImage(highwidth.getX(), highwidth.getY(), BufferedImage.TYPE_INT_ARGB);
        //BufferedImage imgpaint=toBufferedImage(img);
        
        quele.my_setEmp();
        quele.my_add(center);
        C_node_img n=quele.my_next();
        int xmin=0,xmax=0,ymin=0,ymax=0;
        //int rgb1=imgpaint.getRGB(n.getX(), n.getY());
        //int rgb2=imgorg.getRGB(n.getX(), n.getY());
        long i =0;
        color|=0xff000000;
        while(n!=null){
            
            if(checkColor(imgorg, imgpaint , 0, n.getX(), n.getY())){
            i++;
                imgpaint.setRGB(n.getX(), n.getY(), color);
                //เพิ่มตำแหน่งสุดท้ายคิว
                if(checkColor(imgorg, imgpaint , 0, n.getX(), n.getY()-1))quele.my_add(new C_node_img( n.getX(), n.getY()-1));
                if(checkColor(imgorg, imgpaint , 0, n.getX()+1, n.getY()))quele.my_add(new C_node_img( n.getX()+1, n.getY()));
                if(checkColor(imgorg, imgpaint , 0, n.getX(), n.getY()+1))quele.my_add(new C_node_img( n.getX(), n.getY()+1));
                if(checkColor(imgorg, imgpaint , 0, n.getX()-1, n.getY()))quele.my_add(new C_node_img( n.getX()-1, n.getY()));
            }
            n=quele.my_next();
            
        }
        return imgpaint;
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
    
    public void applyGrayscaleMaskToAlpha(BufferedImage image, BufferedImage mask) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] imagePixels = image.getRGB(0, 0, width, height, null, 0, width);
        int[] maskPixels = mask.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < imagePixels.length; i++) {
            int color = imagePixels[i] & 0x00ffffff; // Mask preexisting alpha
            int alpha = maskPixels[i] << 24; // Shift green to alpha
            imagePixels[i] = color | alpha;
        }

        image.setRGB(0, 0, width, height, imagePixels, 0, width);
    }
}
