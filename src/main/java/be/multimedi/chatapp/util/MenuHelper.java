package be.multimedi.chatapp.util;

import be.multimedi.chatapp.domain.User;

public class MenuHelper {
    public static void mainMenu() {
        System.out.println("LeChat Chatting Application [version 1.0.0-SNAPSHOT]");
        System.out.println("@ 2021 Multimedi bvba. All rights reserved");
        System.out.println();
        System.out.println("++++++++++++++++");
        System.out.println("++-  Le Chat -++");
        System.out.println("++++++++++++++++");
        System.out.println("+    Home menu +");
        System.out.println("++++++++++++++++");
        System.out.println("+ 1. Register  +");
        System.out.println("+ 2. Log in    +");
        System.out.println("++++++++++++++++");


    }

    public static void subMenu(User c) {

        System.out.println("++++++++++++++++");
        System.out.println("++-  Le Chat -++");
        System.out.println("++++++++++++++++");
        System.out.println("+   Welcome    +");
        System.out.println("+  "+ c.getUserName()+ "    +");
        System.out.println("+    Home menu +");
        System.out.println("++++++++++++++++");
        System.out.println("+ 1. Chat      +");
        System.out.println("+ 2.Add friend +");
        System.out.println("+ 3.Request   +");
        System.out.println("+ 4.Log uit    +");
    }
}
