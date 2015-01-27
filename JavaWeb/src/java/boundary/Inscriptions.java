package boundary;

import entity.Inscription;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Inscriptions {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    Event<Inscription> listeners;
    
    public Inscription enregistre(Inscription ins) {
        Inscription inscription = em.merge(ins);
        listeners.fire(ins);
        return inscription;
    }
    
    public Inscription find(long insId) {
        return this.em.find(Inscription.class, insId);
    }
    
    public List<Inscription> findAll() {
        return this.em.createNamedQuery("findAllInscriptions",Inscription.class).getResultList();
    }
}
