package objRepo;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import genricUtility.ScreenshotUtility;

public class PurchasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Initialize the driver, PageFactory elements, and explicit wait
    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Purchase Order creation
    @FindBy(name = "subject")
    private WebElement subjectField;

    @FindBy(name = "postatus")
    private WebElement statusDropdown;

    @FindBy(name = "assigned_user_id")
    private WebElement assignedToDropdown;

    @FindBy(name = "bill_street")
    private WebElement billingAddressField;

    @FindBy(name = "ship_street")
    private WebElement shippingAddressField;

    @FindBy(name = "productName1")
    private WebElement productNameField;

    @FindBy(id = "searchIcon1")
    private WebElement productSearchIcon;

    @FindBy(name = "qty1")
    private WebElement quantityField;
    
    @FindBy(name = "assigned_user_id")
    private WebElement AssignedId;
    
    public void getAssignedId() {
    		Select s = new Select(AssignedId);
    		s.selectByIndex(4);
    }
    
    @FindBy(name = "postatus")
    private WebElement status;
    
    public void getstatus() {
    		Select s = new Select(status);
    		s.selectByIndex(1);
    }

    @FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]")
    private WebElement saveButton;

    @FindBy(id = "popup_product_113449")
    private WebElement laptopProductLink;

    public void enterSubject(String subject) {
        wait.until(ExpectedConditions.visibilityOf(subjectField));
        subjectField.clear();
        subjectField.sendKeys(subject);
        Reporter.log("Purchase Order subject entered: " + subject, true);
    }

    public void selectStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        Select select = new Select(statusDropdown);
        List<WebElement> options = select.getOptions();
        for (int i = 0; i < options.size(); i++) {
            Reporter.log("Status option index " + i + ": "+ options.get(i).getText().trim(),true);
        }
        Assert.assertTrue(options.size() > 1,"Status dropdown does not contain an option at index 1.");
        select.selectByIndex(1);
        wait.until(driver ->select.getFirstSelectedOption().isSelected());
        Reporter.log("Selected Status: "+ select.getFirstSelectedOption().getText().trim(),true);
    }

    public void selectAssignedTo() {
        wait.until(ExpectedConditions.elementToBeClickable(assignedToDropdown));
        Select select = new Select(assignedToDropdown);
        wait.until(driver -> select.getOptions().size() > 0);
        select.selectByIndex(0);
        String selectedOption = select.getFirstSelectedOption().getText().trim();
        Assert.assertFalse(selectedOption.isEmpty(),"Assigned To option was not selected.");
        Reporter.log("Selected Assigned To: " + selectedOption, true);
    }

    public void selectLaptopProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopProductLink)).click();
        Reporter.log("Laptop selected from the product popup.", true);
    }

    public void enterBillingAddress(String billingAddress) {
        wait.until(ExpectedConditions.visibilityOf(billingAddressField));
        billingAddressField.clear();
        billingAddressField.sendKeys(billingAddress);
        Reporter.log("Billing Address entered: " + billingAddress, true);
    }

    public void enterShippingAddress(String shippingAddress) {
        wait.until(ExpectedConditions.visibilityOf(shippingAddressField));
        shippingAddressField.clear();
        shippingAddressField.sendKeys(shippingAddress);
        Reporter.log("Shipping Address entered: " + shippingAddress, true);
    }

    // Open the product lookup popup
    public void clickProductSearchIcon() throws InterruptedException {
    	String parentWindow = driver.getWindowHandle();
    		productSearchIcon.click();
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    // Switch to the organization popup
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
	    List<WebElement> organizations = driver.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    driver.switchTo().window(parentWindow);
    }


    // Purchase Order verification
    public void verifyProductName(String expectedProductName) {
        wait.until(ExpectedConditions.visibilityOf(productNameField));
        String actualProductName = productNameField.getAttribute("value");
        Assert.assertEquals(actualProductName == null ? "" : actualProductName.trim(),expectedProductName.trim(),"Selected product name does not match.");
        Reporter.log("Product selected: " + actualProductName, true);
    }

    public void enterQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOf(quantityField));
        quantityField.clear();
        quantityField.sendKeys(quantity);
        Reporter.log("Product quantity entered: " + quantity, true);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        Reporter.log("Purchase Order Save button clicked.", true);
    }
    
    // Popup handling
    public String switchToProductPopup() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Reporter.log("Switched to Products popup.", true);
        return parentWindow;
    }
 
    // Switch back to the Purchase Order form
    public void switchToParentWindow(String parentWindow) {
        wait.until(driver -> driver.getWindowHandles().contains(parentWindow));
        driver.switchTo().window(parentWindow);
        Reporter.log("Returned to Purchase Order form.", true);
    }
    
    // Wait until the Purchase Order form is no longer displayed
    public void waitForPurchaseOrderFormToClose() {
        wait.until(ExpectedConditions.invisibilityOf(subjectField));
        Reporter.log("Purchase Order form closed after save.", true);
    }
    
    //Purchase Order navigation
    @FindBy(xpath="//img[@title='Create Purchase Order...']")
    private WebElement CreatePurchaseIcon;
    
    public void getCreatePurchaseIcon() {
    		CreatePurchaseIcon.click();
    }
 
    @FindBy(xpath = "(//img[@title='Select'])[1]")
    private WebElement vendorIcon;
    
    public void getVendorIcon() throws InterruptedException {
	    	String parentWindow = driver.getWindowHandle();
		    vendorIcon.click();
		    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		    for (String window : driver.getWindowHandles()) {
		        if (!window.equals(parentWindow)) {
		            driver.switchTo().window(window);
		            break;
		        }
		    }
		    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
		    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
		    List<WebElement> organizations = driver.findElements(organizationLocator);
		    if (organizations.isEmpty()) {
		        throw new RuntimeException("No organizations found in popup");
		    }
		    organizations.get(0).click();
		    Thread.sleep(1000);
		    driver.switchTo().window(parentWindow);
	  }
    
   
    public void getproductSearchIcon() throws InterruptedException {
    	String parentWindow = driver.getWindowHandle();
    		productSearchIcon.click();
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    // Switch to the organization popup
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
	    List<WebElement> organizations = driver.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    // Click the organization
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    // Switch back to the main quote window
	    driver.switchTo().window(parentWindow);
    }
    
    @FindBy(name="search_text")
	private WebElement searchfield;
	
	public void getsearchfield(String value) {
		searchfield.sendKeys(value);
	}
	
	@FindBy(id="bas_searchfield")
	private WebElement searchIn;
	
	public void getsearchIn() {
		Select s= new Select(searchIn);
		s.selectByIndex(1);
	}
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;
	
	public void getsearchnowbtn() throws InterruptedException {
		searchnowbtn.click();
		Thread.sleep(2000);
	}
	
	@FindBy(xpath="(//a[text()='Purchase Order'])[3]")
	private WebElement purchaseLink;
	
	public void getpurchaseLink() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    By purchaseOrderLocator = By.xpath("(//a[normalize-space()='Purchase Order'])[3]");
	    wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderLocator)).click();
	}
	
	@FindBy(css="[title='Purchase Order']")
	private List<WebElement> purchasename;
	
    public void verifypurchase(String name) {
    		String Actualpname= name;
    		boolean isDisplayed = false;
    		for(WebElement ele: purchasename) {
    			String Expectedpname= ele.getText();
    			if (Actualpname.equals(Expectedpname)) {
    		        isDisplayed = true;
    		        break;
    			}
    		}
    		Assert.assertTrue(isDisplayed,"purchase name is not created");
    		Reporter.log("Purchase Order is created",true);
    } 
    
    // Purchase Order update
    @FindBy(xpath="//a[@title='Purchase Order']")
    private WebElement product1;
    
    public void getproduct1() {
    		product1.click();
    }
    
    @FindBy(name="Edit")
    private WebElement editbtn;
    
    public void geteditbtn() {
    		editbtn.click();
    }
    
    @FindBy(name ="subject")
    private WebElement editsubject;
    
    public void geteditsubject(String value) {

        By subjectLocator = By.name("subject");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectLocator));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(subjectLocator))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(subjectLocator)).sendKeys(value);
        Reporter.log("Purchase Order subject updated: " + value, true);
    }
    
    @FindBy(css = "[title='Purchase Order']")
	private WebElement orders;
    
    @FindBy(xpath = "//tr[starts-with(@id,'row_')]")
	private List<WebElement> allrecords;
	
	public void verifyeditedsubject(String newName) throws InterruptedException {
		String actualordername = orders.getText();
		String expectedordername = newName;
		Assert.assertEquals(actualordername, expectedordername, "updated name is not edited successfully");
		Reporter.log("updated name is displayed" , true);
		Reporter.log("Edited Order name: "+orders.getText(),true);
		Thread.sleep(2000);
		for(WebElement ele:allrecords) {
			Reporter.log("Edited Orders: "+ele.getText(),true);
		}
	}	
}