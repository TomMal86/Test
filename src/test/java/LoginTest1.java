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

public class LoginTest1 {
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
        driver.get("http://automationpractice.com/index.php");
        logger.info("Automationpractice page displayed");
    }

    //Zadanie 1
    @Test
    public void testLogIn()
    {
        String email = "aaabbb@ccc.com";
        String pswd = "aaabbbccc";
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
        logger.info("Sign In btn clicked");

        WebElement emailFld = driver.findElement(By.id("email"));
        emailFld.sendKeys(email);

        logger.info("Email typed");

        WebElement pswdFld = driver.findElement(By.id("passwd"));
        pswdFld.sendKeys(pswd);
        logger.info("Pswd typed");

        WebElement signInBtn2 = driver.findElement(By.id("SubmitLogin"));
        signInBtn2.click();
        logger.info("Sign in btn clicked");

        WebElement myPersonalInfoBtn = driver.findElement(By.xpath("//*[@title='Information']"));
        myPersonalInfoBtn.click();
        logger.info("My personal info clicked");

        WebElement accopuntEmailFld = driver.findElement(By.id("email"));
        assertEquals(email, accopuntEmailFld.getAttribute("value"));

    }

    //Zadanie 2.1 + Zadanie 2.3
    public void logInAs(String email, String pswd, Boolean isPswdCorrect)
    {
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
        logger.info("Sign In btn clicked");

        WebElement emailFld = driver.findElement(By.id("email"));
        emailFld.sendKeys(email);

        logger.info("Email typed");

        WebElement pswdFld = driver.findElement(By.id("passwd"));
        pswdFld.sendKeys(pswd);
        logger.info("Pswd typed");

        WebElement signInBtn2 = driver.findElement(By.id("SubmitLogin"));
        signInBtn2.click();
        logger.info("Sign in btn clicked");

        if(isPswdCorrect)
        {
            WebElement myPersonalInfoBtn = driver.findElement(By.xpath("//*[@title='Information']"));
            myPersonalInfoBtn.click();
            logger.info("My personal info clicked");

            WebElement accopuntEmailFld = driver.findElement(By.id("email"));
            assertEquals(email, accopuntEmailFld.getAttribute("value"));
            logger.info("Signed in");
        }
        else
        {
            signInBtn = driver.findElement(By.className("login"));
            assertEquals(true, signInBtn.isDisplayed());
            logger.info("Sign in failed");
        }


    }
    //Zadanie 2.2
    @Test
    public void SignInSuccessTest()
    {
        logInAs("aaabbb@ccc.com", "aaabbbccc", true);
    }

    @Test
    public void SingInFailedTest()
    {
        logInAs("aaabbb@ccc.com", "aaabbb", false);
    }

    //Zadanie 3
    public void logOut()
    {
        WebElement signOutBtn = driver.findElement(By.className("logout"));
        signOutBtn.click();
        logger.info("Sign out btn clicked");

        WebElement signInBtn = driver.findElement(By.className("login"));
        assertEquals(true, signInBtn.isDisplayed());
        logger.info("Log out success");

    }

    @Test
    public void logOutTest()
    {
        SignInSuccessTest();
        logOut();
    }

    //Zadanie 4
    public void registerUser2(String email, String pswd)
    {
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();

        WebElement emailFld = driver.findElement(By.id("email_create"));
        emailFld.sendKeys(email);

        WebElement createAnAccountBtn = driver.findElement(By.id("SubmitCreate"));
        createAnAccountBtn.click();

        WebElement titleRadioBtn = driver.findElement(By.id("id_gender1"));
        titleRadioBtn.click();

        WebElement firstNameFld = driver.findElement(By.id("customer_firstname"));
        firstNameFld.sendKeys("TestFName");

        WebElement lastNameFld = driver.findElement(By.id("customer_lastname"));
        lastNameFld.sendKeys("TestLName");

        WebElement pswdFld = driver.findElement(By.id("passwd"));
        pswdFld.sendKeys(pswd);
    }

    @Test
    public void registerSingleUserTest2()
    {
        registerUser("ijdahuda@hnbyvyt.pl", "Testowehaslo");
    }

    //Zadanie 4
    public void registerUser(String email, String pswd)
    {
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
        logger.info("sign In Btn clicked");

        WebElement emailFld = driver.findElement(By.id("email_create"));
        emailFld.sendKeys(email);
        logger.info("Email typed");

        WebElement createAnAccountBtn = driver.findElement(By.id("SubmitCreate"));
        createAnAccountBtn.click();
        logger.info("Create account btn clicked");

        WebElement titleRadioBtn = driver.findElement(By.id("id_gender1"));
        titleRadioBtn.click();
        logger.info("Title radio btn clicked");

        WebElement firstNameFld = driver.findElement(By.id("customer_firstname"));
        firstNameFld.sendKeys("TestFName");
        logger.info("First name typed");

        WebElement lastNameFld = driver.findElement(By.id("customer_lastname"));
        lastNameFld.sendKeys("TestLName");
        logger.info("Last name typed");

        WebElement pswdFld = driver.findElement(By.id("passwd"));
        pswdFld.sendKeys(pswd);
        logger.info("Pswd typed");

        Select dayOfBirth = new Select(driver.findElement(By.id("days")));
        dayOfBirth.selectByValue("1");
        logger.info("Day selected");

        Select monthOfBirth = new Select(driver.findElement(By.id("months")));
        monthOfBirth.selectByVisibleText("January ");
        logger.info("Month selected");

        Select yearOfBirth = new Select(driver.findElement(By.id("years")));
        yearOfBirth.selectByIndex(19);
        logger.info("Year selected");

        WebElement address1Fld = driver.findElement(By.id("address1"));
        address1Fld.sendKeys("Delivery add 11");
        logger.info("Address typed.");

        WebElement cityFld = driver.findElement(By.id("city"));
        cityFld.sendKeys("Gdansk");
        logger.info("City typed.");

        Select stateDropDown = new Select(driver.findElement(By.id("id_state")));
        stateDropDown.selectByVisibleText("Alabama");
        logger.info("Drop down selected.");

        WebElement zipCode = driver.findElement(By.id("postcode"));
        zipCode.sendKeys("12345");
        logger.info("Zip code typed.");

        Select countryDropDown = new Select( driver.findElement(By.id("id_country")));
        countryDropDown.selectByVisibleText("United States");
        logger.info("Country selected");

        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        mobilePhone.sendKeys("1112233");

        WebElement registerAccountBtn = driver.findElement(By.id("submitAccount"));
        registerAccountBtn.click();
        logger.info("Register btn clicked.");

        WebElement signoOutBtn = driver.findElement(By.className("logout"));

        assertEquals(true, signoOutBtn.isDisplayed());
        logger.info("User registered");
    }

    @Test
    public void registerSingleUserTest()
    {
        String email = "ijdada@hnbyvyt.pl";
        String pswd = "Testowehaslo";
        registerUser(email, pswd);
        logOut();
        logInAs(email, pswd, true);
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }


}
