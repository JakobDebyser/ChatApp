package be.multimedi.chatapp;

import be.multimedi.chatapp.service.ChatService;
import be.multimedi.chatapp.util.KeyboardHelper;
import be.multimedi.chatapp.util.MenuHelper;

public class ChatApp {
    public static void main(String[] args) {
        ChatService app = new ChatService();
        boolean exit = false;
        while (!exit) {
            MenuHelper.mainMenu();
            int choice = KeyboardHelper.askForNumber("Your choice:");
            switch (choice) {
                case 1: app.register(); break;
                case 2: app.login(); break;
                default: System.out.println("Invalid choice, please try again.");
            }
            KeyboardHelper.askForText("Press enter to continue");
        }
    }
}
