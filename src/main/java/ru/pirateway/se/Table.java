package ru.pirateway.se;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */

public class Table {

    private WebElement tableElement;

    private WebDriver driver;

    public Table(WebElement table, WebDriver driver) {
        this.tableElement = table;
        this.driver = driver;
    }

    public List<WebElement> getRows() {
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    public List<WebElement> getHeadings() {
        WebElement header = tableElement.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headColumns = header.findElements(By.xpath(".//th"));
        return headColumns;
    }

    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsResult = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWith = row.findElements(By.xpath(".//td"));
            rowsResult.add(rowWith);
        }
        return rowsResult;
    }

    public List<Map<String, WebElement>> getRowsWithColumnsByHeadings() {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeaders = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeadings;
        List<WebElement> headingColumns = getHeadings();
        for (List<WebElement> row : rowsWithColumns) {
            rowByHeadings = new HashMap<String, WebElement>();
            for (int i = 0; i < headingColumns.size(); i++) {
                String heading = headingColumns.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeadings.put(heading, cell);
            }
            rowsWithColumnsByHeaders.add(rowByHeadings);
        }
        return rowsWithColumnsByHeaders;
    }

    public String getValueFromCell(int rowNumber, int columnNumber) {
        List<List<WebElement>> rowsResult = getRowsWithColumns();
        WebElement cell = rowsResult.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }

    public String getValueFromCell(int rowNumber, String columnName) {
        List<Map<String, WebElement>> rowsWithColumnsByHeaders = getRowsWithColumnsByHeadings();
        return rowsWithColumnsByHeaders.get(rowNumber - 1).get(columnName).getText();
    }

}
