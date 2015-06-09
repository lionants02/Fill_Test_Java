/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fill.my_etc;

import java.net.URL;

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

    public C_myfunc_makeURL(int width, int height, double xlenge, double ylenge) {
        this.width = width;
        this.height = height;
        this.xlenge = xlenge;
        this.ylenge = ylenge;
        xlenge_2=xlenge/2;
        ylenge_2=ylenge/2;
    }
    public C_myfunc_makeURL(){
        xlenge_2=xlenge/2;
        ylenge_2=ylenge/2;
    }
    
    
    public String getUrlMaps(double x,double y){
        
        String out=((x<102)?head47:head48)+"BBOX=";
        out+=x-xlenge_2+",";
        out+=y-ylenge_2+",";
        out+=x+xlenge_2+",";
        out+=y+ylenge_2;
        out+="&WIDTH="+width;
        out+="&HEIGHT="+height;
        return out;
    }
    

    
    
    
}
