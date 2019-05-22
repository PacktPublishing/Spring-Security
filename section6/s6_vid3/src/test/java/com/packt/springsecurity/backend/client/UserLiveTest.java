package com.packt.springsecurity.backend.client;

import static com.jayway.restassured.RestAssured.given;
import static com.packt.springsecurity.backend.persistence.setup.SecurityConstants.ADMIN1_PASS;
import static com.packt.springsecurity.backend.persistence.setup.SecurityConstants.ADMIN1_USERNAME;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.http.HttpHeaders;
import org.junit.Test;

import com.jayway.restassured.response.Response;

public class UserLiveTest {

    @Test
    public void givenAuthenticatedByBasicAuth_whenAResourceIsCreated_then401IsReceived() {
        // Given
        // When
        final Response response = given().header(HttpHeaders.ACCEPT, "application/json").get("http://localhost:8080/spring-security/api/users");

        // Then
        assertThat(response.getStatusCode(), is(401));
    }

    @Test
    public void givenAuthenticatedByBasicAuth_whenAResourceIsCreated_then200IsReceived() {
        // Given
        // When
        final Response response = given().auth().preemptive().basic(ADMIN1_USERNAME, ADMIN1_PASS).header(HttpHeaders.ACCEPT, "application/json").get("http://localhost:8080/spring-security/api/users");

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void givenAuthenticatedByDigestAuth_whenAResourceIsCreated_then401IsReceived() {
        // Given
        // When
        final Response response = given().header(HttpHeaders.ACCEPT, "application/json").get("http://localhost:8080/spring-security/api/users");

        // Then
        assertThat(response.getStatusCode(), is(401));
    }

    @Test
    public void givenAuthenticatedByDigestAuth_whenAResourceIsCreated_then200IsReceived() {
        // Given
        // When
        final Response response = given().auth().digest(ADMIN1_USERNAME, ADMIN1_PASS).header(HttpHeaders.ACCEPT, "application/json").get("http://localhost:8080/spring-security/api/users");

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

}
