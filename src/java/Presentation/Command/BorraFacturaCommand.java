/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ClienteBLL;
import BLL.FacturaBLL;
import Entidad.Cliente;
import Entidad.Exceptions.ProgException;
import Entidad.Factura;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vicente
 */
public class BorraFacturaCommand extends ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String idFactura=request.getParameter("idFactura");
         
        try {    
            if((idFactura!=null)) {
                FacturaBLL facturaBLL= new FacturaBLL();
                Factura factura = new Factura();        
                factura.setId(Integer.parseInt(idFactura));
                facturaBLL.borrarFactura(factura);
            }
            ClienteBLL clienteBLL = new ClienteBLL();
            Cliente cliente = (Cliente) request.getSession().getAttribute("ClienteSesion");
            cliente= clienteBLL.getFacturasCliente(cliente);
            request.setAttribute("listaFacturas", cliente.getFacturas());
            
            return "listaFacturasCliente.jsp";
        } catch (ProgException e) {
            e.setPageMostrar("listaFacturasCliente.jsp");
            e.setCommandInitPageMostrar("ListarFacturas");
            throw e;
        }
            
    }
    
    
}
