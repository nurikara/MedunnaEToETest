package stepdefinitions;

import io.cucumber.java.en.When;
import pages.MedunnaHomePage;
import pages.MedunnaRoomPage;

public class MedunnaRoomStepDefinitions {

    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    MedunnaRoomPage medunnaRoomPage= new MedunnaRoomPage();

    @When("Click on ItemsAndTitels")
    public void clickOnItemsAndTitels() {
        medunnaHomePage.itemsdAndTitles.click();
    }

    @When("click on Room option")
    public void click_on_room_option() {
        medunnaHomePage.roomOption.click();
    }

    @When("click on Create a new room button")
    public void click_on_create_a_new_room_button() {
    medunnaRoomPage.createANewRoomButton.click();

    }

    @When("enter {string} room number input")
    public void enter_room_number_input(String string) {

    }

    @When("select Suite option from Room Type dropdown")
    public void select_suite_option_from_room_type_dropdown() {

    }

    @When("click on Status checkbox")
    public void click_on_status_checkbox() {

    }

    @When("enter {string} in Price input")
    public void enter_in_price_input(String string) {

    }

    @When("enter {string} in Description input")
    public void enter_in_description_input(String string) {

    }

    @When("click on Save button")
    public void click_on_save_button() {

    }

    @When("close the application")
    public void close_the_application() {

    }

}
