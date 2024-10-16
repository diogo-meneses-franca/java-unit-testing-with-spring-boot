package com.diogofranca.automatedtestswithjava.modulos1a4introducaojunit;

import org.junit.jupiter.api.*;

import java.util.Arrays;

@Order(6)
public class ArraysCompareTest {

	@Test
	void test(){
		int[] numbers = {16,87,2,34,13,76,3};
		int[] expected = {2,3,13,16,34,76,87};
		Arrays.sort(numbers);
		Assertions.assertArrayEquals(numbers,expected);
	}


	/*
	Este é um exemplo sobre como testar performance em um software.
	Supondo que você esteja executando um método que precisa ser executado em um tempo
	de 15ms. O @Timeout determina um tempo limite para a execução do método, caso
	a execução exceda esse limite o teste falhará. Assim se testa o tempo de execução de um trecho de código.
	 */
	@Test
	@Timeout(1)
	@Disabled
	//@Timeout(value = 3, unit = TimeUnit.MILLISECONDS)
	void testSortPerformance(){
		int[] numbers = {16,87,2,34,13,76,3};
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			numbers[0] = i;
			Arrays.sort(numbers);
		}
	}
}
