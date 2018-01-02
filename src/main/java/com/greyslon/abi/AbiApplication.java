package com.greyslon.abi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AbiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AbiApplication.class, args);
  }
}
