package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.MalformedURLException;

import java.io.File;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Before
    public void openBrowser() throws MalformedURLException {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        String driverName;

        if (os.contains("mac")) {
            if (arch.contains("arm") || arch.contains("aarch64")) {
                driverName = "chromedriver_mac-arm64";
            } else {
                driverName = "chromedriver_macx-64";
            }
        } else if (os.contains("win")) {
            // Check Windows architecture
            if (arch.contains("64")) {
                // Check if 64-bit driver exists
                File win64Driver = new File(libWithDriversLocation + "chromedriver.exe");
                if (win64Driver.exists()) {
                    driverName = "chromedriver.exe";
                } else {
                    // Fall back to win32 if win64 doesn't exist
                    driverName = "chromedriver_win32.exe";
                }
            } else {
                // 32-bit Windows
                driverName = "chromedriver_win32.exe";
            }
        }else if (os.contains("linux")) {
                driverName = "chromedriver_linux64";
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os + " / Arch: " + arch);
        }

        String driverPath = libWithDriversLocation + driverName;

        // Make the driver executable on Mac/Linux
        if (!os.contains("win")) {
            File driverFile = new File(driverPath);
            driverFile.setExecutable(true);
        }

        System.setProperty("webdriver.chrome.driver", driverPath);

        System.out.println("Opening Chrome browser with driver: " + driverPath);

        ChromeOptions options = new ChromeOptions();
        // These arguments help bypass SSL and CORS issues during testing.
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-web-security");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}