/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.Utilidad;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno_tarde
 */
public class Log {
    private static Log INSTANCE =null;
    private boolean DEBUG=true;
    
    // El constructor privado no permite que se genere un constructor por defecto
    private Log(){}
    
    // creador sincronizado para protegerse de posibles probemas multi-hilo
    private synchronized static void createInstance(){
        if(INSTANCE==null){
            INSTANCE=new Log();
        }
    }
    public static Log getInstance(){
        if(INSTANCE==null) createInstance();
        return INSTANCE;
    }

    public void debug(String message){
        if(DEBUG) Logger.getLogger(this.getClass().getName()).log(Level.INFO,message);
        
    }
    public void error(Exception ex){
        ex.printStackTrace();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"EXCEPCIÓN",ex);
    }
    public void error(String ticketId, Exception ex){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,ticketId);
        this.error(ex);
    }
}
