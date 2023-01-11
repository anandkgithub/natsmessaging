package com.akg.mom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

public class sampledemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		 Connection connection =  Nats.connect();
		 
		 Subscription subs  = connection.subscribe("store.ind.delhi");
		 //subs.wait();
		// Message msg = subs.nextMessage(Duration.ofMillis(0));
		 //System.out.println("Received message " + new String(msg.getData(),StandardCharsets.UTF_8));
		 //assertNotNull("No message!", message);
		 //assertEquals("hello there", new String(message.getData()));
		 
		 
		 
		 
		 connection.publish("store.ind.delhi", "message1111".getBytes("UTF-8"));
		 connection.publish("store.ind.delhi", "message1112".getBytes("UTF-8"));
		// Message msg = subs.nextMessage(Duration.ofMillis(0));
		 //System.out.println("Received message " + new String(msg.getData(),StandardCharsets.UTF_8));
		 
		 // DISPATCHER 
		 
		Dispatcher d = null;
		try {
			d = connection.createDispatcher((message) -> {});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Subscription s = d.subscribe("store.ind.delhi", (message) -> {
			 System.out.println("Message received (up to 5 times): " + new String(message.getData(), StandardCharsets.UTF_8));
		});	
		
		d.unsubscribe(s, 5);
		System.out.println("end"); 
		 
	}
	
	
	
	

}
