package boundary;

import entity.Utilisateur;
import entity.Video;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;

@Stateless
public class BoundaryVideo {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<Video> listeners;

    public Video enregistre(Video vid) {
        Video video = em.merge(vid);
        listeners.fire(vid);
        return video;
    }

    public void Supprimer(Video vid) {
        Video video = em.merge(vid);
        em.remove(video);
    }

    public Video find(long vidId) {
        return this.em.find(Video.class, vidId);
    }

    public List<Video> findAll() {
        return this.em.createNamedQuery("findAllVideos", Video.class).getResultList();
    }
}
