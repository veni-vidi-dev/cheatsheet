package com.spaceship.crm

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class Application

object Application extends App {
  SpringApplication.run(classOf[Application]);
}
