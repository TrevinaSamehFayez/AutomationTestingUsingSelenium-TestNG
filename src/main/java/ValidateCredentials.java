import org.openqa.selenium.WebDriver;


public class ValidateCredentials extends PageBase{
    public ValidateCredentials(WebDriver driver) {
        super(driver);
    }
    public void Login(String user, String pass){

        usernameElement = driver.findElement(username);
        enterText(usernameElement, user);

        passwordElement = driver.findElement(password);
        enterText(passwordElement, pass);

        loginButtonElement = driver.findElement(loginButton);
        click(loginButtonElement);
    }

    public void clearField(){
        usernameElement.clear();
        passwordElement.clear();
    }
}
