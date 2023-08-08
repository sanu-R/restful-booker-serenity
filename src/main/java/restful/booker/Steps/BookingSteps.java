package restful.booker.Steps;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import restful.booker.constants.EndPoints;
import restful.booker.model.AuthPojo;
import restful.booker.model.BookingPojo;

public class BookingSteps {
    @Step("Getting Access Token")
    public ValidatableResponse getToken() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth")
                .then().log().all();
    }
    @Step("Creating new booking with firstName : {0}, lastName: {1}, email: {2}, totalprice: {3} depositpaid: {4}, bookingdates: {5} and additonalneeds: {6}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingPojo.BookingDates bookingdates, String additionalneeds) {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalneeds);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPojo)
                .post()
                .then().log().all();
    }
    @Step("Getting exisiting single booking with id: {0}")
    public ValidatableResponse getSingleBookingIDs(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when().
                get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all();
    }
    @Step("Updating record with id:{0}, token{1}, firstName: {2}, lastName: {3}, email: {4}, totalprice: {5} depositpaid: {6}, bookingdates: {7} and additonalneeds: {8} ")
    public static ValidatableResponse updateBookingWithID(int id, String token, String firstname, String lastname, int totalprice, boolean depositpaid, BookingPojo.BookingDates bookingdates, String additionalneeds) {

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalneeds);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id", id)
                .body(bookingPojo)
                .when().put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Deleting existing booking with id: {0} and token: {1}")
    public ValidatableResponse deleteABookingID(int id, String token) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id", id)
                .when().delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then().log().all();

    }
    @Step("Getting booking info by ID")
    public ValidatableResponse getBookingInfoByID() {
        return SerenityRest.given()
                .when()
                .get()
                .then().statusCode(200);
    }

}
