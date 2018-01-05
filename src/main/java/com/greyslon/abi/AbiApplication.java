package com.greyslon.abi;

import com.greyslon.abi.domain.AuditorAwareImpl;
import com.greyslon.abi.models.Person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@Configuration
public class AbiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AbiApplication.class, args);
  }

  @Bean
  public AuditorAware<Person> auditorProvider() {
    return new AuditorAwareImpl();
  }
}
