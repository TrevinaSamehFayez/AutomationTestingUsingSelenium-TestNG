import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;




public class InvalidCredentialsTestCase03 extends TestBase{
    private ValidateCredentials InvalidC;

    @DataProvider(name= "Test Data")
    public static Object[][] Data(){
        return new Object[][]{
                {"standard_user", "secret_sauue"}, //incorrect password
                {"standard_ussr", "secret_sauce"} // incorrect username
        };
    }

    @Test(dataProvider = "Test Data")
    public void checkCredentials(String user, String pass) throws IOException {
        test = extent.createTest("Invalid Credentials", "This test case validates login with incorrect username or incorrect password");
        InvalidC = new ValidateCredentials(driver);
        InvalidC.Login(user, pass);

        String actualError = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();
        String expectedError = "Epic sadface: Username and password do not match any user in this service";


        Assert.assertTrue(actualError.contains(expectedError));

        if (actualError.equals(expectedError)) {
            test.log(Status.PASS, "Test passed");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            test.log(Status.PASS, test.addScreenCaptureFromPath(capture(driver)) + "Test Passed");
        } else {
            test.log(Status.FAIL, "Test failed");
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        }
        InvalidC.clearField();
    }
}
