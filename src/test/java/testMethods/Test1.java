package testMethods;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.HomePage;
import utilityFunctions.InitializeDriver;
import utilityFunctions.PropertiesConfig;

public class Test1 {
	
	@Test(dataProvider="SearchProvider")
	public void book(String fromLocation,String toLocation,String departure_date, String return_date,int searchResultCount) throws IOException, InterruptedException{
		
		PropertiesConfig pc=new PropertiesConfig();
		Properties p=pc.getProerty();
		String url=p.getProperty("url");
		
		InitializeDriver id=new InitializeDriver();		
		WebDriver driver=id.getDriver();		
		driver.get(url);
		Reporter.log("Application is launched");
		HomePage hp=new HomePage(driver);
		hp.goToKayakSiteLink();
		hp.enterFromLocation(fromLocation);
		Reporter.log("From Location "+fromLocation+" is entered");
		hp.enterToLocation(toLocation);
		Reporter.log("To Location "+toLocation+" is entered");
		hp.enterDepartureDate(departure_date);
		Reporter.log("Departure Date "+departure_date+" is selected");
		hp.enterReturnDate(return_date);
		Reporter.log("return Date "+return_date+" is selected");
		hp.clickSearchButton();
		hp.switchToWindow("");		
		hp.verifyFromAndToCity(fromLocation, toLocation);
		Reporter.log("from location and to location is same in search result page");
		hp.clickOnPerticularSearchResult(searchResultCount);
		hp.switchToWindow("Result");
		System.out.println(driver.getCurrentUrl());	
		hp.getPrices();
		driver.quit();
	}

	
	@DataProvider(name="SearchProvider")
	public Object[][] getDatafromDataProvider(){
		 return new Object[][] 
			    	{
			            { "London", "New York","3 May 2020","5 May 2020",2 },
			            { "London", "New York","3 May 2020","5 May 2020",3 },
			            { "London", "New York","3 May 2020","5 May 2020",1 }
			        };

	}
	
	

}
