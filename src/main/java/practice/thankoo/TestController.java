package practice.thankoo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(path = "/coupon")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

}
