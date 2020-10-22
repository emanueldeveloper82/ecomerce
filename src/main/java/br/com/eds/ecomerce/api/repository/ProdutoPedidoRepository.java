package br.com.eds.ecomerce.api.repository;

import br.com.eds.ecomerce.api.entity.Produto;
import br.com.eds.ecomerce.api.entity.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {

    Optional<ProdutoPedido> findByProduto(Produto produto);

}
