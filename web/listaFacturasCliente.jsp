
<%@page import="Entidad.Utilidad.Fechas"%>
<%@page import="Entidad.Factura"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="UTF-8" %>
<h2>Listado de facturas</h2>
<ul>
    <% List<Factura> listaFacturas = (List) request.getAttribute("listaFacturas"); %>

    <%for(Factura factura : listaFacturas ){ %>
        <li>Factura nº: <%=factura.getId()%>
            Fecha: <%=Fechas.getInstance().getFechaFormat(factura.getFecha(), "dd/MM/yyyy")%>
            <a href="Controller?opID=EditaFactura&idFactura=<%=factura.getId()%>">Editar</a>
            /
            <a href="Controller?opID=BorraFactura&idFactura=<%=factura.getId()%>">Borrar</a>
        </li>
    <%}%>            
</ul>
