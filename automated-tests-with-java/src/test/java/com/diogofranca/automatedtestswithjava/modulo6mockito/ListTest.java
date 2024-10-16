package com.diogofranca.automatedtestswithjava.modulo6mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

	@Test
	void testMockingList_WhenSizeIsCalled_ShouldReturnTen(){
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10);

		assertEquals(10, list.size());
	}

	@Test
	void testMockingList_WhenSizeIsCalled_ShouldReturnMultipleValues(){
		List<?> list = mock(List.class);
		//Neste caso, no primeiro assert retorna 10 e no segundo retorna 20
		when(list.size()).thenReturn(10).thenReturn(20);

		assertEquals(10, list.size());
		assertEquals(20, list.size());
	}

	@Test
	void testMockingList_WhenGetIsCalled_ShouldReturnErudio(){
		var list = mock(List.class);
		when(list.get(0)).thenReturn("Erudio");

		assertEquals("Erudio", list.get(0));
	}

	@Test
	void testMockingList_WhenGetIsCalledWithArgumentMatcher_ShouldReturnErudio(){
		var list = mock(List.class);
		//Neste caso, anyInt é o argument matcher
		when(list.get(anyInt())).thenReturn("Erudio");

		assertEquals("Erudio", list.get(anyInt()));
	}

	@Test
	void testMockingList_WhenThrowsAnException(){
		var list = mock(List.class);
		//Neste caso, anyInt é o argument matcher
		when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar"));

		assertThrows(RuntimeException.class, () -> list.get(anyInt()), () -> "Should have thrown an RuntimeException");
	}
}
