package com.diogofranca.automatedtestswithjava.modulo6mockito;

import com.diogofranca.automatedtestswithjava.modulo6mockito.service.MyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public class MyUtilsTest {

    @Test
    void shouldMockStaticMethodWithParams(){
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)){
            mockedStatic.when(
                    ()-> MyUtils.getWelcomeMessage(
                            eq("Diogo"),
                            anyBoolean())).thenReturn("Howdy Diogo!");

            String result = MyUtils.getWelcomeMessage("Diogo", false);

            assertEquals("Howdy Diogo!", result);
        }
    }
}
