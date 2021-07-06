package be.multimedi.chatapp.service;

import be.multimedi.chatapp.domain.Request;
import be.multimedi.chatapp.domain.User;
import be.multimedi.chatapp.repository.message.MessageRepository;
import be.multimedi.chatapp.repository.request.RequestRepository;
import be.multimedi.chatapp.repository.user.UserRepository;
import be.multimedi.chatapp.util.KeyboardHelper;
import be.multimedi.chatapp.util.MenuHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ChatService {
    User user;

    public void register() {
        UserRepository.RegisterUser();
    }

    public void login() {
        user = UserRepository.logInUser();
        try {
            MenuHelper.subMenu(user);
        } catch (Exception e) {
            System.out.println("UserName or password is wrong");
        }

    }

    public void chat() {
        int i = 1;
        int index = 1;
        User friend = null;

        Set<User> list = UserRepository.findFriends(user.getUserId());
        //   list.forEach(System.out::println);
        System.out.println("++++++++++++++++");
        System.out.println("+   Friends    +");
        System.out.println("++++++++++++++++");
        if (list.size() == 0) {
            System.out.println("Geen vrienden toegevoed Wil je request sturen?");
            System.out.println("Ja = 1");
            int x = KeyboardHelper.askForNumber(">");
            if (x == 1) {
                addRequest();
            } else MenuHelper.subMenu(user);
        } else {
            for (User u : list
            ) {
                System.out.println("+ " + i + ". " + u.getUserName() + " +");
                i += 1;
            }
            System.out.println("++++++++++++++++++");
            System.out.println("choose jour friend");
            int num = KeyboardHelper.askForNumber(">");
            System.out.println("");
            while (num > list.size()) {
                System.out.println("num is niet just");
                num = KeyboardHelper.askForNumber(">");
            }

            for (User u : list
            ) {
                if (num == index) {
                    friend = u;

                }
                index += 1;
            }
            System.out.println("++++++++++++++++");
            System.out.println("+ " + friend.getUserName() + " + ");
            System.out.println("++++++++++++++++");
            System.out.println("+ 1. Chat      + ");
            System.out.println("+ 2. Remove      + ");
            System.out.println("++++++++++++++++");
            int n = KeyboardHelper.askForNumber(">");
            while (n > 3) {
                System.out.println("wrong input");
                n = KeyboardHelper.askForNumber(">");
            }
            if (n == 2) {
                UserRepository.RemoveFriend(user.getUserId(), friend.getUserId());
                System.out.println(friend.getUserName() + "is removed");
                MenuHelper.subMenu(user);

            } else {
                MessageRepository.getMessages(user, friend);
                int msgNum = 1;
                while (msgNum < 5) {
                    System.out.println("[" + LocalDateTime.now() + "]" + friend.getUserName());
                    String msg = KeyboardHelper.askForText(">");
                    // SaveMessage.saveMessage(msg, friend.getUserId());
                    MessageRepository.saveMessage(msg, friend.getUserId());
                    System.out.println("[" + LocalDateTime.now() + "]" + user.getUserName());
                    msg = KeyboardHelper.askForText(">");
                    MessageRepository.saveMessage(msg, user.getUserId());
                    msgNum += 1;
                    MenuHelper.subMenu(user);
                }
            }
        }

    }

    public void addRequest() {
        System.out.println("++++++++++++++++");
        System.out.println("+ Add friend + ");
        System.out.println("++++++++++++++++");
        System.out.println("+ Search Result + ");
        System.out.println("++++++++++++++++");
        List<User> list = UserRepository.findUsers(KeyboardHelper.askForText(">"));
        int index = 1;
        int num = KeyboardHelper.askForNumber(">");
        User friend = null;
        System.out.println("");
        while (num > list.size()) {
            System.out.println("num is niet just");
            num = KeyboardHelper.askForNumber(">");
        }

        for (User u : list
        ) {
            if (num == index) {
                friend = u;

            }
            index += 1;
        }
        System.out.println("++++++++++++++++");
        System.out.println("+ " + friend.getUserName() + " + ");

        UserRepository.addRequest(user.getUserId(), friend.getUserId());
        System.out.println("Friend request sent to '" + friend.getUserName() + "'");
        MenuHelper.subMenu(user);
    }

    public void requests() {
        int i = 1;
        List<Request> requests = RequestRepository.getRequest();
        if (requests.size() == 0) {
            System.out.println("Er is geen request");
            MenuHelper.subMenu(user);
        } else {
            System.out.println("++++++++++++++++");
            System.out.println("+   Requests   +");
            System.out.println("++++++++++++++++");
            for (Request u : requests
            ) {
                if (u.getUser().getUserName().equals(user.getUserName())) {
                    System.out.println("+ " + i + ". " + u.getRequestName().getUserName() + " +");
                    i += 1;
                }

            }
            System.out.println("++++++++++++++++++");
            System.out.println("Accept:");

            int index = 1;
            int num = KeyboardHelper.askForNumber(">");
            User friend = null;
            Request request = null;
            System.out.println("");
            while (num > requests.size()) {
                System.out.println("num is niet just");
                num = KeyboardHelper.askForNumber(">");
            }

            for (Request r : requests
            ) {
                if (r.getUser().getUserName().equals(user.getUserName())) {
                    if (num == index) {
                        friend = UserRepository.findUser(r.getRequestName().getUserId());
                        request = r;
                        System.out.println("+ " + index + ". " + r.getRequestName() + " +");
                    }
                    index += 1;
                }
            }
            System.out.println("++++++++++++++++");
            UserRepository.addFriend(user.getUserId(), friend.getUserId());
            RequestRepository.RemoveRequest(user.getUserId(), request.getId());
            System.out.println("Friend added '" + friend.getUserName() + " " + request.getRequestName() + " " + "deleted");
            MenuHelper.subMenu(user);
        }
    }

}
