import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class GetResponse {
    private WebDriver driver;
    Logger logger = Logger.getLogger("TestAutomation");
    //WebDriverWait wait = new WebDriverWait(driver, 10);

    @Before
    public void setUp(){
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Chrome started");
        // Maksymalizujemy okno
        driver.manage().window().maximize();
        logger.info("Chrome maximized");
//        // Ustawienie czasu niejawnego oczekiwania na 10 sekund
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Przechodzimy na wybrana strone
        driver.get("https://www.getresponse.pl/");
        logger.info("GetResponse page displayed");
    }

    @Test
    public void testLogIn() throws InterruptedException {
        String email = "tomasz.malona@wp.pl";
        String pswd = "GetResponse2020#";
        WebElement signInBtn = driver.findElement(By.className("rheader-menu-login"));
        signInBtn.click();
        logger.info("Sign In btn clicked");

        WebElement emailFld = driver.findElement(By.name("email"));
        emailFld.sendKeys(email);

        logger.info("Email typed");

        WebElement pswdFld = driver.findElement(By.name("password"));
        pswdFld.sendKeys(pswd);
        logger.info("Password typed");

        WebElement signInBtn2 = driver.findElement(By.cssSelector("[data-ats-login-form=\"input_submit\"]"));
        signInBtn2.click();
        logger.info("Sign in button clicked");
        Thread.sleep(15000);


    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }


}

