<%-- 
    Document   : index
    Created on : 9/12/2013
    Author     : Vicente Iranzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="Entidad.Cliente"%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AplicaciónJSP</title>
</head>
<body>
    <table  border=2 width="100%">
        <tr>
            <td height="5%" bgcolor="green" colspan="2">
                <table width="100%">
                    <tr>
                        <td style="color:white" align="center" width="75%">Aplicación web</td>
                        <td style="color:white" align="right">
                            <%Cliente clienteSesion=(Cliente) request.getSession().getAttribute("ClienteSesion");
                                if (clienteSesion!= null) { %>
                                    <%=clienteSesion.getNombreCompuesto()%>
                                    - <a href="Controller?opID=Logout">Logout</a>
                               <%}%>
                                    
                        </td>
                    </tr>
                </table>
            </td>
        </tr>  
        <tr>
          <td align="center" bgcolor="#3ADF00" width="10%">
              <jsp:include page="menu.jsp" />
          </td>
          <td width="75%">
              <%String _advertencia=(String) request.getAttribute("advertencia");
              if(_advertencia != null){%><label style="color:red"><%=_advertencia%></label><br/>
              <%}%>            
              <% String  paginaprincipal=(String) request.getAttribute("paginaprincipal");
                 if(paginaprincipal!= null) { %>
                    <jsp:include page='<%=(String) request.getAttribute("paginaprincipal")%>' />
              <%}%>
          </td> 
        </tr>
        <tr>
            <td align="center" style="color:white" height="5%" bgcolor=GREEN colspan=2>2º DAW </td>
        </tr>
    </table>
</body></html>