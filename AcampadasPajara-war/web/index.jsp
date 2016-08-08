<%-- 
    Document   : index
    Created on : 07-ago-2016, 11:39:26
    Author     : Jusio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Bienvenido a la solicitud de las Acampadas.</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/shop-homepage.css" rel="stylesheet">

        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </head>

    <body>

        <!-- 
           <div class="form-group row">
            <label for="example-text-input" class="col-xs-2 col-form-label">Nombre</label>
            <div class="col-xs-10">
                <input class="form-control" type="text" value="" id="example-text-input">
            </div>
        </div>

        <div class="form-group row">
            <label for="example-search-input" class="col-xs-2 col-form-label">Apellidos</label>
            <div class="col-xs-10">
                <input class="form-control" type="search" value="" id="example-search-input">
            </div>
        </div>

        <div class="form-group row">
            <label for="example-email-input" class="col-xs-2 col-form-label">DNI</label>
            <div class="col-xs-10">
                <input class="form-control" type="text" value="" id="example-email-input">
            </div>
        </div>

        <div class="form-group row">
            <label for="example-url-input" class="col-xs-2 col-form-label">Municipio</label>
            <div class="col-xs-10">
                <input class="form-control" type="text" value="" id="example-url-input">
            </div>
        </div>

        <div class="form-group row">
            <label for="example-tel-input" class="col-xs-2 col-form-label">Teléfono</label>
            <div class="col-xs-10">
                <input class="form-control" type="tel" value="" id="example-tel-input">
            </div>
        </div>

        <div class="form-group row">
            <label for="example-password-input" class="col-xs-2 col-form-label">email</label>
            <div class="col-xs-10">
                <input class="form-control" type="email" value="" id="example-password-input">
            </div>
        </div>

       <div class="form-group row">
           <label for="example-number-input" class="col-xs-2 col-form-label">Number</label>
           <div class="col-xs-10">
               <input class="form-control" type="number" value="42" id="example-number-input">
           </div>
       </div>

       <div class="form-group row">
           <label for="example-datetime-local-input" class="col-xs-2 col-form-label">Date and time</label>
           <div class="col-xs-10">
               <input class="form-control" type="datetime-local" value="2011-08-19T13:45:00" id="example-datetime-local-input">
           </div>
       </div>

       <div class="form-group row">
           <label for="example-month-input" class="col-xs-2 col-form-label">Month</label>
           <div class="col-xs-10">
               <input class="form-control" type="month" value="2011-08" id="example-month-input">
           </div>
       </div>
       
       <div class="form-group row">
           <label for="example-time-input" class="col-xs-2 col-form-label">Time</label>
           <div class="col-xs-10">
               <input class="form-control" type="time" value="13:45:00" id="example-time-input">
           </div>
       </div>

       <div class="form-group row">
           <label for="example-color-input" class="col-xs-2 col-form-label">Color</label>
           <div class="col-xs-10">
               <input class="form-control" type="color" value="#563d7c" id="example-color-input">
           </div>
       </div>
        
        Semana del año: <br>
            <input class="form-control" type="week" value="2011-W33" id="example-week-input" style="width: 200px;">
            <br>
        -->
        <div class="container">
            <h1>Rellena la solicitud por favor</h1>
            <br><br>
            <form method="post" action="FrontController">
                <fieldset>
                    <legend>Información personal :</legend>
                    Nombre:<br>
                    <input type="text" size="40" name="nombre" placeholder="Introduce tu nombre"><br><br>
                    Apellidos:<br>
                    <input type="text" size="40" name="apellidos" placeholder="Introduce tus Apellidos"><br><br>
                    DNI:<br>
                    <input type="text" size="40" name="DNI" placeholder="Introduce tu DNI"><br><br>
                    Municipio:<br>
                    <input type="text" size="40" name="Municipio" placeholder="Introduce tu Municipio"><br><br>
                    Email:<br>
                    <input type="text" size="40" name="Email" placeholder="Introduce tu email"><br><br>
                    Teléfono:<br>
                    <input type="text" size="40" name="Telefono" placeholder="Introduce tu nº de teléfono"><br><br>
                </fieldset>

                <fieldset>
                    <legend>Información Acampada :</legend>
                    Fecha Entrada: <br>
                    <input class="form-control" type="date" value="" id="example-date-input" style="width: 175px;">
                    <br>
                    Fecha Salida: <br>
                    <input class="form-control" type="date" value="" id="example-date-input" style="width: 175px;">
                    <br>
                    
                    Playa: 
                    <select name="playaSelect" class="selectpicker">
                        <option>Garcey</option>
                        <option>La Solapa</option>
                        <option>Vigocho</option>
                    </select>
                    <br><br>
                    
                    Vehículo:   
                    <select name="vehiculoSelect" class="selectpicker">
                        <option>Autocaravana</option>
                        <option>Caravana</option>
                        <option>Vehículo + remolque</option>
                    </select>
                    <br><br>
                    
                </fieldset>

                <input type="hidden" name="command" value="RegisterCommand">
                <input type="submit" value="Register">
            </form>
        </div>

    </body>
</html>