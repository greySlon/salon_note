package com.greyslon.abi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaAuditing
@Configuration
public class AbiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AbiApplication.class, args);
  }

//  @Bean
//  public AuditorAware<Person> auditorProvider() {
//    return new AuditorAwareImpl();
//  }
}
