package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        moveTo(Integer.parseInt(LocalDate.now().toString().substring(8)), Integer.parseInt(LocalDate.now().toString().substring(5, 7)), Integer.parseInt(LocalDate.now().toString().substring(0, 4)));


    }

    private void moveTo(int expectedDay, int expectedMonth, int expectedYear) throws InterruptedException {
        //nie działa implicitly wait

        getDriver().findElement(By.id("datepicker")).click();

        while (true) {
            int actualYear = getYearValue();
            if (actualYear < expectedYear) {
                getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click(); //dlaczego tej strzalki nie mozna przypisac do zmiennej
            } else if (getYearValue() > expectedYear) {
                getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]")).click();
            } else
                break;
        }

        while (true) {
            int actualMonth = getMonthValue();
            if (actualMonth < expectedMonth) {
                getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
            } else if (actualMonth > expectedMonth) {
                getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]")).click();
            } else
                break;
        }

        List<WebElement> dayElements = getDriver().findElements(By.xpath("//td/a[contains(@class,'ui-state-default')and not(contains(@class,'ui-priority-secondary'))]"));
        for (WebElement d: dayElements) {
            if (Integer.parseInt(d.getText()) == expectedDay) {
                d.click();
                break;
            }
        }

        String expectedDayFormatted = getExpectedDayFormatted(expectedDay);
        String expectedMonthFormatted = getExpectedMonthFormatted(expectedMonth);

        assertThat(getDriver().findElement(By.id("datepicker")).getAttribute("value"), equalTo(expectedMonthFormatted + "/" + expectedDayFormatted + "/" + expectedYear));
    }


    private int getYearValue() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-year")));
        String yearValue = yearElement.getText();
        return Integer.parseInt(yearValue);
    }

    private String getExpectedDayFormatted(int day) {
        String expectedDayFormatted = "";
        if (day < 10) {
            expectedDayFormatted = "0" + day;
        } else {
            expectedDayFormatted = Integer.toString(day);
        }
        return expectedDayFormatted;
    }

    private String getExpectedMonthFormatted(int month) {
        String expectedMonthFormatted = "";
        if (month < 10) {
            expectedMonthFormatted = "0" + month;
        } else {
            expectedMonthFormatted = Integer.toString(month);
        }
        return expectedMonthFormatted;
    }

    private int getMonthValue() {
        int month = 0;
        String monthValue = getDriver().findElement(By.className("ui-datepicker-month")).getText();
        switch (monthValue) {
            case "Januray":
                month = 1;
                break;
            case "February":
                month = 2;
                break;
            case "March":
                month = 3;
                break;
            case "April":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "June":
                month = 6;
                break;
            case "July":
                month = 7;
                break;
            case "August":
                month = 8;
                break;
            case "September":
                month = 9;
                break;
            case "October":
                month = 10;
                break;
            case "November":
                month = 11;
                break;
            case "December":
                month = 12;
                break;
        }
        return month;
    }
}
