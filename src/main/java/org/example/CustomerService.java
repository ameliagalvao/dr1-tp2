package org.example;

public class CustomerService {
    public boolean registerCustomer(Customer customer) {
        if (customer.getAge() < 18 || customer.getAge() > 99) {
            return false; // Idade inválida
        }
        if (!customer.getEmail().matches("^[\\w-.]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")) {
            return false; // E-mail inválido
        }
        return true; // Cadastro bem-sucedido
    }

    public boolean updateCustomer(Customer customer, String newName, String newEmail, int newAge) {
        if (!customer.isActive()) {
            return false; // Cliente inativo não pode ser atualizado
        }
        customer.setName(newName);
        customer.setEmail(newEmail);
        customer.setAge(newAge);
        return true;
    }

    public boolean deleteCustomer(Customer customer) {
        if (!customer.isActive()) {
            return false; // Cliente inativo não pode ser excluído
        }
        return true;
    }
}
