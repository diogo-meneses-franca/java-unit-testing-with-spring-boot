package com.diogofranca.automatedtestswithjava.modulos1a4introducaojunit;

import org.junit.jupiter.api.*;

/*
Aceita os parametros:
MethodOrderer.MethodName.class //Ordena a execução dos métodos por nome
MethodOrderer.OrdererAnnotation.class //Ordena pela ordem em que foi escrito o código
nesse caso é necessario adicionar @Order(1) no método para definir a ordem de execução escolhida
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Order(1)
public class MethodOrderedRandomlyTest {

	/*
	Este atributo foi criando com a finalidade de demonstrar a alteração no ciclo de vida de
	execução de testes do junit.
	Por padrão o junit cria um novo objeto da classe de testes para executar cada testes, desse modo
	é impossível compartilhar o estado de um método com o outro, pois cada método de teste irá executar
	em uma instancia independente.
	Para mudar esse comportamento pode-se utilizar a annotation
	 */
	StringBuilder builder = new StringBuilder();


	@AfterEach
	void afterEach(){
		System.out.println(builder);
	}

	@Order(1)
	@Test
	void testA(){
		System.out.println("Running TestA");
		builder.append("testA executado, ");
	}

	@Order(2)
	@Test
	void testB(){
		System.out.println("Running TestB");
		builder.append("testB executado, ");
	}

	@Order(3)
	@Test
	void testC(){
		System.out.println("Running TestC");
		builder.append("testC executado, ");
	}

	@Order(4)
	@Test
	void testD(){
		System.out.println("Running TestD");
		builder.append("testD executado.");
	}

}
