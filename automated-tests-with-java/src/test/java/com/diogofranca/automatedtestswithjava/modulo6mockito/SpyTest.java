package com.diogofranca.automatedtestswithjava.modulo6mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
    // Mock: Comportamento simulado, sem lógica real
    List<String> mockList = mock(ArrayList.class);
    when(mockList.size()).thenReturn(10);

    // Spy: Comportamento real, permite interação e verificação
    List<String> spyList = spy(new ArrayList<>());
    spyList.add("item");
    int size = spyList.size(); // Retorna 1, pois mantém a lógica real
 */
public class SpyTest {

    @Test
    void testV1(){

        List<String> spyArrayList = spy(ArrayList.class);
        assertEquals(0, spyArrayList.size());

        //mesmo que eu adicione mais um objeto ele retornará apenas 5 porque estou utilizando o when()
        when(spyArrayList.size()).thenReturn(5);
        spyArrayList.add("Hello");

        assertEquals(5, spyArrayList.size());

    }

    @Test
    void testV2(){

        List<String> spyArrayList = spy(ArrayList.class);
        assertEquals(0, spyArrayList.size());

        spyArrayList.add("Hello");
        assertEquals(1, spyArrayList.size());

        spyArrayList.remove("Hello");
        assertEquals(0, spyArrayList.size());
    }

}
