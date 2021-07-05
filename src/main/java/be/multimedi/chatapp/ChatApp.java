package be.multimedi.chatapp;

import be.multimedi.chatapp.service.ChatService;
import be.multimedi.chatapp.util.KeyboardHelper;
import be.multimedi.chatapp.util.MenuHelper;

public class ChatApp {
    public static void main(String[] args) {
        ChatService app = new ChatService();
        menu1(app);

    }
    public static void menu1(ChatService app){
        boolean exit = false;
        while (!exit) {
            MenuHelper.mainMenu();
            int choice = KeyboardHelper.askForNumber(">");
            switch (choice) {
                case 1: app.register(); break;
                case 2: app.login();
                        menu2(app);
                        break;
                default: System.out.println("Invalid choice, please try again.");
            }
        //    KeyboardHelper.askForText(">");
        }
    }
    public static void menu2(ChatService app){
        System.out.println("Menu2: ");
        boolean exit = false;
        while (!exit) {
            int choice = KeyboardHelper.askForNumber(">");
            switch (choice) {
                case 1: app.chat(); break;
                case 2: app.addRequest(); break;
                case 3: app.requests(); break;
                case 4: menu1(app); break;
                default: System.out.println("Invalid choice, please try again.");
            }
          //  KeyboardHelper.askForText(">");
        }
    }
}
