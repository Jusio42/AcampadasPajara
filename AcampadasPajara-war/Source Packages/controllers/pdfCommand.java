package controllers;

//Librerias excepciones
import java.io.FileOutputStream;
import java.io.IOException;

//Librerias Correo
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
//--------------
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//--------------
// Librerias pdf
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;

public class pdfCommand extends FrontCommand {

    /*
     ---------------------------------------------------------
     PROBLEMA AQUÍ: CAMBIAR POR RUTA RELATIVA -------------------
     ---------------------------------------------------------
     */
    public static final String result = "c:/ProyectoAcampadaRuben/prueba.pdf";

    public void createPdf(String filename) throws DocumentException, IOException, MessagingException {
        File file = new File("c:/ProyectoAcampadaRuben/prueba.txt");
        // step 1
        Document document = new Document();
        Paragraph preface = new Paragraph();
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("PruebaPDF", catFont));
        addEmptyLine(preface, 1);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(preface);
        // step 5
        document.close();
        sendEmail();
        System.out.println("email?");
    }

    public void sendEmail() throws MessagingException {
        final String username = "rubendavidbm@gmail.com";
        final String password = "asusion742";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("rubendavidbm@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @Override
    public void process() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
