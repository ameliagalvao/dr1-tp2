import org.example.Customer;
import org.example.CustomerService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceTest {

    //Teste de cadastro com idade válida e inválida
    //Utilizando o metodo de partição de equivalência, separamos 4 grupos, sendo dois de valores inválidos e
    //dois de valores válidos
    @Test
    public void testRegisterCustomerAge() {
        CustomerService customerService = new CustomerService();

        //Partição de equivalência para valor limítrofe mínimo válido (18)
        Customer customer18 = new Customer(1, "Luke", "luke@jedis.com", 18, true);
        assertTrue(customerService.registerCustomer(customer18), "Cliente com 18 anos deve ser cadastrado");

        //Partição de equivalência para valor limítrofe máximo válido (99)
        Customer customer99 = new Customer(2, "Leia", "leia@alianca.com", 99, true);
        assertTrue(customerService.registerCustomer(customer99), "Cliente com 99 anos deve ser cadastrado");

        //Partição de equivalência para valor limítrofe inválido (17)
        Customer customer17 = new Customer(3, "Chewie", "chewie@wookie.com", 17, true);
        assertFalse(customerService.registerCustomer(customer17), "Cliente com 17 anos não deve ser cadastrado");

        //Partição de equivalência para valor limítrofe inválido (100)
        Customer customer100 = new Customer(4, "Han", "han.solo@alianca.com", 100, true);
        assertFalse(customerService.registerCustomer(customer100), "Cliente com 100 anos não deve ser cadastrado");
    }

    //Teste de atualização de cliente ativo e inativo
    @Test
    public void testUpdateCustomerActive() {
        CustomerService service = new CustomerService();

        Customer activeCustomer = new Customer(2, "Leia", "leia@alianca.com", 99, true);
        boolean updateResult = service.updateCustomer(activeCustomer, "Leia Updated", "leia_updated@alianca.com", 30);
        assertTrue(updateResult, "Cliente ativo deve permitir atualização");
        assertEquals("Leia Updated", activeCustomer.getName());
        assertEquals("leia_updated@alianca.com", activeCustomer.getEmail());
        assertEquals(30, activeCustomer.getAge());
    }

    @Test
    public void testUpdateCustomerInactive() {
        CustomerService service = new CustomerService();
        Customer inactiveCustomer = new Customer(5, "Vader", "vader@sith.com", 40, false);
        boolean updateInactive = service.updateCustomer(inactiveCustomer, "Vader Updated", "vader_updated@sith.com", 45);
        assertFalse(updateInactive, "Cliente inativo não pode ser atualizado");
    }

}
