package pages.steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.LoginPage;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
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
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
        base.Driver.navigate().to("http:/" + socket.getLocalAddress() + ":4200");

    }

    @And("^I enter a name$")
    public void iEnterTheFollowingForLogin(List<Candidate> table)
            throws Throwable {
        base.scenarioDef.createNode(
                new GherkinKeyword("And"),
                "I enter the following for login");

        LoginPage page = new LoginPage(base.Driver);

        page.Add(table.get(0).name);

    }
//
//    @And("^I click login button$")
//    public void iClickLoginButton() throws Throwable {
//        base.scenarioDef.createNode(new GherkinKeyword("And"), "I click login button");
//        LoginPage page = new LoginPage(base.Driver);
//        page.ClickLogin();
//    }
//
//
//    @Then("^I should see the userform page$")
//    public void iShouldSeeTheUserformPage() throws Throwable {
//        scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the userform page");
//
//        Assert.assertEquals("Its not displayed", base.Driver.findElement(By.id("Initial")).isDisplayed(), true);
//    }
//
//    @And("^I enter a ([^\"]*)$")
//    public void iEnterName(String name) throws Throwable {
//        base.scenarioDef.createNode(
//                new GherkinKeyword("And"),
//                "I enter a name");
//    }
//
//    @Then("^I should see the userform page wrongly$")
//    public void iShouldSeeTheUserformPageWrongly() throws Throwable {
//        base.scenarioDef.createNode(new GherkinKeyword("Then"), "I should see  the useform page wrongly");
//        //Assert.assertEquals("Its not displayed", base.Driver.findElement(By.id("sdfgdsfsd")).isDisplayed(), true);
//    }


    public class Candidate {
        public String name;

        public Candidate(String name) {
            this.name= name;
        }
    }

}
