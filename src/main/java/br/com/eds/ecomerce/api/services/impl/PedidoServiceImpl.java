package br.com.eds.ecomerce.api.services.impl;

import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.entity.Pedido;
import br.com.eds.ecomerce.api.repository.PedidoRepository;
import br.com.eds.ecomerce.api.services.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	@Autowired
	private PedidoRepository repository;


	@Override
	public List<Pedido> listarPedidos() {
		return this.repository.findAll();
	}

	@Override
	public Optional<Pedido> findById(Long id) {
		return Optional.of(repository.getOne(id));
	}

	@Override
	public List<Pedido> findAllByCliente(Cliente cliente) {
		return repository.findAllByCliente(cliente);
	}

	@Override
	public Pedido persistir(Pedido pedido) {
		log.info("Persistindo um pedido {}", pedido);
		return this.repository.save(pedido);
	}

}
