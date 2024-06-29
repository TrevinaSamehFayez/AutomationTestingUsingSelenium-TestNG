import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Fields extends PageBase{

    public Fields(WebDriver driver) {
        super(driver);
    }

    public void FindFields(){
        usernameElement = driver.findElement(username);
        passwordElement = driver.findElement(password);
    }
}
