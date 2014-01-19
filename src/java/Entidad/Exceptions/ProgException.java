/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.Exceptions;

/**
 *
 * @author alumno_tarde
 */
public class ProgException extends Exception {
    private String messageError;
    private String pageMostrar;
    private String commandInitPageMostrar;
    
    public ProgException(String messageError){
        this.messageError= messageError;
    }

    public String getMessageError() {
        return messageError;
    }
    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    public String getPageMostrar() {
        return pageMostrar;
    }
    public void setPageMostrar(String pageMostrar) {
        this.pageMostrar = pageMostrar;
    }
    public String getCommandInitPageMostrar() {
        return commandInitPageMostrar;
    }
    public void setCommandInitPageMostrar(String commandInitPageMostrar) {
        this.commandInitPageMostrar = commandInitPageMostrar;
    }
}
