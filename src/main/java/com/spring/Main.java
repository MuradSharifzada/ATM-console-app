package com.spring;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        while (true) {
            ui.displayMenu();
            int choice = ui.getUserChoice();
            ui.handleUserChoice(choice);
        }
    }
}
