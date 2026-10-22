package objRepo;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {

	WebDriver d;

	// Constructor
	public LoginPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d , this);
	}
	
	// Login fields and buttons
	@FindBy(name = "user_name")
	private WebElement un;
	
	@FindBy(name = "user_password")
	private WebElement pw;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	@FindBy(xpath ="//a[@class='hdrLink']")
	private WebElement verify;
	
	@FindBy(css = "[class='errorMessage']")
	private WebElement message;
	
	// Store the entered username and password for reporting
	private String Username;
	private String Password;

	public void getUn(String value) {
		this.Username = value;
		un.sendKeys(value);
	}

	public void getPw(String value) {
		this.Password = value;
		pw.sendKeys(value);
	}

	public void getLoginBtn() {
	    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(20));

	    wait.until(ExpectedConditions.visibilityOf(un));
	    wait.until(ExpectedConditions.visibilityOf(pw));

	    wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

	    wait.until(ExpectedConditions.urlContains("module=Home"));

	    Assert.assertTrue(d.getCurrentUrl().contains("module=Home"),
	            "Login is not successful");

	    Reporter.log("Login Successful", true);
	}
	
	public void getlogin() {
		loginBtn.click();
		Reporter.log("Login Successful", true);
	}
	
	// Perform login using the given credentials
	public void login(String username, String password) {
	    getUn(username);
	    getPw(password);
	    getlogin();
	}
		
	public void getErrorMessage()
	{
		Assert.assertTrue(message.isDisplayed(),"Error message is not displayed");
		String actualMessage = message.getText();
		Assert.assertTrue(!actualMessage.isEmpty(),"Error message is not displayed for: " + un);
		Reporter.log("Username: " + Username,true);
		Reporter.log("Password: " + Password,true);
		Reporter.log("Error Message: " + actualMessage,true);    
	}
	
	
}