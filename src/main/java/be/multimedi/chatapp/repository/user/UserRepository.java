package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.JpaHelper;
import be.multimedi.chatapp.util.KeyboardHelper;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserRepository {

    public static void RegisterUser() {
        System.out.println("++++++++++++++++");
        System.out.println("+ 1. Register  +");
        System.out.println("++++++++++++++++");
        String username = KeyboardHelper.askForText("UserName: ");
        String email = KeyboardHelper.askForText("Email Address: ");
        String password = KeyboardHelper.askForText("Password: ");

        JpaHelper jpaHelper = new JpaHelper();
        jpaHelper.execute(em -> {
            User user = new User();
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            em.persist(user);
            em.flush();
            System.out.println(user.getUserId());
            System.out.println("New account '" + user.getUserName() + "' has been succesfully registered");
        });
    }
    public static User logInUser() {
        System.out.println("++++++++++++++++");
        System.out.println("+    Log in    +");
        System.out.println("++++++++++++++++");

        String log= KeyboardHelper.askForText("Username or email address: ");
        String pw= KeyboardHelper.askForText("Password: ");
        System.out.println("Logging in as " + log + "...");
        JpaHelper jpaHelper=new JpaHelper();
        return jpaHelper.execute(em -> {
            User user=null;
            String q= "select c from User as c where c.userName='"+log + "'" + " OR c.email='"+log+"' and c.password='"+pw+"'";
            TypedQuery<User> query=em.createQuery(q, User.class);
            List<User> results = query.getResultList();
            for (User c: results
            ) {
                user = c;
                System.out.println(user);
            }
            return user;
        });
    }

    public static List<User> findUsers(String name) {

        JpaHelper jpaHelper=new JpaHelper();
        return jpaHelper.execute(em -> {
            int i=1;
            String q = "select c from User as c where c.userName LIKE '%" + name + "%'";
            TypedQuery<User> query = em.createQuery(q, User.class);
            List<User> users = query.getResultList();
            for (User u : users
            ) {
                System.out.printf("%s %d %s %s  %n", "+", i, ".",u.getUserName());
                i+=1;
            }
            return users;
        });
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
