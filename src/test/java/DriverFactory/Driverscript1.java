package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class Driverscript1 {
	static ExtentReports report;
	static ExtentTest test;
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		//creating reference object for excel util methods
		ExcelFileUtil excel=new ExcelFileUtil();
//iterating all row in MasterTestCases sheet
	for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
	{
	
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//store module name into TCModule 
			String TCModule=excel.getData("MasterTestCases", i, 1);
			  report = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
			//iterate all rows in ApplicationLogin sheet
					for(int j=1;j<=excel.rowCount(TCModule);j++)
					{
						test=report.startTest(TCModule);
					//read all columns in ApplicationLogin testcase
						String Description=excel.getData(TCModule, j, 0);
						String Function_Name=excel.getData(TCModule, j, 1);
						String Locator_Type=excel.getData(TCModule, j, 2);
						String Locator_Value=excel.getData(TCModule, j, 3);
						String Test_Data=excel.getData(TCModule, j, 4);
						try{
									if(Function_Name.equalsIgnoreCase("startBrowser"))
									{
									driver=FunctionLibrary.startBrowser();
									System.out.println("Launching browser");
									test.log(LogStatus.INFO, Description);
									}
									else if(Function_Name.equalsIgnoreCase("openApplication"))
									{
										FunctionLibrary.openApplication();
										System.out.println("Launching application in browser");
										test.log(LogStatus.INFO, Description);
									}
									else if(Function_Name.equalsIgnoreCase("typeAction"))
									{
									FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
									test.log(LogStatus.INFO, Description);
									}
									else if(Function_Name.equalsIgnoreCase("clickAction"))
									{
										FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
										test.log(LogStatus.INFO, Description);
									}
									else if(Function_Name.equalsIgnoreCase("waitForElement"))
									{
									FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);	
									test.log(LogStatus.INFO, Description);
									}
									else if(Function_Name.equalsIgnoreCase("closeBrowser"))
									{
										FunctionLibrary.closeBrowser(driver);
										test.log(LogStatus.INFO, Description);
									}
									//write as pass into status column
									excel.setData(TCModule, j, 5, "PASS");
									excel.setData("MasterTestCases", i, 3, "PASS");
									test.log(LogStatus.PASS, Description);								
									
					}catch(Exception e){
							System.out.println("exception handled");
							File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\Screens\\"+TCModule+FunctionLibrary.generateDate()+".png"));
							excel.setData(TCModule, j, 5, "Fail");
							excel.setData("MasterTestCases", i, 3, "Fail");
							test.log(LogStatus.FAIL, Description);	
							break;
					}		
							
				report.flush();
				report.endTest(test);

				}
			}
			else{
			//write as not Executed in status column in MasterTestCases sheet
			excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
		}

	}

}
