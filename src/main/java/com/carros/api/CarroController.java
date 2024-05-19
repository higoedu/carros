package com.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("{id}")
	public Optional<Carro> get(@PathVariable Long id) {
		return service.getCarroById(id);
	}
	
	@PostMapping()
	public String post(@RequestBody Carro carro) {
		Carro c = service.save(carro);
		
		return "Carro salvo com sucesso!" + c.getId();
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tiop") String tipo){
		return service.getCarrosByTipo(tipo);
	}
}
