package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros(){
		return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
	}

	public Optional<CarroDTO> getCarroById(Long id){
		return rep.findById(id).map(CarroDTO::new);

		/*
		Optional<Carro> carro = rep.findById(id);
		return carro.map(c -> Optional.of(new CarroDTO(c))).orElse(null);
		*/
	}
	
	public List<CarroDTO> getCarrosByTipo(String tipo){
		return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
	}
	
	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");

		return rep.save(carro);
	}
	/*
	public Carro update(Carro carro, Long id) {
		Assert.notNull(carro.getId(), "Não foi possível atualizar o registro");
		
		//Busca o carro no banco de dados
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//Atualiza a carro
			rep.save(db);
			
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
		
	}
	*/
	public void delete(Long id) {
		Optional<CarroDTO> carro = getCarroById(id);

		if(carro.isPresent()) {
			rep.deleteById(id);
		}
	}
	
}
