package com.diogofranca.automatedtestswithjava.modulo7mockitoavancado;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

	@Test
	void testHamcrestMatchers(){
		//Given
		List<Integer> scores = Arrays.asList(99, 100, 101, 102, 105);

		//When & Then
		assertThat(scores, hasSize(5));
		assertThat(scores, hasItems(100, 101));
		assertThat(scores, everyItem(greaterThan(98)));
		assertThat(scores, everyItem(lessThan(106)));

		//Check Strings
		assertThat("", is(emptyString()));
		assertThat(null, is(emptyOrNullString()));

		//Arrays
		Integer[] array = {1, 2, 3, 4, 5};
		assertThat(array, arrayWithSize(5));
		assertThat(array, arrayContainingInAnyOrder(2, 4, 5, 1, 3));
	}
}
