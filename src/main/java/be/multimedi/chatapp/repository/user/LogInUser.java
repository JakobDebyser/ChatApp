package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.KeyboardHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class LogInUser {
    public static void main(String[] args) {
        loginClient();
    }

    public static User loginClient(){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        User user =null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            System.out.println("++++++++++++++++");
            System.out.println("+    Log in    +");
            System.out.println("++++++++++++++++");

            String log= KeyboardHelper.askForText("Username or email address: ");
            String pw= KeyboardHelper.askForText("Password: ");
            System.out.println("Logging in as " + log + "...");

            String q= "select c from User as c where c.userName='"+log + "'" + " OR c.email='"+log+"' and c.password='"+pw+"'";
            System.out.println(q);
            TypedQuery<User> query=em.createQuery(q, User.class);
            List<User> results = query.getResultList();
            for (User c: results
                 ) {
                user = c;
                System.out.println(user);
            }

        }
        finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
        return user;

    }
}
