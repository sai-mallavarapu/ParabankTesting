package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By lnkRegister = By.linkText("Register");
	private By txtFirstName = By.name("customer.firstName");
	private By txtLastName = By.name("customer.lastName");
	private By txtAddress = By.name("customer.address.street");
	private By txtCity = By.name("customer.address.city");
	private By txtState = By.name("customer.address.state");
	private By txtZipCode = By.name("customer.address.zipCode");
	private By txtPhone = By.name("customer.phoneNumber");
	private By txtSSN = By.name("customer.ssn");
	private By txtUsername = By.name("customer.username");
	private By txtPassword = By.name("customer.password");
	private By txtConfirmPassword = By.name("repeatedPassword");
	private By btnRegister = By.xpath("//input[@value='Register']");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
	}

	// Actions
	public void clickRegisterLink() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkRegister)).click();
	}

	public void setFirstName(String fname) {
		driver.findElement(txtFirstName).sendKeys(fname);
	}

	public void setLastName(String lname) {
		driver.findElement(txtLastName).sendKeys(lname);
	}

	public void setAddress(String addr) {
		driver.findElement(txtAddress).sendKeys(addr);
	}

	public void setCity(String city) {
		driver.findElement(txtCity).sendKeys(city);
	}

	public void setState(String state) {
		driver.findElement(txtState).sendKeys(state);
	}

	public void setZipCode(String zip) {
		driver.findElement(txtZipCode).sendKeys(zip);
	}

	public void setPhone(String phone) {
		driver.findElement(txtPhone).sendKeys(phone);
	}

	public void setSSN(String ssn) {
		driver.findElement(txtSSN).sendKeys(ssn);
	}

	public void setUsername(String uname) {
		driver.findElement(txtUsername).sendKeys(uname);
	}

	public void setPassword(String pwd) {
		driver.findElement(txtPassword).sendKeys(pwd);
	}

	public void setConfirmPassword(String repwd) {
		driver.findElement(txtConfirmPassword).sendKeys(repwd);
	}

	public void clickRegisterButton() {
		driver.findElement(btnRegister).click();
	}
}
