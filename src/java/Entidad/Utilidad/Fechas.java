/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.Utilidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno_tarde
 */
public class Fechas {
    private static Fechas INSTANCE = null;
    private Fechas(){}
    private synchronized static void createInstance(){
        if(INSTANCE==null){ 
            INSTANCE = new Fechas();
        }
    }
    public static Fechas getInstance(){
        if (INSTANCE==null) createInstance();
        return INSTANCE;
    }
    public String getTicketFecha(){
        Date ahora = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(ahora);
    }
    public int getFechaToInt(){
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(ahora));
    }
    public String getFechaFormat(Date fecha,String formato){
        
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }
    public String getFechaFormat(int fecha,String formato){
        
        String f = "";
        try{
            f=getFechaFormat(getFechaFromInt(fecha),formato);
        } catch (Exception e){
            f="";
        }                
        return f;       
    }
    public Date getFechaFromInt(int fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sFecha=Integer.toString(fecha);
        Date d=null;
        try {
            d = sdf.parse(sFecha);
        } catch (ParseException ex) {
            Logger.getLogger(Fechas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    
}
