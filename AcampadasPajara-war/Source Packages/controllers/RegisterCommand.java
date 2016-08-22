package controllers;

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
import java.util.AbstractList;
import java.util.ArrayList;
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
    String posibleError = "";

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
                posibleError += "Nombre : - No ha introducido ningún nombre en el campo nombre.<br>";
            }
            if (!comprobarSiHayNumero(nombre)) {
                posibleError += "Nombre : - Ha introducido un número en el nombre en el campo nombre.<br>";
            }

            //----- APELLIDOS
            if (!comprobarQueNoEstaVacio(apellidos)) {
                posibleError += "Apellidos : - No ha introducido ningún apellido en el campo apellido.<br>";
            }
            if (!comprobarSiHayNumero(apellidos)) {
                posibleError += "Apellidos : - Ha introducido un número en el nombre en el campo apelido.<br>";
            }

            //----- DNI
            if (!comprobarFormaCorrecta(dni)) {
                posibleError += "DNI : - El DNI introducido NO es valido.<br>";
            }
            if (!comprobarQueNoEstaVacio(dni)) {
                posibleError += "DNI : - Introduzca un DNI por favor.<br>";
            }
            if (!comprobarValidezDNI(dni)) {
                posibleError += "DNI : - El DNI parece no ser correcto, por favor compruebalo.<br>";
            }

            //----- MUNICIPIOS
            if (municipio.length() == 0) {
                if (nomunicipio.length() == 0) {
                    posibleError += "Municipio : - Tiene que introducir un municipio.<br>";
                    setErrorTrue();
                } else if (comprobarQueMunicipioDeVerdadNoEsta(nomunicipio)) {
                    posibleError += "Municipio : - El municipio introducido por usted si existe,seleccionelo del listado por favor.<br>";
                    setErrorTrue();
                }
            }

            //----- DIRECCION
            if (!comprobarQueNoEstaVacio(direccion)) {
                posibleError += "Direccion : - La dirección no puede quedar vacía.<br>";
            }

            //----- EMAIL
            if (!comprobacionesEmail(email)) {
                posibleError += "Email : - El correo electrónico no es válido.<br>";
            }
            if (!comprobarQueNoEstaVacio(email)) {
                posibleError += "Email : - Introduzca un email por favor.<br>";
            }

            //----- FAX
            if (!comprobarQueNoEsNulo(fax)) {
                fax = "Sin fax";
            } else {
                if (!comprobarQueNoHayLetras(fax)) {
                    posibleError += "Fax : - El Fax introducido no es válido.<br>";
                }
                if (!comprobarSiHayNumero(fax)) {
                    posibleError += "Fax : - El Fax introducido no es válido.<br>";
                }
            }

            //----- TELEFONO
            if (!comprobarQueNoHayLetras(telefono)) {
                posibleError += "Telefono : - El Telefono introducido no es válido.<br>";
            }
            if (!comprobarCorrectoNumero(telefono)) {
                posibleError += "Telefono : - El telefono introducido no es válido.<br>";
            }

            //----- FECHAS Y PLAYA
            String posiblesFechasIncompatibles = "";

            if (fechaEntrada.length() == 0 || fechaSalida.length() == 0) {
                posibleError += "Periodo : - Especifica tanto la fecha de entrada como de salida por favor.<br>";
            } else if (!comprobarFechaSalidaMayorQueFechaEntrada(fechaEntrada, fechaSalida)) {
                setErrorTrue();
                posibleError += "Periodo : - La fecha de salida es anterior a la de entrada, revisalo por favor.<br>";
            } else {
                posiblesFechasIncompatibles = comprobarSiHayPlazasEnUnRangoDeFecha(playa, fechaEntrada, fechaSalida);
                if (posiblesFechasIncompatibles.length() != 0) {
                    posibleError += "Periodo : - El rango especificado no es válido debido a que hay fechas intermedias "
                            + "en que la playa seleccionada no dispone de plazas.<br>" + posiblesFechasIncompatibles;
                }
            }

            //----- MATRICULA CARAVANA
            if (!comprobarQueNoEsNulo(matriculaCaravana)) {
                matriculaCaravana = "Sin caravana";
            }

            if (!comprobarCaravanaParaIntroducirMatricula(tipoAcampada, matriculaCaravana)) {
                setErrorTrue();
                posibleError += "Matricula Caravana : - Por favor introduza la matrícula de su caravana.<br>";
            }
            if (!comprobarCaravanaSoloUnaCaseta(tipoAcampada, numeroCasetas)) {
                posibleError += "Matricula Caravana : - No puede solicitar mas de una caseta si va con caravana.<br>";
            }

            //----- OBSERVACIONES
            if (!comprobarQueNoEsNulo(observaciones)) {
                observaciones = "Sin observaciones";
            }

            Playadatospersonas registroInsercionEnBD = new Playadatospersonas(null, playa,
                    nombre, apellidos, dni, municipio, email,
                    telefono, fechaEntrada, fechaSalida, Integer.parseInt(cantidadPersonas),
                    tipoAcampada, Integer.parseInt(numeroCasetas), opcionRecogida);

            registroInsercionEnBD.setFax(fax);
            registroInsercionEnBD.setMatriculaCar(matriculaCaravana);
            registroInsercionEnBD.setObservaciones(observaciones);

            posibilidadInsertaEnPlayaDatosPersonas(registroInsercionEnBD);

            if (errorDeParametros) {
                session.setAttribute("problemas", posibleError);
                forward("/displayErrors.jsp");
            } else {
                insertaEnPlayaDatosPersonas(registroInsercionEnBD);
                insertaEnPlayaPlazasFechasOcupadas(registroInsercionEnBD);
                forward("/reservaHecha.jsp");
            }

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
        } else if (matriculaCaravana.length() > 5 && !matriculaCaravana.equals("Sin caravana")) {
            return true;
        }
        return false;

    }

    private boolean comprobarCaravanaSoloUnaCaseta(String caravanaOCaseta, String numeroCasetas) {
        if (caravanaOCaseta.equals("Caseta")) {
            return true;
        } else if (Integer.parseInt(numeroCasetas) > 1) {
            setErrorTrue();
            return false;
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

    private String sumarUnDiaAFechaEntrada(String fechaEntrada) throws ParseException {
        String fechaASumarUnDia = fechaEntrada;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(fechaASumarUnDia));
        c.add(Calendar.DATE, 1);  // number of days to add
        fechaASumarUnDia = sdf.format(c.getTime());  // dt is now the new date
        return fechaASumarUnDia;
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

    private boolean comprobarSiEstaDNI(String dni) throws NamingException {
        PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
        List<Playadatospersonas> playasYDatos = pdpF.findAll();
        for (Playadatospersonas playasYDato : playasYDatos) {
            if (dni.equals(playasYDato.getDni())) {
                return true;
            }
        }
        return false;
    }

    private void posibilidadInsertaEnPlayaDatosPersonas(Playadatospersonas registroInsercionEnBD)
            throws ParseException, NamingException {

        if (comprobarSiEstaDNI(registroInsercionEnBD.getDni())) {
            List<Playadatospersonas> ocurrenciasDeUnaPersona = extraeTodasLasOcurrenciasDeUnaPersonaEnPlayaDatosPersonas(registroInsercionEnBD);
            for (Playadatospersonas registroBaseDatos : ocurrenciasDeUnaPersona) {
                if (fechaEnRangoFechas(registroInsercionEnBD.getFechaEntrada(), registroBaseDatos.getFechaEntrada(), registroBaseDatos.getFechaSalida())) {
                    posibleError += "Lo siento hemos detectado que usted ya tiene una plaza asignada en el periodo [" + registroBaseDatos.getFechaEntrada() + ","
                            + registroBaseDatos.getFechaSalida() + "] en la playa " + registroBaseDatos.getPlaya();
                    setErrorTrue();
                }
            }
        }
    }

    private void insertaEnPlayaDatosPersonas(Playadatospersonas registroInsercionEnBD)
            throws ParseException, NamingException {
        PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
        pdpF.create(registroInsercionEnBD);
    }

    private void insertaEnPlayaPlazasFechasOcupadas(Playadatospersonas registroExtraerFechas)
            throws ParseException, NamingException {
        PlayaplazasfechaocupadasFacade ppfoF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasfechaocupadasFacade");
        String fecha = registroExtraerFechas.getFechaEntrada();
        // Sumamos 
        registroExtraerFechas.setFechaSalida(sumarUnDiaAFechaEntrada(registroExtraerFechas.getFechaSalida()));
        while (!fecha.equals(registroExtraerFechas.getFechaSalida())) {
            Playaplazasfechaocupadas registro = compruebaSiExisteEnPlayaPlazasFechaOcupadas(registroExtraerFechas.getPlaya(), fecha);
            if (registro.getPlazas() == 0) {
                registro.setPlazas(1);
                ppfoF.create(registro);
                fecha = sumarUnDiaAFechaEntrada(fecha);
                continue;
            }
            fecha = sumarUnDiaAFechaEntrada(fecha);
            registro.setPlazas(registro.getPlazas() + 1);
            ppfoF.edit(registro);
        }
    }

    private boolean fechaEnRangoFechas(String fechaAMirar, String fechaLimInf, String fechaLimSup) throws ParseException {
        fechaLimSup = sumarUnDiaAFechaEntrada(fechaLimSup);
        while (!fechaLimInf.equals(fechaLimSup)) {
            if (fechaLimInf.equals(fechaAMirar)) {
                return true;
            }
            fechaLimInf = sumarUnDiaAFechaEntrada(fechaLimInf);
        }
        return false;
    }

    private List<Playadatospersonas> extraeTodasLasOcurrenciasDeUnaPersonaEnPlayaDatosPersonas(Playadatospersonas registroInsercionEnBD) throws NamingException {
        PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
        List<Playadatospersonas> todas = pdpF.findAll();
        List<Playadatospersonas> filtradas = new ArrayList<>();

        for (Playadatospersonas registroTabla : todas) {
            if (registroInsercionEnBD.getDni().equals(registroTabla.getDni())) {
                filtradas.add(registroTabla);
            }
        }
        return filtradas;
    }

    private Playaplazasfechaocupadas compruebaSiExisteEnPlayaPlazasFechaOcupadas(String playa, String fecha) throws NamingException {
        PlayaplazasfechaocupadasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasfechaocupadasFacade");
        List<Playaplazasfechaocupadas> todas = pdpF.findAll();

        for (Playaplazasfechaocupadas registroBuscado : todas) {
            if (registroBuscado.getPlaya().equals(playa) && registroBuscado.getFecha().equals(fecha)) {
                return registroBuscado;
            }
        }
        return new Playaplazasfechaocupadas(null, playa, fecha, 0);
    }

    private boolean comprobarValidezDNI(String dni) {
        String dniLetra = extraerLetraDni(dni);
        if (!dni.substring(8, 9).toLowerCase().equals(dniLetra)){
            setErrorTrue();
            return false;
        }
        return true;
    }
    
    private String extraerLetraDni (String dni){
        String[] myStringArray = {"t","r","w","a","g","m","y","f","p","d","x","b"
        ,"n","j","z","s","q","v","h","l","c","k","e"};
        return myStringArray[Integer.parseInt(dni.substring(0, 8))%23];
    }
}
/*
 private void crearPDF() {
 /*
 pdfCommand test = new pdfCommand();
        
 try {
 test.createPdf("c:/ProyectoAcampadaRuben/prueba.pdf");
 } catch (DocumentException | MessagingException ex) {
 Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
 }
         
 }*/
