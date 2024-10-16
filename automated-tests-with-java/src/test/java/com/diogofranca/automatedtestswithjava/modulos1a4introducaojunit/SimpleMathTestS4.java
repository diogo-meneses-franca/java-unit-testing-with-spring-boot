package com.diogofranca.automatedtestswithjava.modulos1a4introducaojunit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Order(3)
@DisplayName("Test Arithmetic Operations with class SimpleMath")
public class SimpleMathTestS4 {

	SimpleMath math;

	@BeforeEach
	void beforeEachMethod(){
		System.out.println("Running @BeforeEach method");
		math = new SimpleMath();
	}

	/*
	Na abordagem abaixo há dois modos de executar testes parametrizados.
	O primeiro define um @MethodSource com um método com nome diferente do método que está sendo testado
	para que este sirva como fornecedor dos parâmetros utilizados no teste.
	No segundo é utilizado um método fonte com o mesmo nome do método que executa os testes, assim
	não é necessario informar o nome do método fonte de parâmetros.
	Nestes casos também se utiliza a anotation @ParameterizedTest no lugar da anotation @Test
	 */
	@ParameterizedTest
	//@MethodSource("testDivisionInputParameters")
	@MethodSource
	void testDivision(Double firstNumber, Double secondNumber, Double expected){
		Double actual = math.division(firstNumber, secondNumber);
		assertEquals(expected, actual, 2D,() -> firstNumber + " / " + secondNumber + " should be equal to " + expected);
	}

	//public static Stream<Arguments> testDivisionInputParameters(){
	public static Stream<Arguments> testDivision(){
		return Stream.of(
				Arguments.of(6.2D, 2D, 3.1D),
				Arguments.of(71D, 14D, 5.07D),
				Arguments.of(18.3D, 3.1D, 5.9D)
		);
	}

	@ParameterizedTest
	/*
	@CsvSource({
			"6.2, 2, 3.1",
			"71, 14, 5.07",
			"18.3, 3.1, 5.9"
	})
	*/
	@CsvFileSource(resources = "/testDivision.csv")
	void testDivisionWithCsvSource(Double firstNumber, Double secondNumber, Double expected){
		Double actual = math.division(firstNumber, secondNumber);
		assertEquals(expected, actual, 2D,() -> firstNumber + " / " + secondNumber + " should be equal to " + expected);
	}


	/*
	No caso de uso de @ValueSource passa-se os parametros através de valores
	estes valores podem ser de vários tipos, string, integer, double etc..
	Neste caso foi escolhido String aleatóriamente apenas para servir como exemplo.
	 */
	@ParameterizedTest
	@ValueSource(strings = {"Diogo", "Carlos", "Celia"})
	void testValueSource(String firstName){
		System.out.println(firstName);
		assertNotNull(firstName);
	}

	@RepeatedTest(value = 3, name = "{displayName}. Repetition "
	+ "{currentRepetition} of {totalRepetitions}")
	@DisplayName("Test Division By Zero with repetition, repetition info and testinfo")
	void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException(
			RepetitionInfo repetitionInfo,
			TestInfo testInfo
	){
		System.out.println("Repetition Nº: " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
		System.out.println("Running " + testInfo.getTestMethod().get().getName());
		Double firstNumber = 8.5D;
		Double secondNumber = 0D;
		var expectedMessage = "Impossible division by zero";
		ArithmeticException actual = assertThrows(
				ArithmeticException.class,
				() -> math.division(firstNumber, secondNumber),
				() -> "Division by zero should throw ArithmeticException");
		assertEquals(
				actual.getMessage(),
				expectedMessage,
				() -> "Should show the message: Impossible division by zero");
	}


}
