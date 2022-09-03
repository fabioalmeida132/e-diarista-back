package br.com.ediarista.core.repositories;

import br.com.ediarista.core.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
