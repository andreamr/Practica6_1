/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Entidad.Articulo;
import Entidad.Utilidad.Log;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author victor
 */
public class ArticuloDAO {

public Articulo findById(Connection con, Articulo articulo) throws Exception
{
       Articulo articulo1=null;
       ResultSet rs=null;
       PreparedStatement stmt=null;        
       try {
            stmt = con.prepareStatement("SELECT * FROM articulo WHERE idArticulo=?");
            stmt.setInt(1,articulo.getId());
            
            rs =stmt.executeQuery();
            while (rs.next()) {
               articulo1=new Articulo();
               articulo1.setId(rs.getInt("idArticulo"));
               articulo1.setDescripcion(rs.getString("Descripcion"));
               articulo1.setPrecio(rs.getFloat("Precio"));
               articulo1.setStock(rs.getInt("Stock"));
                  
            }
                         
        } catch (SQLException ex) {
           //ex.printStackTrace();
           Log.getInstance().error(ex); 
           throw new Exception("Ha habido un problema al buscar el articulo "+ex.getMessage());
        }  finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        } 
       return articulo1;
}
public void updateStock(Connection con, Articulo articulo) throws Exception
{
       PreparedStatement stmt=null;
       try {
            
            stmt = con.prepareStatement("UPDATE articulo SET Stock=? WHERE idArticulo=?");
            
            stmt.setInt(1,articulo.getStock());
            stmt.setInt(2,articulo.getId());
           
            stmt.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace();
            Log.getInstance().error(ex);
            throw new Exception("Ha habido un problema al actualizar el Stock "+ex.getMessage());
        } finally
        {
             if (stmt != null) stmt.close();//Cerramos el Statement 
        }        
}
public List<Articulo> findAll(Connection con) throws Exception
{
    List<Articulo> listaArticulos=new ArrayList();
    ResultSet rs=null;
    PreparedStatement stmt=null;        
    try {                
            stmt = con.prepareStatement("SELECT * FROM Articulo");
            rs =stmt.executeQuery();            
            Articulo articulo=null;
            while (rs.next()) {
               articulo=new Articulo();
               articulo.setId(rs.getInt("idArticulo"));
               articulo.setDescripcion(rs.getString("Descripcion"));
               articulo.setPrecio(rs.getFloat("Precio"));
               articulo.setStock(rs.getInt("Stock"));      
               listaArticulos.add(articulo);
            }                       
        } catch (SQLException ex) {
            //ex.printStackTrace();
            Log.getInstance().error(ex);
            throw new Exception("Ha habido un problema al buscar los articulos "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
    return listaArticulos;
}

}

