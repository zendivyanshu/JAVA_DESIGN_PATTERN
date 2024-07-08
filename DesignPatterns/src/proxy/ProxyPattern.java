package proxy;

import java.util.logging.Logger;

// DatabaseExecuter interface
interface DatabaseExecuter {
    void executeDatabase(String query) throws Exception;
}

// DatabaseExecuter implementation
class DatabaseExecuterImpl implements DatabaseExecuter {
    private static final Logger logger = Logger.getLogger(DatabaseExecuterImpl.class.getName());

    @Override
    public void executeDatabase(String query) throws Exception {
        logger.info("Executing Query: " + query);
    }
}

// DatabaseExecuter proxy
class DatabaseExecuterProxy implements DatabaseExecuter {
    private static final String ADMIN_USERNAME = "Admin";
    private static final String ADMIN_PASSWORD = "Admin@123";

    private boolean isAdmin;
    private DatabaseExecuter dbExecuter;
    private static final Logger logger = Logger.getLogger(DatabaseExecuterProxy.class.getName());

    public DatabaseExecuterProxy(String username, String password, DatabaseExecuter dbExecuter) {
        this.isAdmin = ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
        this.dbExecuter = dbExecuter;
    }

    @Override
    public void executeDatabase(String query) throws Exception {
        if (isAdmin) {
            dbExecuter.executeDatabase(query);
        } else {
            if ("DELETE".equalsIgnoreCase(query.trim())) {
                logger.severe("DELETE not allowed for non-admin users");
                throw new Exception("DELETE not allowed for non-admin users");
            } else {
                dbExecuter.executeDatabase(query);
            }
        }
    }
}

// ProxyPattern class
public class ProxyPattern {
    private static final Logger logger = Logger.getLogger(ProxyPattern.class.getName());

    public static void main(String[] args) {
        try {
            DatabaseExecuter dbExecuter = new DatabaseExecuterImpl();

            DatabaseExecuter nonAdminExecuter = new DatabaseExecuterProxy("NonAdmin", "Admin@123", dbExecuter);
            nonAdminExecuter.executeDatabase("SELECT * FROM users");

            DatabaseExecuter nonAdminExecuterDELETE = new DatabaseExecuterProxy("NonAdmin", "Admin@123", dbExecuter);
            nonAdminExecuterDELETE.executeDatabase("DELETE");

            DatabaseExecuter adminExecuter = new DatabaseExecuterProxy("Admin", "Admin@123", dbExecuter);
            adminExecuter.executeDatabase("DELETE");
        } catch (Exception e) {
            logger.severe("Error executing query: " + e.getMessage());
        }
    }
}
