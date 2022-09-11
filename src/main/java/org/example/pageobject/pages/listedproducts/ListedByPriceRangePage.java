package org.example.pageobject.pages.listedproducts;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListedByPriceRangePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"s-refinements\"]/div[12]/ul/li")
    private List<WebElement> priceRangeCount;
    @FindBy(xpath = "//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]")
    public List<WebElement> resulPriceList;
    @FindBy(xpath = "//*[@id=\"s-refinements\"]/div[12]/ul/li[1]/span/a/span")
    private WebElement priceRange;
    public ListedByPriceRangePage(WebDriver webDriver) {
        super(webDriver);
    }
    public int priceRange() {
        return Integer.parseInt(waitForVisibility(priceRange).getText().replaceAll("[^\\d.]", ""));
    }
    public void filterByPriceRange() {
        waitForVisibility( webDriver.findElement(By.xpath(
                "//*[@id=\"s-refinements\"]/div[12]/ul/li[1]/span/a"))).click();
    }
}
