package br.com.eds.ecomerce.api.controllers;

import br.com.eds.ecomerce.api.dtos.ClienteDto;
import br.com.eds.ecomerce.api.dtos.PedidoDto;
import br.com.eds.ecomerce.api.dtos.ProdutoDto;
import br.com.eds.ecomerce.api.dtos.ProdutoPedidoDto;
import br.com.eds.ecomerce.api.entity.Cliente;
import br.com.eds.ecomerce.api.entity.Pedido;
import br.com.eds.ecomerce.api.entity.Produto;
import br.com.eds.ecomerce.api.entity.ProdutoPedido;
import br.com.eds.ecomerce.api.exceptions.BadRequestException;
import br.com.eds.ecomerce.api.response.Response;
import br.com.eds.ecomerce.api.services.ClienteService;
import br.com.eds.ecomerce.api.services.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de Cliente.
 * @author emanuel developer
 * 
 */
@Api(value = "API de Clientes.")
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PedidoService pedidoService;

	/**
	 * Método que busca um cliente pelo id.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Cliente>>
	 */
	@ApiOperation(value = "Buscar um cliente pelo id.")
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Response<ClienteDto>> obterCliente(@PathVariable("id") Long id) {
		
		log.info("Buscando cliente pelo id: {}", id);
		
		Response<ClienteDto> response = new Response<>();
		Optional<Cliente> cliente = this.clienteService.findById(id);

		if (!cliente.isPresent()) {
			log.info("Cliente não localizado pelo id: {}", id);
			response.getErrors().add("Cliente não localizado pelo id "+ id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterClienteParaDto(cliente.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método que busca um cliente pelo id.
	 *
	 * @param id
	 * @return ResponseEntity<Response<Cliente>>
	 */
	@ApiOperation(value = "Buscar um pedido pelo cliente.")
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<Response<PedidoDto>> listarPedidosCliente(@PathVariable("id") Long id) {

		log.info("Buscando cliente pelo id: {}", id);

		Response<PedidoDto> response = new Response<>();

		Optional<Cliente> cliente = this.clienteService.findById(id);
		List<Pedido> pedidosCliente;


		if (cliente.isPresent()) {
			pedidosCliente = this.pedidoService
					.findAllByCliente(cliente.get());

			if (!pedidosCliente.isEmpty()) {
				for (Pedido pedido : pedidosCliente) {
					response.setData(this.converterPedidoParaDto(pedido));
				}
			} else {
				throw new BadRequestException("Cliente não possui um pedido. Nome: "
						+ cliente.get().getNome());
			}
		} else {
			log.info("Cliente não localizado pelo id: {}", id);
			response.getErrors().add("Cliente não localizado pelo id "+ id);
			throw new BadRequestException("Cliente Não Localizado.");
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra um cliente.
	 * @param clienteDto
	 * @param result
	 * @return ResponseEntity<Response<ClienteDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@ApiOperation(value = "Cadastrar dados do cliente.")
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Response<ClienteDto>> inserirClientes(@Valid @RequestBody ClienteDto clienteDto,
			BindingResult result)  {

		log.info("Cadastrando um Cliente: {}", clienteDto.getNome());

		Response<ClienteDto> response = new Response<>();

		Cliente cliente = this.converterDtoParaCliente(clienteDto);

		/**verifica se existe erros de validação.*/
		if(result.hasErrors()) {
			log.error("Erro ao validar cadastro do Cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.persistir(cliente);
		response.setData(this.converterClienteParaDto(cliente));
		return ResponseEntity.ok(response);
	}


	/**
	 * Atualiza os dados de um cliente.
	 *
	 * @param id
	 * @param clienteDto
	 * @param result
	 * @return ResponseEntity<Response<ClienteDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@ApiOperation(value = "Atualiza dados do cliente.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ClienteDto>> atualizarCliente(@PathVariable("id") Long id,
			@Valid @RequestBody ClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando cliente: {}", clienteDto.toString());
		Response<ClienteDto> response = new Response<ClienteDto>();

		Optional<Cliente> cliente = this.clienteService.findById(id);
		if (!cliente.isPresent()) {
			result.addError(new ObjectError("cliente", "cliente não encontrado."));
		}

		this.atualizarDadosCliente(cliente.get(), clienteDto, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.persistir(cliente.get());
		response.setData(this.converterClienteParaDto(cliente.get()));

		return ResponseEntity.ok(response);
	}

	private ClienteDto converterClienteParaDto(Cliente cliente) {
		log.info("Convertenndo dados do Cliente para um DTO Nome: {}", cliente.getNome());
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setDataCadastro(cliente.getDataCadastro());
		clienteDto.setStatus(cliente.getStatus());
		return clienteDto;
	}

	private Cliente converterDtoParaCliente(ClienteDto clienteDto) {
		log.info("Convertenndo dados do DTo para Cliente Nome: {}", clienteDto.getNome());
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDto.getNome());
		cliente.setDataCadastro(LocalDate.now());
		cliente.setStatus(clienteDto.getStatus());
		return cliente;
	}


	private PedidoDto converterPedidoParaDto(Pedido pedido) {
		log.info("Convertenndo dados do DTo para Cliente Nome: {}", pedido.getId());
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setId(pedido.getId());
		pedidoDto.setDataCadastro(pedido.getDataCadastro());
		pedidoDto.setStatusEntrega(pedido.getStatusEntrega());
		pedidoDto.setCliente(this.converterClienteParaDto(pedido.getCliente()));

		if (!pedido.getProdutoPedidos().isEmpty()) {
			pedidoDto.setProdutoPedidos(new ArrayList<>());
			for (ProdutoPedido produtoPedido : pedido.getProdutoPedidos()) {
				pedidoDto.getProdutoPedidos().add((this.converterProdutoPedidoParaDto(produtoPedido)));
			}
		}
		return pedidoDto;
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


	/**
	 * Atualiza os dados do cliente.
	 *
	 * @param cliente
	 * @param clienteDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosCliente(Cliente cliente, ClienteDto clienteDto, BindingResult result)
			throws NoSuchAlgorithmException {
		cliente.setNome(clienteDto.getNome());
		cliente.setDataCadastro(clienteDto.getDataCadastro());
		cliente.setStatus(clienteDto.getStatus());
	}

}
