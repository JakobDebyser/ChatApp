package be.multimedi.chatapp.repository.message;

import be.multimedi.chatapp.domain.Message;
import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.JpaHelper;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class MessageRepository {

    public static void getMessages(User u, User f){
        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em-> {
            String q = "select m from Message as  m where m.user.userName=" + "'" + u.getUserName() + "'" + "or m.user.userName= '" + f.getUserName() + "'";
            TypedQuery<Message> query = em.createQuery(q, Message.class);
            List<Message> results = query.getResultList();
            Comparator<Message> messageComparator = Comparator.comparing(Message::getDateTime);
            results.sort(messageComparator);
            results.forEach(System.out::println);
        });
    }

    
    public static Message saveMessage(String s , long id ){
        JpaHelper jpaHelper=new JpaHelper();
        return jpaHelper.executeWithTx((em, tx) ->{
            Message message = new Message(s, LocalDateTime.now() );
            User user = em.find(User.class, id);
            message.setUser(user);
            user.addMessage(message);
            em.persist(user);
            em.persist(message);
            System.out.println("Message saved");
            return message;
        });
    }
}
