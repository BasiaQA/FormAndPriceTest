package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class FormTest extends TestBase {

    String url;
    String firstName;
    String lastName;
    String email;
    String age;
    String continent;
    String seleniumCommand;
    String filePath;
    String inf;

    @Test
    public void shouldFillFormWithSuccess() {
        url = "https://seleniumui.moderntester.pl/form.php";
        firstName = "Jan";
        lastName = "Kowalski";
        email = "test1@gmail.com";
        age = "70";
        continent = "europe";
        seleniumCommand = "webelement-commands";
        filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file.txt";
        inf = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ipsum eget arcu fermentum viverra.";

        getDriver().get(url);

        getDriver().findElement(By.id("inputFirstName3")).sendKeys(firstName);

        getDriver().findElement(By.id("inputLastName3")).sendKeys(lastName);

        getDriver().findElement(By.id("inputEmail3")).sendKeys(email);

        List<WebElement> gridRadiosSex = getDriver().findElements(By.cssSelector("[name='gridRadiosSex']"));
        gridRadiosSex.get(0).click();

        getDriver().findElement(By.id("inputAge3")).sendKeys(age);

        List<WebElement> experience = getDriver().findElements(By.cssSelector("[name='gridRadiosExperience']"));
        int index = new Random().nextInt(experience.size());
        experience.get(index).click();

        List<WebElement> profession = getDriver().findElements(By.name("gridCheckboxProfession"));
        for (WebElement prof : profession) {
            if (!prof.isSelected()) {
                prof.click();
                break;
            }
        }

        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByValue(continent);

        Select seleniumCommands = new Select(getDriver().findElement(By.id("selectSeleniumCommands")));
        seleniumCommands.selectByValue(seleniumCommand);

        getDriver().findElement(By.id("chooseFile")).sendKeys(filePath);

        getDriver().findElement(By.id("additionalInformations")).sendKeys(inf);

        getDriver().findElement(By.cssSelector(".btn-secondary")).click();

        getDriver().findElement(By.xpath("//button[@type=('submit')]")).click();

        WebElement msg = getDriver().findElement(By.id("validator-message"));

        assertThat(msg.getText(), equalTo("Form send with success"));
    }
}

