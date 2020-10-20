package br.com.eds.ecomerce.api.repository;

import br.com.eds.ecomerce.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
