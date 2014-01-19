/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.ArticuloDAO;
import DAO.Conexion_DB;
import Entidad.Articulo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno_tarde
 */
public class ArticuloBLL {
    public List<Articulo> listaArticulos() throws Exception {
        Connection con=null;
        List<Articulo> listado=new ArrayList();
        Conexion_DB conexionDB = new Conexion_DB();
        con = conexionDB.AbrirConexion();// Abrimos la conexión
        ArticuloDAO articuloDAO= new ArticuloDAO();
        
        listado=articuloDAO.findAll(con);
        conexionDB.CerrarConexion(con);
        return listado;
    }
}
