package objRepo;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class VendorsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public VendorsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    // Create New Vendor button
    @FindBy(xpath = "//img[@title='Create Vendor...']")
    private WebElement createVendorButton;
    
    public void clickCreateVendor() {
    		createVendorButton.click();
        Reporter.log("Create Vendor form opened successfully.", true);
    }
    
    @FindBy(xpath="(//a[text()='Vendors'])[3]")
    private WebElement verifyCreateVendorForm;
    
    public void verifyCreateVendorForm() {
        wait.until(ExpectedConditions.urlContains("action=EditView"));
        Assert.assertTrue(vendorNameField.isDisplayed(),"Vendor Name field is not displayed.");
        verifyCreateVendorForm.getText();
        Reporter.log("Create Vendor form is displayed successfully.", true);
    }

    @FindBy(xpath="(//a[text()='Vendors'])[3]")
    private WebElement vendorlinks;
    
    public void getvendorlinks() {
    		vendorlinks.click();
    }
    // Vendors list heading
    @FindBy(xpath = "//a[@class='hdrLink' and normalize-space()='Vendors']")
    private WebElement vendorsHeading;

    // Search text field
    @FindBy(css="[name='search_text']")
    private WebElement searchTextField;

    // Search field dropdown
    @FindBy(id = "bas_searchfield")
    private WebElement searchFieldDropdown;

    // Search Now button
    @FindBy(xpath = "//input[@name='submit']")
    private WebElement searchNowButton;

    // Vendor records table
    @FindBy(xpath = "//table[contains(@class,'lvt') and contains(@class,'small')]")
    private List<WebElement> vendorTables;

    // Vendor Name
    @FindBy(name="vendorname")
    private WebElement vendorNameField;
    public void getVendorName(String vendorName) {
        vendorNameField.clear();
        vendorNameField.sendKeys(vendorName);
    }

    // Email
    @FindBy(id="email")
    private WebElement emailField;
    public void getEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Phone
    @FindBy(id="phone")
    private WebElement phoneField;
    
    public void enterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }
    public void selectGLAccountByIndex() {
        Select select = new Select(glAccountDropdown);
        select.selectByIndex(1);
    }

    @FindBy(name = "website")
    private WebElement websiteField;
    public void enterWebsite(String website) {
        websiteField.clear();
        websiteField.sendKeys(website);
    }
 // Update Website
    public void updateWebsite(String newWebsite) {
        wait.until(ExpectedConditions.visibilityOf(websiteField));
        websiteField.clear();
        websiteField.sendKeys(newWebsite);
    }

    // Verifies the updated vendor website
    public void verifyUpdatedWebsite(String expectedWebsite) {
        wait.until(ExpectedConditions.visibilityOf(websiteField));
        Assert.assertEquals( websiteField.getAttribute("value"),expectedWebsite,"Vendor website was not updated successfully.");
        Reporter.log("Vendor website updated successfully.", true);
    }
    
    // GL Account dropdown
    @FindBy(name="glacct")
    private WebElement glAccountDropdown;

    // Category
    @FindBy(id="category")
    private WebElement categoryField;
    
    public void enterCategory(String category) {
        categoryField.clear();
        categoryField.sendKeys(category);
    }

    // Street
    @FindBy(name="street")
    private WebElement streetField;
    
    public void enterStreet(String street) {
        streetField.clear();
        streetField.sendKeys(street);
    }

    // PO Box
    @FindBy(id="pobox")
    private WebElement poBoxField;
    
    public void enterPOBox(String poBox) {
        poBoxField.clear();
        poBoxField.sendKeys(poBox);
    }

    // City
    @FindBy(id="city")
    private WebElement cityField;
    
    public void enterCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    // State
    @FindBy(id="state")
    private WebElement stateField;
    
    public void enterState(String state) {
        stateField.clear();
        stateField.sendKeys(state);
    }
    // Postal Code
    @FindBy(name = "postalcode")
    private WebElement postalCodeField;
    
    public void enterPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    // Country
    @FindBy(name = "country")
    private WebElement countryField;
    
    public void enterCountry(String country) {
        countryField.clear();
        countryField.sendKeys(country);
    }
    // Description
    @FindBy(name = "description")
    private WebElement descriptionField;

    public void enterDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    // Save button
    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private List<WebElement> saveButtons;

    // Cancel buttons
    @FindBy(xpath = "//input[@title='Cancel [Alt+X]']")
    private List<WebElement> cancelButtons;
    
    // Duplicate Vendor Button
    @FindBy(xpath = "(//input[@title='Duplicate [Alt+U]'])[1]")
    private WebElement duplicateButton;
  
    // Products Section Arrow
    @FindBy(id = "show_Vendors_Products")
    private WebElement productsSectionArrow;

    // Select Products Button
    @FindBy(xpath = "//input[@title='Select Products']")
    private WebElement selectProductsButton;
    
    // Product Checkbox - Laptop
    @FindBy(xpath = "//input[@value='113449']")
    private WebElement laptopCheckbox;
    
    @FindBy(id = "show_Vendors_Contacts")
    private WebElement contactsSectionArrow;
    
    @FindBy(xpath = "//input[@title='Select Contacts']")
    private WebElement selectContactsButton;

    @FindBy(name = "search_text")
    private WebElement contactSearchTextField;

    @FindBy(name = "search_field")
    private WebElement contactSearchFieldDropdown;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement contactSearchNowButton;
    
    @FindBy(xpath = "//a[normalize-space()='Create Purchase Order']")
    private WebElement createPurchaseOrderLink;
    
    @FindBy(id = "show_Vendors_PurchaseOrder")
    private WebElement purchaseOrderSectionArrow;
 
    public void verifyVendorsPageDisplayed() {
        wait.until(ExpectedConditions.urlContains("module=Vendors"));
        Assert.assertTrue(driver.getCurrentUrl().contains("action=index")|| driver.getCurrentUrl().contains("action=ListView"),"Vendors list page is not displayed.");
        Reporter.log("Vendors page is displayed successfully.",true);
    }

    public void enterSearchText(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchTextField));
        searchTextField.clear();
        searchTextField.sendKeys(searchText);
    }

    public void selectSearchField(String visibleText) {
        wait.until(ExpectedConditions.visibilityOf(searchFieldDropdown));
        Select select = new Select(searchFieldDropdown);
        select.selectByVisibleText(visibleText);
        Reporter.log("Search criteria selected: " + visibleText, true);
    }

    public void clickSearchNow() {
        wait.until(ExpectedConditions.elementToBeClickable(searchNowButton)).click();
        Reporter.log("Search Now button clicked.", true);
    }
    
 // Click Duplicate Vendor
    public void clickDuplicate() {
        wait.until(ExpectedConditions.elementToBeClickable(duplicateButton)).click();
        wait.until(ExpectedConditions.visibilityOf(vendorNameField));
        Reporter.log("Duplicate Vendor form opened.", true);
    }

 // Saves vendor details
    public void clickSave() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Save [Alt+S]']")));
        saveButton.click();
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        Reporter.log("Vendor details saved successfully.", true);
    }

    public void clickCancel() {
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Cancel' and not(@disabled)]")));
        cancelButton.click();
        Reporter.log("Clicked Cancel on the vendor form.",true);
    }
    
 // Vendor No links in the list
    @FindBy(xpath = "//table[contains(@class,'lvt')]//tr/td[2]/a")
    private List<WebElement> vendorNumbers;

    // Search result rows
//    @FindBy(xpath = "//table[contains(@class,'lvt') and contains(@class,'small')]//tr")
//    private List<WebElement> verifVendors;

    // Vendor No displayed on the vendor details page
    @FindBy(xpath = "//td[normalize-space()='Vendor No']/following-sibling::td[1]")
    private WebElement vendorNoRecord;

    // Search result text
    @FindBy(xpath = "//table[contains(@class,'lvt') and contains(@class,'small')]")
    private WebElement record;
    
    public String getVendorNumber() {
        return wait.until(ExpectedConditions.visibilityOf(vendorNoRecord)).getText().trim();
    }
    
 // VENDOR NUMBER
    public void getVendorNoRecord() {
        String vendorNo = wait.until(ExpectedConditions.visibilityOf(vendorNoRecord)).getText().trim();
        enterSearchText(vendorNo);
        selectSearchField("Vendor No");
        Reporter.log("Vendor No entered: " + vendorNo, true);
    }

    // VERIFY VENDOR SEARCH
    public void verifyVendorSearch(String vendorNo) {
        By vendorRows = By.xpath("//table[contains(@class,'lvt')]//tr[td[normalize-space()='"+ vendorNo + "']]");
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(vendorRows));
        Assert.assertEquals(rows.size(), 1,"Vendor search should return exactly one matching record.");
        Reporter.log("Vendor No verified: " + vendorNo, true);
        Reporter.log("Matching vendor count: " + rows.size(), true);
    }
    
    // Opens an existing vendor record
    public void openVendor(String vendorName) {
        enterSearchText(vendorName);
        selectSearchField("Vendor Name");
        clickSearchNow();
        By vendorLink = By.xpath("//table[contains(@class,'lvt')]//a[normalize-space()='"+ vendorName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(vendorLink)).click();
    }

    // Opens the edit form
    public void clickEdit() {
        By editButton = By.xpath("//input[@name='Edit' or @title='Edit [Alt+E]']");
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    
   // Click More Information Tab
    @FindBy(xpath = "//a[normalize-space(.)='More Information']")
    private WebElement moreInformationTab;
    
    public void clickMoreInformationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInformationTab)).click();
        Reporter.log("More Information tab clicked successfully.", true);
        Assert.assertTrue(MoreInformationPage(),"More Information page verification failed!");
        Reporter.log("More Information page displays Products, Purchase Order, Contacts, and Email sections.",true);
    }

    public boolean MoreInformationPage() {
        boolean products = wait.until(ExpectedConditions.visibilityOf(productsSection)).isDisplayed();
        boolean purchaseOrder = purchaseOrderSection.isDisplayed();
        boolean contacts = contactsSection.isDisplayed();
        boolean email = emailSection.isDisplayed();
        return products && purchaseOrder && contacts && email;
    }
    
    @FindBy(id = "show_Vendors_Products")
    private WebElement productsSection;
    
 // Expand Products Section
    public void clickProductsSectionArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(productsSectionArrow)).click();
        System.out.println("Products section expanded successfully.");
    }
    // Click Select Products Button
    public void clickSelectProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(selectProductsButton)).click();
        Reporter.log("Select Products button clicked successfully.", true);
    }
 

    @FindBy(id = "show_Vendors_PurchaseOrder")
    private WebElement purchaseOrderSection;

    @FindBy(id = "show_Vendors_Contacts")
    private WebElement contactsSection;
   
  	public void clickContactsSectionArrow() {
  		wait.until(ExpectedConditions.elementToBeClickable(contactsSectionArrow)).click();
  		Reporter.log("Contacts section expanded.", true);
  	}
  	public void clickSelectContacts() {
 	    wait.until(ExpectedConditions.elementToBeClickable(selectContactsButton)).click();
 	    Reporter.log("Contact selection popup opened.", true);
 	}

  	public void searchContactByFirstName(String firstName) {
 	    wait.until(ExpectedConditions.visibilityOf(contactSearchTextField));
 	    contactSearchTextField.clear();
 	    contactSearchTextField.sendKeys(firstName);
 	    wait.until(ExpectedConditions.elementToBeClickable(contactSearchNowButton)).click();
 	    Reporter.log("Contact searched by first name: " + firstName, true);
 	}

    @FindBy(id = "show_Vendors_Emails")
    private WebElement emailSection;

 
    // Select Laptop in the popup
    public void selectLaptopProduct() {
    		wait.until(ExpectedConditions.elementToBeClickable(laptopCheckbox)).click();
    }

    // Verify that Laptop is associated with the vendor
 	public boolean isLaptopAssociated() {
 		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbl_Vendors_Products']//a[normalize-space()='laptop']"))).isDisplayed();
 	}
    
 	public void switchToPopupWindow() {
	    String mainWindow = driver.getWindowHandle();
	    wait.until(driver -> driver.getWindowHandles().size() > 1);
	    for (String windowHandle : driver.getWindowHandles()) {
	        if (!windowHandle.equals(mainWindow)) {
	            driver.switchTo().window(windowHandle);
	            Reporter.log("Switched to popup window.", true);
	            return;
	        }
	    }
	}
 
 	//purchase
 	public void clickCreatePurchaseOrder() {
	    wait.until(ExpectedConditions.elementToBeClickable(createPurchaseOrderLink)).click();
	    Reporter.log("Create Purchase Order clicked.", true);
	}
 
 	//Expand Purchase Order section
 	public void clickPurchaseOrderSectionArrow() {
 		wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderSectionArrow)).click();
 		Reporter.log("Purchase Order section expanded.", true);
 	}

 	//Verify Purchase Order in the vendor's Purchase Order section
 	public void verifyPurchaseOrderCreated(String subject) {
 		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tbl_Vendors_PurchaseOrder']//tr"));
 		boolean isFound = false;
 		for (WebElement row : rows) {
 			if (row.getText().contains(subject)) {
 				isFound = true;
 				break;
 			}
 		}
 		Assert.assertTrue(isFound,"Purchase Order not found: " + subject);
 		Reporter.log("Purchase Order verified successfully: " + subject, true);
 	}
 	//regression
 	//Read phone number from the vendor details page
 	public String getDisplayedPhone() {
 		By phoneLocator = By.xpath("//td[normalize-space()='Phone']/following-sibling::td[1]");
 		return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneLocator)).getText().trim();
 	}

 	//Verify phone number remains unchanged
 	public void verifyPhoneUnchanged(String expectedPhone) {
 		String actualPhone = getDisplayedPhone();
 		Assert.assertEquals(actualPhone,expectedPhone,"Vendor phone number changed unexpectedly.");
 		Reporter.log("Vendor phone number remains unchanged: " + actualPhone,true);
 	}

 	//Verify Vendor Name validation when left blank
 	public void verifyVendorNameMandatory() {
 		wait.until(ExpectedConditions.visibilityOf(vendorNameField));
 		Assert.assertTrue(vendorNameField.getAttribute("value").trim().isEmpty(),"Vendor Name field should be blank.");
 		Reporter.log("Vendor Name field is blank.", true);
 	}

 	//Click Save and verify the required Vendor Name validation alert
 	public void clickSaveAndVerifyVendorNameAlert() {
 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Save [Alt+S]']"))).click();
 		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
 		String alertMessage = alert.getText();
 		Assert.assertEquals(alertMessage,"Vendor Name cannot be empty","Incorrect validation message displayed.");
 		Reporter.log("Validation alert displayed correctly: " + alertMessage,true);
 		alert.accept();
 	}
 	
 	@FindBy(id="tbl_Vendors_PurchaseOrder")
 	private WebElement purchaseList;
 	
 	public void verifypurchaseList() {
 		wait.until(ExpectedConditions.visibilityOf(purchaseList));
	    List<WebElement> rows = purchaseList.findElements(By.xpath(".//tr[@bgcolor='white']"));
	    Assert.assertTrue(rows.size() > 0, "No purchase found");
	    for (WebElement row : rows) {
	        Reporter.log(row.getText(), true);
	    }
 	}
 	
 	@FindBy(css ="[title='Vendors']")
	private WebElement VendorTable;
 	
 	public void verifyVendorPresent(String vendorName) {
 	    By vendorLocator = By.xpath("//table[contains(@class,'lvt')]//a[normalize-space()='"+ vendorName + "']");
 	    boolean isPresent = !driver.findElements(vendorLocator).isEmpty();
 	    Assert.assertTrue(isPresent,"Vendor is not present in the Vendors list: " + vendorName);
 	    Reporter.log("Vendor is present in the Vendors list: " + vendorName,true);
 	}
 		
 	public void enterInvalidEmailAndSave(String vendorName, String email) throws InterruptedException {
 	    getVendorName(vendorName);
 	    getEmail(email);
 	    clickSave();
 	}
 	
 	public void alertmessage() {
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    String alertMessage = alert.getText();
	    Reporter.log("Confirmation message: " + alertMessage, true);
	    try {
	        driver.switchTo().alert().accept();
	    } catch (NoAlertPresentException ex) {
	        Reporter.log("Confirmation alert was already handled.", true);
	    }
 	}
}