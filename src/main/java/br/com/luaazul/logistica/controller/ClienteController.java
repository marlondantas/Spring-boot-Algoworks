package br.com.luaazul.logistica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luaazul.logistica.model.Cliente;
import br.com.luaazul.logistica.repository.ClienteRepository;
import br.com.luaazul.logistica.service.ClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public ClienteController(){
		super();
	}
	
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return  ResponseEntity.ok(cliente.get());
		}
				
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicinar(@Valid @RequestBody Cliente cliente){
		
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente clienteRequest) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}

		clienteRequest.setId(clienteId);
		clienteRequest = clienteService.salvar(clienteRequest);
		return  ResponseEntity.ok(clienteRequest);

	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		
		clienteService.excluir(clienteId);
		return  ResponseEntity.noContent().build();

	}
}
