package danekerscode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    public void register(@RequestBody CustomerRegistrationRequest customerRequest){
        log.info("new customer registration {}" , customerRequest);
        customerService.register(customerRequest);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(customerService.getALl() , HttpStatus.OK);
    }
 }
