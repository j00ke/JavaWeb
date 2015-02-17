/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoundaryUtilisateur {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<Utilisateur> listeners;

    public Utilisateur enregistre(Utilisateur util) {
        Utilisateur utilisateur = em.merge(util);
        listeners.fire(util);
        return utilisateur;
    }
    
    public Utilisateur find(long utilId) {
        return this.em.find(Utilisateur.class, utilId);
    }
    
    public List<Utilisateur> findAll() {
        return this.em.createNamedQuery("findAllUtilisateurs",Utilisateur.class).getResultList();
    }

}
