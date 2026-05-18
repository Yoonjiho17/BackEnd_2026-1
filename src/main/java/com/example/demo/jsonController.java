package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jsonController {

    @GetMapping("/json")
    public TestApi introduceJson() {
        TestApi test = new TestApi();
        test.setAge(26);
        test.setName("허준기");
        return test;
    }
}
