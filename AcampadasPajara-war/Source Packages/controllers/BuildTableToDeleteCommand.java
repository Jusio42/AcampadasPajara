/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.PlayadatospersonasFacade;
import entities.Playadatospersonas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author W7
 */
public class BuildTableToDeleteCommand extends FrontCommand {

    @Override
    public void process() {
        
        HttpSession session = request.getSession(true);
        String problemas = " ";
        try {
            String dni = request.getParameter("DNI");
            PlayadatospersonasFacade pdpF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayadatospersonasFacade");
            List<Playadatospersonas> todas = pdpF.findAll();
            List<Playadatospersonas> ocurrencias = new ArrayList<>();

            ocurrencias = extraeOcurrenciasDeUnaPersona(todas, dni);
            if (ocurrencias.size() < 1) {
                problemas+="No hemos encontrado su dni en nuestro sistema,reviselo por favor.";
                session.setAttribute("problemas", problemas);
                forward("/displayErrors.jsp");
            }else {
                
            }
            //forward("/FrontController?command=GetInitialDataCommand");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(BuildTableToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Playadatospersonas> extraeOcurrenciasDeUnaPersona(List<Playadatospersonas> todas, String dni) {
        List<Playadatospersonas> ocurrencias = new ArrayList<>();
        for (Playadatospersonas registroBD : todas) {
            if (registroBD.getDni().equals(dni)) {
                ocurrencias.add(registroBD);
            }
        }
        return ocurrencias;
    }

}
