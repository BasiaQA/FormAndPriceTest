package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProgressbarTests extends TestBase {

    @Test
    public void shouldWaitForProgressBarWithImplicitlyWait(){
        getDriver().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS); //zazwyczaj w testBase
        getDriver().get("https://seleniumui.moderntester.pl/progressbar.php");
        getDriver().findElement(By.xpath("//div[.='Complete!']"));
        assertThat(getDriver().findElement(By.className("progress-label")).getText(), equalTo("Complete!"));
    }

    @Test
    public void shouldWaitForProgressBarWithExplicitWait1(){
        getDriver().get("https://seleniumui.moderntester.pl/progressbar.php");
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Complete!']")));
        assertThat(getDriver().findElement(By.className("progress-label")).getText(), equalTo("Complete!"));
    }

    @Test
    public void shouldWaitForProgressBarWithExplicitWait2(){
        getDriver().get("https://seleniumui.moderntester.pl/progressbar.php");
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
         wait.until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.className("progress-label")),"Complete!"));
        assertThat(getDriver().findElement(By.className("progress-label")).getText(), equalTo("Complete!"));
    }
}