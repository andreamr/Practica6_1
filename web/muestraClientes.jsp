<%-- 
    Document   : muestraClientes
    Created on : 24-abr-2008, 20:28:15
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entidad.Cliente"%>
<%@page import="Presentation.Utilidad.GetterUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form action="Controller?opID=MostrarDetallesCliente" method="POST">
        <h2>Ver datos de cliente</h2>
        <select name="clienteElegido">
        <%
        List  listadoCliente=(List) request.getAttribute("listadoClientes");
        if(listadoCliente!=null) 
        {
            for (int i=0;i<listadoCliente.size();i++)
            {
            Cliente cliente=(Cliente) listadoCliente.get(i);
            %>
            <option 
                <%if (cliente.getDNI() == GetterUtil.getInstance()
                     .getValueInt(request,"clienteElegido")) { %>selected <%}%> 
                value="<%=cliente.getDNI()%>">
                <%=cliente.getNombre()%> <%=cliente.getApellido1()%> <%=cliente.getApellido2()%>
            </option>
            <%              
            }
        }
        %>
        </select>
        <input type="submit" value="Ver datos" />
    </form>
    </body>
</html>
