 package presentation;

import boundary.Inscriptions;
import entity.Inscription;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ListeInscriptions {
    @Inject
    private Inscriptions inscriptions;
    private List<Inscription> liste = new ArrayList<>();

    public Inscriptions getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Inscriptions inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<Inscription> getListe() {
        liste = inscriptions.findAll();
        return liste;
    }

    public void setListe(List<Inscription> liste) {
        this.liste = liste;
    }
    
}
