package pages;

import model.Candidate;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CandidatePage {

    public CandidatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "count-btn")
    public WebElement countButton;

    @FindBy(how = How.ID, using = "count")
    public WebElement countP;

    @FindBy(how = How.ID, using = "toggle-btn")
    public WebElement toggleButton;

    @FindBy(how = How.ID, using = "toggle")
    public WebElement toggleP;

    public void ClickCountButton() {
        countButton.click();
    }

    public void ClickToggleButton() {
        toggleButton.click();
    }

    public String GetCount() {
        return countP.getText();
    }

    public String GetToggle() {
        return toggleP.getText();
    }
}
