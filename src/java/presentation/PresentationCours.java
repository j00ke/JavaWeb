/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryCours;
import entity.Cours;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PresentationCours {

    @Inject
    BoundaryCours boundaryCours;
    private Cours cours;
    private List<Cours> listeCours = new ArrayList<>();
    private long cid;

    @PostConstruct
    public void onInit() {
        this.cours = new Cours();
    }

    public BoundaryCours getBoundaryCours() {
        return boundaryCours;
    }

    public void setBoundaryCours(BoundaryCours boundaryCours) {
        this.boundaryCours = boundaryCours;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /*
     public String doAjouter() {
     cours = BoundaryCours.enregistre(cours);
     return "connexion.xhtml?faces-redirect=true";
     }*/
    public Cours getCoursById(int cId) {
        cours = boundaryCours.find(cId);
        return cours;
    }

    public List<Cours> getListeCours() {
        listeCours = boundaryCours.findAll();
        return listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

}
