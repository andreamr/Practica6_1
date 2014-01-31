/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ClienteBLL;
import Entidad.Cliente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vicente Iranzo
 */
public class ListarFacturasCommand extends ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ClienteBLL clienteBLL = new ClienteBLL();
        Cliente cliente = (Cliente) request.getSession().getAttribute("ClienteSesion");
        cliente= clienteBLL.getFacturasCliente(cliente);
        request.setAttribute("listaFacturas", cliente.getFacturas());
        return "/listaFacturasCliente.jsp";
    }
        
}
