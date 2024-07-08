package memento;

import java.util.Stack;
import java.util.logging.Logger;

// Memento Interface (abstracts away the details of the Memento)
interface Memento {
    // No methods needed here since it's just a marker interface
}

// Concrete Memento Implementation
class OriginatorMemento implements Memento {
    private final String state;

    public OriginatorMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator Interface with a single responsibility of managing state
class Originator {
    private static final Logger logger = Logger.getLogger(Originator.class.getName());
    private String state;

    public void setState(String state) {
        this.state = state;
        logger.info("State set to: " + state);
    }

    public String getState() {
        return state;
    }

    // Save state to Memento
    public Memento saveStateToMemento() {
        logger.info("Saving state to Memento: " + state);
        return new OriginatorMemento(state);
    }

    // Restore state from Memento
    public void getStateFromMemento(Memento memento) {
        if (memento instanceof OriginatorMemento) {
            state = ((OriginatorMemento) memento).getState();
            logger.info("State restored from Memento: " + state);
        } else {
            throw new IllegalArgumentException("Invalid Memento passed");
        }
    }
}

// Caretaker class with the responsibility of managing Mementos
class Caretaker {
    private static final Logger logger = Logger.getLogger(Caretaker.class.getName());
    private final Stack<Memento> mementoStack = new Stack<>();

    // Add Memento to stack
    public void addMemento(Memento memento) {
        mementoStack.push(memento);
        logger.info("Memento added to stack");
    }

    // Retrieve Memento from stack
    public Memento getMemento() {
        if (!mementoStack.isEmpty()) {
            logger.info("Memento retrieved from stack");
            return mementoStack.pop();
        }
        logger.warning("Memento stack is empty");
        return null;
    }
}

public class MementoPattern {
    private static final Logger logger = Logger.getLogger(MementoPattern.class.getName());

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.addMemento(originator.saveStateToMemento());

        originator.setState("State #3");
        caretaker.addMemento(originator.saveStateToMemento());

        originator.setState("State #4");
        logger.info("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.getMemento());
        logger.info("First restored State: " + originator.getState());

        originator.getStateFromMemento(caretaker.getMemento());
        logger.info("Second restored State: " + originator.getState());
    }
}
