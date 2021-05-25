package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableTest extends TestBase {

    @Test
    public void shouldPrintHigherThan4000andSwitzerland() {
        getDriver().get("https://seleniumui.moderntester.pl/table.php");

        List<WebElement> allMountains = getDriver().findElements(By.cssSelector("tbody tr"));

        for (WebElement mountain : allMountains) {
            List<WebElement> allColumns = mountain.findElements(By.cssSelector("tbody tr *"));
            if (Integer.parseInt(allColumns.get(4).getText()) > 4000 && allColumns.get(3).getText().contains("Switzerland")) {
                System.out.println("Rank: " + allColumns.get(0).getText() +
                        ", Peak: " + allColumns.get(1).getText() +
                        ", Mountain: " + allColumns.get(2).getText());
            }
        }
    }
}

