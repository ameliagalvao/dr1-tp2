import org.example.OrderService;
import org.example.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    //Testa pagamento aprovado (retorno True)
    @Test
    public void testProcessOrderApproved() {
        PaymentProcessor mockPaymentProcessor = Mockito.mock(PaymentProcessor.class);
        //Poderia ser qualquer valor, já que pelo que está escrito no código
        //esse parâmetro não vai mudar nada no processamento da função.
        double amount = 10.0;
        when(mockPaymentProcessor.processPayment(amount)).thenReturn(true);
        OrderService orderService = new OrderService(mockPaymentProcessor);
        boolean result = orderService.processOrder(amount);
        assertTrue(result);
        verify(mockPaymentProcessor, times(1)).processPayment(amount);
    }

    //Testa pagamento é recusado (False)
    @Test
    public void testProcessOrderRejected() {
        PaymentProcessor mockPaymentProcessor = Mockito.mock(PaymentProcessor.class);
        double amount = 10.0;
        when(mockPaymentProcessor.processPayment(amount)).thenReturn(false);
        OrderService orderService = new OrderService(mockPaymentProcessor);
        boolean result = orderService.processOrder(amount);
        assertFalse(result);
        verify(mockPaymentProcessor, times(1)).processPayment(amount);
    }
}
