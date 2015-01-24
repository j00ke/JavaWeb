package presentation;

import boundary.Inscriptions;
import entity.Inscription;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class NouvelleInscription {
    @Inject
    Inscriptions inscriptions;
    private Inscription inscription;

    @PostConstruct
    public void onInit() {
        this.inscription = new Inscription();
    }
    
    public Inscriptions getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Inscriptions inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
    
    public String doAjouter() {
        inscription = inscriptions.enregistre(inscription);
        return "listeInscriptions.xhtml?faces-redirect=true";
    }
}
