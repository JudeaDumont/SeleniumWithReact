package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Candidate;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CandidatePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.util.getCandidateFromTableDiff;

public class LoginStep extends BaseUtil {

    private final List<List<Candidate>> candidateTableHistory
            = new ArrayList<>();

    private Candidate candidateFromTableDiff = null;

    private List<Candidate> candidatesToAdd = null;

    public LoginStep() {
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public Candidate convert(Map<String, String> entry) {
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
    public void iAddCandidateToTableHistory() throws ClassNotFoundException {
        scenarioDef.createNode(
                new GherkinKeyword("And"),
                "I add candidate table to history");
        CandidatePage page = new CandidatePage(Driver);

        List<Candidate> candidatesFromTable = forceCandidateTableDelta(page);

        candidateTableHistory.add(candidatesFromTable);
    }

    private List<Candidate> forceCandidateTableDelta(
            CandidatePage page) {
        //dirty hack for random table refresh interval (FORCE DELTAS)

        List<Candidate> candidatesFromTable = page.getCandidatesFromTable();
        int candidateTableHistorySize = candidateTableHistory.size();
        if (candidateTableHistorySize > 0) {
            for (int i = 0; i < 100; i++) {
                if (candidatesFromTable.equals(
                        candidateTableHistory.get(candidateTableHistorySize - 1))) {
                    candidatesFromTable = page.getCandidatesFromTable();
                } else {
                    System.out.println("Force candidate table history delta tries: " + i);
                    break;
                }
                if(i!= 0 && i%10 == 0){
                    Driver.navigate().to("http://localhost:4200"); //try to refresh the page
                }
                if (i == 99) {
                    throw new RuntimeException("Candidate table never updated with new candidate!");
                }
            }
        }
        return candidatesFromTable;
    }

    @Then("^diff tables to get the new candidates ID$")
    public void diffTablesToGetNewID() {
        candidateFromTableDiff = getCandidateFromTableDiff(
                candidateTableHistory.get(0),
                candidateTableHistory.get(1)
        );
    }

    @Then("^the table does not contain the candidate$")
    public void tableDoesNotContainCandidate() {
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

        page.SendUserNameKeys(table.get(0).getName());

        candidatesToAdd = table;
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
    }

    @And("^I click the delete button$")
    public void iClickTheDeleteButton() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"),
                "I click the delete button");
        CandidatePage page = new CandidatePage(Driver);
        page.clickDelete();
    }

}
