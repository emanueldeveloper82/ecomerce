package br.com.eds.ecomerce.api.services;

import br.com.eds.ecomerce.api.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

	List<Produto> listarProdutos();

	Optional<Produto> findById(Long id);

	Optional<Produto> findByNomeLike(String nome);

	Produto persistir(Produto produto);

}
