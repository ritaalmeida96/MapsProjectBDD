package MapsProjectBDD.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MapsTest {

	WebDriver driver;

	private final By acceptCookiesButton = By.xpath(
			"//body[1]/c-wiz[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/form[2]/div[1]/div[1]/button[1]/div[3]");
	private final By searchBar = By.id("searchboxinput");
	private final By dublinTitle = By.xpath("//h1[normalize-space()='Dublin']");
	private final By dublinDirection = By
			.xpath("//button[@aria-label='Direções para Dublin']//span[contains(@class,'DVeyrd')]");
	private final By dublinDestination = By.xpath("//input[@aria-label='Destino Dublin, Irlanda']");

	private WebElement waitAcceptCookiesButton;
	private WebElement waitSearch;
	private WebElement waitDublinTitle;
	private WebElement waitDublinDirection;
	private WebElement waitDublinDestination;

	@Given("I am at Google Maps page")
	public void I_am_at_Google_Maps_page() {

		String chromeDriverPath = "drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.get("https://www.google.com/maps/");

		waitAcceptCookiesButton = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));

		// Dismiss the cookies popup using JavaScript click
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", waitAcceptCookiesButton);

	}

	@When("I write {string} on Search bar and press Enter")
	public void I_write_on_Search_bar_and_press_Enter(String location) {

		waitSearch = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(searchBar));
		waitSearch.clear();
		waitSearch.sendKeys(location);

		waitSearch.sendKeys(Keys.ENTER);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("I should have {string} on the title")
	public void I_should_have_Dublin_on_the_title(String location) {

		waitDublinTitle = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(dublinTitle));

		String actualLocation = waitDublinTitle.getText();
		String expectedLocation = location;
		
		assertEquals(expectedLocation, actualLocation);
			
		System.out.println("A cidade no titulo é: " + actualLocation);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("I click on the directions icon")
	public void i_click_on_the_directions_icon() {

		waitDublinDirection = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(dublinDirection));

		waitDublinDirection.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("I should have {string} has destination")
	public void i_should_have_dublin_has_destination(String location) {

		waitDublinDestination = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(dublinDestination));

		String actualDestination = waitDublinDestination.getAttribute("value");
		String expectedDestination = location;
		
		assertTrue(actualDestination.startsWith(expectedDestination));
		
		System.out.println("A cidade no titulo é: " + actualDestination);
	

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void fecharBrowser() {
		driver.quit();
	}

}
