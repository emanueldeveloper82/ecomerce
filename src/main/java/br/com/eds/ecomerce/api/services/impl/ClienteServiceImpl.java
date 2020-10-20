package br.com.eds.ecomerce.api.services.impl;

import java.util.List;
import java.util.Optional;

import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.repository.ClienteRepository;
import br.com.eds.ecomerce.api.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteRepository repository;


	@Override
	public List<Cliente> listarClientes() {
		return this.repository.findAll();
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return Optional.of(repository.getOne(id));
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo um cliente {}", cliente);
		return this.repository.save(cliente);
	}

}
