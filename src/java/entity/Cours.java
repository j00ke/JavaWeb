/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author J00ke
 */

@Entity
public class Cours implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="COURSID") // lien avec la bdd
    private String cid;
    private String nom;
    private String description;
    private String img;
    private Boolean payant ;   // true = payant  false = gratuit
    private double prix; 

    public Cours(String nom, String description, String img, Boolean payant, double prix) {
        this.nom = nom;
        this.description = description;
        this.img = img;
        this.payant = payant;
        this.prix = prix;
    }

    public Cours() {
    }
    
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getPayant() {
        return payant;
    }

    public void setPayant(Boolean payant) {
        this.payant = payant;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    } 
    
}
