/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.DocumentException;
import controller.PlayadatospersonasFacade;
import controller.PlayaplazasmaximasasociadasFacade;
import entities.Playadatospersonas;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/*  A continuación se explica que tipo de control de error efectuará cada método.
    Método -> Error que controla
    comprobarSiEstaVacio   -> Comprueba si un campo introducido por el usuario
                              está vacío.
    comprobarSiNoHayNumero -> Comprueba si un campo introducido por el usuario
                              tiene un número.
    comprobarQueMunicipioDeVerdadNoEsta -> ¿?
    comprobacionesDNI      -> Comprueba que el DNI del usuario introducido
                              no esté en la BD.
    comprobacionesEmail    -> Comprueba que hay un @ solamente.
    comprobacionesFechas   -> Comprobaciones de fechas :
                                1º Que la fecha de entrada no sea superior a 
                                la de salida.
                                2º Que no hayan fechas intermedias entre la de 
                                entrada y salida donde estén las plazas 
                                ocupadas.
    comprobarCaravanaParaIntroducirMatricula -> En el caso de que haya 
                                escogido caravana que introduzca la matrícula.
    comprobarCaravanaSoloUnaCaseta   -> En el caso de que haya escogido caravana
                                que solo coja una caseta.
*/

public class RegisterCommand extends FrontCommand {

    @Override
    public void process() {
        try {

            /*String que irá almacenando todas las incidencias que se produzcan.
              En el caso de que haya alguna incidencia se le comunicará al usuario
              y no se hará efectiva su solicitud.*/

            String posibleError = "A continuación se listan una serie de problemas encontrados,revisalos por favor.";
            
            String nombre = request.getParameter("nombre");
            if(comprobarSiEstaVacio(nombre))
                posibleError+="- No ha introducido ningún nombre en el campo nombre.\n";
            if (comprobarSiNoHayNumero(nombre))
                posibleError+="Ha introducido un número en el nombre en el campo nombre.\n";
            
            
            String apellidos = request.getParameter("apellidos");
            if(comprobarSiEstaVacio(apellidos))
                posibleError+="- No ha introducido ningún nombre en el campo nombre.\n";
            if (comprobarSiNoHayNumero(apellidos))
                posibleError+="- Ha introducido un número en el nombre en el campo nombre.\n";
            
            String dni = request.getParameter("DNI");
            comprobacionesDNI(dni);
            
            String municipio = request.getParameter("municipioSelect");
            String nomunicipio = request.getParameter("noMunicipio");
            comprobarQueMunicipioDeVerdadNoEsta(nomunicipio);
            
            String direccion = request.getParameter("direccion");
            comprobarSiEstaVacio(direccion);
            
            String email = request.getParameter("email");
            comprobacionesEmail(email);
            
            String fax = request.getParameter("fax");
            comprobarQueNoHayLetras(fax);
            
            String telefono = request.getParameter("telefono");
            comprobarQueNoHayLetras(telefono);
            
            String playa = request.getParameter("playaSelect");
            
            String fechaEntrada = request.getParameter("fechaentrada");
            String fechaSalida = request.getParameter("fechasalida");
            comprobacionesFechas(fechaEntrada, fechaSalida);

            String cantidadPersonas = request.getParameter("personasCantidad");
            String tipoAcampada = request.getParameter("tipoAcampada");
            String numeroCasetas = request.getParameter("numeroCasetas");
            
            String caravanaOCaseta = request.getParameter("caravana");
            String matriculaCaravana = request.getParameter("matriculaCaravana");
            comprobarCaravanaParaIntroducirMatricula(caravanaOCaseta,matriculaCaravana);
            comprobarCaravanaSoloUnaCaseta(caravanaOCaseta,numeroCasetas);
            
            String opcionRecogida = request.getParameter("optradio");
            String observaciones = request.getParameter("observaciones");

            
            PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(fechaEntrada);
            Playadatospersonas insercion = new Playadatospersonas(null, playa, 
                    nombre, apellidos, dni, municipio, email, telefono, null, null,
                    Integer.parseInt(cantidadPersonas), tipoAcampada, Integer.parseInt(numeroCasetas), caravanaOCaseta, matriculaCaravana,observaciones);
            insercion.setFechaEntrada(formatter.parse(fechaEntrada));
            insercion.setFechaSalida(formatter.parse(fechaSalida));
            
            //pdpF.create(insercion);
            
            
            pdfCommand test = new pdfCommand();
            try {
                test.createPdf("c:/ProyectoAcampadaRuben/prueba.pdf");
            } catch (DocumentException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward("/unknown.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void comprobacionesFechas(String aux12, String aux13) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(aux12);
        Date date2 = formatter.parse(aux13);
        if (date.compareTo(date2) == -1) {
            System.out.println(date);
        } else {
            System.out.println(date2);
        }
    }

    private boolean comprobarSiEstaVacio(String nombre) {
        return true;
    }

    private boolean comprobarSiNoHayNumero(String nombre) {
        return true;
    }

    private void comprobacionesDNI(String dni) {
    }

    private void comprobarQueMunicipioDeVerdadNoEsta(String nomunicipio) {
    }

    private void comprobacionesEmail(String email) {
    }

    private void comprobarQueNoHayLetras(String fax) {
    }

    private void comprobarCaravanaParaIntroducirMatricula(String caravanaOCaseta, String matriculaCaravana) {
    }

    private void comprobarCaravanaSoloUnaCaseta(String caravanaOCaseta, String numeroCasetas) {
    }
}
