/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryUtilisateur;
import entity.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Jean
 */
@ManagedBean(name = "connexion")
@SessionScoped
public class PresentationConnexion implements Serializable {

    @Inject
    BoundaryUtilisateur boundaryUtilisateur;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String mail;
    private String mdp;
    private boolean isLoggedIn;
    private boolean isAdmin;
    private Utilisateur utilisateur;

    @PostConstruct
    public void onInit() {
        this.utilisateur = new Utilisateur();
    }

    public void setUser(Utilisateur user) {
        this.utilisateur = user;
    }

    public Utilisateur getUser() {
        return utilisateur;
    }

    public BoundaryUtilisateur getUtilisateurs() {
        return boundaryUtilisateur;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setUtilisateurs(BoundaryUtilisateur boundaryUtilisateur) {
        this.boundaryUtilisateur = boundaryUtilisateur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setMdp(String mdp) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(mdp.getBytes());

        byte byteData[] = digest.digest();

        //convertit byte en hexadecimal
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        this.mdp = sb.toString();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String connexion() throws NoSuchAlgorithmException {
        String url = "connexion.xhtml?faces-redirect=true";
        this.setUser(boundaryUtilisateur.check(mail, mdp));

        if (utilisateur != null && mdp.equals(utilisateur.getMdp())) {
            if (mail.equals("admin@admin.com")) {
                this.isAdmin = true;
            }
            url = "index.xhtml?faces-redirect=true";
            isLoggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", utilisateur);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            setLoggedIn(false);
            setMail("");
            setMdp("");
            url = "connexion.xhtml?faces-redirect=false";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Email ou mot de passe incorrect"));
        }
        return url;
    }

    public void checkLogin(ComponentSystemEvent event) {
        if (!isLoggedIn) {
            doRedirect("connexion.xhtml?faces-redirect=true");
        }
    }

    public String deconnexion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        setLoggedIn(false);
        return "connexion.xhtml?faces-redirect=true";
    }

    private void doRedirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAdmin(ComponentSystemEvent event) {
        if (!isAdmin) {
            doRedirect("connexion.xhtml?faces-redirect=true");
        }
    }
}
