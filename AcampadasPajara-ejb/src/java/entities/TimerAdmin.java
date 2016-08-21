package entities;

import controller.MunicipiosFacade;
import controller.PlayadatospersonasFacade;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class TimerAdmin {

    //@Schedule(second = "*/5", minute = "*", hour = "*")
    public void scheduleTimer() {
        try {
            Date dateactual = new Date();
            Calendar calendario = Calendar.getInstance();
            LocalDateTime horalocal = LocalDateTime.now();
            
            String fechaActual = horalocal.getDayOfMonth()+"/"+horalocal.getMonthValue()+"/"+horalocal.getYear();
            PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
            /*
             FALTA HACER EL BORRADO DE LA TABLA CON FECHAS INFERIORES A LA fechaActual.
             */
        } catch (NamingException ex) {
            Logger.getLogger(TimerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
