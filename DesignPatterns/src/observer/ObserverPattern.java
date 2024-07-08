package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

interface Subject {
    void register(Observer obj);
    void unregister(Observer obj);
    void notifyObservers();
}

class DeliveryData implements Subject {
    private static final Logger logger = Logger.getLogger(DeliveryData.class.getName());

    private List<Observer> observers;
    private String location;

    public DeliveryData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
        logger.info("Registered observer: " + obj.getClass().getSimpleName());
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
        logger.info("Unregistered observer: " + obj.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Observer obj : observers) {
            obj.update(location);
        }
    }

    public void locationChanged() {
        this.location = getLocation();
        notifyObservers();
    }

    private String getLocation() {
        // This is a stub for actual location fetching logic
        return "YPlace";
    }
}

interface Observer {
    void update(String location);
}

class Seller implements Observer {
    private static final Logger logger = Logger.getLogger(Seller.class.getName());
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    private void showLocation() {
        logger.info("Notification at Seller - Current Location: " + location);
    }
}

class User implements Observer {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    private void showLocation() {
        logger.info("Notification at User - Current Location: " + location);
    }
}

class DeliveryWarehouseCenter implements Observer {
    private static final Logger logger = Logger.getLogger(DeliveryWarehouseCenter.class.getName());
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    private void showLocation() {
        logger.info("Notification at Warehouse - Current Location: " + location);
    }
}

public class ObserverPattern {
    private static final Logger logger = Logger.getLogger(ObserverPattern.class.getName());

    public static void main(String[] args) {
        DeliveryData topic = new DeliveryData();

        Observer seller = new Seller();
        Observer user = new User();
        Observer warehouse = new DeliveryWarehouseCenter();

        topic.register(seller);
        topic.register(user);
        topic.register(warehouse);

        topic.locationChanged();
        topic.unregister(warehouse);

        logger.info("Unregistered warehouse observer.");

        topic.locationChanged();
    }
}
