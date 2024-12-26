package com.pedalgenie.vote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testPage() {
        return "git hub actions CI/CD 테스트, 인스턴스 변경 후";
    }
}
