package crmVtigerHome;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.HomePage;


public class Home_Test extends BaseClass {

	@Test(groups = {"SMK", "POS"}) 
	public void test01() throws Exception {
		HomePage h = new HomePage(driver);
		login();
		h.getMorelink();
		h.verifyHome();
		h.getServiceslink();
		h.getMorelink();
		h.getAssetslink();
		h.getMorelink();
		h.getPurchaselink();
		h.getMorelink();
		h.getPricelink();
		h.getMorelink();
		h.getVendorslink();
	}
}