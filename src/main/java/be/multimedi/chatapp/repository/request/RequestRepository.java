package be.multimedi.chatapp.repository.request;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.JpaHelper;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class RequestRepository {
    public static List<Request> getRequest(){
        JpaHelper jpaHelper = new JpaHelper();
        return jpaHelper.execute(em-> {
            String q = "select r from Request as  r ";
            TypedQuery<Request> query = em.createQuery(q, Request.class);
             List<Request> results = query.getResultList();
            return results;
        });
    }

    public static Request saveRequest(Long id, long rId, LocalDateTime dateTime){
        JpaHelper jpaHelper = new JpaHelper();
        return jpaHelper.executeWithTx((em, tx) -> {
            Request request = new Request();
            User u = em.find(User.class, id);
            User r = em.find(User.class, rId);
            request.setUser(u);
            request.setRequestName(r);
            request.setDateTime(dateTime);
            u.addRequest(request);
            em.persist(u);
            em.persist(request);
            System.out.println("Request saved");
            return request;
        });
    }

    public static void RemoveRequest(Long userId, Long rId) {
        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em -> {
            User u = em.find(User.class, userId);
            Request r=em.find(Request.class, rId);
            u.removeRequest(r);
            em.persist(u);
        });
    }
}
