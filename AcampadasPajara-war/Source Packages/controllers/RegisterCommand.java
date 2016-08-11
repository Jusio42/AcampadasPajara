/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;

/**
 *
 * @author W7
 */
public class RegisterCommand extends FrontCommand {

    @Override
    public void process() {
        try {

            String aux = request.getParameter("nombre");
            String aux2 = request.getParameter("apellidos");
            String aux3 = request.getParameter("DNI");
            String aux4 = request.getParameter("municipioSelect");
            String aux5 = request.getParameter("noMunicipio");
            String aux6 = request.getParameter("direccion");
            String aux7 = request.getParameter("email");
            String aux8 = request.getParameter("fax");
            String aux9 = request.getParameter("telefono");
            String aux11 = request.getParameter("playaSelect");
            String aux12 = request.getParameter("fechaentrada");
            String aux13 = request.getParameter("fechasalida");

            checkDates(aux12, aux13);

            String aux14 = request.getParameter("personasCantidad");
            String aux15 = request.getParameter("tipoAcampada");
            String aux16 = request.getParameter("numeroCasetas");
            String aux17 = request.getParameter("caravana");
            String aux18 = request.getParameter("matriculaCaravana");
            String aux19 = request.getParameter("optradio");
            String aux21 = request.getParameter("observaciones");

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
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkDates(String aux12, String aux13) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(aux12);
        Date date2 = formatter.parse(aux13);
        if (date.compareTo(date2) == -1) {
            System.out.println(date);
        } else {
            System.out.println(date2);
        }
    }
}
