/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class MostrarLoginCommand extends ICommand {

    @Override
    public String execute(HttpServletRequest request, 
                          HttpServletResponse response) throws Exception {
        return "/login.jsp";
       
    }
}
