package br.com.luaazul.logistica.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luaazul.logistica.api.exception.NegocioException;
import br.com.luaazul.logistica.model.Cliente;
import br.com.luaazul.logistica.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		Optional<Cliente> emailExists = clienteRepository.findByEmail(cliente.getEmail());
		
		if(emailExists.isPresent() && !emailExists.get().equals(cliente)) {
			throw new NegocioException("Já existe esse email no sistema");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clientID) {
		clienteRepository.deleteById(clientID);
	}
	
}
