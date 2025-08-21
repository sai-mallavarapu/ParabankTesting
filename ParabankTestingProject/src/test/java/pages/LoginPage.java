package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //  Locators
    private By txtUsername = By.name("sername");
    private By txtPassword = By.name("password");
    private By btnLogin = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    // 🔹 Actions
    public void setUsername(String uname) {
        wait.until(ExpectedConditions.elementToBeClickable(txtUsername)).sendKeys(uname);
    }

    public void setPassword(String pwd) {
        driver.findElement(txtPassword).sendKeys(pwd);
    }

    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }
}

