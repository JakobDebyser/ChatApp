package be.multimedi.chatapp.repository.user;

import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.util.KeyboardHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RegisterUser {
    public static void main(String[] args) {
        registerNewClient();
    }

    public static void registerNewClient() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            User user = new User();
            System.out.println("++++++++++++++++");
            System.out.println("+ 1. Register  +");
            System.out.println("++++++++++++++++");
            String username = KeyboardHelper.askForText("UserName: ");
            String email = KeyboardHelper.askForText("Email Address: ");
            String password = KeyboardHelper.askForText("Password: ");
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            em.persist(user);
            em.flush();
            System.out.println(user.getUserId());
            tx.commit();
            System.out.println("New account '"+ user.getUserName()+"' has been succesfully registered");
        } finally {
            if (em != null)
                em.close();
            if (emf != null)
                emf.close();
        }
    }
}
