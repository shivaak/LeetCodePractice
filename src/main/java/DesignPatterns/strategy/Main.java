package DesignPatterns.strategy;

interface SortStrategy{
    public void sort();
}

class MergeSortStrategy implements  SortStrategy{

    @Override
    public void sort() {
        System.out.println("Merge sort");
    }
}

class BubbleSortStrategy implements  SortStrategy{

    @Override
    public void sort() {
        System.out.println("Bubble sort");
    }
}

public class Main {

    SortStrategy sortStrategy;

    public Main(SortStrategy sortStrategy){
        this.sortStrategy = sortStrategy;
    }
    public static void main(String[] args) {
        Main m = new Main(new MergeSortStrategy());
        m.performSort();
    }

    public void performSort(){
        this.sortStrategy.sort();
    }
}
