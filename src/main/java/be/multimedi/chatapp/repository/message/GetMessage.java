package be.multimedi.chatapp.repository.message;

import be.multimedi.chatapp.domain.Message;
import be.multimedi.chatapp.domain.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GetMessage {
    public static void main(String[] args) {
        //   getMessages();
    }

    public static void getMessages(User u, User f) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            String q = "select m from Message as  m where m.user.userName=" + "'" + u.getUserName() + "'" + "or m.user.userName= '" + f.getUserName() + "'";
            TypedQuery<Message> query = em.createQuery(q, Message.class);
            List<Message> results = query.getResultList();
            Comparator<Message> messageComparator = Comparator.comparing(Message::getDateTime);
            Collections.sort(results, messageComparator);
            results.forEach(System.out::println);
            tx.commit();
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
    }
}
