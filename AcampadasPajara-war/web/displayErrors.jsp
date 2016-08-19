<%@page import="entities.Playaplazasmaximasasociadas"%>
<%@page import="entities.Municipios"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <title> Errores encontrados </title>

    </head>
    <body>

        <h1>A continuación se listan una serie de problemas encontrados,revisalos por favor :</h1>
        <%
            String problemas = (String) session.getAttribute("problemas");
        %>
        <p style="color:red" class="lead"><%=problemas%></p><br><br>
    <center>
        <h3>La reserva no se ha efectuado, puede volver atras y rellenar correctamente el formualario.</h3>
    </center>
</body>
</html>
