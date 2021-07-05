package be.multimedi.chatapp.repository.message;

import be.multimedi.chatapp.domain.Message;
import be.multimedi.chatapp.domain.User;

import javax.persistence.*;
import java.util.List;

public class GetMessage {
   public static void main(String[] args) {
      getMessages();
   }

   public static void getMessages(){
      EntityManagerFactory emf = null;
      EntityManager em = null;
      try {
         emf = Persistence.createEntityManagerFactory("course");
         em = emf.createEntityManager();
         EntityTransaction tx = em.getTransaction();
         tx.begin();
         String q= "select m from Message as  m ";
         TypedQuery<Message> query=em.createQuery(q, Message.class);
         List<Message> results = query.getResultList();
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
