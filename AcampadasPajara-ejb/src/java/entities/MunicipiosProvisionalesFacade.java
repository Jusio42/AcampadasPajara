/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import controller.MunicipiosProvisionales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author W7
 */
@Stateless
public class MunicipiosProvisionalesFacade extends AbstractFacade<MunicipiosProvisionales> {
    @PersistenceContext(unitName = "AcampadasPajara-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosProvisionalesFacade() {
        super(MunicipiosProvisionales.class);
    }
    
}
