package hooks;

import io.cucumber.java.Before;

import static baseUrls.MeddunnaBaseUrl.setUp;


public class Hooks {

    @Before("@api")
    public void beforeApi(){

        setUp();
    }
}
