package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Candidate;
import org.junit.Assert;
import pages.CandidatePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static util.util.getCandidateFromTableDiff;

public class LoginStep extends BaseUtil {

    private final String urlBase = "http://localhost:3000";

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

    @Given("^I navigate to React")
    public void iNavigateToAngular() throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Given"),
                "I navigate to React");
        Driver.navigate().to(urlBase);
    }


    @Then("^Verify Count P Text is \"([^\"]*)\"")
    public void iVerifyCountPText(String count) throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Then"),
                "Verify Count P Text is \"([^\"]*)\"");
        CandidatePage page = new CandidatePage(Driver);
        Assert.assertEquals(page.GetCount(), count);
    }

    @Then("^Verify Toggle P Text is \"([^\"]*)\"")
    public void iVerifyTogglePText(String toggle) throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Then"),
                "Verify Toggle P Text is \"([^\"]*)\"");
        CandidatePage page = new CandidatePage(Driver);
        Assert.assertEquals(page.GetToggle(), toggle);
    }

    @Then("^Click the Count Button")
    public void ClickCount() throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Then"),
                "Click the Count Button");
        CandidatePage page = new CandidatePage(Driver);
        page.ClickCountButton();
    }

    @Then("^Click the Toggle Button")
    public void ClickToggle() throws Throwable {
        scenarioDef.createNode(
                new GherkinKeyword("Then"),
                "Click the Toggle Button");
        CandidatePage page = new CandidatePage(Driver);
        page.ClickToggleButton();
    }
}
