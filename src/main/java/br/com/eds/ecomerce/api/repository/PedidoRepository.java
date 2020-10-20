package br.com.eds.ecomerce.api.repository;

import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByCliente(Cliente cliente);

}
