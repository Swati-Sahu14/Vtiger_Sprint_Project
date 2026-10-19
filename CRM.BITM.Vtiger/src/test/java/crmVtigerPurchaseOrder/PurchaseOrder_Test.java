package crmVtigerPurchaseOrder;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import objRepo.HomePage;
import objRepo.PurchasePage;

public class PurchaseOrder_Test extends BaseClass {
	@Test(priority = 1,groups = {"FUN", "POS"},dataProvider = "purchase", dataProviderClass = DataProviderClass.class)
		public void verifyPurchaseOrderCreation_Test(String subject,String billingAddress,String shippingAddress,String quantity) throws Exception {
			login();
			HomePage h = new HomePage(driver);
			h.getMorelink();
			h.getPurchaselink();
			PurchasePage p = new PurchasePage(driver);
			p.getCreatePurchaseIcon();
			p.enterSubject(subject);
			p.getVendorIcon();
			p.getstatus();
			p.getAssignedId();
			p.enterBillingAddress(billingAddress);
			p.enterShippingAddress(shippingAddress);
			p.getproductSearchIcon();
			p.enterQuantity(quantity);
			p.clickSave();
			p.getpurchaseLink();
			p.getsearchfield(subject);
			p.getsearchIn();
			p.getsearchnowbtn();
			p.verifypurchase(subject);
			logout();
		
		}

	 @Test(priority = 2, groups = {"REG", "POS"},dataProvider = "newPurchase", dataProviderClass = DataProviderClass.class)
	    public void verifyEditingOnePurchaseOrderDoesNotAffectAnother_Test(String newName)throws Exception {

	        login();
	        HomePage home = new HomePage(driver);
	        home.getMorelink();
	        home.getPurchaselink();
	        PurchasePage p = new PurchasePage(driver);
	        p.getproduct1();
	        p.geteditbtn();
	        p.geteditsubject(newName);
	        p.clickSave();
	        p.getpurchaseLink();
	        p.getsearchfield(newName);
	        p.getsearchIn();
	        p.getsearchnowbtn();
	        p.verifyeditedsubject(newName);
	    }
}
