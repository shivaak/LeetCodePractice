package DesignPatterns.abstractfactory2;

public class Main {

    public static void main(String[] args) {
        Application app = new Application(new WinUIFactory());
        app.paint();
    }
}
