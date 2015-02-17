/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryUtilisateur;
import entity.Utilisateur;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PresentationUtilisateur {

    @Inject
    BoundaryUtilisateur boundaryUtilisateur;
    private static Utilisateur utilisateur;

    @PostConstruct
    public void onInit() {
        this.utilisateur = new Utilisateur();
    }

    public BoundaryUtilisateur getBoundaryUtilisateur() {
        return boundaryUtilisateur;
    }

    public void setBoundaryUtilisateur(BoundaryUtilisateur boundaryUtilisateur) {
        this.boundaryUtilisateur = boundaryUtilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public String doAjouter() {
        utilisateur = boundaryUtilisateur.enregistre(utilisateur);
        return "connexion.xhtml?faces-redirect=true";
    }
}
