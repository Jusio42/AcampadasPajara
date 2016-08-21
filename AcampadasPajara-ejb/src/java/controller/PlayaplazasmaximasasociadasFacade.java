/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Playaplazasmaximasasociadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rubn_
 */
@Stateless
public class PlayaplazasmaximasasociadasFacade extends AbstractFacade<Playaplazasmaximasasociadas> {

    @PersistenceContext(unitName = "AcampadasPajara-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayaplazasmaximasasociadasFacade() {
        super(Playaplazasmaximasasociadas.class);
    }
    
}
