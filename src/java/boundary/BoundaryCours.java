/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.Cours;
import entity.Utilisateur;
import entity.Video;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoundaryCours {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<Cours> listeners;

    public Cours enregistre(Cours c) {
        Cours cours = em.merge(c);
        listeners.fire(c);
        return cours;
    }

    public void Supprimer(Cours c) {
        Cours cours = em.merge(c);
        em.remove(cours);
    }

    public void Modifier(Cours c) {
        em.merge(c);
    }

    public Cours find(long cId) {
        return this.em.find(Cours.class, cId);
    }

    public List<Cours> findAll() {
        return this.em.createNamedQuery("findAllCours", Cours.class).getResultList();
    }
}
