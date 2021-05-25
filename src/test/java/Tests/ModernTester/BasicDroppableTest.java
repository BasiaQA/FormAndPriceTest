package Tests.ModernTester;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BasicDroppableTest extends TestBase {


    @Test
    public void shouldDragAndDrop(){
        getDriver().get("https://seleniumui.moderntester.pl/droppable.php");

        WebElement drag = getDriver().findElement(By.id("draggable"));
        WebElement drop = getDriver().findElement(By.id("droppable"));


        Actions actions = new Actions(getDriver());
//dragAndDrop(source - el that we need to drag,destination - el on which we need to drop the first el)
        actions.dragAndDrop(drag, drop)
                .perform();

        assertThat(drop.getText(),
                equalTo("Dropped!"));
    }

    @Test
    public void shouldDragAndDropAlternative(){
        getDriver().get("https://seleniumui.moderntester.pl/droppable.php");

        WebElement drag = getDriver().findElement(By.id("draggable"));
        WebElement drop = getDriver().findElement(By.id("droppable"));


        Actions actions = new Actions(getDriver());

        actions.clickAndHold(drag)
                .moveToElement(drop)
                .release()
                .perform();

        assertThat(drop.getText(),
                equalTo("Dropped!"));
    }

    @Test
    public void shouldDragAndDropWithXY(){
        getDriver().get("https://seleniumui.moderntester.pl/droppable.php");

        WebElement drag = getDriver().findElement(By.id("draggable"));
        WebElement drop = getDriver().findElement(By.id("droppable"));

        int dropx =drop.getLocation().getX();
        int dropy = drop.getLocation().getY();

        int dragx =drag.getLocation().getX();
        int dragy = drag.getLocation().getY();
        Actions actions = new Actions(getDriver());

        actions.dragAndDropBy(drag,dropx-dragx,dropy-dragy)
                .perform();


        assertThat(drop.getText(),
                equalTo("Dropped!"));
    }

}