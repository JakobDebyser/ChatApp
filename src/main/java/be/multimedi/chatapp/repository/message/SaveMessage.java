package be.multimedi.chatapp.repository.message;

import be.multimedi.chatapp.domain.Message;
import be.multimedi.chatapp.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

public class SaveMessage {
   public static void main(String[] args) {
    saveMessage("Hello World", 47L);
   }

   public static void saveMessage(String s , long id ){
      EntityManagerFactory emf = null;
      EntityManager em = null;
      try {
         emf = Persistence.createEntityManagerFactory("course");
         em = emf.createEntityManager();
         EntityTransaction tx = em.getTransaction();
         tx.begin();
         Message message = new Message(s, LocalDateTime.now() );
         User user = em.find(User.class, id);
         message.setUser(user);
         user.addMessage(message);
         em.persist(user);
         em.persist(message);
         tx.commit();
         System.out.println("Message saved");
      } finally {
         if (em != null)
            em.close();
         if (emf != null)
            emf.close();
      }
   }
}
