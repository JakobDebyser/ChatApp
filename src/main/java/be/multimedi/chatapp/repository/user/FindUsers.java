package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class FindUsers {
    public static void main(String[] args) {
        findUsers("ja");
    }

    public static List<User> findUsers(String name) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<User> users = null;
        int i=1;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();

            String q = "select c from User as c where c.userName LIKE '%" + name + "%'";
            TypedQuery<User> query = em.createQuery(q, User.class);
            users = query.getResultList();

        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }

        for (User u : users
        ) {
            System.out.printf("%s %d %s %s  %n", "+", i, ".",u.getUserName());
            i+=1;
        }
        return users;
    }
}
