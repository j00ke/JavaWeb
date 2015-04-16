/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryCours;
import boundary.BoundaryVideo;
import entity.Cours;
import entity.Video;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PresentationCours implements Serializable {

    @Inject
    BoundaryCours boundaryCours;
    private Cours cours;
    @Inject
    BoundaryVideo boundaryVideo;
    private Video video;
    private List<Cours> listeCours = new ArrayList<>();
    private List<Video> listeVideo = new ArrayList<>();
    
    private int cid;

    public int getCid() {
        return this.cid;
    }
    
    public String compareCid(int c) {
        return (c == this.cid) ? "inline" : "none";
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
     
    @PostConstruct
    public void onInit() {
        this.cours = new Cours();
        this.video = new Video();
        
    }

    public void init() {
        if (this.cours.getCid() == 0) {
            String message = "Erreur de requête. Utilisez un lien du site pour accéder à cette page";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }
        
        this.cours = boundaryCours.find(this.cours.getCid());
        
        this.cid = (int)(this.cours.getCid());

        if (cours == null) {
            String message = "Erreur de requête. Utilisateur inconnu";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public List<Video> getListeVideo() {
        listeVideo = boundaryVideo.findAll();
        return listeVideo;
    }

    public void setListeVideo(List<Video> listeVideo) {
        this.listeVideo = listeVideo;
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

    public BoundaryVideo getBoundaryVideo() {
        return boundaryVideo;
    }

    public void setBoundaryVideo(BoundaryVideo boundaryVideo) {
        this.boundaryVideo = boundaryVideo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String doAjouter() {
        cours = boundaryCours.enregistre(cours);
        return "gererCours.xhtml?faces-redirect=true";
    }

    public String doSupprimer(long cId) {
        this.setCours(boundaryCours.find(cId));
        boundaryCours.Supprimer(cours);
        return "gererCours.xhtml?faces-redirect=true";
    }

    public String doSupprimerVideo(long cId, long vId) {
        this.setCours(boundaryCours.find(cId));
        this.setVideo(boundaryVideo.find(vId));

        listeVideo = cours.getlisteVideos();

        for (int i = 0; i < listeVideo.size(); i++) {
            if (listeVideo.get(i).getId() == vId) {
                listeVideo.remove(i);
            }
        }
        cours.setVideos(listeVideo);
        boundaryCours.Modifier(cours);
        boundaryVideo.Supprimer(video);
        return "modifCours.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String ajouterVideo(long cId) {
        this.setCours(boundaryCours.find(cId));
        video.setCours(cours);
        video.setNomVideo(video.getNom());
        listeVideo = cours.getlisteVideos();
        listeVideo.add(video);
        cours.setVideos(listeVideo);
        boundaryCours.Modifier(cours);
        return "modifCours.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String doModifier(long cId) {
        Cours c = boundaryCours.find(cId);
        c.setNom(cours.getNom());
        c.setDescription(cours.getDescription());
        c.setPayant(cours.getPayant());
        c.setPrix(cours.getPrix());
        c.setImg(cours.getImg());

        boundaryCours.Modifier(c);
        return "gererCours.xhtml?faces-redirect=true";
    }

    public Cours getCoursById(long cId) {
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

    public String navigateToPage(String url, long param) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("param", param);
        return url;
    }
}
