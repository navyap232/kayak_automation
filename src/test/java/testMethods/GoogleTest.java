package testMethods;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pom.GoogleSearchPage;
import pom.SearchResultPage;
import utilityFunctions.InitializeDriver;
import utilityFunctions.PropertiesConfig;

public class GoogleTest {
	
	@Test
	public void googleSearchTest() throws IOException{
		PropertiesConfig pc=new PropertiesConfig();
		Properties p=pc.getProerty();
		String textToSearch=p.getProperty("textToSearch");
		InitializeDriver id=new InitializeDriver();		
		WebDriver driver=id.getDriver();		
		driver.get("https://www.google.com/");		
		GoogleSearchPage gsp=new GoogleSearchPage(driver);
		gsp.enterTextInSearchBox(textToSearch);
		gsp.enterSearch();		
		SearchResultPage srp=new SearchResultPage(driver);		
		srp.clickOnPerticularLink(3);		
		String title=driver.getTitle();		
		System.out.println(title);
		
		
	}

}
