package com.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	/*
	public Iterable<Carro> get() {
		return service.getCarros();
	}
	*/
	public ResponseEntity<Iterable<Carro>> get() {
		//return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);

		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	/*
	public Optional<Carro> get(@PathVariable Long id) {
		return service.getCarroById(id);
	}
	*/
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarroById(id);
		
		/*
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		*/
		
		/*
		return carro.isPresent() ?
				ResponseEntity.ok(carro.get()) :
				ResponseEntity.notFound().build();
		*/
		
		return carro
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/tipo/{tipo}")
	//public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo){
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo){
		//return service.getCarrosByTipo(tipo);
		
		List<Carro> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}

	@PostMapping()
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso!" + c.getId();
	}
	
	@PutMapping("{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		
		return "Carro atualizado com sucesso!" + c.getId();
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Carro deletado com sucesso!";
	}
}
