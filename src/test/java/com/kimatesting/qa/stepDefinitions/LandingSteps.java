package com.kimatesting.qa.stepDefinitions;

import com.kimatesting.qa.pages.LandingPage;
import com.kimatesting.qa.stepDefinitions.utils.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LandingSteps extends BaseTest {

	LandingPage landingPage = new LandingPage(getDriver());

	@Given("A user go to KIMA Testing page")
	public void a_user_go_to_KIMATesting_page() {
		landingPage.goToKIMATestingPage();
	}

	@Then("{string} title is displayed")
	public void kima_testing_title_is_displayed(String title) {
		Assert.assertEquals("Page title is invalid.", title, landingPage.getKIMATestingTitle());
	}
	
}
