package DesignPatterns.Adapter;

interface BoxShape {
    public void draw(String type);
}

interface RoundShape{
    public void draw(String type);
}

class BoxShapeImpl implements BoxShape {

    @Override
    public void draw(String type) {
        switch(type){
            case "Square":
                System.out.println("Square shape");
                break;
            case "Rectangle":
                System.out.println("Rectangle shape");
                break;
        }
    }
}

class RoundShapeImpl implements RoundShape {

    @Override
    public void draw(String type) {
        switch(type){
            case "Circle":
                System.out.println("Circle shape");
                break;
            case "Cylinder":
                System.out.println("Cylinder shape");
                break;
        }
    }
}

class BoxShapeAdapter implements BoxShape {

    RoundShape roundShape;

    public BoxShapeAdapter(){
        roundShape = new RoundShapeImpl();
    }
    @Override
    public void draw(String type) {
        switch(type){
            case "Square":
                new BoxShapeImpl().draw(type);
                break;
            case "Circle":
                roundShape.draw(type);
                break;
        }
    }
}


public class Main {
    public static void main(String[] args) {

        BoxShapeAdapter adapter = new BoxShapeAdapter();
        adapter.draw("Circle");
        adapter.draw("Square");


    }
}
