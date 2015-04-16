package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "findAllVideos", query = "SELECT i FROM Video i")
public class Video implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Size(min = 2, max = 40)
    private String nom;
    private String nomVideo;
    
    @Size(min = 2, max = 80)
    private String url;

    //Cours auquel la vidéo est associée
    @ManyToOne
    Cours cours;
    
    //Liste des utilisateurs ayant vus la vidéo
    @ManyToMany(mappedBy = "videosVues")
    List<Utilisateur> listeUtils;

    public Video() {
    }

    public Video(long id, String nom, String nomVideo, String url, Cours cours) {
        this.id = id;
        this.nom = nom;
        this.nomVideo = nomVideo;
        this.url = url;
        this.cours = cours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomVideo() {
        return nomVideo;
    }

    public void setNomVideo(String nomVideo) {
        this.nomVideo = nomVideo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Utilisateur> getListeUtils() {
        return listeUtils;
    }

    public void setListeUtils(List<Utilisateur> listeUtils) {
        this.listeUtils = listeUtils;
    }
    

}
