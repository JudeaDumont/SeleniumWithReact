package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Candidate;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import pages.CandidatePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.util.getCandidateFromTableDiff;

public class LoginStep extends BaseUtil{

    private final List<List<Candidate>> candidateTableHistory
            = new ArrayList<>();

    private Candidate candidateFromTableDiff = null;

    public LoginStep() {
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public Candidate convert(Map<String, String> entry){
        return new Candidate(
                entry.get("name")
        );
    }

    @Given("^I navigate to Angular$")
    public void iNavigateToAngular() throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Given"),
                "I navigate to Angular");
        Driver.navigate().to("http://localhost:4200");

    }

    @And("^I add candidate table to history$")
    public void iGetExistingCandidates(){
        CandidatePage page = new CandidatePage(Driver);
        candidateTableHistory.add(page.getCandidatesFromTable());
    }

    @Then("^diff tables to get the new candidates ID$")
    public void diffTablesToGetNewID(){
        candidateFromTableDiff = getCandidateFromTableDiff(
                candidateTableHistory.get(0),
                candidateTableHistory.get(1)
        );
    }

    @Then("^the table does not contain the candidate$")
    public void tableDoesNotContainCandidate(){
        boolean containsDeleted = candidateTableHistory.get(2).contains(candidateFromTableDiff);
        assert (!containsDeleted);
    }


    @And("^I enter a name to add$")
    public void iEnterANameToAdd(List<Candidate> table)
            throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("And"),
                "I enter a name to add");

        CandidatePage page = new CandidatePage(Driver);

        page.Add(table.get(0).getName());

    }


    @And("^I enter the new candidates id to delete$")
    public void iEnterTheNewCandidatesIDToDelete()
            throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("And"),
                "I enter the new candidates id to delete");

        CandidatePage page = new CandidatePage(Driver);

        page.Delete(candidateFromTableDiff);

    }

    @And("^I click the add button$")
    public void iClickTheAddButton() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"),
                "I click the add button");
        CandidatePage page = new CandidatePage(Driver);
        page.clickAdd();
        Thread.sleep(1000);
        LogEntries entry = Hook.base.Driver.manage().logs().get(LogType.BROWSER);
        // Retrieving all log
        List<LogEntry> logs= entry.getAll();
        // Print one by one
        for(LogEntry e: logs)
        {
            System.out.println(e);
        }
    }

    @And("^I click the delete button$")
    public void iClickTheDeleteButton() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"),
                "I click the delete button");
        CandidatePage page = new CandidatePage(Driver);
        page.clickDelete();
    }

}
