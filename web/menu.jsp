
    <% if(request.getSession().getAttribute("ClienteSesion")==null) { %>
        <a href="Controller?opID=MostrarLogin">Identifiquese</a>
    <% } else { %>
        <a href="Controller?opID=MostrarClientes">Clientes</a><br/>
        <a href="Controller?opID=EditaFactura">Comprar</a><br/>
    <%}%>
