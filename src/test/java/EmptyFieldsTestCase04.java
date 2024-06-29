import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class EmptyFieldsTestCase04 extends TestBase {

    private ValidateCredentials emptyC;

    @DataProvider(name = "Empty Field")
    public static Object[][] Data() {
        return new Object[][]{
                {"", "secret_sauce"},
                {"standard_user", ""}
        };
    }

    @Test(dataProvider = "Empty Field")
    public void checkCredentials(String user, String pass) throws IOException {
        test = extent.createTest("Empty username or password fields", "This test case validates login with empty username field or empty password field");
        emptyC = new ValidateCredentials(driver);
        emptyC.Login(user, pass);

        String actualErrMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();

        String expectedErrMsg1 = "Epic sadface: Username is required";
        String expectedErrMsg2 = "Epic sadface: Password is required";


        if (actualErrMsg.equals(expectedErrMsg1) || actualErrMsg.equals(expectedErrMsg2)) {
            Assert.assertTrue(true);
            test.log(Status.PASS, "Test passed");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            test.log(Status.PASS, test.addScreenCaptureFromPath(capture(driver)) + "Test Passed");
        } else {
            test.log(Status.FAIL, "Test failed");
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        }
        emptyC.clearField();
    }
}
