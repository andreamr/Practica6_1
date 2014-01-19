/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ClienteBLL;
import Entidad.Cliente;
import Entidad.Exceptions.ProgException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class LoginCommand extends ICommand {

    @Override
    public void initPage(HttpServletRequest request, 
                          HttpServletResponse response)  throws Exception {
        ClienteBLL _clienteBLL = new ClienteBLL();
        request.setAttribute("listadoClientes", _clienteBLL.listaClientes());
    }
    
    @Override
    public String execute(HttpServletRequest request, 
                          HttpServletResponse response) throws Exception {
        try {
            Cliente cliente=new Cliente();//Crea un objeto Cliente con el DNI que se solicita
            cliente.setNick(request.getParameter("Nick"));
            cliente.setPassword(request.getParameter("Password"));

            ClienteBLL clienteBLL = new ClienteBLL();
            cliente=clienteBLL.validaCliente(cliente);
            request.getSession().setAttribute("ClienteSesion", cliente);
            return "/muestraClientes.jsp";
        } catch (ProgException e) {
            e.setPageMostrar("login.jsp");
            e.setCommandInitPageMostrar(null);
            throw e;
        }
       
    }
    
}
