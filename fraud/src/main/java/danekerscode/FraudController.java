package danekerscode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {
    private final FraudCheckService checkService;

    @GetMapping(path ="{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId")
            Integer customerId){
   boolean result =  checkService.isFraudulentCustomer(customerId);
   log.info("fraud check request for customer {}" , customerId);
    return new FraudCheckResponse(result);
    }
}
