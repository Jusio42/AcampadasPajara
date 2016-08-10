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
                    <h4>Nombre:<br>
                        (Como en documento oficial)</h4>
                    <input type="text" size="40" name="nombre" placeholder="Introduce tu nombre"><br><br>
                    <h4>Apellidos:<br>
                        (Como en documento oficial)</h4>
                    <input type="text" size="40" name="apellidos" placeholder="Introduce tus Apellidos"><br><br>
                    <h4>DNI:<br>
                        Indicar número y letras de su documento oficial acreditativo.DNI,NIF o PASAPORTE (Poner el número completo y letra/s en mayúsculas, sin dejar espacios).<br>
                        <strong>SOLO 1 SOLICITUD POR DOCUMENTO</strong></h4>
                    <input type="text" size="40" name="DNI" placeholder="Introduce tu DNI"><br><br>
                    <h4>Municipio donde reside actualmente. Si su municipio no está en este listado, rellene el campo de la derecha.<br>
                        Municipio:
                        <select name="municipioSelect"  class="selectpicker">
                            <option>Pajara</option>
                            <option>La Oliva</option>
                            <option>Tuineje</option>
                        </select>
                        Escriba aquí su municipio:
                        <input type="text" size="40" name="noMunicipio" placeholder="Introduce tu municipio"><br><br>
                    </h4>
                    <h4>
                        Dirección:<br>
                        Calle,Urbanización,Avenida,etc...<br>
                        <input type="text" size="40" name="direccion" placeholder="Introduce tu dirección"><br><br>
                        Email:<br>
                        Si no tiene correo electrónico,escriba en el recuadro el número 1 (Por favor, vigilen las MAYÚSCULAS y minúsculas).<br>
                        <input type="text" size="40" name="email" placeholder="Introduce tu email"><br><br>
                        Fax:<br>
                        Si no tiene fax,escriba en el recuadro el número 1.<br>
                        <input type="text" size="40" name="fax" placeholder="Introduce tu fax"><br><br>
                        Teléfono:<br>
                        Indicar número de teléfono, móvil o fijo. (Poner el número completo SIN ESPACIOS).<br>
                        <input type="text" size="40" name="telefono" placeholder="Introduce tu nº de teléfono"><br><br>
                    </h4>
                </fieldset>

                <fieldset>
                    <legend>Información Acampada :</legend>
                    <h4>
                        Zona: 
                        <select name="playaSelect" class="selectpicker">
                            <option>Garcey</option>
                            <option>La Solapa</option>
                            <option>Vigocho</option>
                        </select>
                        <br><br>

                        Fecha Entrada: <br>
                        Indique la FECHA DE ENTRADA:<br>
                        <input class="form-control" type="date" name="fechaentrada" value="" id="example-date-input" style="width: 175px;">
                        <br>

                        Fecha Salida: <br>
                        Indique la FECHA DE SALIDA:<br>
                        <input class="form-control" type="date" name="fechasalida" value="" id="example-date-input" style="width: 175px;">
                        <br>

                        Personas:<br>
                        Indique el número total de personas que van a acampar.<br>
                        <select name="personasCantidad" class="selectpicker">
                            <option>1</option><option>2</option><option>3</option>
                            <option>4</option><option>5</option><option>6</option>
                            <option>7</option><option>8</option><option>9</option>
                            <option>10</option><option>11</option><option>12</option>
                        </select>
                        <br><br>

                        Tipo:<br>
                        Indique el TIPO DE ACAMPADA (Tamaño de la parcela 7,7 metros cuadrados, 
                        solo esta permitida una caseta en caso de llevar caravana y solo se puede pedir una caravana por solicitud)<br>
                        <select name="tipoAcampada" class="selectpicker">
                            <option>Caseta</option>
                            <option>Caravana</option>
                            <option>Ambas</option>
                        </select>
                        <br><br>

                        Casetas:<br>
                        Indique el NÚMERO DE CASETAS (Recuerde con Caravana máximo 1 caseta)<br>
                        <select name="numeroCasetas" class="selectpicker">
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                        </select>
                        <br><br>

                        Caravanas:<br>
                        Indique 0 si solo acampa con casetas o 1 si acampa con una caravana(en el caso de acampar con una caravana indique en el recuadro de la derecha la matrícula.) <br>
                        <select name="numeroCasetas" class="selectpicker">
                            <option>0</option>
                            <option>1</option>
                        </select>
                        Indique la matrícula de la caravana (en el caso de que acampe con caravana)
                        <input type="text" size="40" name="matriculaCaravana" placeholder="Introduce la matrícula"><br><br>
                        <br>

                        Recoger:<br>
                        Indique el punto de recogida de su autorización. No se enviarán autorizaciones a la Tenencia de Alcaldía de La Lajita.<br>
                        <form>
                            <input type="radio" name="optradio" value="Ayuntamiento de Pájara (Plaza Nuestra Señora de Regla)" > Ayuntamiento de Pájara (Plaza Nuestra Señora de Regla)<br>
                            <input type="radio" name="optradio" value="Usos Múltiples de Morro Jable (Edificio Policia Local)"> Usos Múltiples de Morro Jable (Edificio Policia Local)<br>
                            <input type="radio" name="optradio" value="Tenencia de Alcaldía de Costa Calma"> Tenencia de Alcaldía de Costa Calma<br>
                            <input type="radio" name="optradio" value="Por correo electrónico"> Por correo electrónico<br>
                            <input type="radio" name="optradio" value="Por Fax"> Por Fax
                        </form>

                        <br>
                        <br>
                        <div class="form-group">
                            <label for="comment">Observaciones:</label>
                            <textarea class="form-control" rows="5" id="observaciones"></textarea>
                        </div>
                    </h4>
                </fieldset>
                <center>
                    <br>
                    <strong><h2>Por favor,antes de enviar la solicitud asegúrese de que todo es correcto.</h2></strong>
                    <br>

                    <input  type="hidden" name="command" value="RegisterCommand">
                    <input type="submit" value="Enviar Solicitud">
                    <br><br><br>
                </center>
        </div>

    </body>
</html>