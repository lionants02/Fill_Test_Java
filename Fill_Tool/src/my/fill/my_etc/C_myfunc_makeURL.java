/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fill.my_etc;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SSI
 */
public class C_myfunc_makeURL {
    private String head48="http://110.164.49.55/mapserver2014/mapagent/mapagent.fcgi?REQUEST=GetMap&SERVICE=WMS&VERSION=1.1.1&LAYERS=DolWMS/Layers/mvsparcel48&STYLES=&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&SRS=EPSG:4326&";
    private String head47="http://110.164.49.55/mapserver2014/mapagent/mapagent.fcgi?REQUEST=GetMap&SERVICE=WMS&VERSION=1.1.1&LAYERS=DolWMS/Layers/mvsparcel47&STYLES=&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&SRS=EPSG:4326&";
    private 
            int width=1660;
    private int height=1036;
    private double xlenge=0.009727227519f;//100
    private double ylenge=0.0060707275365f;//14
    private double xlenge_2;
    private double ylenge_2;
    
    Map<String,Double> tempposition=null;

    public C_myfunc_makeURL(int width, int height, double xlenge, double ylenge) {
        this.width = width;
        this.height = height;
        this.xlenge = xlenge;
        this.ylenge = ylenge;
        calibate();
    }
    public C_myfunc_makeURL(){
        calibate();
    }
    
    private void calibate(){
        xlenge_2=xlenge/2;
        ylenge_2=ylenge/2;
    }
    
    public String getUrlMaps(double x,double y){
        tempposition=new HashMap<String,Double>();
        double tempx=x-xlenge_2;
        tempposition.put("xmin", tempx);
        double tempy=y-ylenge_2;
        tempposition.put("ymin", tempy);
        double tempxx=x+xlenge_2;
        tempposition.put("xmax", tempxx);
        double tempyy=y+ylenge_2;
        tempposition.put("ymax", tempyy);
        String out=((x<102)?head47:head48)+"BBOX=";
        out+=tempx+",";
        out+=tempy+",";
        out+=tempxx+",";
        out+=tempyy;
        out+="&WIDTH="+width;
        out+="&HEIGHT="+height;
        return out;
    }
    public void setZoom(double Zoom){
        Zoom=1/Zoom;
        xlenge*=Zoom;
        ylenge*=Zoom;
        calibate();
    }
    /**
     * ต้องเรีกการทำงานหลัง getUrlMaps
     * @return พิกัดตำสุดของกรอบ x
     */
    public Map<String,Double> getPositionBox(){
        return tempposition;
    }
}
