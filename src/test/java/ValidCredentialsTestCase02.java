import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class ValidCredentialsTestCase02 extends TestBase{

    private ValidateCredentials validC;
    @Test
    public void checkCredentials() throws IOException {
        test = extent.createTest("Valid Credentials", "This test case validates login with correct username and password");
        validC = new ValidateCredentials(driver);
        validC.Login("standard_user", "secret_sauce");
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        String actual = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[2]/div")).getText();
        String expected = "Swag Labs";

        String actualTitle = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span")).getText();
        String expectedTitle = "Products";

        Assert.assertTrue(actual.contains(expected));
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        if (actualTitle.equals(expectedTitle) && actual.equals(expected)) {
            test.log(Status.PASS, "Test passed");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            test.log(Status.PASS, test.addScreenCaptureFromPath(capture(driver)) + "Test Passed");
        } else {
            test.log(Status.FAIL, "Test failed");
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        }
    }
}
