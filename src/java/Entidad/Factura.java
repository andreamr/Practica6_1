/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidad;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 */
public class Factura {
private int Id;
private int Fecha;
private int cliente_dni;
private List<Articulo> articulos=new ArrayList();

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getFecha() {
        return Fecha;
    }

    public void setFecha(int Fecha) {
        this.Fecha = Fecha;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    /**
     * @return the cliente_dni
     */
    public int getCliente_dni() {
        return cliente_dni;
    }

    /**
     * @param cliente_dni the cliente_dni to set
     */
    public void setCliente_dni(int cliente_dni) {
        this.cliente_dni = cliente_dni;
    }

}
