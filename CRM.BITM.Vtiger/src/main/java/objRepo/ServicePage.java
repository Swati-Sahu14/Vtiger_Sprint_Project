package objRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class ServicePage {
	WebDriver driver ;
	
	public ServicePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(xpath ="//img[@title='Create Service...']")
	private WebElement createIcon;
	
	public void getcreateIcon() {
		createIcon.click();
	}
	
	@FindBy(xpath="//span[text()='Creating New Service']")
	private WebElement creatingservice;
	
	public void verifycreatingservice() {
		creatingservice.getText();
		Reporter.log("Create Service Page opens Successfully",true);
	}
	
	@FindBy(css = "[title='Services']")
	private List<WebElement> services;
	public void servicesname() {
		for (WebElement service : services) {
			service.click();
			break;
		}	
	}
	
	@FindBy(id = "dtlview_Active")
	private WebElement verifyActive;
	public void getverifyactivecheck() {
		Reporter.log("Active Status:"+verifyActive.getText(),true);
	}
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editlink;
	public void getEditlink() throws Exception {
		editlink.click();
		Thread.sleep(1000);
	}
	
	@FindBy(name = "discontinued")
	private WebElement activeCheck;
	
	public void getactivercheck() {
		activeCheck.click();
	}
	
	@FindBy(xpath = "//input[@value='  Save  ']")
	private WebElement savebtn;
	public void getsavebtn() {
		savebtn.click();
	}
}
