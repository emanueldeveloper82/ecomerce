package br.com.eds.ecomerce.api.services;

import br.com.eds.ecomerce.api.entity.Produto;
import br.com.eds.ecomerce.api.entity.ProdutoPedido;
import java.util.Optional;

public interface ProdutoPedidoService {

	Optional<ProdutoPedido> findByProduto(Produto produto);

	ProdutoPedido persistir(ProdutoPedido produtoPedido);

}
