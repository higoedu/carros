package com.carros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RestController
@RequestMapping("api/v1/carros")
public class CarroController {
	@Autowired
	private CarroService service;// = new CarroService();
	
	@GetMapping()
	public Iterable<Carro> get() {
		return service.getCarros();
	}
}
