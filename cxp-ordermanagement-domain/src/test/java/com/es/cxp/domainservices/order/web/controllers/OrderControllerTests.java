package com.es.cxp.domainservices.order.web.controllers;

import static com.es.cxp.domainservices.order.AbstractIT.mockGetProductByCode;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.es.cxp.domainservices.order.AbstractIT;
import com.es.cxp.domainservices.order.model.OrderSummary;
import com.es.cxp.domainservices.order.testdata.TestDataFactory;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    //    @LocalServerPort
    //    private int port;
    //
    //    @Container
    //    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
    //            .withDatabaseName("testdb")
    //            .withUsername("testuser")
    //            .withPassword("testpass");
    //
    //    @Container
    //    static final RabbitMQContainer rabbit = new
    // RabbitMQContainer("rabbitmq:3.12.11-alpine").withExposedPorts(5672);
    //
    //    @DynamicPropertySource
    //    static void configureProperties(DynamicPropertyRegistry registry) {
    //        // Configure database
    //        registry.add("spring.datasource.url", postgres::getJdbcUrl);
    //        registry.add("spring.datasource.username", postgres::getUsername);
    //        registry.add("spring.datasource.password", postgres::getPassword);
    //        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    //
    //        // Configure RabbitMQ
    //        registry.add("spring.rabbitmq.host", rabbit::getHost);
    //        registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
    //        registry.add("spring.rabbitmq.username", () -> "guest");
    //        registry.add("spring.rabbitmq.password", () -> "guest");
    //
    //        // Disable Flyway for tests to avoid version conflicts
    //        registry.add("spring.flyway.enabled", () -> "false");
    //
    //        // Configure H2 for testing if needed
    //        // registry.add("spring.datasource.url", () ->
    // "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    //        // registry.add("spring.datasource.username", () -> "sa");
    //        // registry.add("spring.datasource.password", () -> "");
    //    }
    //
    //    @BeforeEach
    //    void setup() {
    //        RestAssured.port = port;
    //    }

    WireMockServer wireMockServer;

    //    @BeforeEach
    //    void setUp() {
    ////        wireMockServer = new WireMockServer(options().port(8888));
    //        wireMockServer = new WireMockServer(
    //                wireMockConfig()
    //                        .bindAddress("localhost")
    //                        .dynamicPort()
    //        );
    //        wireMockServer.start();
    //    }

    @BeforeAll
    static void startWiremock() {
        ESWireMockServer.start();
        WireMock.configureFor("localhost", ESWireMockServer.httpPort());
    }

    @Nested
    public static class createOrderTests {

        @Test
        public void shouldCreateOrderSuccessfully() {
            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
            // Given a valid order request payload
            var payload =
                    """
                        {
                            "customer" : {
                                "name": "Kishore",
                                "email": "kpadala@gmail.com",
                                "phone": "999999999"
                            },
                            "deliveryAddress" : {
                                "addressLine1": "HNO 123",
                                "addressLine2": "Kukatpally",
                                "city": "Hyderabad",
                                "state": "Telangana",
                                "zipCode": "500072",
                                "country": "India"
                            },
                            "items": [
                                {
                                    "code": "P100",
                                    "name": "Product 1",
                                    "price": 25.50,
                                    "quantity": 1
                                }
                            ]
                        }
                    """;
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders/createOrder")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        public void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders/createOrder")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    //                    .header("Authorization", "Bearer " + getToken())
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<>() {});

            assertThat(orderSummaries).hasSize(2);
        }
    }

    @Nested
    class GetOrderByOrderNumberTests {
        String orderNumber = "order-123";

        @Test
        void shouldGetOrderSuccessfully() {
            given().when()
                    //                    .header("Authorization", "Bearer " + getToken())
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }
    }
}
