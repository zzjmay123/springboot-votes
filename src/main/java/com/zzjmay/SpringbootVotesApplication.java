package com.zzjmay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * run中的application不一定要是主类
 */
@SpringBootApplication
public class SpringbootVotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVotesApplication.class, args);
	}

}

