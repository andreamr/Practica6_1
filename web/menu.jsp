<div id="menu">
    <% if(request.getSession().getAttribute("ClienteSesion")==null) { %>
        <a href="Controller?opID=MostrarLogin">Identifiquese</a>
    <% } else { %>
        <a href="Controller?opID=MostrarClientes">Clientes</a><br/>
        <a href="Controller?opID=EditaFactura">Comprar</a><br/>
        <a href="Controller?opID=ListarFacturas">ListarFacturas</a><br/>
        <a href="Controller?opID=Logout">Cerrar sesión</a><br/>
    <%}%>
</div>
