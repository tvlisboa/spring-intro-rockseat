package org.serratec.backend.springbootintro.Repository;
import org.serratec.backend.springbootintro.Entity.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dentro das interface somente
 * e definido os metodos
 * e nao as suas implementacoes
 */

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
    PessoaModel findByUsername(String username);
}
