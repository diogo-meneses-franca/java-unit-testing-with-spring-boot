package com.diogofranca.automatedtestswithjava.modulos1a4introducaojunit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(4)
public class FooBarTest {

	@Test
	public void testFooBar() {
		System.out.println("Teste rodando");
	}
}
