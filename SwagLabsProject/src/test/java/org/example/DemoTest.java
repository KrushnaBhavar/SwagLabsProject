package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DemoTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verified successfully");
        } else {
            System.out.println("Title verification failed");
        }

        Select selectDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        selectDropdown.selectByVisibleText("Price (low to high)");

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualPrices = new ArrayList<>();
        List<Double> sortedPrices = new ArrayList<>();

        for (WebElement price : priceElements) {
            Double value = Double.parseDouble(price.getText().replace("$", ""));
            actualPrices.add(value);
            sortedPrices.add(value);
        }

        Collections.sort(sortedPrices);

        if (actualPrices.equals(sortedPrices)) {
            System.out.println("Products sorted correctly (Low to High)");
        } else {
            System.out.println("Sorting failed");
        }

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Krushna");
        driver.findElement(By.id("last-name")).sendKeys("Bhavar");
        driver.findElement(By.id("postal-code")).sendKeys("411001");
        driver.findElement(By.id("continue")).click();

        String totalAmount = driver.findElement(By.className("summary_total_label")).getText();
        System.out.println("Total Amount displayed: " + totalAmount);
        driver.quit();
    }
}
