/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entidad.Exceptions.ProgException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class Dispatcher {
    public void procesa(HttpServletRequest request, HttpServletResponse response, String nextPage) 
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("paginaprincipal", nextPage);
            String _beforePage=(String) request.getSession().getAttribute("paginaprincipal");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (IOException ex){
            throw ex;
        } catch (ServletException ex){
            throw ex;
        }       
    }
    
    public void procesa(HttpServletRequest request, HttpServletResponse response, ProgException ex) 
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String _beforePage= ex.getPageMostrar();
            request.setAttribute("paginaprincipal",_beforePage);
            request.setAttribute("advertencia",ex.getMessageError());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    
}
