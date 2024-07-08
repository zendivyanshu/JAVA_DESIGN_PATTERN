package singleton;

import java.util.logging.Level;
import java.util.logging.Logger;

class SingletonEager {
    private static final Logger logger = Logger.getLogger(SingletonEager.class.getName());
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {
        logger.log(Level.INFO, "SingletonEager instance created.");
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}

class Singleton {
    private static final Logger logger = Logger.getLogger(Singleton.class.getName());
    private static Singleton instance;

    private Singleton() {
        logger.log(Level.INFO, "Singleton instance created.");
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

class SingletonSynchronizedMethod {
    private static final Logger logger = Logger.getLogger(SingletonSynchronizedMethod.class.getName());
    private static SingletonSynchronizedMethod instance;

    private SingletonSynchronizedMethod() {
        logger.log(Level.INFO, "SingletonSynchronizedMethod instance created.");
    }

    public static synchronized SingletonSynchronizedMethod getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronizedMethod();
        }
        return instance;
    }
}

class SingletonSynchronized {
    private static final Logger logger = Logger.getLogger(SingletonSynchronized.class.getName());
    private static SingletonSynchronized instance;

    private SingletonSynchronized() {
        logger.log(Level.INFO, "SingletonSynchronized instance created.");
    }

    public static SingletonSynchronized getInstance() {
        if (instance == null) {
            synchronized (SingletonSynchronized.class) {
                if (instance == null) {
                    instance = new SingletonSynchronized();
                }
            }
        }
        return instance;
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        SingletonSynchronized instance = SingletonSynchronized.getInstance();
        System.out.println(instance);
        SingletonSynchronized instance1 = SingletonSynchronized.getInstance();
        System.out.println(instance1);
    }
}
