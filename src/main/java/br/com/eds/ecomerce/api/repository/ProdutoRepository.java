package br.com.eds.ecomerce.api.repository;

import br.com.eds.ecomerce.api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
