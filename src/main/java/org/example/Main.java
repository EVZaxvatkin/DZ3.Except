package org.example;


import org.example.presentation.Presentation;
import org.example.view.ConsoleView;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {

        Presentation<View> prog = new Presentation<View>(new ConsoleView());
        prog.start();
    }
}