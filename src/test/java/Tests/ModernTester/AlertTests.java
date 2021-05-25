package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AlertTests extends TestBase {

         /*
     Alternative alert interactions
        getDriver().switchTo().alert().getText(); // returning text from alert
        getDriver().switchTo().alert().sendKeys("text"); // enter text to alert's input
        getDriver().switchTo().alert().dismiss(); // clicks cancel button
        getDriver().switchTo().alert().accept(); //clicks OK buttton
     */

    @Test
    public void shouldHandleBasicAlert() {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("simple-alert")).click();
        getDriver().switchTo().alert().accept();
        assertThat(getDriver().findElement(By.id("simple-alert-label")).getText(), equalTo("OK button pressed"));
    }

    @Test
    public void shouldHandlePromptAlert() {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("prompt-alert")).click();
        getDriver().switchTo().alert().sendKeys("Lord Vader");
        getDriver().switchTo().alert().accept();
        assertThat(getDriver().findElement(By.id("prompt-label")).getText(), equalTo("Hello Lord Vader! How are you today?"));
    }

    @Test
    public void shouldHandleConfirmAlert() {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("confirm-alert")).click();
        getDriver().switchTo().alert().accept();
        assertThat(getDriver().findElement(By.id("confirm-label")).getText(), equalTo("You pressed OK!"));

        //for clicking cancel
        getDriver().findElement(By.id("confirm-alert")).click();
        getDriver().switchTo().alert().dismiss();
        assertThat(getDriver().findElement(By.id("confirm-label")).getText(), equalTo("You pressed Cancel!"));
    }

    @Test
    public void shouldHandleDelayedAlert() {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("delayed-alert")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
        assertThat(getDriver().findElement(By.id("delayed-alert-label")).getText(), equalTo("OK button pressed"));

    }
}
