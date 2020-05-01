package pom;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage {
	WebDriver driver;

	private final By goToKayak=By.linkText("Go to kayak.com instead.");
	private final By divFromPlace=By.xpath("(//div[@data-placeholder='From?'])[1]");
	private final By textFromPlace=By.xpath("(//input[@placeholder='From?'])[1]");
	private final By divToPlace=By.xpath("(//div[@data-placeholder='To?'])[1]");
	private final By textToPlace=By.xpath("(//input[@placeholder='To?'])[1]");
	private final By btn_Search=By.xpath("(//button[@aria-label='Search flights'])[1]");
	private final By popup_Window=By.xpath("//button[contains(@id,'-dialog-close')]");
	private final By from_City=By.xpath("//div[contains(@id,'origin-airport-display')]//div[contains(@class,'-selection-display')]");
	private final By to_City=By.xpath("//div[contains(@id,'destination-airport-display')]//div[contains(@class,'-selection-display')]");
	private final By searchResults=By.xpath("//a[contains(@aria-label,'View Deal') and @role='button']");
	private final By price_nonStop=By.xpath("//div[@id='nonstop_flights']//div[@class='segmentPrice']");
	private final By price_MaxiumOneStop=By.xpath("//div[@id='maximum_one_stop']//div[@class='segmentPrice']");
	private final By price_maxTwoStop=By.xpath("//div[@id='maximum_2_stops']//div[@class='segmentPrice']");
	public HomePage(WebDriver driver){
		this.driver=driver;
	}

	
	public void goToKayakSiteLink(){
		if(driver.findElement(goToKayak).isDisplayed()){
		driver.findElement(goToKayak).click();
		}
	}
	
	public void enterFromLocation(String text) throws InterruptedException{
		driver.findElement(divFromPlace).click();
		driver.findElement(textFromPlace).sendKeys(text);
		Thread.sleep(1000);
		driver.findElement(textFromPlace).click();
		driver.findElement(textFromPlace).sendKeys(Keys.TAB);
	}
	
	
	public void enterToLocation(String text) throws InterruptedException{
		driver.findElement(divToPlace).click();
		driver.findElement(textToPlace).sendKeys(text);
		Thread.sleep(1000);
		driver.findElement(textToPlace).click();
		driver.findElement(textToPlace).sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}
	
	public void enterDepartureDate(String departure_date) throws InterruptedException{
		Thread.sleep(1000);
		String[] dep=departure_date.split(" ");
		String day=dep[0];
		String month=dep[1];
		String year=dep[2];
		driver.findElement(By.xpath("(//div[@aria-label='"+month+" "+day+"'])[1]")).click();
		driver.findElement(By.tagName("body")).click();
	}
	
	public void enterReturnDate(String return_date) throws InterruptedException{
		String[] ret=return_date.split(" ");
		 String day=ret[0];
		 String month=ret[1];
		 String year=ret[2];
		driver.findElement(By.xpath("(//div[@aria-label='Return date input'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@aria-label='"+month+" "+day+"'])[1]")).click();
	}
	public void clickSearchButton(){
		driver.findElement(btn_Search).click();
	}
	
	
	public void switchToWindow(String title){
		
		String currentWindow=driver.getWindowHandle();
		Set<String> allWindows=driver.getWindowHandles();
		
		for(String window:allWindows){
			if(title.equals("")){
				if(!window.equals(currentWindow)){
					driver.switchTo().window(window);
					break;
				}
			}else{
				driver.switchTo().window(window);
				if(driver.getTitle().contains(title)){
					break;
				}
			}
		}
		
		
	}
	
	
	public void verifyFromAndToCity(String fromCity,String toCity) throws InterruptedException{
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(7000);
		List<WebElement> list=driver.findElements(popup_Window);
		int size=list.size()-1;
		System.out.println(size);
		driver.findElement(By.xpath("(//button[contains(@id,'-dialog-close')])["+size+"]")).click();
		
		
	   String cityFrom=driver.findElement(from_City).getText();
	   String cityTo=driver.findElement(to_City).getText();
	   
	   System.out.println(cityFrom);
	   System.out.println(cityTo);
	   
	   Assert.assertTrue(cityFrom.contains(fromCity));
	   Assert.assertTrue(cityTo.contains(toCity));
	}
	
	public void clickOnPerticularSearchResult(int searchResultCount){
		 List<WebElement> searchResultList=driver.findElements(searchResults);
		   int count=0;
		   for(int i=0;i<searchResultList.size();i++){
			   
			   WebElement searchResult=searchResultList.get(i);
			   
			   if(searchResult.isDisplayed()==true){
				   count++;
			   }
			   
			   if(count==searchResultCount){
				  searchResult.click(); 
				  break;
			   }
		   }
	}
	
	public void getPrices(){
		
		String nonStopPrice=driver.findElement(price_nonStop).getText();
		String oneStopPrice=driver.findElement(price_MaxiumOneStop).getText();
		String twoStopPrice=driver.findElement(price_maxTwoStop).getText();
		
		Reporter.log("Non Stop Price : "+nonStopPrice);
		Reporter.log("Maxium one Stop price : "+oneStopPrice);
		Reporter.log("Maxium 2 Stop price : "+twoStopPrice);
	}

}
