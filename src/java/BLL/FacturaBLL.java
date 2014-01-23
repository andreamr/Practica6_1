/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.ArticuloDAO;
import DAO.ClienteDAO;
import DAO.Conexion_DB;
import DAO.FacturaDAO;
import Entidad.Articulo;
import Entidad.Cliente;
import Entidad.Factura;
import Entidad.Utilidad.Fechas;
import java.sql.Connection;

/**
 *
 * @author alumno_tarde
 */
public class FacturaBLL {

    public Factura crearFactura(Cliente cliente) throws Exception {
        Connection con=null;
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        FacturaDAO facturaDAO=new FacturaDAO();
        Factura factura=new Factura();
        int fecha=Fechas.getInstance().getFechaToInt();
        factura.setFecha(fecha);
        factura=facturaDAO.creaFactura(con, factura, cliente); // crea una nueva factura
        conexionDB.CerrarConexion(con); // cerramos la conexión
        return factura;        
    }
    
    public Factura getArticulosFactura(Factura factura) throws Exception {
        Connection con=null;
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        FacturaDAO facturaDAO=new FacturaDAO();  
        int fecha=Fechas.getInstance().getFechaToInt();
        factura.setFecha(fecha);
        factura=facturaDAO.getArticulosFactura(con, factura); // carga los articulos de la factura
        conexionDB.CerrarConexion(con); // cerramos la conexión
        return factura;  
        
    }
    public void removeArticulo(Cliente cliente,Factura factura, Articulo articulo) throws Exception {
        Connection _con = null;
        Articulo articulo1=null;
        try {
            Conexion_DB _conexion_DB = new Conexion_DB();
            _con = _conexion_DB.AbrirConexion();// Abrimos la conexión
            _con.setAutoCommit(false);
            
            
            FacturaDAO facturaDAO = new FacturaDAO();
            
            articulo1 = facturaDAO.removeArticulo(_con, factura, articulo); // elimina la línea de factura
            // en articulo1 tenemos la cantidadComprada 
            
            //cargamos el articulo de la base da datos
            ArticuloDAO articuloDAO = new ArticuloDAO();
            articulo=articuloDAO.findById(_con, articulo);
            articulo.setStock(articulo.getStock() + articulo1.getCantidadComprada());
            articuloDAO.updateStock(_con, articulo); //Actualizar el articulo

            // calcular el importe de la compra para actualicar al cliente
            float importeCompra = articulo1.getCantidadComprada() * articulo.getPrecio();
            
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente.setSaldo(cliente.getSaldo() + importeCompra);
            clienteDAO.updateSaldo(_con, cliente); //Actualiza el saldo del cliente
                   
            _con.commit(); //Ejecutamos la operación
            _conexion_DB.CerrarConexion(_con); //Cerramos la conexión */

        } catch (Exception ex) {
            System.out.println("Excepcion->" + ex.getMessage());
            if (_con != null) {
                _con.rollback();
            }
            throw ex;
        }
    }
    
    public void addArticulo(Cliente cliente, Factura factura,
            Articulo articulo, int numero) throws Exception {
        
        Connection _con = null;
        try {
            Conexion_DB _conexion_DB = new Conexion_DB();
            _con = _conexion_DB.AbrirConexion();// Abrimos la conexión
            _con.setAutoCommit(false);
            ArticuloDAO _articuloDAO = new ArticuloDAO();
            articulo = _articuloDAO.findById(_con, articulo);//Recuperamos los datos del articulo
            ClienteDAO _clienteDAO = new ClienteDAO();
            cliente = _clienteDAO.findByDNI(_con, cliente);//Recuperamos los datos del cliente
            if (cliente.isMoroso() == false) {//Evaluamos si el cliente es Moroso
                if (articulo.getStock() >= numero) {//Evaluamos si hay suficiente Stock
                    float _precioCompra = numero * articulo.getPrecio();
                    if (_precioCompra <= cliente.getSaldo()) {//Evaluamos si el cliente tiene suficiente dinero
                        FacturaDAO facturaDAO = new FacturaDAO();
                        facturaDAO.addArticulo(_con, factura, articulo, numero);//Inserta la línea de factura
                        articulo.setStock(articulo.getStock() - numero);
                        _articuloDAO.updateStock(_con, articulo);//Actualiza el articulo
                        cliente.setSaldo(cliente.getSaldo() - _precioCompra);
                        _clienteDAO.updateSaldo(_con, cliente);//Actualiza el saldo del cliente
                    } else {
                        throw new Exception("No tiene suficiente saldo para realizar la operación");
                    }
                } else {
                    throw new Exception("No queda suficiente stock");
                }
            } else {
                throw new Exception("Usted es moroso");
            }
            _con.commit(); //Ejecutamos la operación
            _conexion_DB.CerrarConexion(_con); //Cerramos la conexión */
        } catch (Exception ex) {
            System.out.println("Excepcion->" + ex.getMessage());
            if (_con != null) {
                _con.rollback();
            }
            throw ex;
        }
    
    }
}