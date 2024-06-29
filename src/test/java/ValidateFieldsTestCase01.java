import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
public class ValidateFieldsTestCase01 extends TestBase{

    @Test
    public void checkFields() throws IOException {
        test = extent.createTest("Check Username and Password Fields", "This test case Check if the username and password fields are on the main screen of the application:");
        Fields field = new Fields(driver);
        field.FindFields();
        String actualUsername = driver.findElement(By.id("user-name")).getAttribute("placeholder");
        String expectedUsername = "Username";

        String actualPass = driver.findElement(By.id("password")).getAttribute("placeholder");
        String expectedPass = "Password";

        Assert.assertTrue(actualUsername.contains(expectedUsername));


        Assert.assertTrue(actualPass.contains(expectedPass));

        if (actualUsername.equals(expectedUsername) && actualPass.equals(expectedPass)) {
            test.log(Status.PASS, "Test passed");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            test.log(Status.PASS, test.addScreenCaptureFromPath(capture(driver)) + "Test Passed");
        } else {
            test.log(Status.FAIL, "Test failed");
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        }

    }

}
