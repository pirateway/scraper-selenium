package ru.pirateway.se;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pirateway.se.entity.ForkDto;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\###PROJECT\\JAVA\\scraper\\16.11\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://positivebet.com/ru/bets/index");
        WebElement tableElement = driver.findElement(By.xpath("//*[@id=\"gridBets\"]/table")); //*[@id="gridBets"]/table
        Table table = new Table(tableElement, driver);
        String pageSource = driver.getPageSource();
        System.out.println(pageSource); // полностью HTML страницы

        for (int i = 0; i < table.getRows().size(); i++) {
            ForkDto forkDto = getFortDtoFromTable(table, i + 1);
            System.out.println(forkDto);
        }

        System.out.println(table.getRows().size());
        driver.quit();
    }

    private static ForkDto getFortDtoFromTable(Table table, int rowNumber) {
        ForkDto forkDto = new ForkDto();
        forkDto.setTime(table.getValueFromCell(rowNumber, 1)); //time
        forkDto.setPercent(table.getValueFromCell(rowNumber, 2)); //percent

        String[] arraysBookmaker = Arrays.toString(table.getValueFromCell(rowNumber, 3)
                .split("\\n")).split(",");//bookmakers
        forkDto.setBookmaker1(arraysBookmaker[0].substring(1));
        forkDto.setBookmaker2(arraysBookmaker[1].substring(1, arraysBookmaker[1].length() - 1));

        String[] arrayEvents = Arrays.toString(table.getValueFromCell(rowNumber, 4)
                .split("\\n")).split(","); //events
        forkDto.setGameEvent1(arrayEvents[0].substring(1));
        forkDto.setGameEvent2(arrayEvents[1].substring(1, arrayEvents[1].length() - 1));

        String[] arrayBets = Arrays.toString(table.getValueFromCell(rowNumber, 5)
                .split("\\n")).split(","); //bets
        forkDto.setBet1(arrayBets[0].substring(1));
        forkDto.setBet2(arrayBets[1].substring(1, arrayBets[1].length() - 1));

        String[] arrayCoefficients = Arrays.toString(table.getValueFromCell(rowNumber, 5)
                .split("\\n")).split(","); //coefficients
        forkDto.setCoefficient1(arrayCoefficients[0].substring(1));
        forkDto.setCoefficient2(arrayCoefficients[1].substring(1, arrayCoefficients[1].length() - 1));

        return forkDto;
    }

}
