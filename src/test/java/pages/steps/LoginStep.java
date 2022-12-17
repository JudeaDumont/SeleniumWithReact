package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Candidate;
import pages.CandidatePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
