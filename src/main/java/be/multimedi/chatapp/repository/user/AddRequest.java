package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class AddRequest {
    public static void main(String[] args) {
      // addRequest(38L);
    }
    public static void addRequest(User user, Long id){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            User u=em.find(User.class, user.getUserId());
            User friend = em.find(User.class, id);
            Request request=new Request();
            request.setRequestName(friend.getUserName());
            request.setDateTime(LocalDateTime.now());
            request.setUser(u);
            u.addRequest(request);
            em.persist(request);
            em.persist(u);
            tx.commit();
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }

    }
}
