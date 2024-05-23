package com.carros;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTest {

	@Autowired
	private CarroService service;
	
	@Test
	public void test1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		//Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);
		
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		//Deletar o objeto
		service.delete(id);
		
		//Verificar se deletou
		try {
			assertNull(service.getCarroById(id));
			fail("O carro não foi excluído!");
		} catch(ObjectNotFoundException ex) {
			//Ok
		}
	}

	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(31, carros.size());
	}
	
	@Test
	public void testListaPorTipo() {
		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());

		assertEquals(0, service.getCarrosByTipo("x").size());
	}

	@Test
	public void testGet() {
		CarroDTO c = service.getCarroById(11L);
		
		assertNotNull(c);
		
		assertEquals("Ferrari FF", c.getNome());
	}
}
