package br.com.eds.ecomerce.api.services.impl;

import br.com.eds.ecomerce.api.entity.Produto;
import br.com.eds.ecomerce.api.repository.ProdutoRepository;
import br.com.eds.ecomerce.api.services.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	private static final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

	@Autowired
	private ProdutoRepository repository;


	@Override
	public List<Produto> listarProdutos() {
		return repository.findAll();
	}

	@Override
	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Produto> findByNomeLike(String nome) {
		return repository.findByNomeLike(nome);
	}

	@Override
	public Produto persistir(Produto produto) {
		return repository.save(produto);
	}
}
