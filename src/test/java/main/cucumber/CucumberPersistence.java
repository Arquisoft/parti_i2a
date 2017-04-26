package main.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jpa.model.Proposal;
import jpa.repositories.ProposalRepository;
import main.Application;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;

import java.util.List;

@ContextConfiguration(classes = Application.class, loader = ContextLoader.class)
public class CucumberPersistence {

    @Qualifier("proposalRepository")
    @Autowired
    private ProposalRepository repository;

    private List<Proposal> p;

    @When("^the database gets a connection$")
    public void theDatabaseGetsAConnection(){
    }

    @Then("^the application gets the proposal$")
    public void theApplicationGetsTheProposal(){
        p= (List<Proposal>) repository.findAll();
        Assert.assertNotNull(p);
    }

    @And("^the list is not empty?")
    public void theListIsNotEmpty(){
        Assert.assertTrue(!p.isEmpty());
    }

    public ProposalRepository getProposalRepository() {
        return repository;
    }
}
