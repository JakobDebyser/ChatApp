package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.JpaHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRepository {
    public static void main(String[] args) {
        Set<User> users= findFriends(47L);
        users.forEach(System.out::println);
    }
    public static User findUser(long id) {
        JpaHelper jpaHelper = new JpaHelper();
        return jpaHelper.execute(em -> {
            return em.find(User.class, id);
        });

    }

   public static Set<User> findFriends(long id) {

     JpaHelper jpaHelper=new JpaHelper();
        return jpaHelper.execute(em -> {
            Set<User> friends= em.find(User.class, id).getFriends();
            System.out.println(friends);
            return friends;
        });
    }

    public static void addFriend(Long userId, Long friendId) {
        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em -> {
            User u = em.find(User.class, userId);
            User friend = em.find(User.class, friendId);
            u.addFriend(friend);
            friend.addFriend(u);
            em.persist(friend);
            em.persist(u);
        });

    }
    public static void addRequest(Long userId, Long friendId) {
        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em -> {
            User u=em.find(User.class, userId);
            User friend = em.find(User.class, friendId);
            Request request=new Request();
            request.setRequestName(friend);
            request.setDateTime(LocalDateTime.now());
            request.setUser(u);
            u.addRequest(request);
            em.persist(request);
            em.persist(u);
        });

    }
    public static void RemoveFriend(Long userId, Long friendId) {
        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em -> {
            User u = em.find(User.class, userId);
            User friend = em.find(User.class, friendId);
            u.removeFriend(friend);
            friend.removeFriend(u);
            em.persist(friend);
            em.persist(u);
        });
    }

}
