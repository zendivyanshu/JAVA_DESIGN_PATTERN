package facade;

import java.sql.Driver;
import java.util.logging.Logger;

// BrowserDriver interface
interface BrowserDriver {
    Driver getDriver();
    void generateHTMLReport(String test, Driver driver);
    void generateJUnitReport(String test, Driver driver);
}

// Firefox implementation
class Firefox implements BrowserDriver {
    private static final Logger logger = Logger.getLogger(Firefox.class.getName());

    @Override
    public Driver getDriver() {
        // Logic to get Firefox driver
        return null;
    }

    @Override
    public void generateHTMLReport(String test, Driver driver) {
        logger.info("Generating HTML Report for Firefox Driver");
    }

    @Override
    public void generateJUnitReport(String test, Driver driver) {
        logger.info("Generating JUNIT Report for Firefox Driver");
    }
}

// Chrome implementation
class Chrome implements BrowserDriver {
    private static final Logger logger = Logger.getLogger(Chrome.class.getName());

    @Override
    public Driver getDriver() {
        // Logic to get Chrome driver
        return null;
    }

    @Override
    public void generateHTMLReport(String test, Driver driver) {
        logger.info("Generating HTML Report for Chrome Driver");
    }

    @Override
    public void generateJUnitReport(String test, Driver driver) {
        logger.info("Generating JUNIT Report for Chrome Driver");
    }
}

// WebExplorerHelperFacade
class WebExplorerHelperFacade {
    private final BrowserDriver browserDriver;

    public WebExplorerHelperFacade(BrowserDriver browserDriver) {
        this.browserDriver = browserDriver;
    }

    public void generateReports(String reportType, String test) {
        Driver driver = browserDriver.getDriver();
        switch (reportType) {
            case "html":
                browserDriver.generateHTMLReport(test, driver);
                break;
            case "junit":
                browserDriver.generateJUnitReport(test, driver);
                break;
            default:
                throw new IllegalArgumentException("Invalid report type: " + reportType);
        }
    }
}

// FacadePattern class
public class FacadePattern {
    public static void main(String[] args) {
        String test = "CheckElementPresent";

        BrowserDriver firefoxDriver = new Firefox();
        WebExplorerHelperFacade firefoxFacade = new WebExplorerHelperFacade(firefoxDriver);
        firefoxFacade.generateReports("html", test);
        firefoxFacade.generateReports("junit", test);

        BrowserDriver chromeDriver = new Chrome();
        WebExplorerHelperFacade chromeFacade = new WebExplorerHelperFacade(chromeDriver);
        chromeFacade.generateReports("html", test);
        chromeFacade.generateReports("junit", test);
    }
}
