package DesignPatterns.abstractfactory2;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("Windows button");
    }
}
