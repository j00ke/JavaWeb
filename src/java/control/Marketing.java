package control;

import entity.Inscription;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class Marketing {
    
    public void onStore(@Observes Inscription ins) {
        System.out.println(">>> Notification département Marketing");
    }
    
}
