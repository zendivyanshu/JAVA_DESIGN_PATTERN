package factory;

import java.util.logging.Logger;

abstract class Vehicle {
    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    public abstract int getWheel();

    @Override
    public String toString() {
        return "Wheel: " + this.getWheel();
    }
}

class Car extends Vehicle {
    private final int wheel;

    Car(int wheel) {
        this.wheel = wheel;
        logger.info("Car created with wheels: " + wheel);
    }

    @Override
    public int getWheel() {
        return this.wheel;
    }
}

class Bike extends Vehicle {
    private final int wheel;

    Bike(int wheel) {
        this.wheel = wheel;
        logger.info("Bike created with wheels: " + wheel);
    }

    @Override
    public int getWheel() {
        return this.wheel;
    }
}

interface VehicleFactory {
    Vehicle createVehicle(int wheel);
}

class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(int wheel) {
        return new Car(wheel);
    }
}

class BikeFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(int wheel) {
        return new Bike(wheel);
    }
}

class VehicleFactoryProvider {
    public static VehicleFactory getFactory(String type) {
        if ("car".equalsIgnoreCase(type)) {
            return new CarFactory();
        } else if ("bike".equalsIgnoreCase(type)) {
            return new BikeFactory();
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
}

public class FactoryPattern {
    private static final Logger logger = Logger.getLogger(FactoryPattern.class.getName());

    public static void main(String[] args) {
        try {
            VehicleFactory carFactory = VehicleFactoryProvider.getFactory("car");
            Vehicle car = carFactory.createVehicle(4);
            logger.info(car.toString());

            VehicleFactory bikeFactory = VehicleFactoryProvider.getFactory("bike");
            Vehicle bike = bikeFactory.createVehicle(2);
            logger.info(bike.toString());
        } catch (IllegalArgumentException e) {
            logger.severe("Error: " + e.getMessage());
        }
    }
}
