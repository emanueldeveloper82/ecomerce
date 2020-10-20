package br.com.eds.ecomerce.api.services;

import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.entity.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

	List<Pedido> listarPedidos();

	Optional<Pedido> findById(Long id);

	List<Pedido> findAllByCliente(Cliente cliente);

	Pedido persistir(Pedido pedido);

}
