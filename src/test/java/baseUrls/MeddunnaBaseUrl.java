package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import javax.swing.text.Utilities;

import static utilities.AuthenticationMedunna.generateToken;

public class MeddunnaBaseUrl {

    public static RequestSpecification spec;


    public  static void setUp(){
        spec= new RequestSpecBuilder().setContentType(ContentType.JSON).addHeader("Authorization","Bearer "+ generateToken())
                .setBaseUri("https://medunna.com").build();

    }
}
