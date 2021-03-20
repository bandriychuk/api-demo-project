package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

	private final UserApiService userApiService = new UserApiService();
	private final Faker faker = new Faker(new Locale("uk"));

	@BeforeClass
	public void setUp(){
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
	}

	@Test
	public void testCanRegisterNewUser() {
		//given
		UserPayload user = new UserPayload()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.email("test@gmail.com")
				.password("test1234");
		//expect
	userApiService.registerUser(user)
				.shouldHave(statusCode(200))
				.shouldHave(bodyField("password",not(isEmptyOrNullString())));
	}

	@Test
	public void testCanNotRegisterSameUserTwice() {
		UserPayload user = new UserPayload()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.email("test@gmail.com")
				.password("test1234");

		userApiService.registerUser(user)
				.shouldHave(statusCode(200));

		userApiService.registerUser(user)
				.shouldHave(statusCode(200));
	}
}
