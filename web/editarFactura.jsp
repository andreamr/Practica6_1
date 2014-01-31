<%@page import="Entidad.Utilidad.Fechas"%>
<%@page import="Entidad.Articulo" %>
<%@page import="java.util.List" %>
<jsp:useBean id="ClienteSesion" scope="session" class="Entidad.Cliente" />
<jsp:useBean id="FacturaCliente" scope="request" class="Entidad.Factura" />
<!-- Mostrar el clienteSesion -->
ClienteSesion: <%=ClienteSesion.getNombreCompuesto()%><br>
Fecha Factura: <%=Fechas.getInstance().getFechaFormat(FacturaCliente.getFecha(), "dd/MM/yyyy") %>
<br>IdFactura:<jsp:getProperty name="FacturaCliente" property="id" />
<br>
<form action="Controller?opID=EditaFactura" method="POST">
    <input type="hidden" name="idFactura" value="<jsp:getProperty name="FacturaCliente" property="id" />" />
    Añadir articulo
    <select name="productoElegido">
        <%
            List listadoArticulos=(List) request.getAttribute("listadoArticulos");
            if (listadoArticulos!=null) {
               for(int i=0;i<listadoArticulos.size();i++){
                   Articulo articulo=(Articulo) listadoArticulos.get(i);
                   %>
                   <option value="<%=articulo.getId()%>">
                       <%=articulo.getDescripcion()%> - <%=articulo.getPrecio() %> Euros 
                   </option>
                   <%
               }  
            }
         %>       
    </select>
    Cantidad:<input type="text" name="cantidad" value="" />
    <input type="submit" value="Añadir" />
    <br>Saldo disponible: <jsp:getProperty name="ClienteSesion" property="saldo" /> Euros
    <br>
    <%
      List listadoArticulosComprados=(List) FacturaCliente.getArticulos();
      if(listadoArticulosComprados!=null && listadoArticulosComprados.size()>0) { 
      %>
        <table border="1" width="70%">
            <tr>
                <th width="80%">Descripción</th>
                <th>Cantidad</th>
            </tr>
                <% 
                    for (int i=0;i<listadoArticulosComprados.size();i++) {
                        Articulo articulo=(Articulo) listadoArticulosComprados.get(i);
                    %>
                    <tr>
                        <td><%=articulo.getDescripcion()%></td>
                        <td><%=articulo.getCantidadComprada()%></td>
                        <td><a href="Controller?opID=EditaFactura&idFactura=<jsp:getProperty name="FacturaCliente" property="id" />&borraArticulo=<%=articulo.getId()%>">Eliminar</a></td>
                    </tr>
                <% } %> 
        </table>
    <% } %>
</form>    