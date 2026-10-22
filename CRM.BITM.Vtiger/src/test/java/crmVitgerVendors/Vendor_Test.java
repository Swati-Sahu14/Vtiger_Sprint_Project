package crmVitgerVendors;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import genricUtility.ScreenshotUtility;
import objRepo.HomePage;
import objRepo.PurchasePage;
import objRepo.VendorsPage;

public class Vendor_Test extends BaseClass {

	//Verifies that the Vendors module opens successfully and displays the vendor records
	@Test(priority = 1, groups = {"SMK", "POS"})
	public void verifyVendorsListPageOpens_Test() throws Exception {
		login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    Assert.assertTrue(driver.getCurrentUrl().contains("module=Vendors"), "Vendors List Page is not displayed");
	}
	
	// Verifies that the Create New Vendor form opens successfully
	@Test(priority = 2, groups = {"SMK", "POS"})
	public void verifyCreateVendorFormOpens_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.verifyCreateVendorForm();
	}
	
	//Verifies that a vendor is created successfully with valid details
	@Test(priority = 3,groups = {"FUN", "POS"},dataProvider = "vendorData",dataProviderClass = DataProviderClass.class)
	public void createVendorWithValidDetails_Test(String vendorName,String email,String phone,String website,String category,String street,String poBox,String city,String state,String postalCode,String country,String description) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.getVendorName(vendorName);
	    vendor.getEmail(email);
	    vendor.enterPhone(phone);
	    vendor.selectGLAccountByIndex();
	    vendor.enterWebsite(website);
	    vendor.enterCategory(category);
	    vendor.enterStreet(street);
	    vendor.enterPOBox(poBox);
	    vendor.enterCity(city);
	    vendor.enterState(state);
	    vendor.enterPostalCode(postalCode);
	    vendor.enterCountry(country);
	    vendor.enterDescription(description);
	    vendor.clickSave();
	}
	//Verify vendor search using generated Vendor No
	@Test(priority = 4,groups = {"FUN", "POS"},dataProvider = "vendorSearchData",dataProviderClass = DataProviderClass.class)
		public void verifyVendorByNumber_Test(String vendorName) throws Exception {
		    login();
		    HomePage h = new HomePage(driver);
		    h.getMorelink();
		    h.getVendorslink();
		    VendorsPage v = new VendorsPage(driver);
		    v.openVendor(vendorName);
		    String vendorNo = v.getVendorNumber();
		    driver.navigate().back();
		    v.enterSearchText(vendorNo);
		    v.selectSearchField("Vendor No");
		    v.clickSearchNow();
		    v.verifyVendorSearch(vendorNo);
		}
	
	//Verify existing vendor website can be updated
	@Test(priority = 5,groups = {"FUN", "POS"},dataProvider = "vendorUpdateData",dataProviderClass = DataProviderClass.class)
	public void updateVendorWebsite_Test(String vendorName, String newWebsite) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickEdit();
	    vendor.updateWebsite(newWebsite);
	    vendor.clickSave();
	    Reporter.log("Vendor website updated: " + newWebsite, true);
	}
	
	//Verify Duplicate action creates a separate vendor record
	@Test(priority = 6,groups = {"FUN", "POS"},dataProvider = "vendorDuplicateData",dataProviderClass = DataProviderClass.class)
	public void duplicateVendor_Test(String vendorName , String duplicateName) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickDuplicate();
	    vendor.getVendorName(duplicateName);
	    vendor.clickSave();
	    Reporter.log("Duplicate vendor saved: " + duplicateName,  true);
	}
	
	//Verify More Information tab opens
	@Test(priority = 7,groups = {"FUN", "POS"},dataProvider = "vendorDuplicateData",dataProviderClass = DataProviderClass.class)
	public void verifyMoreInformationTab_Test(String vendorName, String name ) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickMoreInformationTab();
	}
	
	//Verify product association with an existing vendor
	@Test(priority = 8,groups = {"INT", "POS"},dataProvider = "vendorProductData",dataProviderClass = DataProviderClass.class)
	public void associateProductWithVendor_Test(String vendorName) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickMoreInformationTab();
	    vendor.clickProductsSectionArrow();
	    vendor.clickSelectProducts();
	    vendor.switchToPopupWindow();
	    vendor.selectLaptopProduct();
	    Reporter.log("Laptop product selected for vendor association.", true);
	}
	
	//Associate an existing contact with a vendor
	@Test(priority = 9,groups = {"INT", "POS"},dataProvider = "vendorContactData",dataProviderClass = DataProviderClass.class)
	public void associateContactWithVendor_Test(String vendorName, String contactFirstName) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickMoreInformationTab();
	    vendor.clickContactsSectionArrow();
	    vendor.clickSelectContacts();
	    vendor.switchToPopupWindow();
	    vendor.searchContactByFirstName(contactFirstName);
	}
	//Verify end-to-end Purchase Order creation from a vendor
	@Test(priority = 10,groups = {"SYS", "POS"},dataProvider = "purchaseOrderData",dataProviderClass = DataProviderClass.class)
	public void createPurchaseOrderFromVendor_Test(String subject,String vendorName,String billingAddress,String shippingAddress,String productName,String quantity) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickCreatePurchaseOrder();
	    PurchasePage purchase = new PurchasePage(driver);
	    purchase.enterSubject(subject);
	    purchase.selectStatus();
	    purchase.selectAssignedTo();
	    purchase.enterBillingAddress(billingAddress);
	    purchase.enterShippingAddress(shippingAddress);
	    purchase.clickProductSearchIcon();
	    purchase.enterQuantity(quantity);
	    purchase.clickSave();
	    vendor.clickMoreInformationTab();
	    vendor.clickPurchaseOrderSectionArrow();
	    vendor.verifypurchaseList();
	    Reporter.log("Purchase Order section displayed successfully.", true);
	    logout();
}
	// Verify end-to-end vendor creation and confirm the newly created vendor appears in the Vendors list.
	@Test(priority = 11,groups = {"SYS", "POS"},dataProvider = "vendor",dataProviderClass = DataProviderClass.class)
	public void EndToEndVendor_Test(String vendorName) throws Exception {
	
		login();
		HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.getVendorName(vendorName);
	    vendor.clickSave();
	    vendor.getvendorlinks();
	    vendor.enterSearchText(vendorName);
	    vendor.selectSearchField("Vendor Name");
	    vendor.clickSearchNow();
	    vendor.verifyVendorPresent(vendorName);
	    logout();
	}

	//Verify editing one vendor does not modify another vendor
	@Test(priority = 12,groups = {"REG", "POS"},dataProvider = "vendorPhoneRegressionData",dataProviderClass = DataProviderClass.class)
	public void verifyVendorPhoneRegression_Test(String vendorToUpdate,String updatedPhone,String vendorToVerify) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorToVerify);
	    String originalPhone = vendor.getDisplayedPhone();
	    Reporter.log("Original phone of " + vendorToVerify + ": " + originalPhone,true);
	    home.getMorelink();
	    home.getVendorslink();
	    vendor.openVendor(vendorToUpdate);
	    vendor.clickEdit();
	    vendor.enterPhone(updatedPhone);
	    vendor.clickSave();
	    home.getMorelink();
	    home.getVendorslink();
	    vendor.openVendor(vendorToVerify);
	    vendor.verifyPhoneUnchanged(originalPhone);
	    Reporter.log("Updating " + vendorToUpdate+ " did not change the phone number of " + vendorToVerify + ".",true);
	    logout();
	}
	// Verify vendor cannot be saved with blank Vendor Name
	@Test(priority = 13, groups = {"NEG"})
	public void verifyVendorNameMandatory_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.verifyCreateVendorForm();
	    vendor.verifyVendorNameMandatory();
	    vendor.clickSaveAndVerifyVendorNameAlert();
	    ScreenshotUtility.takeScreenshot(driver, "VendorNameMandatoryValidation");
	    Reporter.log("Vendor creation was prevented because Vendor Name was blank.",true);
	    logout();
	}	
	
	// Verify invalid email is rejected during vendor creation
	@Test(priority = 14,groups = {"NEG"},dataProvider = "invalidVendorEmail",dataProviderClass = DataProviderClass.class)
		public void verifyVendorCreationWithInvalidEmail_Test(String vendorName, String invalidEmail) throws Exception {
		    login();
		    HomePage home = new HomePage(driver);
		    home.getMorelink();
		    home.getVendorslink();
		    VendorsPage vendor = new VendorsPage(driver);
		    vendor.clickCreateVendor();
		    vendor.getVendorName(vendorName);
		    vendor.getEmail(invalidEmail);
		    vendor.clickSave();
		    vendor.alertmessage();
		    ScreenshotUtility.takeScreenshot(driver,"VendorCreationWithInvalidEmail");
		    logout();
		}
	
	// Verify Purchase Order Qty using Boundary Value Analysis
	@Test(priority = 15,groups = {"FUN", "BVA"},dataProvider = "QtyBVA",dataProviderClass = DataProviderClass.class)
	public void verifyPurchaseOrderQtyBVA_Test(String subject,String vendorName,String billingAddress,String shippingAddress,String quantity,String expectedResult) throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickCreatePurchaseOrder();
	    PurchasePage purchase = new PurchasePage(driver);
	    purchase.enterSubject(subject);
	    purchase.selectStatus();
	    purchase.selectAssignedTo();
	    purchase.enterBillingAddress(billingAddress);
	    purchase.enterShippingAddress(shippingAddress);
	    purchase.clickProductSearchIcon();
	    purchase.enterQuantity(quantity);
	    purchase.clickSave();
	    String actualResult = expectedResult.equals("Reject")? purchase.verifyInvalidQtyAlert(): purchase.verifyValidQtySubmission();
	    Assert.assertEquals(actualResult,expectedResult,"Unexpected validation result for Qty: " + quantity);
	    ScreenshotUtility.takeScreenshot(driver, "QtyBVA_" + quantity.replace("-", "Negative"));
	    logout();
	}
}