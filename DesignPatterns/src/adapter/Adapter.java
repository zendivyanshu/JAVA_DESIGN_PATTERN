package adapter;

import java.util.logging.Logger;

// Define WebDriver interface
interface WebDriver {
    void getElement();
    void selectElement();
}

// Implement WebDriver for ChromeDriver
class ChromeDriver implements WebDriver {
    private static final Logger logger = Logger.getLogger(ChromeDriver.class.getName());

    @Override
    public void getElement() {
        logger.info("Get element from ChromeDriver");
    }

    @Override
    public void selectElement() {
        logger.info("Select element from ChromeDriver");
    }
}

// Define IEDriver with its own methods
class IEDriver {
    private static final Logger logger = Logger.getLogger(IEDriver.class.getName());

    public void findElement() {
        logger.info("Find element from IEDriver");
    }

    public void clickElement() {
        logger.info("Click element from IEDriver");
    }
}

// Implement WebDriverAdapter to adapt IEDriver to WebDriver
class WebDriverAdapter implements WebDriver {
    private final IEDriver ieDriver;
    private static final Logger logger = Logger.getLogger(WebDriverAdapter.class.getName());

    public WebDriverAdapter(IEDriver ieDriver) {
        this.ieDriver = ieDriver;
    }

    @Override
    public void getElement() {
        logger.info("Adapting findElement from IEDriver");
        ieDriver.findElement();
    }

    @Override
    public void selectElement() {
        logger.info("Adapting clickElement from IEDriver");
        ieDriver.clickElement();
    }
}

// Test the Adapter
public class Adapter {
    private static final Logger logger = Logger.getLogger(Adapter.class.getName());

    public static void main(String[] args) {
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.getElement();
        chromeDriver.selectElement();

        IEDriver ieDriver = new IEDriver();
        ieDriver.findElement();
        ieDriver.clickElement();

        WebDriver webDriverAdapter = new WebDriverAdapter(ieDriver);
        webDriverAdapter.getElement();
        webDriverAdapter.selectElement();
    }
}
