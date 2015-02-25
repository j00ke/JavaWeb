/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryCours;
import boundary.BoundaryUtilisateur;
import boundary.BoundaryVideo;
import entity.Cours;
import entity.Utilisateur;
import entity.Video;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PresentationUtilisateur {

    @Inject
    BoundaryUtilisateur boundaryUtilisateur;
    @Inject
    BoundaryCours boundaryCours;
    @Inject
    BoundaryVideo boundaryVideo;
    private long uId;
    private static Utilisateur utilisateur;
    private List<Utilisateur> listeUtils;

    @PostConstruct
    public void onInit() {
        this.utilisateur = new Utilisateur();
    }

    public void init() {
        if (uId == 0) {
            String message = "Erreur de requête. Utilisez un lien du site pour accéder à cette page";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }
        utilisateur = boundaryUtilisateur.find(uId);
        if (utilisateur == null) {
            String message = "Erreur de requête. Utilisateur inconnu";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
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

    public Utilisateur getUtilisateurById(long Uid) {
        utilisateur = boundaryUtilisateur.find(Uid);
        return utilisateur;
    }

    public List<Utilisateur> getListeUtils() {
        listeUtils = boundaryUtilisateur.findAll();
        return listeUtils;
    }

    public void setListeUtils(List<Utilisateur> listeUtils) {
        this.listeUtils = listeUtils;
    }

    public String doAjouter() throws NoSuchAlgorithmException {
        boolean existe = false;
        listeUtils = boundaryUtilisateur.findAll();
        for (int i = 0; i < listeUtils.size(); i++) {
            if (listeUtils.get(i).getMail().equals(utilisateur.getMail())) {
                existe = true;
            }
        }
        if (!existe) {
            utilisateur.setMdp(encoder(utilisateur.getMdp()));
            utilisateur = boundaryUtilisateur.enregistre(utilisateur);
            return "connexion.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "L'adresse email est déjà utilisée"));
            return "inscription.xhtml?faces-redirect=false";
        }
    }

    public String encoder(String mdp) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(mdp.getBytes());

        byte byteData[] = digest.digest();

        //convertit byte en hexadecimal
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public String doSupprimer(long uId) {
        this.setUtilisateur(boundaryUtilisateur.find(uId));
        boundaryUtilisateur.Supprimer(utilisateur);
        return "gererUtilisateurs.xhtml?faces-redirect=true";
    }

    public String doModifier(long uId) {
        Utilisateur u = boundaryUtilisateur.find(uId);
        u.setMail(utilisateur.getMail());
        u.setNom(utilisateur.getNom());
        u.setPrenom(utilisateur.getPrenom());

        boundaryUtilisateur.Modifier(u);
        return "gererUtilisateurs.xhtml?faces-redirect=true";
    }

    //Ajoute un cours à un utilisateur
    public String ajouterCours(long cId, long uId) {
        System.out.println(cId);
        this.setUtilisateur(boundaryUtilisateur.find(uId));
        Cours c = new Cours();
        c = boundaryCours.find(cId);
        utilisateur = boundaryUtilisateur.enregistrerCours(utilisateur, c);
        return "index.xhtml?faces-redirect=true";
    }

    //Ajoute une vidéo à un utilisateur (vidéo vue)
    public void ajouterVideo(long videoId, long uId) {
        this.setUtilisateur(boundaryUtilisateur.find(uId));
        boolean existe = false;
        Video v = new Video();
        v = boundaryVideo.find(videoId);

        for (int i = 0; i < utilisateur.getVideosVues().size(); i++) {
            if (utilisateur.getVideosVues().get(i).getId() == v.getId()) {
                existe = true;
            }
        }
        if (!existe) {
            utilisateur = boundaryUtilisateur.enregistrerVideo(utilisateur, v);
        } else {
            System.out.println("Video deja ajoutee");
        }
    }

    public boolean possedeCours(long cId, long uId) {
        this.setUtilisateur(boundaryUtilisateur.find(uId));
        boolean possede = false;
        Cours c = new Cours();
        c = boundaryCours.find(cId);
        for (int i = 0; i < utilisateur.getListeCours().size(); i++) {
            if (utilisateur.getListeCours().get(i).getCid() == c.getCid()) {
                possede = true;
            }
        }
        return possede;
    }
}
