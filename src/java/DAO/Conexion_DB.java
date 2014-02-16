/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Entidad.Utilidad.Log;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Conexion_DB {
    public Connection AbrirConexion() throws Exception 
    {
        Connection con=null;  // instacia una conexión
        try {
           Class.forName("com.mysql.jdbc.Driver");  // Cargar el driver
           String urlOdbc = "jdbc:mysql://localhost:3306/clientesDB";
           con=(java.sql.DriverManager.getConnection(urlOdbc,"root",""));  //crea conexión
           //con=(java.sql.DriverManager.getConnection(urlOdbc,"usuClientesDB",""));  //crea conexión
           return con;
         } catch(Exception ex){//SQLException y ClassNotFoundException
            //ex.printStackTrace();
            Log.getInstance().error(ex);
            throw new Exception("Ha sido imposible establecer la conexion"+ex.getMessage());
         }          
    }
    
    public Connection AbrirConexionDS() throws Exception 
    {
        Connection con=null;  // instacia una conexión
        try {
           Context ctx = new InitialContext();
           DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/clienteDS");
           con  = ds.getConnection(); 
           return con;
         } catch(Exception ex){//SQLException y ClassNotFoundException
            //ex.printStackTrace();
            Log.getInstance().error(ex); 
            throw new Exception("Ha sido imposible establecer la conexion desde DataSource"+ex.getMessage());
         }          
    }
    public Connection AbrirConexionDSAlt() throws Exception 
    {
        Connection con=null;  // instacia una conexión
        try {
           Context ctx = new InitialContext();
           DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/clientesDSAlt");
           con  = ds.getConnection(); 
           return con;
         } catch(Exception ex){//SQLException y ClassNotFoundException
            //ex.printStackTrace();
            Log.getInstance().error(ex); 
            throw new Exception("Ha sido imposible establecer la conexion desde DataSource"+ex.getMessage());
         }          
    }
    
    public  void CerrarConexion(Connection con) throws Exception
    {
        try {
             if (con!= null) con.close();    
        } catch (SQLException ex) {
            //ex.printStackTrace();
            Log.getInstance().error(ex);
            throw new Exception("Ha sido imposible cerrar la conexion"+ex.getMessage());
        }    
        }    
}
