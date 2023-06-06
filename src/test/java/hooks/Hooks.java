package hooks;

import io.cucumber.java.Before;

import static baseUrls.MeddunnaBaseUrl.setUp;


public class Hooks {

    @Before
    public void beforeApi(){

        setUp();
    }
}
