package entity;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllInscriptions", query = "SELECT i FROM Inscription i")
public class Inscription {

    @Id
    @GeneratedValue
    private long id;
    private String formation;
    private int nbJours;
    private int nbInscrits;

    private final static int PRIX_JOUR = 300;
    
    public Inscription() {
    }

    public Inscription(String formation, int nbJours, int nbInscrits) {
        this.formation = formation;
        this.nbJours = nbJours;
        this.nbInscrits = nbInscrits;
    }

    public int getPrixNet() {
        return nbInscrits * nbJours * PRIX_JOUR;
    }
    
    public int getPrixTotal() {
        BigDecimal prixNet = valueOf(getPrixNet());
        return prixNet.add(prixNet.multiply(valueOf(0.196))).intValue();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public int getNbInscrits() {
        return nbInscrits;
    }

    public void setNbInscrits(int nbInscrits) {
        this.nbInscrits = nbInscrits;
    }

}
