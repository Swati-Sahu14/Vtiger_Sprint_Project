package crmVtigerAssest;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import objRepo.AssetPage;
import objRepo.HomePage;

public class Assest_Test extends BaseClass {
	@Test(priority = 1, groups = {"SMK", "POS"})
	public void verifyCreateAssestFormOpens_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.verifycreatingassest();
	}
	
	@Test(priority = 2, groups = {"FUN", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyAssetStatusCanBeChangedAndSaved_Test(String no,String name) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getsaveButton();
	    a.getverifyassest();
	    a.getsearchfield(name);
	    a.getsearchIn();
	    a.getsearchnowbtn();
	}
	
	@Test(priority = 3, groups = {"INT", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyExistingInvoiceCanBeAssociatedWithAsset_Test(String no,String name)
	        throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getInvoiceIcon();
	    a.getsaveButton();
	    a.getAssetlink();
	    a.verifyAssetPresent(name);
	    logout();
	}
	@Test(priority = 4, groups = {"SYS", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyAssetInformationPersistsAfterRelogin_Test(String no,String name) throws Exception {
		login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getInvoiceIcon();
	    a.getsaveButton();
	    logout();
	    login();
	    HomePage h = new HomePage(driver);
	    h.getMorelink();
	    h.getAssetslink();
	    a.verifyAssetPresent(name);	
	}
	
	@Test(priority = 5,groups = {"REG", "POS"},dataProvider = "updatedAssetName",dataProviderClass = DataProviderClass.class)
		public void verifyCancelDoesNotSaveAssetChanges_Test(String existingName, String updatedName) throws Exception {
		    login();
		    HomePage home = new HomePage(driver);
		    home.getMorelink();
		    home.getAssetslink();
		    AssetPage a = new AssetPage(driver);
		    a.getsearchfield(existingName);
		    a.getsearchIn();
		    a.getsearchnowbtn();
		    a.openAsset(existingName);
		    a.editButton();
		    a.changeAssetName(updatedName);
		    a.getcancelButton();
		    a.getAssetlink();
		    a.getsearchfield(updatedName);
		    a.getsearchIn();
		    a.getsearchnowbtn();
		    a.verifyUpdatedAssetNotPresent(updatedName);
		  //  ScreenshotUtility.takeScreenshot(driver, "CancelDoesNotSaveAssetChanges");
		    logout();
		}
	
}
