package dnt.jwttokenauthentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestRestController {
    @GetMapping
    public ResponseEntity<String> getMessage() {
        return new ResponseEntity<>("You've authenticated!", HttpStatus.OK);
    }
}
