package com.carros;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {

	@Autowired
	private CarroService service;
	
	@Test
	public void test1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		service.insert(carro);
	}

	@Test
	public void test2() {
	}
}
