/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author J00ke
 */
@Entity
@NamedQuery(name = "findAllCours", query = "SELECT i FROM Cours i")
public class Cours implements Serializable {

    @Id
    @GeneratedValue
    private long cid;

    private String nom;
    private String description;
    private String img;
    private Boolean payant;   // true = payant  false = gratuit
    private double prix;
    @OneToMany(mappedBy = "cours")
    Set<Video> videos;

    public Cours(int cid, String nom, String description, String img, Boolean payant, double prix, Set<Video> videos) {
        this.cid = cid;
        this.nom = nom;
        this.description = description;
        this.img = img;
        this.payant = payant;
        this.prix = prix;
        this.videos = videos;
    }

    public Cours() {
    }

    
    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
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
