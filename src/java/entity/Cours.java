/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author J00ke
 */
@Entity
@NamedQuery(name = "findAllCours", query = "SELECT i FROM Cours i")
public class Cours implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long cid;
    
    @Size(min = 2, max = 40)
    private String nom;
    
    @Size(min = 2)
    @Column(length=500)
    private String description;
    
    @Size(min = 2, max = 40)
    private String img;
    
    private Boolean payant;   // true = payant  false = gratuit
    
    private double prix;
    
    //Liste des épisodes vidéo associé au cours
    @OneToMany(mappedBy = "cours", orphanRemoval=true)
    List<Video> listeVideos;
       
    //Liste des utilisateurs ayant ajouté ce cours
    @ManyToMany(mappedBy = "listeCours")
    List<Utilisateur> listeUtils;

    
    public Cours(long cid, String nom, String description, String img, Boolean payant, double prix, List<Video> listeVideos) {
        this.cid = cid;
        this.nom = nom;
        this.description = description;
        this.img = img;
        this.payant = payant;
        this.prix = prix;
        this.listeVideos = listeVideos;
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
 
    public List<Video> getlisteVideos() {
        return this.listeVideos;
    }

    public void setVideos(List<Video> listeVideos) {
        this.listeVideos = listeVideos;
    }

    public List<Utilisateur> getListeUtils() {
        return listeUtils;
    }

    public void setListeUtils(List<Utilisateur> listeUtils) {
        this.listeUtils = listeUtils;
    }

}
