package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RemoveFriend {
    public static void main(String[] args) {
    }
    public static void removeUser(User user, User friend){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            User u = em.find(User.class, user.getUserId());
            User fr=em.find(User.class, friend.getUserId());
            u.removeFriend(fr);
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
