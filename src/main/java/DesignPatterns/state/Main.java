package DesignPatterns.state;


interface State {
    void doAction(Context context);
}

class StateA implements State {
    public void doAction(Context context) {
        // Define the behavior for StateA
    }
}

class StateB implements State {
    public void doAction(Context context) {
        // Define the behavior for StateB
    }
}

class Context {
    private State state;

    public Context() {
        state = new StateA(); // Set the initial state
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doAction() {
        state.doAction(this);
    }
}

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.doAction(); // Output: StateA
        context.setState(new StateB());
        context.doAction(); // Output: StateB
    }
}
