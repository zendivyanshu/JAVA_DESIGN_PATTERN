package chainofresponsibility;

import java.util.logging.Logger;

// Request class
class Request {
    private final int amount;

    public Request(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

// Handler interface
interface Handler {
    void setNext(Handler nextHandler);
    void handleRequest(Request request);
}

// Abstract base class for concrete handlers
abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;
    protected Logger logger;

    public AbstractHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Template method for handling requests
    @Override
    public void handleRequest(Request request) {
        if (canHandle(request)) {
            process(request);
        } else if (nextHandler != null) {
            logger.info("Passing request to next handler: " + nextHandler.getClass().getSimpleName());
            nextHandler.handleRequest(request);
        } else {
            logger.warning("No handler can process the request: " + request.getAmount());
        }
    }

    // Abstract method to check if this handler can handle the request
    protected abstract boolean canHandle(Request request);

    // Abstract method to process the request
    protected abstract void process(Request request);
}

// Concrete Handler 1
class ConcreteHandler1 extends AbstractHandler {
    public ConcreteHandler1(Logger logger) {
        super(logger);
    }

    @Override
    protected boolean canHandle(Request request) {
        return request.getAmount() <= 100;
    }

    @Override
    protected void process(Request request) {
        logger.info("Request handled by ConcreteHandler1: Amount = " + request.getAmount());
    }
}

// Concrete Handler 2
class ConcreteHandler2 extends AbstractHandler {
    public ConcreteHandler2(Logger logger) {
        super(logger);
    }

    @Override
    protected boolean canHandle(Request request) {
        return request.getAmount() > 100 && request.getAmount() <= 500;
    }

    @Override
    protected void process(Request request) {
        logger.info("Request handled by ConcreteHandler2: Amount = " + request.getAmount());
    }
}

// Client class
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ChainOfResponsibilityPattern.class.getName());

        Handler handler1 = new ConcreteHandler1(logger);
        Handler handler2 = new ConcreteHandler2(logger);

        handler1.setNext(handler2);

        Request request1 = new Request(50);
        handler1.handleRequest(request1);

        System.out.println();

        Request request2 = new Request(200);
        handler1.handleRequest(request2);

        System.out.println();

        Request request3 = new Request(1000);
        handler1.handleRequest(request3);
    }
}
