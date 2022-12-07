package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Karthik on 21/09/2019.
 */
public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "candidate-name-textbox")
    public WebElement txtUserName;

    @FindBy(how = How.NAME, using = "add-candidate-btn")
    public WebElement btnAdd;

    public void Add(String name)
    {
        txtUserName.sendKeys(name);
    }

    public void clickAdd()
    {
        btnAdd.submit();
    }

}
