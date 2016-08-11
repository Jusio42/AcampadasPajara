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
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <title>Bienvenido a la solicitud de las Acampadas.</title>

    </head>

    <body>
        <div class="container">
            <h1>Atención</h1>
            <h4>
                Las personas que soliciten el Permiso por Internet, indicando un correo electrónico lo recibirán en la dirección escrita en este formulario. En caso contrario pueden retirar el Permiso en la oficina municipal que nos indiquen. <br><br>

                Cuando los agentes de la autoridad lo requieran deberán aportar el DNI original o fotocopia, o permiso de conducir junto con el permiso de acampada. <br><br>

                Este formulario digital ha sido enviado por correo electrónico a acampadas@pajara.es
                Oficina de Servicios, sito en el Edificio de la Policia Local, Avda. de Jandía nº 13 de la localidad de Morro Jable. Teléfono 928 54 07 11
                El Fax es 928 54 24 14.<br><br>
            </h4>
            <dl>
                <dt><h1>MUCHA ATENCIÓN:</h1></dt>
                <dd><h4>- No se enviarán autorizaciones a la Tenencia de Alcaldía de La Lajita.</h4></dd>
                <dd><h4>- La solicitud de acampada no significa su autorización, esta sujeta a revisión según número de plazas y cumplimento de la normativa. </h4></dd>
                <dd><h4>- Solo se permite una solicitud por Documento oficial, en caso de necesitar varias poner documentos distintos.  </h4></dd>
            </dl>

            <h1>Rellena la solicitud por favor</h1>
            <br>
            <form method="post" action="FrontController">
                <fieldset>
                    <legend>Información personal :</legend>
                    <h4><strong>Nombre</strong>(Como en documento oficial):
                        <input type="text" size="10" name="nombre" placeholder="Introduce tu nombre">
                    </h4><br>
                    <h4><strong>Apellidos</strong>(Como en documento oficial):
                        <input type="text" size="15" name="apellidos" placeholder="Introduce tus Apellidos">
                    </h4><br>
                    <h4>Indicar número y letras de su documento oficial acreditativo.<strong>DNI,NIF o PASAPORTE</strong> (Poner el número completo y letra/s en mayúsculas, sin dejar espacios):
                        <br><strong>SOLO 1 SOLICITUD POR DOCUMENTO</strong>
                        <input type="text" size="10" name="DNI" placeholder="Introduce tu DNI">
                    </h4><br>
                    <h4>Municipio donde reside actualmente. Si su municipio no está en este listado, rellene el campo de abajo.<br>
                        <strong>Municipio:</strong>
                        <select name="municipioSelect"  class="selectpicker">
                            <option> </option>
                            <option>Pajara</option>
                            <option>La Oliva</option>
                            <option>Tuineje</option>
                        </select>
                        Escriba aquí su <strong>municipio </strong>(en el caso de que <strong>NO ESTÉ EN EL LISTADO</strong>):
                        <input type="text" size="15" name="noMunicipio" placeholder="Introduce tu municipio">
                    </h4><br>
                    <h4>
                        <strong>Dirección</strong>(Calle,Urbanización,Avenida,etc...):
                        <input type="text" size="15" name="direccion" placeholder="Introduce tu dirección"><br><br>
                        <br>
                        <strong>Email</strong>(Si no tiene correo electrónico,escriba en el recuadro el número 1 (Por favor, vigilen las MAYÚSCULAS y minúsculas)):
                        <input type="text" size="10" name="email" placeholder="Introduce tu email">
                        <br><br>
                        <strong>Fax</strong>(Si no tiene fax,escriba en el recuadro el número 1):
                        <input type="text" size="10" name="fax" placeholder="Introduce tu fax">
                        <br><br>
                        <strong>Teléfono</strong>(Indicar número de teléfono, móvil o fijo. (Poner el número completo SIN ESPACIOS)):
                        <input type="text" size="10" name="telefono" placeholder="Introduce tu nº de teléfono"><br><br>
                    </h4>
                </fieldset>

                <fieldset>
                    <legend>Información Acampada :</legend>
                    <h4>
                        <strong>Zona</strong>: 
                        <select name="playaSelect" class="selectpicker">
                            <option>Garcey</option>
                            <option>La Solapa</option>
                            <option>Vigocho</option>
                        </select>
                        <br><br>

                        <strong>Fecha Entrada </strong>(Indique la FECHA DE ENTRADA por favor):
                        <input class="form-control" type="date" name="fechaentrada" value="" id="example-date-input" style="width: 175px;">
                        <br>

                        <strong>Fecha Salida </strong>(Indique la FECHA DE SALIDA):
                        <input class="form-control" type="date" name="fechasalida" value="" id="example-date-input" style="width: 175px;">
                        <br>

                        <strong>Personas</strong>:
                        Indique el número total de personas que van a acampar:
                        <select name="personasCantidad" class="selectpicker">
                            <option>1</option><option>2</option><option>3</option>
                            <option>4</option><option>5</option><option>6</option>
                            <option>7</option><option>8</option><option>9</option>
                            <option>10</option><option>11</option><option>12</option>
                        </select>
                        <br><br>

                        <strong>Tipo</strong>(Indique el <strong>TIPO DE ACAMPADA</strong> (Tamaño de la parcela 7,7 metros cuadrados, 
                        solo esta permitida una caseta en caso de llevar caravana y solo se puede pedir una caravana por solicitud)):
                        <select name="tipoAcampada" class="selectpicker">
                            <option>Caseta</option>
                            <option>Caravana</option>
                            <option>Ambas</option>
                        </select>
                        <br><br>

                        <strong>Casetas</strong>(Indique el NÚMERO DE CASETAS (Recuerde con Caravana máximo 1 caseta)):
                        <select name="numeroCasetas" class="selectpicker">
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                        </select>
                        <br><br>

                        <strong>Caravanas</strong> (Indique si solo acampa con casetas o  si acampa con una caravana(en el caso de acampar con una caravana indique en el recuadro de la derecha la matrícula)):
                        <select name="caravana" class="selectpicker">
                            <option>Casetas</option>
                            <option>Caravanas</option>
                        </select>
                        <br><br>

                        Indique la <strong>matrícula de la caravana </strong>(en el caso de que acampe con caravana)
                        <input type="text" size="10" name="matriculaCaravana" placeholder="Introduce la matrícula"><br><br>

                        <strong>Recoger</strong>(Indique el punto de recogida de su autorización. No se enviarán autorizaciones a la Tenencia de Alcaldía de La Lajita):<br>
                        <input type="radio" name="optradio" value="Ayuntamiento de Pajara (Plaza Nuestra Señora de Regla)" > Ayuntamiento de Pájara (Plaza Nuestra Señora de Regla)<br>
                        <input type="radio" name="optradio" value="Usos Multiples de Morro Jable (Edificio Policia Local)"> Usos Múltiples de Morro Jable (Edificio Policia Local)<br>
                        <input type="radio" name="optradio" value="Tenencia de Alcaldia de Costa Calma"> Tenencia de Alcaldía de Costa Calma<br>
                        <input type="radio" name="optradio" value="Por correo electronico"> Por correo electrónico<br>
                        <input type="radio" name="optradio" value="Por Fax"> Por Fax
                        <br>
                        <br>
                    </h4>
                    <div class="form-group">
                        <label for="comment">Observaciones:</label>
                        <textarea class="form-control" name="observaciones" rows="5" id="observaciones"></textarea>
                    </div>
                </fieldset>
                <center>
                    <br>
                    <h2>
                        <strong>Por favor,antes de enviar la solicitud asegúrese de que todo es correcto.</strong>
                    </h2>
                    <br>
                    <input  type="hidden" name="command" value="RegisterCommand">
                    <input type="submit" value="Enviar Solicitud">
                    <br><br><br>
                    <br><br><br>
                </center>
        </div>
    </body>
</html>