package br.com.eds.ecomerce.api.services;

import br.com.eds.ecomerce.api.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService  {

	List<Cliente> listarClientes();

	Optional<Cliente> findById(Long id);

	Cliente persistir(Cliente cliente);

}
