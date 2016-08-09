/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;

/**
 *
 * @author W7
 */
public class RegisterCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            
            /*String aux = request.getParameter("nombre");
            String aux2 = request.getParameter("apellidos");
            String aux3 = request.getParameter("DNI");
            String aux4 = request.getParameter("municipioSelect");
            String aux5 = request.getParameter("email");
            */
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
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
