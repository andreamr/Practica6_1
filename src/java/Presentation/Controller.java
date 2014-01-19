/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entidad.Exceptions.ProgException;
import Entidad.Utilidad.Fechas;
import Entidad.Utilidad.Log;
import Presentation.Command.ICommand;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter();
        ICommand _beforeCommand=null;
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.setContentType("text/html;charset=UTF-8");
            String operacion= request.getParameter("opID");
            if(!operacion.equals("Login") && !operacion.equals("MostrarLogin"))
            { // el resto de operaciones requieren identificacion
                if(request.getSession().getAttribute("ClienteSesion")==null){
                    throw new ProgException("Identifiquese para acceder al aplicativo");
                }
            }
            // el resto del codigo no cambia
            
            // carga de forma dinamica la clase que implementa ICommand
            ICommand command = (ICommand) Class.forName("Presentation.Command."+operacion+"Command").newInstance();
            // carga los datos iniciales necesarios para la siguiente página a visualizar
            command.initPage(request, response);
            
            String nextPage=command.execute(request, response);
            Log.getInstance().debug(nextPage);
            new Dispatcher().procesa(request, response, nextPage);
        } catch (ProgException ex) { 
            String _parameterName="";
            for(Enumeration e = request.getParameterNames();e.hasMoreElements();){
                _parameterName = (String)e.nextElement();
                request.setAttribute(_parameterName, request.getParameter(_parameterName));
            }
            try{
                _beforeCommand=(ICommand) Class.forName("Presentation.Command."+ex.getCommandInitPageMostrar()+"Command").newInstance();
                _beforeCommand.initPage(request, response);
            } catch(Exception e){};
            new Dispatcher().procesa(request, response, ex);
        } catch (Exception ex) {
            String _ticketId=Fechas.getInstance().getTicketFecha();
            Log.getInstance().error(_ticketId,ex);
            request.setAttribute("ticketId", _ticketId);
            throw new ServletException("Excepción no controlada "+ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
