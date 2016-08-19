package controllers;

import com.itextpdf.text.DocumentException;
import controller.MunicipiosFacade;
import controller.MunicipiosProvisionalesFacade;
import controller.PlayadatospersonasFacade;
import controller.PlayaplazasfechaocupadasFacade;
import controller.PlayaplazasmaximasasociadasFacade;
import entities.Municipios;
import entities.MunicipiosProvisionales;
import entities.Playadatospersonas;
import entities.Playaplazasfechaocupadas;
import entities.Playaplazasmaximasasociadas;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.servlet.http.HttpSession;
import org.jboss.weld.servlet.SessionHolder;

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

        HttpSession session = request.getSession(true);

        try {

            /*String que irá almacenando todas las incidencias que se produzcan.
             En el caso de que haya alguna incidencia se le comunicará al usuario
             y no se hará efectiva su solicitud.
             En el caso de haber un error el boolean se pone a true no insertando
             nada en la BD.
             */
            
            String posibleError = "";

            // ----------------------------------------------------------------
            // TODOS LAS COMPROBACIONES DEVUELVEN -- TRUE -- SI ESTÁN CORRECTAS.
            // ----------------------------------------------------------------
            //Obtención de los datos todo los datos 
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("DNI");
            String municipio = request.getParameter("municipioSelect");
            String nomunicipio = request.getParameter("noMunicipio");
            String direccion = request.getParameter("direccion");
            String email = request.getParameter("email");
            String fax = request.getParameter("fax");
            String telefono = request.getParameter("telefono");
            String playa = request.getParameter("playaSelect");
            String fechaEntrada = request.getParameter("fechaentrada");
            String fechaSalida = request.getParameter("fechasalida");
            String cantidadPersonas = request.getParameter("personasCantidad");
            String tipoAcampada = request.getParameter("tipoAcampada");
            String numeroCasetas = request.getParameter("numeroCasetas");
            String matriculaCaravana = request.getParameter("matriculaCaravana");
            String opcionRecogida = request.getParameter("optradio");
            String observaciones = request.getParameter("observaciones");
            //-------------------------------------------------------------------

            //----- NOMBRE
            if (!comprobarQueNoEstaVacio(nombre)) {
                posibleError += "- No ha introducido ningún nombre en el campo nombre.<br>";
            }
            if (!comprobarSiHayNumero(nombre)) {
                posibleError += "- Ha introducido un número en el nombre en el campo nombre.<br>";
            }

            //----- APELLIDOS
            if (!comprobarQueNoEstaVacio(apellidos)) {
                posibleError += "- No ha introducido ningún apellido en el campo apellido.<br>";
            }
            if (!comprobarSiHayNumero(apellidos)) {
                posibleError += "- Ha introducido un número en el nombre en el campo apelido.<br>";
            }

            //----- DNI
            if (!comprobarSiEstaDNI(dni, fechaEntrada)) {
                posibleError += "- Hemos encontrado una petición suya para el día y playa solicitada.";
                posibleError += "Por favor escoja otro periodo.<br>";
            }
            if (!comprobarFormaCorrecta(dni)) {
                posibleError += "- El DNI introducido NO es valido.<br>";
            }
            if (!comprobarQueNoEstaVacio(dni)) {
                posibleError += "- Introduzca un DNI por favor.<br>";
            }

            //----- MUNICIPIOS
            if (municipio.length() == 0) {
                if (nomunicipio.length() == 0) {
                    posibleError += "Tiene que introducir un municipio.<br>";
                    setErrorTrue();
                } else {
                    if (comprobarQueMunicipioDeVerdadNoEsta(nomunicipio)) {
                        posibleError += "El municipio introducido por usted si existe,seleccionelo del listado por favor.<br>";
                        setErrorTrue();
                    }
                }
            }

            //----- DIRECCION
            if (!comprobarQueNoEstaVacio(direccion)) {
                posibleError += "La dirección no puede quedar vacía.<br>";
            }

            //----- EMAIL
            if (!comprobacionesEmail(email)) {
                posibleError += "El correo electrónico no es válido.<br>";
            }
             if (!comprobarQueNoEstaVacio(email)) {
                posibleError += "- Introduzca un email por favor.<br>";
            }

            //----- FAX
            if (!comprobarQueNoHayLetras(fax)) {
                posibleError += "El Fax introducido no es válido.<br>";
            }
            if (!comprobarQueNoEsNulo(fax)) {
                fax = "Sin fax";
            }
            if (!comprobarCorrectoNumero(fax)) {
                posibleError += "El Fax introducido no es válido.<br>";
            }

            //----- TELEFONO
            if (!comprobarQueNoHayLetras(telefono)) {
                posibleError += "El Telefono introducido no es válido.<br>";
            }
            if (!comprobarCorrectoNumero(telefono)) {
                posibleError += "El telefono introducido no es válido.<br>";
            }

            //----- FECHAS Y PLAYA
            String posiblesFechasIncompatibles = "";

            if (fechaEntrada.length() == 0 || fechaSalida.length() == 0) {
                posibleError += "Especifica tanto la fecha de entrada como de salida por favor.<br>";
            } else {
                if (!comprobarFechaSalidaMayorQueFechaEntrada(fechaEntrada, fechaSalida)) {
                    setErrorTrue();
                    posibleError += "La fecha de salida es anterior a la de entrada, revisalo por favor.<br>";
                } else {
                    posiblesFechasIncompatibles = comprobarSiHayPlazasEnUnRangoDeFecha(playa, fechaEntrada, fechaSalida);
                    if (posiblesFechasIncompatibles.length() != 0) {
                        posibleError += "El rango especificado no es válido debido a que hay fechas intermedias "
                                + "en que la playa seleccionada no dispone de plazas.<br>" + posiblesFechasIncompatibles;
                    }
                }
            }

            //----- MATRICULA CARAVANA
            if (!comprobarQueNoEsNulo(matriculaCaravana)) {
                matriculaCaravana = "Sin caravana";
            }

            if (!comprobarCaravanaParaIntroducirMatricula(tipoAcampada, matriculaCaravana)) {
                posibleError += "Por favor introduza la matrícula de su caravana.<br>";
            }
            if (!comprobarCaravanaSoloUnaCaseta(tipoAcampada, numeroCasetas)) {
                posibleError += "No puede solicitar mas de una caseta si va con caravana.<br>";
            }

            //----- OBSERVACIONES
            if (!comprobarQueNoEsNulo(observaciones)) {
                observaciones = "Sin observaciones";
            }

            PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");

            if (!errorDeParametros) {
                while (comprobarFechaSalidaMayorQueFechaEntrada(fechaEntrada, fechaSalida)) {
                    Playadatospersonas registroInsercionEnBD = new Playadatospersonas(null, playa,
                            nombre, apellidos, dni, municipio, email,
                            telefono, fechaEntrada, fechaSalida, Integer.parseInt(cantidadPersonas),
                            tipoAcampada, Integer.parseInt(numeroCasetas), opcionRecogida);
                    registroInsercionEnBD.setFax(fax);
                    registroInsercionEnBD.setMatriculaCar(matriculaCaravana);
                    registroInsercionEnBD.setObservaciones(observaciones);

                    fechaEntrada = sumarUnDiaAFechaEntrada(fechaEntrada);

                    insertaEnBD(registroInsercionEnBD, pdpF);
                }
                pdfCommand test = new pdfCommand();
                try {
                    test.createPdf("c:/ProyectoAcampadaRuben/prueba.pdf");
                } catch (DocumentException | MessagingException ex) {
                    Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("problemas", posibleError);
                forward("/displayErrors.jsp");
            }
            forward("/reservaHecha.jsp");
        } catch (ServletException | IOException | NamingException | ParseException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private boolean comprobarSiEstaDNI(String dni, String fEntrada) throws NamingException {
        PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
        List<Playadatospersonas> playasYDatos = pdpF.findAll();
        for (Playadatospersonas playasYDato : playasYDatos) {
            if ((dni.equals(playasYDato.getDni()) && (fEntrada.equals(playasYDato.getFechaEntrada())))) {
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
            if (municipio.getMunicipio().equals(nomunicipio)) {
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

    private boolean comprobarQueNoHayLetras(String check) {
        if (check.contains("[a-z]") || check.contains("[A-Z]")) {
            setErrorTrue();
            return false;
        }
        return true;
    }

    private boolean comprobarCaravanaParaIntroducirMatricula(String caravanaOCaseta, String matriculaCaravana) {
        if (caravanaOCaseta.equals("Caseta")) {
            return true;
        } else {
            if (matriculaCaravana.length() > 5) {
                return true;
            }
        }
        return false;

    }

    private boolean comprobarCaravanaSoloUnaCaseta(String caravanaOCaseta, String numeroCasetas) {
        if (caravanaOCaseta.equals("Caseta")) {
            return true;
        } else {
            if (Integer.parseInt(numeroCasetas) > 1) {
                setErrorTrue();
                return false;
            }
        }
        return true;
    }

    private boolean comprobarFechaSalidaMayorQueFechaEntrada(String fechaEntrada, String fechaSalida) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaEntradaDate = formatter.parse(fechaEntrada);
        Date fechaSalidaDate = formatter.parse(fechaSalida);
        if (fechaEntradaDate.compareTo(fechaSalidaDate) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private String comprobarSiHayPlazasEnUnRangoDeFecha(String playa, String fechaEntrada, String fechaSalida) throws ParseException, NamingException {
        String fechasNoPosiblesReservar = "";
        while (comprobarFechaSalidaMayorQueFechaEntrada(fechaEntrada, fechaSalida)) {
            if (!consultarFechaBD(fechaEntrada, playa)) {
                fechasNoPosiblesReservar += "El dia " + fechaEntrada + " no se puede reservar.<br>";
            }
            fechaEntrada = sumarUnDiaAFechaEntrada(fechaEntrada);
        }
        return fechasNoPosiblesReservar;
    }

    private String sumarUnDiaAFechaEntrada(String fechaEntrada) throws ParseException {
        String fechaASumarUnDia = fechaEntrada;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(fechaASumarUnDia));
        c.add(Calendar.DATE, 1);  // number of days to add
        fechaASumarUnDia = sdf.format(c.getTime());  // dt is now the new date
        return fechaASumarUnDia;
    }

    private boolean consultarFechaBD(String fechaATratar, String playa) throws NamingException {
        int plazasMaximasPermitidasEnPlaya = obtenerPlazasMaximasDePlaya(playa);
        PlayaplazasfechaocupadasFacade ppfoF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasfechaocupadasFacade");
        List<Playaplazasfechaocupadas> playaFechaPlazas = ppfoF.findAll();

        // Buscamos Playa,Fecha y que ademas el numero de plazas < que el permitido,
        // AQUI NO INSERTAMOS SOLO CONSULTAMOS.
        for (Playaplazasfechaocupadas playaFechaPlaza : playaFechaPlazas) {
            if (playaFechaPlaza.getPlaya().equals(playa)
                    && playaFechaPlaza.getFecha().equals(fechaATratar)) {
                if (playaFechaPlaza.getPlazas() < plazasMaximasPermitidasEnPlaya) {
                    return true;
                } else {
                    setErrorTrue();
                    return false;
                }
            }
        }
        return true;
    }

    private int obtenerPlazasMaximasDePlaya(String playa) throws NamingException {
        PlayaplazasmaximasasociadasFacade ppmF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasmaximasasociadasFacade");
        List<Playaplazasmaximasasociadas> playasYPlazas = ppmF.findAll();
        for (Playaplazasmaximasasociadas playasYPlaza : playasYPlazas) {
            if (playasYPlaza.getNombreplaya().equals(playa)) {
                return playasYPlaza.getNplazasmaximo();
            }
        }
        return 0;
    }

    private void insertaEnBD(Playadatospersonas registroInsercionEnBD, PlayadatospersonasFacade pdpF) throws NamingException {
        /* 
         Algoritmo que hace dos inserciones en dos tablas diferentes :
         1ºPlayaYDatosPersonas : Hacemos todas las inserciones de todas las fechas en esta tabla.
         2ºPlayaYPlazasFechasOcupadas : Si hay una fecha cogida pues incrementamos las plazas.
         En este caso hay que tener un pequeño control de si existe o no esa entrada en la bd,
         bien para incrementar o para generar la entrada pertinente.
         
         SE HARÁN LAS DOS INSERCIONES AL MISMO TIEMPO PARA EVITAR INCONSISTENCIAS.
         */
        int plazasMaximasPermitidasEnPlaya = obtenerPlazasMaximasDePlaya(registroInsercionEnBD.getPlaya());
        boolean editado = false;
        PlayaplazasfechaocupadasFacade ppfoF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasfechaocupadasFacade");
        List<Playaplazasfechaocupadas> playaFechaPlazas = ppfoF.findAll();

        //||||||||||
        //Algoritmo comprobacion para la 2º tabla
        //---------------------------------------------------------------------------------
        for (Playaplazasfechaocupadas playaFechaPlaza : playaFechaPlazas) {
            if (playaFechaPlaza.getPlaya().equals(registroInsercionEnBD.getPlaya())
                    && playaFechaPlaza.getFecha().equals(registroInsercionEnBD.getFechaEntrada())) {
                if (playaFechaPlaza.getPlazas() < plazasMaximasPermitidasEnPlaya) {
                    Playaplazasfechaocupadas registroEditado = new Playaplazasfechaocupadas(null, playaFechaPlaza.getPlaya(), playaFechaPlaza.getFecha(), playaFechaPlaza.getPlazas() + 1);
                    //Edicion de registro en la 2º Tabla (Insercion)
                    ppfoF.edit(registroEditado);
                    editado = true;
                    //Insercion en la 1º Tabla
                    pdpF.create(registroInsercionEnBD);
                    break;
                }
            }
        }
        if (!editado) {
            //Insercion en la 2º Tabla
            pdpF.create(registroInsercionEnBD);

            //Insercion en la 1º Tabla
            Playaplazasfechaocupadas registroEditado = new Playaplazasfechaocupadas(null, registroInsercionEnBD.getPlaya(), registroInsercionEnBD.getFechaEntrada(), 1);
            ppfoF.create(registroEditado);
        }
        //---------------------------------------------------------------------------------
    }

    private void setErrorTrue() {
        errorDeParametros = true;
    }

    private boolean comprobarQueNoEsNulo(String posibilidadNulo) {
        if (posibilidadNulo.length() == 0) {
            return false;
        }
        return true;
    }

    private boolean comprobarCorrectoNumero(String number) {
        if (number.length() < 9) {
            setErrorTrue();
            return false;
        }
        return true;
    }

}
