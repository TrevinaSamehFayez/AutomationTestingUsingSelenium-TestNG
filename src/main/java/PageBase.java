import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class PageBase {
    protected WebDriver driver;
    protected By username = By.id("user-name");
    WebElement usernameElement;

    protected By password = By.id("password");
    WebElement passwordElement;

    protected By loginButton = By.id("login-button");
    WebElement loginButtonElement;
    public PageBase(WebDriver driver){
        this.driver = driver;
    }

    public void enterText(WebElement element, String text){
        element.sendKeys(text);
    }

    public void click(WebElement button){
        button.click();
    }

}
