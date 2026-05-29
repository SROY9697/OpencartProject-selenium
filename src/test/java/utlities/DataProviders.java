package utlities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public Object[][] getData() throws IOException{
		String path=".\\testData\\LoginData.xlsx";
	        return ExcelUtility.getExcelData(path, "Sheet1");
	    
	}

}
