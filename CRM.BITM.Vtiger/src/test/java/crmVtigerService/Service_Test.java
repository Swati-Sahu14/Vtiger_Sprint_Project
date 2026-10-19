package crmVtigerService;

import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.HomePage;
import objRepo.ServicePage;

public class Service_Test extends BaseClass {
	@Test(priority = 1, groups = {"SMK", "POS"})
	public void verifyCreateServiceFormOpens_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getServiceslink();
	    ServicePage s = new ServicePage(driver);
	    s.getcreateIcon();
	    s.verifycreatingservice();
	}
	
	//Verify that the Active field's saved value is retained after editing and reopening the service.
		@Test (priority = 2,groups = {"SYS","POS"})
		public void STC02() throws Exception {
			login();
			HomePage h = new HomePage(driver);
			h.getMorelink();
			h.getServiceslink();
			ServicePage s = new ServicePage(driver);
			s.servicesname();
			s.getverifyactivecheck();
			s.getEditlink();
			s.getactivercheck();
			s.getsavebtn();
			Reporter.log("After",true);
			s.getverifyactivecheck();
			logout();
		}
}
