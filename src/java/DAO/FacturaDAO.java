/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Entidad.Articulo;
import Entidad.Cliente;
import Entidad.Exceptions.ProgException;
import Entidad.Factura;
import Entidad.Utilidad.Log;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author victor
 */
public class FacturaDAO {
    public Factura creaFactura(Connection con, Factura factura,Cliente cliente) throws ProgException, Exception
    {
           PreparedStatement stmt=null;
           try {

                stmt = con.prepareStatement("INSERT INTO FACTURA(Fecha,Cliente_DNI) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,factura.getFecha());
                stmt.setInt(2,cliente.getDNI());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys ();
                rs.next();
                int key = rs.getInt(1);
                factura.setId(key);

            } catch (SQLException ex) {
                //ex.printStackTrace();
                Log.getInstance().error(ex);
                throw new ProgException("Ha habido un problema al insertar la factura "+ex.getMessage());
            }finally
            {
                 if (stmt != null) stmt.close();//Cerramos el Statement 
            }
           return factura;
    }
    
    public Articulo removeArticulo(Connection con,Factura factura,Articulo articulo) throws ProgException,Exception
    {
           Articulo articulo1=null; 
           PreparedStatement stmt=null;
           try {            
                // si hay un articulo en la factura obtenerlo antes de borrarlo
                if((articulo1=getArticuloFactura(con, factura, articulo))!=null) {
                    
                    stmt = con.prepareStatement("DELETE FROM articulo_factura "
                                            + " WHERE Factura_idFactura=? and Articulo_idArticulo=?");
                    stmt.setInt(1,factura.getId());
                    stmt.setInt(2,articulo.getId());
                    stmt.executeUpdate();
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Log.getInstance().error(ex);
                throw new ProgException("Ha habido un problema al borrar el articulo en la factura "+ex.getMessage());
            }  finally
            {
                if (stmt != null) stmt.close();//Cerramos el Statement 
            }     
           return articulo1;
    }


    public void addArticulo(Connection con,Factura factura,Articulo articulo,int numero) throws ProgException,Exception
    {
           PreparedStatement stmt=null;
           try {            
                stmt = con.prepareStatement("INSERT INTO articulo_factura VALUES(?,?,?)");
                stmt.setInt(1,articulo.getId());
                stmt.setInt(2,factura.getId());
                stmt.setInt(3,numero);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Log.getInstance().error(ex);
                throw new ProgException("Ha habido un problema al insertar el articulo en la factura "+ex.getMessage());
            }  finally
            {
                if (stmt != null) stmt.close();//Cerramos el Statement 
            }     
    }

    public Factura getArticulosFactura(Connection con,Factura factura) throws ProgException,Exception
    {
        ResultSet rs=null;
        PreparedStatement stmt=null;   
        try {
                stmt = con.prepareStatement("SELECT Articulo_idArticulo, Numero, Descripcion " +
                        "FROM  articulo_factura af,articulo a " +
                        "WHERE af.Articulo_idArticulo=a.idArticulo AND af.Factura_idFactura=?");
                stmt.setInt(1,factura.getId());
                rs =stmt.executeQuery();

                Articulo articulo1=null;
                while (rs.next()) {
                   articulo1=new Articulo();
                   articulo1.setId(rs.getInt("Articulo_idArticulo"));
                   articulo1.setDescripcion(rs.getString("Descripcion"));                    
                   articulo1.setCantidadComprada(rs.getInt("Numero"));
                   factura.getArticulos().add(articulo1);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Log.getInstance().error(ex);
                throw new ProgException("Ha habido un problema al los articulos de la factura "+ex.getMessage());
            } finally
            {
                if (rs != null) rs.close(); //Cerramos el resulset
                if (stmt != null) stmt.close();//Cerramos el Statement 
            }
        return factura;
    }
    public Articulo getArticuloFactura(Connection con,Factura factura,Articulo articulo) throws ProgException,Exception
    {
        Articulo articulo1=null;
        ResultSet rs=null;
        PreparedStatement stmt=null;   
        try {
                stmt = con.prepareStatement("SELECT Articulo_idArticulo, Numero, Descripcion " +
                        "FROM  articulo_factura af, articulo a " +
                        "WHERE af.Articulo_idArticulo=a.idArticulo "
                        + "AND af.Factura_idFactura=? "
                        + "AND af.Articulo_idArticulo=?"
                        );
                // colocamos los parametros
                stmt.setInt(1,factura.getId());
                stmt.setInt(2,articulo.getId());
                rs =stmt.executeQuery();
                // si hay algun articulo lo rellenamos
                if (rs.next()) {
                   articulo1=new Articulo();
                   articulo1.setId(rs.getInt("Articulo_idArticulo"));
                   articulo1.setDescripcion(rs.getString("Descripcion"));                    
                   articulo1.setCantidadComprada(rs.getInt("Numero"));
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Log.getInstance().error(ex);
                throw new ProgException("Ha habido un problema al buscar un articulo de la factura "+ex.getMessage());
            } finally
            {
                if (rs != null) rs.close(); //Cerramos el resulset
                if (stmt != null) stmt.close();//Cerramos el Statement 
            }
        return articulo1;
    }
}
