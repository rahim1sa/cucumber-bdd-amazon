package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageobject.pages.homepage.HomePage;
import org.example.pageobject.pages.listedproducts.ListedByBrandNamePage;
import org.example.pageobject.pages.listedproducts.ListedByPriceRangePage;
import org.example.pageobject.pages.listedproducts.ListedBySortedPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class VerifyFilterAndSearchSteps extends BaseSteps{
    public int currentPrice = 0, priceRange=0;
    HomePage homepage = new HomePage(webDriver);
    ListedByBrandNamePage brandName = new ListedByBrandNamePage(webDriver);
    ListedByPriceRangePage listPG = new ListedByPriceRangePage(webDriver);
    ListedBySortedPage sortedPage = new ListedBySortedPage(webDriver);

    List<WebElement> filteredList;
    @Given("User is on homepage")
    public void user_is_on_homepage() {
        homepage.open();
    }
    @And("User clicks category")
    public void user_clicks_category() {
        homepage.clickCategory();
    }
    @When("User filters products by first brand name")
    public void user_filters_products_by_first_brand_name() {
        filteredList = brandName.filterByBrand().getList();
    }
    @Then("one of listed products contain brand name")
    public void one_of_listed_products_contain_brand_name() {
        // brand name
        String titleBrand = brandName.getTitle();
        // checking filtered item names whether it's matched with brand name
        for (WebElement x : filteredList) {
            Assert.assertTrue(x.getText().contains(titleBrand), "One of the products contains brand name");
        }
    }
    @When("User filters products by price range")
    public void user_filters_products_by_price_range() {
        // taking price range
        priceRange = listPG.priceRange();
        // filter by price range
        listPG.filterByPriceRange();
    }
    @Then("products prices should be in range")
    public void products_prices_should_be_in_range() {
        // taking price range
        priceRange = listPG.priceRange();
        // filter by price range
        listPG.filterByPriceRange();

        // checking price range
        for (int i = 0; i < listPG.resulPriceList.size(); i++) {
            currentPrice = Integer.parseInt(listPG.resulPriceList.get(i).getText());
            Assert.assertTrue(currentPrice > priceRange, "Product-" + i + " price(" + currentPrice + ") is under " + priceRange);
        }
    }
    @When ("User sorts products")
    public void user_sorts_products() {
        // filter by price range
        sortedPage.filterByPrice();
        // sort from "low" to "high"
        sortedPage.sort();
    }
    @Then ("Products appear with sorted price")
    public void products_appear_with_sorted_price() {
        List<WebElement> sortedList = sortedPage.sortedProducts;
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int currentPrice = Integer.parseInt(sortedList.get(i).getText());
            int nextPrice = Integer.parseInt(sortedList.get(i + 1).getText());

            Assert.assertTrue(currentPrice <= nextPrice, "unordered price-" + sortedList.get(i + 1).getText());
        }
    }
}
