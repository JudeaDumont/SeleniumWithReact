package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import model.Candidate;
import pages.CandidatePage;

import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 31/01/2019.
 */
public class LoginStep extends BaseUtil{

    private  BaseUtil base;

    public LoginStep(BaseUtil base) {
        this.base = base;
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public Candidate convert(Map<String, String> entry){
        return new Candidate(
                entry.get("name")
        );
    }

    @Given("^I navigate to Angular$")
    public void iNavigateToAngular() throws Throwable {
        base.scenarioDef.createNode(
                new GherkinKeyword("Given"),
                "I navigate to Angular");
        base.Driver.navigate().to("http://localhost:4200");

    }

    @And("^I get existing candidates$")
    public void iGetExistingCandidates(){
        CandidatePage page = new CandidatePage(base.Driver);
        page.getCandidatesFromTable();
    }


    @And("^I enter a name to add$")
    public void iEnterANameToAdd(List<Candidate> table)
            throws Throwable {
        base.scenarioDef.createNode(
                new GherkinKeyword("And"),
                "I enter a name to add");

        CandidatePage page = new CandidatePage(base.Driver);

        page.Add(table.get(0).getName());

    }

    @And("^I click the add button$")
    public void iClickLoginButton() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"),
                "I click login button");
        CandidatePage page = new CandidatePage(base.Driver);
        page.clickAdd();
    }

}
