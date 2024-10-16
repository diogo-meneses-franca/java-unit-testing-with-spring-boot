package com.diogofranca.automatedtestswithjava.modulo6mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;

public class ListWithBDDTest {


	@Test
	void testMockingList_WhenSizeIsCalled_ShouldReturnTen(){
		List<?> list = mock(List.class);
		given(list.size()).willReturn(10);

		assertThat(list.size(), is(10));
	}

	@Test
	void testMockingList_WhenSizeIsCalled_ShouldReturnMultipleValues(){
		List<?> list = mock(List.class);
		//Neste caso, no primeiro assert retorna 10 e no segundo retorna 20
		given(list.size()).willReturn(10).willReturn(20);

		assertThat(list.size(), is(10));
		assertThat(list.size(), is(20));
	}

	@Test
	void testMockingList_WhenGetIsCalled_ShouldReturnErudio(){
		List<String> list = mock(List.class);
		given(list.get(anyInt())).willReturn("Erudio");

		assertThat(list.get(0), is("Erudio"));
	}

	@Test
	void testMockingList_WhenGetIsCalledWithArgumentMatcher_ShouldReturnErudio(){
		List<String> list = mock(List.class);
		//Neste caso, anyInt é o argument matcher
		given(list.get(anyInt())).willReturn("Erudio");

		assertThat(list.get(anyInt()), is("Erudio"));
	}

	@Test
	void testMockingList_WhenThrowsAnException(){
		List<String> list = mock(List.class);
		given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar"));

		assertThrows(RuntimeException.class, () -> list.get(anyInt()), () -> "Should have thrown an RuntimeException");
	}
}
