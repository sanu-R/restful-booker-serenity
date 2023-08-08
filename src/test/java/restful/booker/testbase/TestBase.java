package restful.booker.testbase;


import io.restassured.RestAssured;
import org.junit.BeforeClass;
import restful.booker.constants.Path;
import restful.booker.utils.PropertyReader;



public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = Path.BOOKING;
    }

}
