package DesignPatterns.abstractfactory2;

public class Application {

    Button button;

    public Application(UIFactory factory){
        button = factory.createButton();
    }

    public void paint() {
        button.paint();
    }

}
