package genricUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

	// Reads test data from the specified Excel sheet
	public Object[][] readExcelData(String sheetName, int startRow, int columnCount)throws IOException {
	    String path = "./src/test/resources/TestData.xlsx";
	    try (FileInputStream fis = new FileInputStream(path);
	        Workbook workbook = WorkbookFactory.create(fis)) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            throw new IllegalArgumentException("Excel sheet not found: " + sheetName);
	        }
	        int lastRow = sheet.getLastRowNum();
	        if (lastRow < startRow) {
	            return new Object[0][columnCount];
	        }
	        int rowCount = lastRow - startRow + 1;
	        Object[][] data = new Object[rowCount][columnCount];
	        DataFormatter formatter = new DataFormatter();
	        for (int i = 0; i < rowCount; i++) {
	            Row row = sheet.getRow(startRow + i);
	            for (int j = 0; j < columnCount; j++) {
	                if (row == null) {
	                    data[i][j] = "";
	                } else {
	                    data[i][j] = formatter.formatCellValue(row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
	                }
	            }
	        }
	        return data;
	    }
	}

	// Provides vendor details for creation
	@DataProvider(name = "vendorData")
	public Object[][] getVendorData() throws IOException {
	    return readExcelData("VendorData", 1, 12);
	}
	// Provides a vendor name for testing
	@DataProvider(name = "vendor")
	public Object[][] getVendor() throws IOException {
		return new Object[][] {{"UniqueVendor14"}};
	}
	// Provides one vendor name for search
	@DataProvider(name = "vendorSearchData")
	public Object[][] getVendorSearchData() throws IOException {
	    return new Object[][] {{ readExcelData("VendorData", 1, 12)[0][0]}};
	}
	// Provides vendor details for website update
	@DataProvider(name = "vendorUpdateData")
	public Object[][] getVendorUpdateData() throws IOException {
	    return new Object[][] {{readExcelData("VendorData", 1, 12)[0][0],"https://updated-example.com"}};
	}
	
	// Provides vendor names for duplicate testing
	@DataProvider(name = "vendorDuplicateData")
	public Object[][] getVendorDuplicateData() throws IOException {
	    return new Object[][] {{"UniqueVendor01","UniqueVendor01_Copy"}};
	}

	// Provides vendor name for product association
	@DataProvider(name = "vendorProductData")
	public Object[][] getVendorProductData() throws IOException {
	    Object[][] allVendorData = readExcelData("VendorData", 1, 13);
	    return new Object[][] {{allVendorData[1][0].toString() }};
	}
	
	// Provides vendor details for contact association
	@DataProvider(name = "vendorContactData")
	public Object[][] getVendorContactData() throws IOException {
		Object[][] allVendorData = readExcelData("VendorData", 1, 13);
		return new Object[][] {{allVendorData[0][0],allVendorData[0][12]}};
	}
	
	// Provides purchase order details from Excel
	@DataProvider(name = "purchaseOrderData")
	public Object[][] getPurchaseOrderData() throws IOException {
		return readExcelData("PurchaseOrderData", 1, 6);
	}
		
	// Provides data for vendor phone regression testing
	@DataProvider(name = "vendorPhoneRegressionData")
	public Object[][] getVendorPhoneRegressionData() throws IOException {
		Object[][] vendorData = readExcelData("VendorData", 1, 14);
		return new Object[][] {{vendorData[2][0],vendorData[2][13],vendorData[1][0]}};
	}
	
	// Provides currency for price book editing
	@DataProvider(name = "priceBookEditData")
	public Object[][] getPriceBookEditData() throws IOException {
		return new Object[][] {{"USA, Dollars"}};
	}
		
	// Provides currency for Price Book integration testing
	@DataProvider(name = "priceBookIntegrationData")
	public Object[][] getPriceBookIntegrationData() {
		return new Object[][] {{"PB_INT_01", "India, Rupees"}};
	}
	
	// Provides purchase order data for creation
	@DataProvider(name = "purchase")
	public Object[][] getPurchaseOrder() throws IOException {
	    Object[][] excelData = readExcelData("PurchaseOrderData", 1, 6);
	    Object[][] purchaseOrderData = new Object[excelData.length][4];
	    for (int i = 0; i < excelData.length; i++) {
	        purchaseOrderData[i][0] = excelData[i][0]; // Subject
	        purchaseOrderData[i][1] = excelData[i][2]; // BillingAddress
	        purchaseOrderData[i][2] = excelData[i][3]; // ShippingAddress
	        purchaseOrderData[i][3] = excelData[i][5]; // Quantity
	    }
	    return purchaseOrderData;
	}
	
	// Provides a purchase order name for update testing
	@DataProvider(name = "newPurchase")
	public Object[][] getnewPurchase() throws IOException {
	    return new Object[][] {{"Updated_System_test"}};
	}
	
	// Provides asset details for creation
	@DataProvider(name = "newAsset")
	public Object[][] getNewAsset() throws IOException {
	    return new Object[][] {{"456","New_Assest01"}};
	}
	
	// Provides old and new asset names for update testing
	@DataProvider(name = "updatedAssetName")
	public Object[][] getUpdatedAssetName() {
	    return new Object[][] {{"New_Assest01","Updated_New_Assest01"}};
	}
	
	// Provides invalid login credentials from Excel
	@DataProvider(name = "invalidLoginData")
	public Object[][] getInvalidLoginData() throws IOException {
	    return readExcelData("InvalidLogin", 1, 2);
	}
	
	// Provides invalid email data for vendor validation
	@DataProvider(name = "invalidVendorEmail")
	public Object[][] getInvalidVendorEmail() {
	    return new Object[][] {{"InvalidEmailVendor01", "invalid-email"}};
	}
	
	@DataProvider(name = "QtyBVA")
	public Object[][] purchaseOrderQtyBVA() throws IOException {
	    return readExcelData("QtyBVA", 1, 6);
	}

}