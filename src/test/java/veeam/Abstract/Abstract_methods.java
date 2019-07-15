package veeam.Abstract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import veeam.Run;
import cucumber.api.java.en.*;

import static org.junit.Assert.assertEquals;

public class Abstract_methods {

    WebDriver driver = Run.getDriver();

    Abstract_selectors selectors = new Abstract_selectors();

    @Given("make full screen")
    public void makeFullScreen() {
        driver.manage().window().fullscreen();
    }

    @Given("open page \"(.*)\"")
    public void setUrl(String url) {
        driver.navigate().to(url);
    }

    @When("click on country selection")
    public void clickOnCountrySelection() {
        driver.findElement(By.xpath(selectors.countrySelection)).click();
    }

    @When("select country \"(.*)\" in the list")
    public void selectCountry(String country) {
        driver.findElement(By.xpath(String.format(selectors.countryOnTheList, country))).click();
    }

    @When("click on language selection")
    public void clickOnLanguageSelection() {
        driver.findElement(By.xpath(selectors.languageSelection)).click();
    }

    @When("select language \"(.*)\" in the list")
    public void selectLanguage(String language) {
        int numberLanguage = 0;
        switch (language) {
            case ("English"):
                numberLanguage = 7;
                break;
            case ("German"):
                numberLanguage = 11;
                break;
            case ("Russian"):
                numberLanguage = 22;
                break;
            default:
                System.out.println("К сожалению страна " + language + " не поддерживается");
                break;
        }
        driver.findElement(By.xpath(String.format(selectors.languageOnTheList, numberLanguage))).click();
    }

    @When("apply language selection")
    public void applyLanguageSelection() {
        driver.findElement(By.xpath(selectors.applyLanguageSelection)).click();
    }

    @When("click button show all jobs if she displayed")
    public void clickButtonShowAllJobsIfSheDisplayed() {
        driver = Run.getDriver();
        boolean exist = elementIsVisible(selectors.buttonShowAllJobs);
        if (exist) {
            driver.findElement(By.xpath(selectors.buttonShowAllJobs)).click();
            driver.findElement(By.xpath(selectors.hideButtonShowAllJobs));
        }
    }

    public boolean elementIsVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(Run.getDriver(), 1, 500);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Then("number of jobs for the country \"(.*)\" and with language \"(.*)\" should be \"(.*)\"")
    public void numberOfJobsForTheCountryAndWithLanguageShouldBe(String country, String language, int count) {
        clickButtonShowAllJobsIfSheDisplayed();
        int countCardJobs = driver.findElements(By.xpath(String.format(selectors.jobCardWithCountryAndLanguageSettings,
                country, language))).size();
        if (countCardJobs == 12 || countCardJobs == 6) {
            clickButtonShowAllJobsIfSheDisplayed();
            countCardJobs = driver.findElements(By.xpath(String.format(selectors.jobCardWithCountryAndLanguageSettings,
                    country, language))).size();
        }
        assertEquals("number of jobs did not match", count, countCardJobs);
    }


}
