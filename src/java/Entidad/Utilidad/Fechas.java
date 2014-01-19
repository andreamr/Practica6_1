/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.Utilidad;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
        return Integer.parseInt(sdf.format(ahora));
    }
}
