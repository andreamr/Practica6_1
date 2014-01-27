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
import java.util.List;
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
         // la pantalla requiere de un listado de articulos para añadir
         ArticuloBLL articuloBLL = new ArticuloBLL();
         
         if ((idFactura==null) || (idFactura.equals("0"))) 
         { // si se da un error controlado, o se quiere añadir un producto
           // a la Factura, no se genera una nueva  factura si ya existe
            FacturaBLL facturaBLL= new FacturaBLL();
            Cliente cliente=(Cliente) request.getSession().getAttribute("ClienteSesion");
            Factura factura=facturaBLL.crearFactura(cliente);
            request.setAttribute("FacturaCliente",factura);
            request.setAttribute("listadoArticulos", articuloBLL.listaArticulos());
         } else
         { // la pantalla requiere de una factura y de los articulos que la componen
            Factura factura = new Factura();
            factura.setId(Integer.parseInt(idFactura));
            FacturaBLL facturaBLL=new FacturaBLL();
            factura=facturaBLL.getArticulosFactura(factura);
            request.setAttribute("FacturaCliente",factura);
            List<Articulo> listadoArticulos = 
                (List<Articulo>) articuloBLL.listaArticulos();
             // quitar del listadoArticulos el articulosAñadido
            for(Articulo af: factura.getArticulos()){
                for(Articulo a : listadoArticulos){
                    if(a.getId()==af.getId()) {
                        listadoArticulos.remove(a);
                        break;
                    }
                }
            }
            request.setAttribute("listadoArticulos", listadoArticulos);
         }
         
         
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
                
                // recargar de nuevo el cliente en el atributo de sesión
                // para que la vista lo muestre
                
                ClienteBLL clienteBLL = new ClienteBLL();
                cliente=clienteBLL.findByDNI(cliente);
                request.getSession().setAttribute("ClienteSesion", cliente);                
                
                // recargar la factura para que la vista los muestre
                factura=facturaBLL.getArticulosFactura(factura); 
                request.setAttribute("FacturaCliente",factura);
                ArticuloBLL articuloBLL = new ArticuloBLL();
                List<Articulo> listadoArticulos = articuloBLL.listaArticulos();
                 // quitar del listadoArticulos los articulos facturados
                for(Articulo af: factura.getArticulos()){
                    for(Articulo a : listadoArticulos){
                        if(a.getId()==af.getId()) {
                            listadoArticulos.remove(a);
                            break;
                        }
                    }
                }
                request.setAttribute("listadoArticulos", listadoArticulos);
            } else {
                if ((productoId!=null) && ((cantidad!=null))) {
                    // pasamos a añadir ese producto a la factura
                    FacturaBLL facturaBLL = new FacturaBLL();
                    Cliente cliente = (Cliente) request.getSession().getAttribute("ClienteSesion");
                    Factura factura=new Factura();
                    factura.setId(Integer.parseInt(idFactura));

                    Articulo articulo=new Articulo();
                    articulo.setId(Integer.parseInt(productoId));
                    try {
                        facturaBLL.addArticulo(cliente, factura, articulo, Integer.parseInt(cantidad));
                    } catch (NumberFormatException ex){
                        throw new ProgException("Introduzca una cantidad válida");
                    }
                    //se ha actualizado la factura

                    // Ha cambiado el saldo del cliente desde la logica de negocio
                    // recargar de nuevo el cliente en el atributo de sesión
                    // para que la vista lo muestre
                    
                    ClienteBLL clienteBLL = new ClienteBLL();
                    cliente=clienteBLL.findByDNI(cliente);
                    request.getSession().setAttribute("ClienteSesion", cliente);

                    factura=facturaBLL.getArticulosFactura(factura);
                    request.setAttribute("FacturaCliente",factura);
                    ArticuloBLL articuloBLL = new ArticuloBLL();
                    List<Articulo> listadoArticulos = articuloBLL.listaArticulos();
                     // quitar del listadoArticulos el articulosAñadido
                    for(Articulo af: factura.getArticulos()){
                        for(Articulo a : listadoArticulos){
                            if(a.getId()==af.getId()) {
                                listadoArticulos.remove(a);
                                break;
                            }
                        }
                    }
                    request.setAttribute("listadoArticulos", listadoArticulos);                    
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
