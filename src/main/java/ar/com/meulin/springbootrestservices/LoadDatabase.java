package ar.com.meulin.springbootrestservices;


import ar.com.meulin.springbootrestservices.employee.Employee;
import ar.com.meulin.springbootrestservices.employee.EmployeeRepository;
import ar.com.meulin.springbootrestservices.order.Order;
import ar.com.meulin.springbootrestservices.order.OrderRepository;
import ar.com.meulin.springbootrestservices.order.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

//@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //@Autowired
    EmployeeRepository employeeRepository;

    //@Autowired
    OrderRepository orderRepository;

    //@Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


            orderRepository.save(new Order("MacBook Pro", Status.IN_PROGRESS));
            orderRepository.save(new Order("iPhone", Status.COMPLETED));
            orderRepository.save(new Order("MacBook Pro", Status.CANCELLED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("MacBook Pro", Status.CANCELLED));
            orderRepository.save(new Order("MacBook Pro", Status.CANCELLED));
            orderRepository.save(new Order("MacBook Pro", Status.CANCELLED));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

            log.info(String.valueOf(Status.IN_PROGRESS));
            log.info(String.valueOf(Status.COMPLETED));
            log.info(String.valueOf(Status.CANCELLED));

        };
    }
}