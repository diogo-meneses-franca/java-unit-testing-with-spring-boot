package com.diogofranca.automatedtestswithjava.modulo6mockito;

import com.diogofranca.automatedtestswithjava.modulo6mockito.service.Order;
import com.diogofranca.automatedtestswithjava.modulo6mockito.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final UUID defaultUUID = UUID.fromString("201b365d-b1b5-43c8-b3df-349f5f5b3a88");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2024, 10, 15, 16, 58);

    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExist() {
        try(MockedStatic<UUID> mockedUuid = Mockito.mockStatic(UUID.class)) {
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUUID);

            Order result = service.createOrder("Macbook Pro", 2L, null);
            assertEquals(defaultUUID.toString(), result.getId());
        }
    }

    @Test
    void testShouldIncludeLocalDateTime_When_CreateAOrder() {
        try(MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = service.createOrder("Macbook Pro", 2L, null);
            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }
}
