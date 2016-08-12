/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.MunicipiosFacade;
import controller.PlayaplazasmaximasasociadasFacade;
import entities.Municipios;
import entities.Playaplazasmaximasasociadas;
import java.util.List;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class GetInitialDataCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        try {
            MunicipiosFacade municipios = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/MunicipiosFacade");
            PlayaplazasmaximasasociadasFacade ppmF = InitialContext.doLookup("java:global/AcampadasPajara/AcampadasPajara-ejb/PlayaplazasmaximasasociadasFacade");
            List<Municipios> listadoMunicipios = municipios.findAll();
            request.setAttribute("listadoMunicipios", listadoMunicipios);
            
            List<Playaplazasmaximasasociadas> playasTodas = ppmF.findAll();
            List<Playaplazasmaximasasociadas> playas = new ArrayList<Playaplazasmaximasasociadas>();
            for (Playaplazasmaximasasociadas playa : playasTodas) {
                if (playa.getNplazasmaximo()>0){
                    playas.add(playa);
                }
            }
            request.setAttribute("playas", playas);
            forward("/index.jsp");
            
        } catch (NamingException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
