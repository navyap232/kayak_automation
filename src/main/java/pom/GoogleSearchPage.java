package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {
	WebDriver driver;

	private final By txt_Search=By.name("q");
	
	public GoogleSearchPage(WebDriver driver){
		this.driver=driver;
	}

	
	public void enterTextInSearchBox(String textBox){
		driver.findElement(txt_Search).sendKeys(textBox);
	}
	
	public void enterSearch(){
		driver.findElement(txt_Search).sendKeys(Keys.ENTER);
	}
	
	

}
