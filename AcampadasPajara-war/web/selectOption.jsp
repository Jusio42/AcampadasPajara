<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Opciones Acampada Pajara</title>
    </head>
    <body>

        <h1>Servicio Acampadas Ayto de Pajara</h1><br>
        <h3>Seleccione la opción que desea.</h3><br>
        
        <fieldset>
            <form method="post" action="FrontController">
                <h4>Seleccione esta opcion para rellenar una solicitud nueva.</h4>
                <input  type="hidden" name="command" value="GetInitialDataCommand">
                <input type="submit" value="Rellenar Solicitud">
            </form>
        </fieldset>

        <fieldset>
            <form method="post" action="FrontController"> 
                <h4>Indicar número y letras de su documento oficial acreditativo.<strong>DNI,NIF o PASAPORTE</strong> (Poner el número completo y letra/s en mayúsculas, sin dejar espacios):</h4>
                <input type="text" maxlength="9" size="10" value ="12345678X" name="DNI" placeholder="Introduce tu DNI"><br><br>
                <input  type="hidden" name="command" value="EditCommand">
                <input type="submit" value="Editar Solicitud">
            </form>
        </fieldset>

        <fieldset>
            <form method="post" action="FrontController">
                <h4>Introduzca DNI, luego del listado elimine la que desee.</h4>
                <input type="text" maxlength="9" size="10" value ="12345678X" name="DNI" placeholder="Introduce tu DNI"><br><br>
                <input  type="hidden" name="command" value="BuildTableToDeleteCommand">
                <input type="submit" value="Eliminar Solicitud">
            </form>
        </fieldset>

    </body>
</html>
