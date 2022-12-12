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

    @FindBy(how = How.ID, using = "candidate-name-textbox")
    public WebElement txtUserName;

    @FindBy(how = How.ID, using = "delete-candidate-id")
    public WebElement txtDeleteId;

    @FindBy(how = How.NAME, using = "add-candidate-btn")
    public WebElement btnAdd;

    @FindBy(how = How.NAME, using = "delete-candidate-by-id")
    public WebElement btnDelete;
    @FindBy(how = How.ID, using = "candidate-table")
    public WebElement candidateTable;

    public void Add(String name) {
        txtUserName.sendKeys(name);
    }

    public void Delete(Candidate candidate) {
        txtDeleteId.sendKeys(candidate.getId().toString());
    }

    public void clickAdd() {
        btnAdd.submit();
    }

    public void clickDelete() {
        btnDelete.submit();
    }

    public List<Candidate> getCandidatesFromTable() {
        List<Candidate> candidates = new ArrayList<>();
        List<WebElement> allRows = null;
        try {
            allRows = candidateTable.findElements(By.tagName("tr"));
            for (WebElement row : allRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() == 2) {
                    candidates.add(new Candidate(
                            cells.get(0).getText(),
                            Long.parseLong(cells.get(1).getText())));
                }
            }
        } catch (StaleElementReferenceException e) { //I really do not like this
            return getCandidatesFromTable();
        }
        return candidates;
    }
}
