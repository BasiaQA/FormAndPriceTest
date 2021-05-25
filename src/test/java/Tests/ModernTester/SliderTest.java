package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SliderTest extends TestBase {

    @Test
    public void shouldMoveSliderToExpectedValue() {
        getDriver().get("http://seleniumui.moderntester.pl/slider.php");

        moveTo(50);
        moveTo(30);
        moveTo(30);
        moveTo(80);
    }

    private void moveTo(int expectedSliderValue) {

        WebElement slider = getDriver().findElement(By.id("custom-handle"));

        int actualSliderValue = getSliderValue();

        if (actualSliderValue < expectedSliderValue) {
            for (int i = 0; i < (expectedSliderValue - actualSliderValue); i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        } else if (actualSliderValue > expectedSliderValue) {
            for (int i = 0; i < (actualSliderValue - expectedSliderValue); i++) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        assertThat(getSliderValue(), equalTo(expectedSliderValue));
    }

    private int getSliderValue() {
        String sliderValue = getDriver().findElement(By.id("custom-handle")).getText();
        return Integer.parseInt(sliderValue);
    }
}