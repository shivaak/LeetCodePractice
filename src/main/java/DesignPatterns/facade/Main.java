package DesignPatterns.facade;

interface Shape {
    public void draw();
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("This is Square");
    }
}

class Circle implements  Shape{

    @Override
    public void draw() {
        System.out.println("This is Circle");
    }
}

class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("This is Rectangle");
    }
}

class ShapeFacade {
    Shape circle;
    Shape rectangle;
    Shape square;


    public ShapeFacade(){
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void draw(){
        circle.draw();
        rectangle.draw();
        square.draw();
    }


}


public class Main {

    public static void main(String[] args) {
        ShapeFacade facade = new ShapeFacade();
        facade.draw();
    }
}
