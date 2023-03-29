package DesignPatterns.decorator;

interface Shape {
    public void draw();
}

class Circle implements  Shape{

    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}

//Now I want to add border to the class without modifying the Circle class

abstract class ShapeDecorator implements Shape {
    Shape shape;

    ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    public void draw(){
        shape.draw();
    }
}

class BorderDecorator extends ShapeDecorator {

    BorderDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        drawBorder();
    }

    private void drawBorder() {
        System.out.println("Drawing Border");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape circle = new BorderDecorator(new Circle());
        circle.draw();
    }
}
