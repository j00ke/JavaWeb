/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.Cours;
import entity.Utilisateur;
import entity.Video;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public void Supprimer(Utilisateur util) {
        Utilisateur utilisateur = em.merge(util);
        em.remove(utilisateur);
    }

    public void Modifier(Utilisateur util) {
        em.merge(util);
    }

    public Utilisateur find(long utilId) {
        return this.em.find(Utilisateur.class, utilId);
    }

    public List<Utilisateur> findAll() {
        return this.em.createNamedQuery("findAllUtilisateurs", Utilisateur.class).getResultList();
    }

    public Utilisateur check(String mail, String mdp) {
        Query q = em.createQuery("SELECT u FROM Utilisateur u WHERE u.mail = :MAIL AND u.mdp = :MDP");
        q.setParameter("MAIL", mail);
        q.setParameter("MDP", mdp);
        try {
            Utilisateur utilisateur = (Utilisateur) q.getSingleResult();
            if (mail.equalsIgnoreCase(utilisateur.getMail()) && mdp.equals(utilisateur.getMdp())) {
                return utilisateur;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Utilisateur enregistrerCours(Utilisateur utilisateur, Cours cours) {
        try {
            utilisateur.getListeCours().add(cours);
            return em.merge(utilisateur);
        } catch (Exception e) {
            System.out.println("erreur ajout" + e);
            return null;
        }
    }

    public Utilisateur enregistrerVideo(Utilisateur utilisateur, Video video) {
        try {
            utilisateur.getVideosVues().add(video);
            return em.merge(utilisateur);
        } catch (Exception e) {
            System.out.println("erreur ajout" + e);
            return null;
        }

    }

}
