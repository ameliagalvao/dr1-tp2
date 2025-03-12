import net.jqwik.api.*;
import org.example.OrderService;
import org.example.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    //Provedor Jqwik de valores doubles positivos aleatórios
    @Provide
    Arbitrary<Double> positiveDoubles() {
        return Arbitraries.doubles().greaterThan(0.0);
    }

    //Testa um pagamento aprovado usando os valores aleatórios
    @Property
    public void testProcessOrderApprovedPayment(
            @ForAll("positiveDoubles") double payment) {
        PaymentProcessor mockPaymentProcessor = Mockito.mock(PaymentProcessor.class);
        when(mockPaymentProcessor.processPayment(payment)).thenReturn(true);
        OrderService orderService = new OrderService(mockPaymentProcessor);
        boolean result = orderService.processOrder(payment);
        assertTrue(result);
        verify(mockPaymentProcessor, times(1)).processPayment(eq(payment));
    }

    @Property
    void processOrderRejectedPayment(
            @ForAll("positiveDoubles") double payment) {
        PaymentProcessor mockPaymentProcessor = mock(PaymentProcessor.class);
        when(mockPaymentProcessor.processPayment(anyDouble())).thenReturn(false);
        OrderService orderService = new OrderService(mockPaymentProcessor);
        boolean result = orderService.processOrder(payment);
        assertFalse(result);
        verify(mockPaymentProcessor, times(1)).processPayment(eq(payment));
    }
}
