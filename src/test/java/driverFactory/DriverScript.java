package driverFactory;



import package


import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	public static WebDriver driver;
	String inputpath ="C:\\Users\\DELL\\eclipse-workspace\\Hybrid_FinalProject\\TestInput\\DataEngine.xlsx";
	String outputpath ="C:\\Users\\DELL\\eclipse-workspace\\Hybrid_FinalProject\\TestOutput\\HybridResults.xlsx";
	public void startTest()throws Throwable
	{
		String ModuleStatus="";
		//create object for excelfiule util
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule =xl.getCellData("MasterTestCases", i, 1);
				for(int j=1;j<=xl.rowCount(TCModule);j++)
				{
					String Description =xl.getCellData(TCModule, j, 0);
					String ObjectType =xl.getCellData(TCModule, j, 1);
					String LocatorType =xl.getCellData(TCModule, j, 2);
					String LocatorValue= xl.getCellData(TCModule, j, 3);
					String TestData =xl.getCellData(TCModule, j, 4);
					try {
						if(ObjectType.equalsIgnoreCase("startBrowser"))
						{
							driver =FunctionLibrary.startBrowser();
						}
						else if(ObjectType.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl(driver);
						}
						else if(ObjectType.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
						}
						else if(ObjectType.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(driver, TestData);
						}
						else if(ObjectType.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
						}
						/*else if(ObjectType.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, LocatorType, LocatorValue);
						}
						else if(ObjectType.equalsIgnoreCase("supplierTable"))
						{
							FunctionLibrary.supplierTable(driver);
						}
						else if(ObjectType.equalsIgnoreCase("mouseClick"))
						{
							FunctionLibrary.mouseClick(driver);
						}
						else if(ObjectType.equalsIgnoreCase("stockTable"))
						{
							FunctionLibrary.stockTable(driver, TestData);
						}*/
						//write ass pass into status
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						ModuleStatus="true";
					}catch(Exception e)
					{
						System.out.println(e.getMessage());
						//write as fail into status cell
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						ModuleStatus="false";
					}
					if(ModuleStatus.equalsIgnoreCase("True"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);
					}
					if(ModuleStatus.equalsIgnoreCase("False"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);	
					}
				}
			}
			else
			{
				//write as blocked into MasterTestCases sheet
				xl.setCellData("MasterTestCases", i, 3, "Blocked", outputpath);
			}
		}
	}

}


