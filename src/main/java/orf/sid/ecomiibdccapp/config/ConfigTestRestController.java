package orf.sid.ecomiibdccapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope//indique de ce controlleur va etre instancier une fois on mets refresh
public class ConfigTestRestController {

    private final String p1;
    private final String p2;
    private final CustomerConfigParams customerConfigParams;

    public ConfigTestRestController(
            @Value("${global.params.p1}") String p1,//pour injecter les valeurs
            @Value("${global.params.p2}") String p2,
            CustomerConfigParams customerConfigParams
    ) {
        this.p1 = p1;
        this.p2 = p2;
        this.customerConfigParams = customerConfigParams;
    }

    @GetMapping("/testConfig1")
    public Map<String, String> configTest() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2() {
        return customerConfigParams;
    }
}
