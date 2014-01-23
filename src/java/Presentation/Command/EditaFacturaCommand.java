/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ArticuloBLL;
import BLL.ClienteBLL;
import BLL.FacturaBLL;
import Entidad.Articulo;
import Entidad.Cliente;
import Entidad.Exceptions.ProgException;
import Entidad.Factura;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno_tarde
 */
public class EditaFacturaCommand extends ICommand {
    @Override
    public void initPage(HttpServletRequest request, 
                          HttpServletResponse response)  throws Exception {
         
         String idFactura = request.getParameter("idFactura");
         
         if ((idFactura==null) || (idFactura.equals("0"))) 
         { // si se da un error controlado, o se quiere añadir un producto
           // a la Factura, no se genera una nueva  factura si ya existe
             FacturaBLL facturaBLL= new FacturaBLL();
             Cliente cliente=(Cliente) request.getSession().getAttribute("ClienteSesion");
             Factura factura=facturaBLL.crearFactura(cliente);
             request.setAttribute("FacturaCliente",factura);
         } else
         { // la pantalla requiere de una factura y de los articulos que la componen
             Factura factura = new Factura();
             factura.setId(Integer.parseInt(idFactura));
             FacturaBLL facturaBLL=new FacturaBLL();
             factura=facturaBLL.getArticulosFactura(factura);
             request.setAttribute("FacturaCliente",factura);
         }
         // la pantalla requiere de un listado de articulos para añadir
         ArticuloBLL articuloBLL = new ArticuloBLL();
         request.setAttribute("listadoArticulos", articuloBLL.listaArticulos());
    }
    
    @Override
    public String execute(HttpServletRequest request, 
                          HttpServletResponse response) throws Exception {
    
      
        try {
            String productoId=request.getParameter("productoElegido");
            String cantidad=request.getParameter("cantidad");
            String idFactura=request.getParameter("idFactura");

            // este parametro existe si se pulsa uno de los enlaces Eliminar
            String borraArticulo=request.getParameter("borraArticulo");
            
            if(borraArticulo!=null) { // se pulso en borrar articulo
                FacturaBLL facturaBLL = new FacturaBLL();
                Cliente cliente = (Cliente) request.getSession().getAttribute("ClienteSesion");
                Factura factura=new Factura();
                factura.setId(Integer.parseInt(idFactura));                
                Articulo articulo=new Articulo();
                articulo.setId(Integer.parseInt(borraArticulo)); 
                facturaBLL.removeArticulo(cliente, factura, articulo);
                //se ha actualizado la factura y el saldo del cliente
                
                // recargar la factura para que la vista los muestre
                factura=facturaBLL.getArticulosFactura(factura); 
                request.setAttribute("FacturaCliente",factura);
            } else {
                if ((productoId!=null) && ((cantidad!=null))) {
                    // pasamos a añadir ese producto a la factura
                    FacturaBLL facturaBLL = new FacturaBLL();
                    Cliente cliente = (Cliente) request.getSession().getAttribute("ClienteSesion");
                    Factura factura=new Factura();
                    factura.setId(Integer.parseInt(idFactura));

                    Articulo articulo=new Articulo();
                    articulo.setId(Integer.parseInt(productoId));
                    facturaBLL.addArticulo(cliente, factura, articulo, Integer.parseInt(cantidad));
                    //se ha actualizado la factura

                    factura=facturaBLL.getArticulosFactura(factura);
                    
                    // Ha cambiado el saldo del cliente desde la logica de negocio
                    // recargar de nuevo el cliente en el atributo de sesión
                    // para que la vista lo muestre
                    ClienteBLL clienteBLL = new ClienteBLL();
                    cliente=clienteBLL.findByDNI(cliente);
                    request.getSession().setAttribute("ClienteSesion", cliente);

                    request.setAttribute("FacturaCliente",factura);
                }
            }
            return "editarFactura.jsp";
        } catch (ProgException e) {
            e.setPageMostrar("editarFactura.jsp");
            e.setCommandInitPageMostrar("EditarFactura");
            throw e;
        }
    }
}
