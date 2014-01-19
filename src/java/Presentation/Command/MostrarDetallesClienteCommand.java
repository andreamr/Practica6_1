/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ClienteBLL;
import Entidad.Cliente;
import Presentation.Dispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class MostrarDetallesClienteCommand extends ICommand{
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            Cliente cliente=new Cliente();//Crea un objeto Cliente con el DNI que se solicita
            cliente.setDNI(Integer.parseInt(request.getParameter("clienteElegido")));
            
            ClienteBLL clienteBLL = new ClienteBLL();
            cliente=clienteBLL.findByDNI(cliente);
            request.setAttribute("Cliente", cliente);
            
            return "/muestraDetallesCliente.jsp";
        
    }
    
}
