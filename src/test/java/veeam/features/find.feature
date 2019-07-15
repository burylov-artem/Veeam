@Check_the_number_of_displayed_jobs
@Test

Feature: Check the number of displayed jobs on the page (Romania, English)

    Scenario:
        Given open page "https://careers.veeam.com/"
        Given make full screen

        When click on country selection
        When select country "Romania" in the list
        When click on language selection
        When select language "English" in the list
        When apply language selection

        Then number of jobs for the country "Romania" and with language "English" should be "40"



