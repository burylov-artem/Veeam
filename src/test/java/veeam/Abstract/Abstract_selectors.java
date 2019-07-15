package veeam.Abstract;

public class Abstract_selectors {

    public String
            countrySelection = "//dd[@id='country-element']",
            countryOnTheList = "//dd[@id='country-element']//span[text()='%s']",
            languageSelection = "//div[@id='language']",
            languageOnTheList = "//label[@for='ch-%s']",
            applyLanguageSelection = "//a[@class='selecter-fieldset-submit' and text()='Apply']",
            buttonShowAllJobs = "//a[text()='Show all jobs']",
            hideButtonShowAllJobs = "//a[text()='Show all jobs' and contains(@class, 'hide')]",
            jobCardWithCountryAndLanguageSettings = "//span[@itemprop='addressRegion' and text()='%s']" +
                    "/ancestor::div[@class='vacancies-blocks-item-description-location']//p[contains(text(), '%s')]";
}
