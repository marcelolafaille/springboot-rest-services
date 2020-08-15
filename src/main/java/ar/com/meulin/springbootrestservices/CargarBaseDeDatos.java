package ar.com.meulin.springbootrestservices;

import ar.com.meulin.springbootrestservices.employee.Employee;
import ar.com.meulin.springbootrestservices.employee.EmployeeRepository;
import ar.com.meulin.springbootrestservices.order.Order;
import ar.com.meulin.springbootrestservices.order.OrderRepository;
import ar.com.meulin.springbootrestservices.order.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargarBaseDeDatos {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Bean
    public void cargarBD() {
        int contador = 0;
        int numero;

        Status status = Status.COMPLETED;

        while (contador < 1000) {
            employeeRepository.save(new Employee(
                    RandomStringUtils.randomAlphabetic(10, 30)
                    , RandomStringUtils.randomAlphabetic(10, 30)
                    , RandomStringUtils.randomAlphabetic(5, 100)));

            numero = Integer.parseInt(RandomStringUtils.randomNumeric(1));

            if (numero == 0 || numero == 1 || numero == 2 || numero == 3) {
                status = Status.IN_PROGRESS;
            }
            if (numero == 4 || numero == 5 || numero == 6) {
                status = Status.COMPLETED;
            }
            if (numero == 7 || numero == 8 || numero == 9) {
                status = Status.CANCELLED;
            }

            orderRepository.save(new Order(
                    RandomStringUtils.randomAlphabetic(20, 50)
                    , status));

            contador++;
        }
    }
}