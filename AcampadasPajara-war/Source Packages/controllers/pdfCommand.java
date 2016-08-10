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
        //File file = new File("c:/ProyectoAcampadaRuben/prueba.txt");
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

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("rubendavidbm@gmail.com", "asusion742");
                    }
                });
        try {

            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("rubendavidbm@gmail.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("rubn_42mq@hotmail.com", false));

            msg.setSubject("ASDSAD", "UTF-8");

            msg.setText("asASDQWE", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rubendavidbm@gmail.com", false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
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
