package com.carros.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long> {
	//Iterable<Carro> findByTipo(String tipo);
	
	List<Carro> findByTipo(String tipo);
}
