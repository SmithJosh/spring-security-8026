package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class GatewayCsrfApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayCsrfApplication.class, args);
  }

  @GetMapping(value = "/")
  public String index() {
    return "index";
  }
}
