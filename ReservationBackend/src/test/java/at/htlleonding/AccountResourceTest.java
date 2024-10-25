package at.htlleonding;

import at.htlleonding.boundary.AccountResource;
import at.htlleonding.model.Account;
import at.htlleonding.repository.AccountRepository;
import io.quarkus.test.InjectMock;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(AccountResource.class)
public class AccountResourceTest {

    @InjectMock
    AccountRepository accountRepository;

    private static Account c;
    @BeforeAll
    static void setup() {
        c = new Account();
        c.setName("John Doe 123");
        c.setEmail("johndoe@gmail.com");
        c.setPhone("+43 677 1234567890");
        c.setPassword("password");
        c.setStreet("Main Street");
        c.setZip("1234");
        c.setCity("City");
        c.setCountry("Country");
        c.setHouseNo("1");
    }

    @Order(1)
    @Test
    public void testCreateAccount() {
        Mockito.doAnswer(invocationOnMock -> {
            Account cust = invocationOnMock.getArgument(0);
            cust.setId(1L);
            return null;
        }).when(accountRepository).persist(any(Account.class));
        Mockito.when(accountRepository.isPersistent(any(Account.class))).thenReturn(true);
        c.setId(1L);

        given()
                .contentType(ContentType.JSON)
                .body(c)
                .accept(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", is(1),
                        "name", is("John Doe 123"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(2)
    @Test
    public void testGetAccountById() {
        Mockito.when(accountRepository.findById(1L)).thenReturn(c);

        given()
                .accept(ContentType.JSON)
                .when().get("/1")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is(1),
                        "name", is("John Doe 123"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(3)
    @Test
    public void testUpdateAccount() {
        c.setName("Jane Doe 123");
        Mockito.doAnswer(invocationOnMock -> {
            Account cust = invocationOnMock.getArgument(0);
            cust.setName("Jane Doe 123");
            return cust;
        }).when(accountRepository).update(any(Account.class));

        given()
                .contentType(ContentType.JSON)
                .body(c)
                .accept(ContentType.JSON)
                .when().put()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is(1),
                        "name", is("Jane Doe 123"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(4)
    @Test
    public void testDeleteAccount() {
        Mockito.doAnswer(invocationOnMock -> null).when(accountRepository).deleteById(anyLong());

        given()
                .when().delete("/1")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}
