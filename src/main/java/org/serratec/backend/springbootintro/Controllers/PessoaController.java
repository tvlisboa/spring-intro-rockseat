package org.serratec.backend.springbootintro.Controllers;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.serratec.backend.springbootintro.Repository.PessoaRepository;
import org.serratec.backend.springbootintro.Entity.PessoaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody PessoaModel pessoaModel) {
        var user = this.pessoaRepository.findByUsername(pessoaModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuario informado ja esta cadastrado!");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, pessoaModel.getPassword().toCharArray());
        pessoaModel.setPassword(passwordHashred);

        var userCreated = this.pessoaRepository.save(pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso!");
    }

}
