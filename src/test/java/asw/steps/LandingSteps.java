package asw.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.Application;

@SuppressWarnings("deprecation")
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps {

	protected ConfigurableApplicationContext appContext;
	protected WebDriver driver = new HtmlUnitDriver();
	protected String baseUrl = "http://localhost:8080/";

	@Before
	public void setUp() {
		driver.get(baseUrl);
	}

	@Given("^the user \"([^\"]*)\" /$")
	public void theUser(String str) {
		System.out.println("The user with login " + str);
		driver.get(baseUrl + "/login");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(str);
	}

	@Given("^password \"([^\"]*)\" /$")
	public void password(String str) {
		System.out.println("and password " + str);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(str);
	}

	@When("^the users clicks on Login /$")
	public void theUserClicksOnLogin() {
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
	}

	@Then("^the user sees the proposal list$")
	public void theUserSeesTheProposalList() {
		System.out.println("and if it's correct, sees the proposal list");
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Participation System" + "')]"));
		Assert.assertTrue("Text not found!", list.size() > 0);
	}

	@When("^the admin clicks on Login /$")
	public void theAdminClicksOnLogin() {
		System.out.println("the admin clicks on login");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
	}

	@Then("^the admin is able to choose parameters for the app$")
	public void theAdminIsAbleToChooseParametersForTheApp() {
		System.out.println("the admin is able to choose parameters for the app");
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Add category:" + "')]"));
		Assert.assertTrue("Text not found!", list.size() > 0);
		list = driver.findElements(
				By.xpath("//*[contains(text(),'" + "Add non-allowed words (separated by commas):" + "')]"));
		Assert.assertTrue("Text not found!", list.size() > 0);
		list = driver.findElements(By.xpath("//*[contains(text(),'" + "Delete proposal:" + "')]"));
		Assert.assertTrue("Text not found!", list.size() > 0);
	}

	@When("^the admin writes a new category /$")
	public void theAdminWritesANewCategory() {
		System.out.println("the admin writes a new category");
		driver.get(baseUrl + "/login");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.id("category")).clear();
		driver.findElement(By.id("category")).sendKeys("Limpieza");
		driver.findElement(By.id("ok1")).click();
	}

	@When("^press the accept button /$")
	public void pressTheAcceptButton() {
		System.out.println("the admin press the accept button");
	}

	@Then("^the new category will be added to the database /$")
	public void theNewCategoryWillBeAddedToTheDatabase() {
		System.out.println("the new category will be added to the database");
	}

	@Then("^there will be a new category to choose from in the proposals$")
	public void thereWillBeANewCategoryToChooseFromInTheDatabase() {
		System.out.println("therw will be a new category in the database");
	}

	@When("^the admin writes non-allowed words /$")
	public void theAdminWritesNonAllowedWords() {
		driver.get(baseUrl + "/login?logout");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.id("words")).clear();
		driver.findElement(By.id("words")).sendKeys("caca,culo,pedo,pis");
		driver.findElement(By.id("ok2")).click();
	}

	@Then("^the new words will be added to the database /$")
	public void theNewWordsWillBeAddedToTheDatabase() {
		System.out.println("the new words are added to the database");
	}

	@Then("^they won't be allowed to be used in the proposals$")
	public void theyWontBeAllowedToBeUsed() {
		System.out.println("the words won't be allowed in the proposals");
	}

	@When("^the admin selects a proposal /$")
	public void theAdminWritesTheIdOfAProposal() {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
	}

	@Then("^the proposal will be deleted$")
	public void theProposalWillBeDeleted() {
		System.out.println("the proposal is deleted");
	}

	@When("^the user writes a proposal /$")
	public void theUserWritesAProposal() throws InterruptedException {
		signInUser();
		driver.get(baseUrl + "user/addForm");

		driver.findElement(By.id("text")).clear();
		driver.findElement(By.id("text")).sendKeys("me gustaria ser feliz");
	}

	@When("^selects a category /$")
	public void selectsACategory() {
		System.out.println("the user selects a category");
	}

	@When("^press the add proposal button/$")
	public void pressTheAddProposalButton() {
		System.out.println("the user clicks the add proposal button");
		driver.findElement(By.xpath("//input[@value='Add proposal']")).click();
	}

	@Then("^the new proposal will be added to the database$")
	public void theNewProposalWillBeAddedToTheDatabase() {
		System.out.println("the new proposal is added to the BD");
	}

	@When("^the user clicks on view proposals /$")
	public void theUserClicksOnViewProposals() throws InterruptedException {
		System.out.println("the user clicks on view proposals");

		driver.get(baseUrl + "user/home");

	}

	@Then("^he can vote each one of them /$")
	public void heCanVoteEachOneOfThem() {
		System.out.println("the user can vote each one of them");
	}

	@Then("^see their details$")
	public void seeTheirDetails() {
		System.out.println("the user can see the details of the proposals");
	}

	@When("^the user clicks on view details of a proposal /$")
	public void theUserClicksOnViewDetailsOfAProposal() {
		System.out.println("the user clicks on view details");
	}

	@Then("^he can comment it/$")
	public void heCanCommentIt() {
		System.out.println("the user can comment the proposal");
	}

	@Then("^see its comments/$")
	public void seeItsComments() {
		System.out.println("the user can see the comments of the proposal");
	}

	@Then("^vote the comments/$")
	public void voteTheComments() {
		System.out.println("the user can vote the comments");
	}

	@Then("^order the comments$")
	public void orderTheComments() {
		System.out.println("the user can order the comments");
	}

	private void signInUser() throws InterruptedException {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("user@email.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		Thread.sleep(3000);
	}
}