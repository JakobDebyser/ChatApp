package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class AddFriend {

    public static void addFriend(User user, Long id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            User u = em.find(User.class, user.getUserId());
            User friend = em.find(User.class, id);
            u.addFriend(friend);
            friend.addFriend(u);
            em.persist(friend);
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
