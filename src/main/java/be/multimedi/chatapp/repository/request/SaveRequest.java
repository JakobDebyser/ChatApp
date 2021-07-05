package be.multimedi.chatapp.repository.request;

import be.multimedi.chatapp.domain.Message;
import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class SaveRequest {
    public static void main(String[] args) {

        saveRequest(47L,48L, LocalDateTime.now());
    }

    public static void saveRequest(Long id, long rId, LocalDateTime dateTime) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Request request = new Request();
            User u = em.find(User.class, id);
            User r = em.find(User.class, rId);
            request.setUser(u);
            request.setRequestName(r.getUserName());
            request.setDateTime(dateTime);

            u.addRequest(request);
            em.persist(u);
            em.persist(request);
            tx.commit();
            System.out.println("Request saved");
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
    }
}
