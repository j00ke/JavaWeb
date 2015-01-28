/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.Utilisateur;
import javax.persistence.EntityManager;

/**
 *
 * @author J00ke
 */
public class BoundaryUtilisateur {
    
    protected EntityManager em;
    
    public BoundaryUtilisateur(EntityManager em)
    {
        this.em=em;
    }
    
     public Utilisateur creationUtilisateur(String nom,String prenom,String mail,String mdp)
    {
        Utilisateur u=new Utilisateur();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setMail(mail);
        u.setMdp(mdp);
        em.persist(u);
        return u;
    }
}
