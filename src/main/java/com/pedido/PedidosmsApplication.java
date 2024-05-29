package com.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; 


@SpringBootApplication
@EnableFeignClients 
public class PedidosmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosmsApplication.class, args);
	}

}
