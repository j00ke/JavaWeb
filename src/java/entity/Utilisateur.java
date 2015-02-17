/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author J00ke
 */

@Entity
@NamedQuery(name = "findAllUtilisateurs", query = "SELECT i FROM Utilisateur i")
public class Utilisateur{
    @Id
    @GeneratedValue
    @Column(name="UTILISATEURID") // lien avec la bdd
    private String uid;
    
    @Size(min = 2, max = 20)
    private String nom;
    
    @Size(min = 2, max = 20)
    private String prenom;
    
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String mail;
    private String mdp;

    public Utilisateur(String nom, String prenom, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
    }

    public Utilisateur() {
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    //Encodage du mdp avec SHA-256
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
    
    
    
}
