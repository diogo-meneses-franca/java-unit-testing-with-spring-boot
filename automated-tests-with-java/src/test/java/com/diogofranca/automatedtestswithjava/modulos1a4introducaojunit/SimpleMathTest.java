package com.diogofranca.automatedtestswithjava.modulos1a4introducaojunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

@DisplayName("Test Arithmetic Operations with class SimpleMath")
@Order(2)
public class SimpleMathTest {

	SimpleMath math;

	@BeforeAll
	static void setup(){
		System.out.println("Running @BeforeAll method");
	}

	@AfterAll
	static void cleanup(){
		System.out.println("Running @AfterAll method");
	}

	@BeforeEach
	void beforeEachMethod(){
		System.out.println("Running @BeforeEach method");
		math = new SimpleMath();
	}

	@AfterEach
	void afterEachMethod(){
		System.out.println("Running @AfterEach method");
	}

	@Test
	void testSum_When_FivePointFourIsAddedByTwo_ShouldReturnSevenPointFour(){
		//Given, When e Then é uma abordagem utilizada na metodologia BDD (Behavior Driven Development)
		//Outra abordagem muito similar é utilizar a metodologia de testes AAA (Triple A) Arrange, Act, Assert
		//Given ou Arrange
		Double firstNumber = 5.4D;
		Double secondNumber = 2D;
		Double expected = 7.4D;

		//When ou Act
		Double actual = math.sum(firstNumber, secondNumber);

		//Then ou Assert
		//Utilizar uma lâmbda garante que a expressão "firstnumber..." só será executada quando o teste falhar
		//Sem a lâmbda ela será sempre executada, portanto o uso da lâmbda tem como resultado uma otimização do código
		assertEquals(expected, actual,  () -> firstNumber + " + " + secondNumber + " should be equal to " + expected);
	}

	//A annotation @Disabled desativa este teste e garante que este não será executado
	@Disabled
	@Test
	void testSubtraction_WhenFivePointFourIsSubtractedByTwo_ShouldReturnThreePointFour(){
		Double firstNumber = 5.4D;
		Double secondNumber = 2D;
		Double actual = math.subtraction(firstNumber, secondNumber);
		Double expected = 3.4D;
		Double delta = 0.0001; //delta define uma margem de erro muito pequena de 0,0001
		assertEquals(expected, actual, delta,() -> firstNumber + " - " + secondNumber + " should be equal to " + expected);
	}

	@Test
	void testMultiplication_WhenFivePointFourIsMultipliedByTwo_ShouldReturnTenPointEight(){
		Double firstNumber = 5.4D;
		Double secondNumber = 2D;
		Double actual = math.multiplication(firstNumber, secondNumber);
		Double expected = 10.8D;
		Double delta = 0.0001; //delta define uma margem de erro muito pequena de 0,0001
		assertEquals(expected, actual, delta,() -> firstNumber + " * " + secondNumber + " should be equal to " + expected);
	}

	@Test
	void testDivision_WhenFivePointFourIsDividedByTwo_ShouldReturnTwoPointSeven(){
		Double firstNumber = 5.4D;
		Double secondNumber = 2D;
		Double actual = math.division(firstNumber, secondNumber);
		Double expected = 2.7D;
		Double delta = 0.0001; //delta define uma margem de erro muito pequena de 0,0001
		assertEquals(expected, actual, delta,() -> firstNumber + " / " + secondNumber + " should be equal to " + expected);
	}

	@Test
	void testMean_WhenFivePointFourAndTwo_ShouldReturnThreePointSeven() {
		Double firstNumber = 5.4D;
		Double secondNumber = 2D;
		Double actual = math.mean(firstNumber, secondNumber);
		Double expected = 3.7D;
		Double delta = 0.0001;
		assertEquals(expected, actual, delta, () -> "(" + firstNumber + " + " + secondNumber + ")/2 should be equal to " + expected);
	}


	@Test
	void testSquareRoot_WhenEightyOne_ShouldReturnNine(){
		Double firstNumber = 81D;

		Double actual = math.squareRoot(firstNumber);
		Double expected = 9D;
		Double delta = 0.0001; //delta define uma margem de erro muito pequena de 0,0001
		assertEquals(expected, actual, delta,() -> "Square root of " + firstNumber + " should be equal to " + expected);
	}

	@Test
	@DisplayName("Test Division By Zero")
	void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException(){
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
