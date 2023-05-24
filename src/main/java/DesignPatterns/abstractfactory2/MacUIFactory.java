package DesignPatterns.abstractfactory2;

public class MacUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }
}
