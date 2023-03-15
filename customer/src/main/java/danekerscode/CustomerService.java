package danekerscode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void register(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder().
                firstName(customerRegistrationRequest.getFirstName())
                        .lastName(customerRegistrationRequest.getLastName())
                                .email(customerRegistrationRequest.getEmail()).
                                        build();


          customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

    }

    public List<Customer> getALl() {
        return customerRepository.findAll();
    }
}
