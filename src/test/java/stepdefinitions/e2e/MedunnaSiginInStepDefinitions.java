package stepdefinitions.e2e;

import io.cucumber.java.en.Given;
import pages.MedunnaHomePage;
import pages.MedunnaLoginPage;
import pages.MedunnaRoomPage;
import utilities.ConfigReader;
import utilities.Driver;

public class MedunnaSiginInStepDefinitions {
    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
   MedunnaLoginPage medunnaLoginPage= new MedunnaLoginPage();
    public static int roomNumberFaker;
    @Given("go to {string}")
    public void go_to(String string) {
        Driver.getDriver().get(ConfigReader.getProperty(string));
    }
    @Given("click on user icin")
    public void click_on_user_icin() {

        medunnaHomePage.userIcon.click();

    }
    @Given("click on signin options")
    public void click_on_signin_options() {
        medunnaHomePage.signInOption.click();

    }
    @Given("send username into username input")
    public void send_username_into_username_input() {
        medunnaLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("medunna_username"));

    }
    @Given("send password into password input")
    public void send_password_into_password_input() {
medunnaLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("medunna_password"));
    }
    @Given("click on sign in submit button")
    public void click_on_sign_in_submit_button() {
        medunnaLoginPage.signInSubmitButton.click();

    }






}