package asw.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps {

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;
	protected MvcResult result;

	@Value("${local.server.port}")
	protected int port;

	@Given("^the user \"([^\"]*)\" /$")
	public void theUser(String str) {
		System.out.println("The user with login " + str);
	}

	@Given("^password \"([^\"]*)\" /$")
	public void password(String str) {
		System.out.println("and password " + str);
	}

	@When("^the users clicks on Login /$")
	public void theUserClicksOnLogin() {
		System.out.println("the user clicks on login");
	}

	@Then("^the user sees the proposal list$")
	public void theUserSeesTheProposalList() {
		System.out.println("and if it's correct, sees the proposal list");
	}

	@When("^the admin clicks on Login /$")
	public void theAdminClicksOnLogin() {
		System.out.println("the admin clicks on login");
	}

	@Then("^the admin is able to choose parameters for the app$")
	public void theAdminIsAbleToChooseParametersForTheApp() {
		System.out.println("the admin is able to choose parameters for the app");
	}

	@When("^the admin writes a new category /$")
	public void theAdminWritesANewCategory() {
		System.out.println("the admin writes a new category");
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

	@When("^the admin writes non-allowes words /$")
	public void theAdminWritesNonAllowedWords() {
		System.out.println("the admin writes non-allowed words");
	}

	@Then("^the new words will be added to the database /$")
	public void theNewWordsWillBeAddedToTheDatabase() {
		System.out.println("the new words are added to the database");
	}

	@Then("^they won't be allowed to be used in the proposals$")
	public void theyWontBeAllowedToBeUsed() {
		System.out.println("the words won't be allowed in the proposals");
	}

	@When("^the admin writes the id of a proposal /$")
	public void theAdminWritesTheIdOfAProposal() {
		System.out.println("the admin writes the id of the proposal");
	}

	@Then("^the proposal will be deleted$")
	public void theProposalWillBeDeleted() {
		System.out.println("the proposal is deleted");
	}

	@When("^the user writes a proposal /$")
	public void theUserWritesAProposal() {
		System.out.println("the user writes a proposal");
	}

	@When("^selects a category /$")
	public void selectsACategory() {
		System.out.println("the user selects a category");
	}

	@When("^press the add proposal button/$")
	public void pressTheAddProposalButton() {
		System.out.println("the user clicks the add proposal button");
	}

	@Then("^the new proposal will be added to the database$")
	public void theNewProposalWillBeAddedToTheDatabase() {
		System.out.println("the new proposal is added to the BD");
	}

	@When("^the user clicks on view proposals /$")
	public void theUserClicksOnViewProposals() {
		System.out.println("the user clicks on view proposals");
	}

	@Then("^the he can vote each one of them /$")
	public void heCanVoteEachOneOfThem() {
		System.out.println("the user can vote each one of them");
	}

	@Then("^see their details$")
	public void seeTheirDetails() {
		System.out.println("the user can see the details of the proposals");
	}

	@When("^the user clicks on view details of a proposal /$")
		public void theUserClicksOnViewDetailsOfAProposal(){
			System.out.println("the user clicks on view details");
		}

	@Then("^he can comment it/$")
		public void heCanCommentIt(){
			System.out.println("the user can comment the proposal");
		}

	@Then("^see its comments/$")
		public void seeItsComments(){
			System.out.println("the user can see the comments of the proposal");
		}

	@Then("^vote the comments/$")
	public void voteTheComments(){
		System.out.println("the user can vote the comments");
	}

	@Then("^order the comments$")
		public void orderTheComments(){
			System.out.println("the user can order the comments");
		}
	

}
