package builder;

import java.util.logging.Logger;

class Vehicle {
    private static final Logger logger = Logger.getLogger(Vehicle.class.getName());

    private final String engine;
    private final int wheel;
    private final int airbags;

    public String getEngine() {
        return this.engine;
    }

    public int getWheel() {
        return this.wheel;
    }

    public int getAirbags() {
        return this.airbags;
    }

    private Vehicle(VehicleBuilder builder) {
        this.engine = builder.engine;
        this.wheel = builder.wheel;
        this.airbags = builder.airbags;
        logger.info("Vehicle created with engine: " + this.engine + ", wheels: " + this.wheel + ", airbags: " + this.airbags);
    }

    public static class VehicleBuilder {
        private final String engine;
        private final int wheel;
        private int airbags;

        public VehicleBuilder(String engine, int wheel) {
            if (engine == null || engine.isEmpty()) {
                throw new IllegalArgumentException("Engine cannot be null or empty");
            }
            if (wheel <= 0) {
                throw new IllegalArgumentException("Wheel count must be positive");
            }
            this.engine = engine;
            this.wheel = wheel;
        }

        public VehicleBuilder setAirbags(int airbags) {
            if (airbags < 0) {
                throw new IllegalArgumentException("Airbag count cannot be negative");
            }
            this.airbags = airbags;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}

public class BuilderPattern {
    private static final Logger logger = Logger.getLogger(BuilderPattern.class.getName());

    public static void main(String[] args) {
        Vehicle car = new Vehicle.VehicleBuilder("1500cc", 4).setAirbags(4).build();
        Vehicle bike = new Vehicle.VehicleBuilder("250cc", 2).build();

        logger.info("Car Engine: " + car.getEngine());
        logger.info("Car Wheels: " + car.getWheel());
        logger.info("Car Airbags: " + car.getAirbags());

        logger.info("Bike Engine: " + bike.getEngine());
        logger.info("Bike Wheels: " + bike.getWheel());
        logger.info("Bike Airbags: " + bike.getAirbags());
    }
}
