package com.cts.training.collateralwebportal.feign;

import com.cts.training.collateralwebportal.model.LoginModel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-client", url = "http://localhost:8081/auth")
public interface AuthClient {
    
    @GetMapping("/validate")
    public boolean validate(String token);

    @PostMapping("/login")
    public String login(LoginModel model);
}
