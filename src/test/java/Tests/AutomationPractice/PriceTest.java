package Tests.AutomationPractice;

import TestBase.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PriceTest extends TestBase {
    String baseURL = "http://automationpractice.com/index.php";
    String cssSelector = "ul[id='homefeatured'] div[class='right-block'] span[class='price product-price']";

    @Test
    @DisplayName("Printing prices from main page")
    public void shouldFindPrices() {
        getDriver().get(baseURL);

        List<WebElement> prices = getDriver().findElements(By.cssSelector(cssSelector));
        assertThat(prices.size(), equalTo(7));

        for (WebElement p : prices) {
            System.out.println(p.getText());
        }

    }
}
