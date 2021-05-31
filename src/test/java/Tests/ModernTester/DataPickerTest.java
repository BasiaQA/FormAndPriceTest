package Tests.ModernTester;

import TestBase.TestBase;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.indexOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataPickerTest extends TestBase {

    @Test
    public void shouldPickExpectedDate() throws InterruptedException {
        getDriver().get("https://seleniumui.moderntester.pl/datepicker.php");

        moveTo(29, 5, 2021);
        moveTo(5, 2, 2021);
        moveTo(5, 2, 2021);
        moveTo(1, 11, 2020);
        moveTo(1, 12, 2020);
        moveTo(25, 12, 2020);
        moveTo(1, 2, 2022);

        LocalDate date = LocalDate.now();
        String dateText = date.format(DateTimeFormatter.ofPattern("dd MM yyyy"));
        String[] actualDate = dateText.split(" ");
        moveTo(Integer.parseInt(actualDate[0]), Integer.parseInt(actualDate[1]), Integer.parseInt(actualDate[2]));
    }

    private void moveTo(int expectedDay, int expectedMonth, int expectedYear) throws InterruptedException {

        getDriver().findElement(By.id("datepicker")).click();

        while (true) {
            if (getDisplayedYear() < expectedYear) {
                goNext();
            } else if (getDisplayedYear() > expectedYear) {
                goPrev();
            } else
                break;
        }

        while (true) {
            if (getDisplayedMonth() < expectedMonth) {
                goNext();
            } else if (getDisplayedMonth() > expectedMonth) {
                goPrev();
            } else
                break;
        }

        List<WebElement> dayElements = getDriver().findElements(By.xpath("//td/a[contains(@class,'ui-state-default')and not(contains(@class,'ui-priority-secondary'))]"));
        for (WebElement d : dayElements) {
            if (Integer.parseInt(d.getText()) == expectedDay) {
                d.click();
                break;
            }
        }

        String expectedDayFormatted = getExpectedDateFormatted(expectedDay);
        String expectedMonthFormatted = getExpectedDateFormatted(expectedMonth);

        assertThat(getDriver().findElement(By.id("datepicker")).getAttribute("value"), equalTo(expectedMonthFormatted + "/" + expectedDayFormatted + "/" + expectedYear));
    }

    private int getDisplayedYear() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-year")));
        String yearValue = yearElement.getText();
        return Integer.parseInt(yearValue);
    }

    private String getExpectedDateFormatted(int value) {
        String expectedDateFormatted = "";
        if (value < 10) {
            expectedDateFormatted = "0" + value;
        } else {
            expectedDateFormatted = Integer.toString(value);
        }
        return expectedDateFormatted;
    }

    private int getDisplayedMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String monthValue = getDriver().findElement(By.className("ui-datepicker-month")).getText();
        return indexOf(months, monthValue) + 1;
    }

    private void goNext() {
        getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
    }

    private void goPrev() {
        getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]")).click();
    }
}
