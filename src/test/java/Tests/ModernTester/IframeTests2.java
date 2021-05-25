package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class IframeTests2 extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("IframeTests2.class");

    String url;
    String firstName;
    String lastName;
    String login;
    String password;
    int continentIndex;

    @Test
    public void shouldFillForm() throws InterruptedException {
        url = "https://seleniumui.moderntester.pl/iframes.php";
        firstName = "Jan";
        lastName = "Kowalski";
        login = "J.Kowalski";
        password = "test1234";
        continentIndex = 1;

        getDriver().get(url);
        logger.info("Webpage url: " + url);

        getDriver().switchTo().frame("iframe1");
        logger.info("Properly switched to iframe1");

        getDriver().findElement(By.id("inputFirstName3")).sendKeys(firstName);
        getDriver().findElement(By.id("inputSurname3")).sendKeys(lastName);
        logger.debug("First name is {} and last name is {}", firstName, lastName);

        getDriver().findElement(By.cssSelector("button[class='btn btn-primary']")).click();
        logger.debug("Button properly clicked");

        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame("iframe2");
        logger.info("Properly switched to iframe2");

        getDriver().findElement(By.id("inputLogin")).sendKeys(login);
        getDriver().findElement(By.id("inputPassword")).sendKeys(password);
        logger.debug("Login is: {} and password {}", login, password);

        Select continents = new Select(getDriver().findElement(By.id("inlineFormCustomSelectPref")));
        continents.selectByIndex(continentIndex);
        logger.debug("Index of selected continent is {}", continentIndex);

        List<WebElement> experience = getDriver().findElements(By.name("gridRadios"));
        int index = new Random().nextInt(experience.size());
        experience.get(index);
        logger.debug("Selected index is {}", index);

        getDriver().findElement(By.cssSelector("button[class='btn btn-primary']")).click();
        logger.debug("Button properly clicked");

        getDriver().switchTo().defaultContent();
        logger.info("Properly switched to default Content");
        getDriver().findElement(By.xpath("//a[contains(text(),'Basic')]")).click();
        logger.debug("Button 'Basic' clicked properly");

    }
}
