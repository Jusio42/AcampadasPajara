package controllers;

import com.itextpdf.text.DocumentException;
import controller.MunicipiosFacade;
import controller.MunicipiosProvisionalesFacade;
import controller.PlayadatospersonasFacade;
import controller.PlayaplazasmaximasasociadasFacade;
import entities.Municipios;
import entities.MunicipiosProvisionales;
import entities.Playadatospersonas;
import entities.Playaplazasmaximasasociadas;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/*  A continuación se explica que tipo de control de error efectuará cada método.
 Método                   -> Error que controla
 comprobarSiEstaVacio     -> Comprueba si un campo introducido por el usuario
 está vacío.
 comprobarSiNoHayNumero   -> Comprueba si un campo introducido por el usuario
 tiene un número.
 comprobarQueMunicipioDeVerdadNoEsta -> ¿?
 comprobacionesDNI        -> Comprueba que el DNI del usuario introducido
 no esté en la BD.
 comprobarFormaCorrectaDNI -> Comprueba que el usuario ha respetado la forma de meter un dni.
 comprobacionesEmail      -> Comprueba que hay un @ solamente.
 comprobacionesFechas     -> Comprobaciones de fechas :
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

    boolean errorDeParametros = false;

    @Override
    public void process() {

        try {

            /*String que irá almacenando todas las incidencias que se produzcan.
             En el caso de que haya alguna incidencia se le comunicará al usuario
             y no se hará efectiva su solicitud.
             En el caso de haber un error el boolean se pone a true no insertando
             nada en la BD.
             */
            String posibleError = "A continuación se listan una serie de problemas encontrados,revisalos por favor : \n";

            //----- NOMBRE
            String nombre = request.getParameter("nombre");
            if (!comprobarQueNoEstaVacio(nombre)) {
                posibleError += "- No ha introducido ningún nombre en el campo nombre.\n";
            }
            if (!comprobarSiHayNumero(nombre)) {
                posibleError += "- Ha introducido un número en el nombre en el campo nombre.\n";
            }

            //----- APELLIDOS
            String apellidos = request.getParameter("apellidos");
            if (!comprobarQueNoEstaVacio(apellidos)) {
                posibleError += "- No ha introducido ningún apellido en el campo apellido.\n";
            }
            if (!comprobarSiHayNumero(apellidos)) {
                posibleError += "- Ha introducido un número en el nombre en el campo apelido.\n";
            }

            //----- DNI
            String dni = request.getParameter("DNI");
            if (!comprobarSiEstaDNI(dni) || !comprobarFormaCorrecta(dni)) {
                posibleError += "- Hemos encontrado una petición suya hecha recientemente o no ha introducido de la forma correcta el DNI, por favor revíselo.\n";
            }

            //----- MUNICIPIOS
            String municipio = request.getParameter("municipioSelect");
            String nomunicipio = request.getParameter("noMunicipio");
            if (municipio.length() == 0) {
                if (nomunicipio.length() == 0) {
                    posibleError += "Tiene que introducir un municipio.\n";
                    setErrorTrue();
                } else {
                    if (comprobarQueMunicipioDeVerdadNoEsta(nomunicipio)) {
                        posibleError += "El municipio introducido por usted si existe.\n";
                        setErrorTrue();
                    }
                }
            }

            //----- DIRECCION
            String direccion = request.getParameter("direccion");
            if (!comprobarQueNoEstaVacio(direccion)) {
                posibleError += "La dirección no puede quedar vacía.\n";
            }

            //----- EMAIL
            String email = request.getParameter("email");
            if (!comprobacionesEmail(email)) {
                posibleError += "El correo electrónico no es válido.\n";
            }

            //----- FAX
            String fax = request.getParameter("fax");
            if (comprobarQueNoHayLetras(fax)) {
                posibleError += "El Fax introducido no es válido.\n";
            }

            //----- TELEFONO
            String telefono = request.getParameter("telefono");
            if (comprobarQueNoHayLetras(telefono)) {
                posibleError += "El Telefono introducido no es válido.\n";
            }

            //----- PLAYA
            String playa = request.getParameter("playaSelect");

            //----- FECHAS Y PLAYA
            String fechaEntrada = request.getParameter("fechaentrada");
            String fechaSalida = request.getParameter("fechasalida");
            comprobacionesFechasYZona(playa, fechaEntrada, fechaSalida);

            //----- PERSONAS
            String cantidadPersonas = request.getParameter("personasCantidad");

            //----- OPCIONES DE ACAMPADA
            String tipoAcampada = request.getParameter("tipoAcampada");
            String numeroCasetas = request.getParameter("numeroCasetas");
            String caravanaOCaseta = request.getParameter("caravana");// ESTE SOBRA
            String matriculaCaravana = request.getParameter("matriculaCaravana");
            if (!comprobarCaravanaParaIntroducirMatricula(caravanaOCaseta, matriculaCaravana)) {
                posibleError += "Por favor introduza la matrícula de su caravana.\n";
            }
            if (comprobarCaravanaSoloUnaCaseta(caravanaOCaseta, numeroCasetas)){
                posibleError += "No puede solicitar mas de una caseta si va con caravana.\n";
            }

            //----- RECOGER DOCUMENTO
            String opcionRecogida = request.getParameter("optradio");

            //----- OBSERVACIONES
            String observaciones = request.getParameter("observaciones");

            PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");

            if (!errorDeParametros) {
                Playadatospersonas insercionEnBD = new Playadatospersonas(42, playa, nombre, apellidos, dni,
                        municipio, email, telefono, fechaEntrada, fechaSalida,
                        Integer.parseInt(cantidadPersonas), tipoAcampada, Integer.parseInt(numeroCasetas), matriculaCaravana, opcionRecogida, observaciones);
                insercionEnBD.setCaraCase(caravanaOCaseta);
                insercionEnBD.setMatriculaCar(matriculaCaravana);

                pdpF.create(insercionEnBD);
                pdfCommand test = new pdfCommand();
                try {
                    test.createPdf("c:/ProyectoAcampadaRuben/prueba.pdf");
                } catch (DocumentException ex) {
                    Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            forward("/unknown.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODOS LAS COMPROBACIONES DEVUELVEN -- TRUE -- SI ESTÁN CORRECTAS.
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

    private boolean comprobarQueNoEstaVacio(String nombre) {
        if (nombre.length() <= 0) {
            setErrorTrue();
            return false;
        }
        return true;
    }

    private boolean comprobarSiHayNumero(String nombre) {
        if (nombre.matches(".*\\d.*")) {
            setErrorTrue();
            return false;
        }
        return true;
    }

    private boolean comprobarSiEstaDNI(String dni) throws NamingException {
        PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
        List<Playadatospersonas> playasYDatos = pdpF.findAll();
        for (Playadatospersonas playasYDato : playasYDatos) {
            if (dni.equals(playasYDato.getDni())) {
                setErrorTrue();
                return false;
            }
        }
        return true;
    }

    private boolean comprobarFormaCorrecta(String dni) {
        if (dni.length() == 9) {
            if (((dni.substring(0, 7).contains("[a-z]"))
                    || (dni.substring(0, 7).contains("[A-Z]")))
                    || (dni.substring(7, 8).contains("[0-9]"))) {
                setErrorTrue();
                return false;
            }
        }
        return true;
    }

    private boolean comprobarQueMunicipioDeVerdadNoEsta(String nomunicipio) throws NamingException {
        MunicipiosFacade mF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/MunicipiosFacade");
        List<Municipios> municipios = mF.findAll();
        for (Municipios municipio : municipios) {
            if (municipio.getMunicipio() == nomunicipio) {
                setErrorTrue();
                return false;
            }
        }
        MunicipiosProvisionalesFacade mpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/MunicipiosProvisionalesFacade");
        MunicipiosProvisionales municipioQueNoEsta = new MunicipiosProvisionales(null, nomunicipio);
        mpF.create(municipioQueNoEsta);
        return true;
    }

    private boolean comprobacionesEmail(String email) {
        if (email.equals("1")) {
            return true;
        }
        /*Patrón para evaluar si un email es válido(OJO, no contempla 
         si el email existe o no, solo si la forma en la que está
         escrito es correcto, por ejemplo [test@example.com , true];
         [exampel101@test.a , false]).*/
        String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean comprobarQueNoHayLetras(String fax) {
        if (fax.contains("[a-z]") || fax.contains("[A-Z]")) {
            setErrorTrue();
            return true;
        }
        return false;
    }

    private boolean comprobarCaravanaParaIntroducirMatricula(String caravanaOCaseta, String matriculaCaravana) {
        if (caravanaOCaseta.equals("Caravanas")) {
            if (matriculaCaravana.length() > 0) {
                return true;
            } else {
                setErrorTrue();
                return false;
            }
        }
        return true;
    }

    private boolean comprobarCaravanaSoloUnaCaseta(String caravanaOCaseta, String numeroCasetas) {
        if ((caravanaOCaseta.equals("Caravanas"))&& (Integer.parseInt(numeroCasetas) > 1)){
            setErrorTrue();
            return false;
        }
        return true;
    }

    private boolean comprobacionesFechasYZona(String playa, String fechaEntrada, String fechaSalida) {
        return true;
    }

    private void setErrorTrue() {
        errorDeParametros = true;
    }
}
