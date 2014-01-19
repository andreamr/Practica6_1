/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vicente Iranzo
 */
public abstract class ICommand {
    public void initPage(HttpServletRequest request, 
            HttpServletResponse response)  throws Exception {
        //no obligamos a inicializar la página
    }
    public abstract String execute(HttpServletRequest request, 
            HttpServletResponse response) throws Exception;
    
}
