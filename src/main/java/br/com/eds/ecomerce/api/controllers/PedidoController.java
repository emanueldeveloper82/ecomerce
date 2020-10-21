package br.com.eds.ecomerce.api.controllers;

import br.com.eds.ecomerce.api.dtos.ClienteDto;
import br.com.eds.ecomerce.api.dtos.PedidoDto;
import br.com.eds.ecomerce.api.dtos.ProdutoDto;
import br.com.eds.ecomerce.api.dtos.ProdutoPedidoDto;
import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.entity.Pedido;
import br.com.eds.ecomerce.api.entity.Produto;
import br.com.eds.ecomerce.api.entity.ProdutoPedido;
import br.com.eds.ecomerce.api.enums.StatusEntregaEnum;
import br.com.eds.ecomerce.api.response.Response;
import br.com.eds.ecomerce.api.services.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller de Cliente.
 * @author emanuel developer
 * 
 */
@Api(value = "API de Pedidos.")
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	private static final Logger log = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	private PedidoService pedidoService;

	/**
	 * Cadastrar um pedido.
	 * @param pedidoDto
	 * @param result
	 * @return ResponseEntity<Response<PedidoDto>>
	 */
	@ApiOperation(value = "Cadastrar um pedido para um cliente.")
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Response<PedidoDto>> inserirPedido(@Valid @RequestBody PedidoDto pedidoDto,
															 BindingResult result)  {

		log.info("Cadastrando um pedido para o cliente: {}", pedidoDto.getCliente().getNome());

		Response<PedidoDto> response = new Response<>();

		Pedido pedido = this.converterDtoParaPedido(pedidoDto);

		/**verifica se existe erros de validação.*/
		if(result.hasErrors()) {
			log.error("Erro ao validar cadastro do pedido do cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.pedidoService.persistir(pedido);
		//response.setData(this.converterPedidoParaDto(pedido));
		return ResponseEntity.ok(response);
	}

	private Pedido converterDtoParaPedido(PedidoDto pedidoDto) {
		log.info("Convertenndo dados do DTo para o pedido do cliente Nome: {}", pedidoDto.getCliente().getNome());
		Pedido pedido = new Pedido();
		pedido.setCliente(this.converterDtoParaCliente(pedidoDto.getCliente()));
		pedido.setDataCadastro(pedidoDto.getDataCadastro());
		pedido.setStatusEntrega(pedidoDto.getStatusEntrega());

		if (!pedidoDto.getProdutoPedidos().isEmpty()) {
			pedidoDto.setProdutoPedidos(new ArrayList<>());
			for (ProdutoPedidoDto produtoPedidoDto : pedidoDto.getProdutoPedidos()) {
				pedido.getProdutoPedidos().add((this.converterDtoParaProdutoPedido(produtoPedidoDto)));
			}
		}
		return pedido;
	}


	private Cliente converterDtoParaCliente(ClienteDto clienteDto) {
		log.info("Convertenndo dados do DTo para Cliente Nome: {}", clienteDto.getNome());
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDto.getNome());
		cliente.setDataCadastro(LocalDate.now());
		cliente.setStatus(clienteDto.getStatus());
		return cliente;
	}

	private ProdutoPedido converterDtoParaProdutoPedido(ProdutoPedidoDto produtoPedidoDto) {
		log.info("Convertenndo dados do DTo para um ProdutoPedido: {}", produtoPedidoDto.getId());

		ProdutoPedido produtoPedido = new ProdutoPedido();
		produtoPedido.setId(produtoPedidoDto.getId());
		produtoPedido.setQuantidade(produtoPedidoDto.getQuantidade());
		produtoPedido.setProduto(this.converterDtoParaProduto(produtoPedidoDto.getProduto()));
		return produtoPedido;
	}


	private ProdutoPedidoDto converterProdutoPedidoParaDto(ProdutoPedido produtoPedido) {
		log.info("Convertenndo dados do ProdutoPedido para DTo ProdutoPedido: {}", produtoPedido.getId());

		ProdutoPedidoDto produtoPedidoDto = new ProdutoPedidoDto();
		produtoPedidoDto.setId(produtoPedido.getId());
		produtoPedidoDto.setQuantidade(produtoPedido.getQuantidade());
		produtoPedidoDto.setProduto(this.converterProdutoParaDto(produtoPedido.getProduto()));
		return produtoPedidoDto;
	}

	private ProdutoDto converterProdutoParaDto(Produto produto) {
		log.info("Convertenndo dados do Produto para DTo Produto: {}", produto.getId());
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(produto.getId());
		produtoDto.setNome(produto.getNome());
		produtoDto.setValor(produto.getValor());
		produtoDto.setDisponivel(produto.isDisponivel());
		return produtoDto;
	}


	private Produto converterDtoParaProduto(ProdutoDto produtoDto) {
		log.info("Convertenndo dados do Dto para Produto: {}", produtoDto.getNome());
		Produto produto = new Produto();
		produto.setId(produtoDto.getId());
		produto.setNome(produtoDto.getNome());
		produto.setValor(produtoDto.getValor());
		produto.setDisponivel(produtoDto.isDisponivel());
		return produto;
	}






}
