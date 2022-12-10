package pages.steps;

import Base.BaseUtil;


//import cucumber.api.PickleStepTestStep;
//import cucumber.api.TestCase;
//import gherkin.pickles.PickleStep;
//import io.cucumber.core.api.Scenario;


import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Hook extends BaseUtil{

    public static BaseUtil base = null;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.scenarioDef = base.features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox"); //required for CI/CD workflows
        base.Driver = new ChromeDriver(chromeOptions);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        base.Driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println(
                //"Before every step " + scenario.getId()
        );
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
    }

}
