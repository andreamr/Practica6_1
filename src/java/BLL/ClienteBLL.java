/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAO.ClienteDAO;
import DAO.Conexion_DB;
import Entidad.Cliente;
import Entidad.Exceptions.ProgException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 */
public class ClienteBLL {

    public Cliente findByDNI(Cliente cliente) throws Exception{
        Connection con=null;
        Cliente clienteObtenido=null;
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        ClienteDAO clienteDAO=new ClienteDAO();
        clienteObtenido=clienteDAO.findByDNI(con,cliente);//Recuperamos los clientes
        if (clienteObtenido.getNombre().startsWith("Mar"))
           throw new ProgException("Esta aplicacion no debe mostrar datos de ese cliente"+clienteObtenido.getDNI());
        conexionDB.CerrarConexion(con); //Cerramos la conexión */         
        return clienteObtenido;
    }
    public List<Cliente> listaClientes() throws Exception
    {
        Connection con=null;
        List<Cliente> listado=new ArrayList();
        try {            
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        ClienteDAO clienteDAO=new ClienteDAO();
        
        listado=clienteDAO.findAll(con);//Recuperamos los clientes
                 
         conexionDB.CerrarConexion(con); //Cerramos la conexión */ 
        } catch (Exception ex) {
            System.out.println("Excepcion->"+ex.getMessage());           
        }  
        return listado;
    }

    public Cliente validaCliente(Cliente cliente) throws Exception {
        Connection con=null;
        Cliente clienteObtenido=null;
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        ClienteDAO clienteDAO=new ClienteDAO();
        clienteObtenido=clienteDAO.findByNick(con,cliente);//Recuperamos el cliente
        conexionDB.CerrarConexion(con); //Cerramos la conexión */         
        if (clienteObtenido == null 
            || !clienteObtenido.getPassword().equals(cliente.getPassword()))
            throw new ProgException("Usuario o clave incorrecta");
        return clienteObtenido;  
        
    }

}
