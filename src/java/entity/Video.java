package entity;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllVideos", query = "SELECT i FROM Video i")
public class Video {

    @Id
    @GeneratedValue
    private long id;
    private String nom;
    private String nomVideo;
    private String url;

    @ManyToOne
    Cours cours;

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

}
