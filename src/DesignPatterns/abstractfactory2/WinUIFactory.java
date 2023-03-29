package DesignPatterns.abstractfactory2;

public class WinUIFactory implements UIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
