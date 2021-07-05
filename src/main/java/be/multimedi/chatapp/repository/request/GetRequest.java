package be.multimedi.chatapp.repository.request;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;

import javax.persistence.*;
import java.util.List;

public class GetRequest {

    public static void main(String[] args) {
     //   getRequest();
    }

    public static List<Request> getRequest(User user) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Request> results=null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            String q = "select r from Request as  r ";
            TypedQuery<Request> query = em.createQuery(q, Request.class);
            results = query.getResultList();
         //   results.forEach(System.out::println);
            tx.commit();
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
        return results;
    }
}
