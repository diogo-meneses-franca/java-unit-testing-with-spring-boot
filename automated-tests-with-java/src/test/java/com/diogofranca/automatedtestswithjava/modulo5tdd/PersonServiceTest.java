package com.diogofranca.automatedtestswithjava.modulo5tdd;

import com.diogofranca.automatedtestswithjava.modulo5tdd.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

	Person person;
	IPersonService service;

	@BeforeEach
	void setup(){
		person = new Person(
				"Keith",
				"Moon",
				"kmoon@gmail.com",
				"Wembley - UK",
				"Male"
		);

		service = new PersonService();
	}

	@DisplayName("When Create a Person with Success Should Return a Person Object")
	@Test
	void testCreatePerson_WhenSuccess_ShouldReturnPersonObject(){
		Person actual = service.createPerson(person);
		assertNotNull(actual, () -> "Method createPerson shouldn't return null");
	}

	@DisplayName("When Create a Person with Success Should Contain Valid Fields in Returned Person Object")
	@Test
	void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnedPersonObject(){
		Person actual = service.createPerson(person);
		assertNotNull(actual, () -> "Method createPerson shouldn't return null");
		assertNotNull(actual.getId(), () -> "Person's id shouldn't be null");
		assertEquals(person.getFirstName(), actual.getFirstName(), () -> "Firstname doesn't match!");
		assertEquals(person.getLastName(), actual.getLastName(), () -> "Lastname doesn't match!");
		assertEquals(person.getEmail(), actual.getEmail(), () -> "Email doesn't match!");
		assertEquals(person.getAddress(), actual.getAddress(), () -> "Address doesn't match!");
		assertEquals(person.getGender(), actual.getGender(), () -> "Gender doesn't match!");
	}

	@DisplayName("When Create Person with null email Should Throw IllegalArgumentException")
	@Test
	void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException(){
		person.setEmail(null);
		assertThrows(
				IllegalArgumentException.class,
				() -> service.createPerson(person),
				() -> "E-mail shouldn't be null"
		);

		Exception exception = assertThrows(
				IllegalArgumentException.class,
				() -> service.createPerson(person),
				() -> "E-mail shouldn't be null"
		);
		String actualMessage = "The person e-mail is null or empty!";
		assertEquals(actualMessage, exception.getMessage(), () -> "Exception messages don't match!");
	}
}
