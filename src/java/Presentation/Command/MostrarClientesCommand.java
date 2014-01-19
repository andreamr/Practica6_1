/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ClienteBLL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class MostrarClientesCommand extends ICommand {

    @Override
    public void initPage(HttpServletRequest request, 
                          HttpServletResponse response)  throws Exception {
        ClienteBLL _clienteBLL = new ClienteBLL();
        request.setAttribute("listadoClientes", _clienteBLL.listaClientes());
    }
    
    @Override
    public String execute(HttpServletRequest request, 
                          HttpServletResponse response) throws Exception {
        return "/muestraClientes.jsp";
       
    }
    
}
