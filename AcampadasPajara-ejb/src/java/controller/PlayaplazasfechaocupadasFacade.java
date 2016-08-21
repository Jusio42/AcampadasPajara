/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Playaplazasfechaocupadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rubn_
 */
@Stateless
public class PlayaplazasfechaocupadasFacade extends AbstractFacade<Playaplazasfechaocupadas> {

    @PersistenceContext(unitName = "AcampadasPajara-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayaplazasfechaocupadasFacade() {
        super(Playaplazasfechaocupadas.class);
    }
    
}
