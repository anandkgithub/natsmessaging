package com.akg.mom;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
@Bean
CommandLineRunner commandLineRunner()
{
  return args ->  {
	  
	  Connection connection =  Nats.connect();
	  //connection.createDispatcher(m->System.out.println(String.format("Received Message:[%s] of subject:[%s]"  , new String(m.getData(),StandardCharsets.UTF_8),m.getSubject()))).subscribe("store.ind.*");
	 	  
	   Dispatcher dispatcher = connection.createDispatcher((m)-> {
		  	System.out.println(String.format("Received Message [%s] Subject name [%s] "  ,  new String(m.getData(),StandardCharsets.UTF_8),m.getSubject()));
	  });
      dispatcher.subscribe("store.ind.*");	  
 };
}
}
