<%-- 
    Document   : deletePage
    Created on : 24-ago-2016, 10:09:11
    Author     : W7
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Playadatospersonas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla para borrar solicitud</title>
    </head>
    <body>
        <h3>Pulse el boton de la derecha para borrar la solicitud deseada.</h3>
        <table class="table-striped table table-hover">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>DNI</th>
                <th>Fecha Entrada</th>
                <th>Fecha Salida</th>
                <th>Tipo Acampada</th>
            </tr>
            <%
                List<Playadatospersonas> ocurrencias = (List<Playadatospersonas>) request.getAttribute("packDetailsList");
                for (Playadatospersonas element : ocurrencias) {
            %>
            <td><%=element.getNombrepersona()%></td>
            <td><%=element.getApellido()%></td>
            <td><%=element.getDni()%></td>
            <td><%=element.getFechaEntrada()%></td>
            <td><%=element.getFechaSalida()%></td>
            <td><%=element.getTipoAcamp()%></td>
            <td>
                <form action="FrontController" method="post">
                    <input type="hidden" name="idSolicitud" value="<%=element.getPdpId()%>">
                    <input type="hidden" name="command" value="DeleteCommand">
                </form>
            </td>
            <%
                }
            %>

        </table>

    </body>
</html>
